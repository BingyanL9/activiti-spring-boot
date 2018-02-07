package com.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testing.model.Club;

public interface ClubRepository extends JpaRepository<Club, Long> {

}
