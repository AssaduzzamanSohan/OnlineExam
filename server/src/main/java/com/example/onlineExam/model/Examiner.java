package com.example.onlineExam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "T_EXAMINER")
@Data
public class Examiner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_examiner_key")
	private Long examinerKey;

	@Column(name = "tx_name", length = 128)
	private String name;

	@Column(name = "tx_email", length = 128)
	private String email;

	@Column(name = "tx_phone", length = 128)
	private String phone;

	@Column(name = "tx_organization", length = 128)
	private String organization;

	@Column(name = "tx_designation", length = 128)
	private String designation;

	@Column(name = "tx_role", length = 128)
	private String role;

	@Column(name = "is_allowed_to_prepare_exam")
	private boolean allowedToPrepareExam;
}
