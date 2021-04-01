package com.example.onlineExam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineExam.model.RunningExam;

public interface RunningExamRepository extends JpaRepository<RunningExam, Long> {

	RunningExam findByRunningExamKey(Long runningExamKey);

	List<RunningExam> findByExamerKey(Long examerKey);

	List<RunningExam> findByExamKey(Long examKey);
}
