package com.example.onlineExam.model;

import lombok.Data;

@Data
public class RequestMessage {
	RequestMessageHeader header;
	String payload;
}
