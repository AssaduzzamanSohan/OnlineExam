package com.example.onlineExam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineExam.model.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {

	Exam findByExamKey(Long examKey);

	List<Exam> findByExamType(String examType);

	List<Exam> findByExaminerKey(Long examinerKey);
}
