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
@Table(name = "T_EXAM_ANSWER")
@Data
public class ExamAnswer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_exam_ans_key")
	private Long examAnsKey;

	@Column(name = "id_exam_result_key")
	private Long examResultKey;

	@Column(name = "id_exam_qus_key")
	private Long examQusKey;

	@Column(name = "id_given_ans_key", length = 256)
	private Long givenAnswerKey;

	@Column(name = "is_correct_answer", length = 256)
	private boolean answerIsCorrect;

	@Column(name = "dec_mark")
	private Double mark;

	@Column(name = "dec_acquired_mark")
	private Double acquiredMark;

	@Column(name = "dtt_answer_time")
	private LocalDateTime answerTime;
}
