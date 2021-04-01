package com.example.onlineExam.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "T_RUNNING_EXAM")
@Data
public class RunningExam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_running_exam_key")
	private Long runningExamKey;

	@Column(name = "id_exam_key")
	private Long examKey;

	@Column(name = "id_examer_key")
	private Long examerKey;

	@Column(name = "dtt_exam_start_time")
	private LocalDateTime examStartTime;

	@Column(name = "dtt_exam_end_time")
	private LocalDateTime examEndTime;

}
