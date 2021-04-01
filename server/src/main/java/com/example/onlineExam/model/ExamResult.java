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
@Table(name = "T_EXAM_RESULT")
@Data
public class ExamResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_exam_result_key")
	private Long examResultKey;

	@Column(name = "id_examiner_key")
	private Long examinerKey;

	@Column(name = "id_examer_key")
	private Long examerKey;

	@Column(name = "id_exam_key")
	private Long examKey;

	@Column(name = "dec_total_mark")
	private Double totalMark;

	@Column(name = "dec_acquired_mark")
	private Double acquiredMark;

	@Column(name = "dtt_exam_start_time")
	private LocalDateTime examStartTime;

	@Column(name = "dtt_exam_end_time")
	private LocalDateTime examEndTime;
}
