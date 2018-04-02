package com.activiti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activiti.model.AbroadOtherInfo;
import com.activiti.repository.AbroadOtherInfoRepository;

@Service
public class AbroadOtherInfoService {

  @Autowired
  private AbroadOtherInfoRepository abroadOtherInfoRepository;
  
  public void save(AbroadOtherInfo abroadOtherInfo) {
    abroadOtherInfoRepository.save(abroadOtherInfo);
  }
}
