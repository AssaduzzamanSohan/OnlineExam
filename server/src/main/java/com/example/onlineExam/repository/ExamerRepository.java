package com.example.onlineExam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineExam.model.Examer;

public interface ExamerRepository extends JpaRepository<Examer, Long> {

	Examer findByExamerKey(Long examerKey);

	List<Examer> findByName(String name);

	List<Examer> findByEmail(String email);

	List<Examer> findByPhone(String phone);

	List<Examer> findByOrganization(String organization);

	List<Examer> findByDesignation(String designation);
}