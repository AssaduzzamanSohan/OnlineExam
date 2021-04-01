package com.example.onlineExam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineExam.model.Examiner;

public interface ExaminerRepository extends JpaRepository<Examiner, Long> {

	Examiner findByExaminerKey(Long examinerKey);

	List<Examiner> findByName(String name);

	List<Examiner> findByEmail(String email);

	List<Examiner> findByPhone(String phone);

	List<Examiner> findByOrganization(String organization);

	List<Examiner> findByDesignation(String designation);

	List<Examiner> findByAllowedToPrepareExam(boolean allowedToPrepareExam);
}
