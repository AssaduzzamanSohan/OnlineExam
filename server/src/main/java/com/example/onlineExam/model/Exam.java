package com.example.onlineExam.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Table(name = "T_EXAM")
@Data
public class Exam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_exam_key")
	private Long examKey;

	@Column(name = "tx_exam_type", length = 128)
	private String examType;

	@Column(name = "id_examiner_key")
	private Long examinerKey;

	@Column(name = "dec_total_time_in_min")
	private Double totalTimeInMin;

	@Column(name = "dec_negative_mark")
	private Double negativeMark;

	@Column(name = "do_negative_marking")
	private boolean doNegativeMarking;

	@Column(name = "is_use_each_qus_time")
	private boolean useEachQusTime;

	@Transient
	private List<ExamQuestion> examQuestionList;

}
