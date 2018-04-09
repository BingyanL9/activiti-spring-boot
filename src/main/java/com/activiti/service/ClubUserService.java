package com.activiti.service;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activiti.model.ClubUser;
import com.activiti.model.TeacherUser;
import com.activiti.repository.ClubUserRepository;
@Service
public class ClubUserService implements Serializable {

  private static final long serialVersionUID = 1L;

  private static final Logger logger = LoggerFactory.getLogger(ClubUserService.class);
  
  @Autowired
  private ClubUserRepository clubRepository;
  
  @Autowired
  private IdentityService identityService;

  
  public ClubUser findByName(String userName) {
    return clubRepository.findByUserName(userName);
  }
  
  public ClubUser getCurrentUser() {
    return findByName(getCurrentUserName());
  }
  
  private String getCurrentUserName() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
      return ((UserDetails) principal).getUsername();
    }
    if (principal instanceof Principal) {
      return ((Principal) principal).getName();
    }
    return String.valueOf(principal);
  }
  
  public ClubUser findByCardNum(String cardNum) {
    return clubRepository.findByCardNum(cardNum);
  }
  
  @Transactional
  public void save(ClubUser clubUser) {
    clubRepository.save(clubUser);
    if(clubUser.getLeaderClub()==null) {
      clubUser.setLeaderClub(clubUser);
    }
    clubRepository.save(clubUser);
    saveAsActivityUser(clubUser);
  }
  
  @Transactional
  public void update(ClubUser clubUser) {
    clubRepository.save(clubUser);
    updateActivityUser(clubUser);
  }
  
  private void updateActivityUser(ClubUser clubUser) {
    org.activiti.engine.identity.User activitiUser = identityService.createUserQuery().userId(clubUser.getUserName()).singleResult();
    activitiUser.setEmail(clubUser.getEmail());
    activitiUser.setLastName(clubUser.getDisplayName());
    activitiUser.setPassword(clubUser.getPassword());
    identityService.saveUser(activitiUser);
  }

  public void saveAsActivityUser(ClubUser clubUser) {
    org.activiti.engine.identity.User activitiUser = identityService.newUser(clubUser.getUserName());
    activitiUser.setEmail(clubUser.getEmail());
    activitiUser.setLastName(clubUser.getDisplayName());
    activitiUser.setPassword(clubUser.getPassword());
    identityService.saveUser(activitiUser);
    Group users = identityService.createGroupQuery().groupType("users").singleResult();
    identityService.createMembership(activitiUser.getId(), users.getId());
    Group g =
        identityService.createGroupQuery().groupType(clubUser.getRole().toString()).singleResult();
    identityService.createMembership(activitiUser.getId(), g.getId());
  }
  
  @Transactional
  public void deleteTeachers(List<ClubUser> clubUsers) {
    clubRepository.delete(clubUsers);
    for (ClubUser clubUser : clubUsers) {
      identityService.deleteUser(clubUser.getUserName());
      List<Group> groups =
          identityService.createGroupQuery().groupMember(clubUser.getUserName()).list();
      if (!groups.isEmpty()) {
        for (Group group : groups) {
          identityService.deleteMembership(clubUser.getUserName(), group.getId());
        }
      }
    }
  }
  
  public List<ClubUser> getclubUsers(){
    return clubRepository.findAll();
  }
}
