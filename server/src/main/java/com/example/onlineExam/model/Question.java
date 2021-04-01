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
@Table(name = "T_QUESTION")
@Data
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_question_key")
	private Long questionKey;

	@Column(name = "tx_question", length = 2048)
	private String question;

	@Column(name = "dec_mark")
	private Double mark;

	@Transient
	List<Option> optionList;

}
