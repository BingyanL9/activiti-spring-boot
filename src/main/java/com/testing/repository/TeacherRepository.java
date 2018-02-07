package com.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testing.model.StudentInfo;

public interface TeacherRepository extends JpaRepository<StudentInfo, Long>{
  

}
