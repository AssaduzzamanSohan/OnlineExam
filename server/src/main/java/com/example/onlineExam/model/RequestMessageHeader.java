package com.example.onlineExam.model;

import lombok.Data;

@Data
public class RequestMessageHeader {
	String actionType;
	String contentType;
	String reference;
}
