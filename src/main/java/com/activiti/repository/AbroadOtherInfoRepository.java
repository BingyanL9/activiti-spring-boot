package com.activiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.activiti.model.AbroadOtherInfo;

@Repository
public interface AbroadOtherInfoRepository extends JpaRepository<AbroadOtherInfo, Long>{

}
