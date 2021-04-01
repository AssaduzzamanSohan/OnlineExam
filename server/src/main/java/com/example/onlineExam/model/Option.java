package com.example.onlineExam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "T_OPTION")
@Data
public class Option {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_option_key")
	private Long optionKey;

	@Column(name = "id_question_key")
	private Long questionKey;

	@Column(name = "tx_option", length = 256)
	private String option;

	@Column(name = "is_correct_ans")
	private boolean correctAns;

}
