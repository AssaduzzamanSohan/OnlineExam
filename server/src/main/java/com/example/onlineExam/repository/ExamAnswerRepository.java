package com.example.onlineExam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineExam.model.ExamAnswer;

public interface ExamAnswerRepository extends JpaRepository<ExamAnswer, Long> {

	ExamAnswer findByExamAnsKey(Long examAnsKey);

	List<ExamAnswer> findByExamResultKey(Long examResultKey);

	List<ExamAnswer> findByExamQusKey(Long examQusKey);
}
