package com.example.onlineExam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineExam.model.ExamQuestion;

public interface ExamQuestionRepository extends JpaRepository<ExamQuestion, Long> {

	ExamQuestion findByExamQusKey(Long examQusKey);

	List<ExamQuestion> findByExamKey(Long examKey);

	List<ExamQuestion> findByQuestionKey(Long questionKey);
}
