package com.example.onlineExam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineExam.model.ExamResult;

public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {

	ExamResult findByExamResultKey(Long examResultKey);

	List<ExamResult> findByExaminerKey(Long examinerKey);

	List<ExamResult> findByExamerKey(Long examerKey);

	List<ExamResult> findByExamKey(Long examKey);
}
