package com.example.onlineExam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "T_EXAM_QUESTION")
@Data
public class ExamQuestion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_exam_qus_key")
	private Long examQusKey;

	@Column(name = "id_examer_key")
	private Long examKey;

	@Column(name = "id_question_key")
	private Long questionKey;

	@Column(name = "tx_answer_option_key", length = 256)
	private String answerOptionKey;

	@Column(name = "dec_mark")
	private Double mark;

	@Column(name = "dec_time_in_second")
	private Double timeInSecond;

}
