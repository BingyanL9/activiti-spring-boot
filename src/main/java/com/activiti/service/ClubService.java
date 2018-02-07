package com.activiti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activiti.repository.ClubRepository;

@Service
public class ClubService {

  @Autowired
  private ClubRepository clubRepository;
}
