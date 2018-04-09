package com.activiti.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activiti.model.AbroadOtherInfo;
import com.activiti.repository.AbroadOtherInfoRepository;

@Service
public class AbroadOtherInfoService  implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @Autowired
  private AbroadOtherInfoRepository abroadOtherInfoRepository;
  
  public void save(AbroadOtherInfo abroadOtherInfo) {
    abroadOtherInfoRepository.save(abroadOtherInfo);
  }
}
