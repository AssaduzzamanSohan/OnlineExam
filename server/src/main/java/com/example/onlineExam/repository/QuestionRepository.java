package com.example.onlineExam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineExam.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	Question findByQuestionKey(Long questionKey);

	List<Question> findByQuestion(String question);
}
