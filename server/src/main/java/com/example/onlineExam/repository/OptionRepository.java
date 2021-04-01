package com.example.onlineExam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineExam.model.Option;

public interface OptionRepository extends JpaRepository<Option, Long> {

	Option findByOptionKey(Long optionKey);

	List<Option> findByQuestionKey(Long questionKey);
}
