package com.example.onlineExam.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.onlineExam.constants.ActionType;
import com.example.onlineExam.model.ExamAnswer;
import com.example.onlineExam.model.RequestMessage;
import com.example.onlineExam.repository.ExamAnswerRepository;
import com.example.onlineExam.utils.MyGson;

public class ExamAnswerService extends AbstractService<ExamAnswer> {

	private static Logger log = LogManager.getLogger(ExamAnswerService.class);

	@Autowired
	ExamAnswerRepository examAnswerRepo;

	@Override
	public String serviceSingle(RequestMessage msg) {
		try {
			ExamAnswer examAns = MyGson.gson.fromJson(msg.getPayload(), ExamAnswer.class);
			if (msg.getHeader().getActionType().matches(ActionType.NEW.toString())) {
				return MyGson.gson.toJson(save(examAns));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.UPDATE.toString())) {
				return MyGson.gson.toJson(update(examAns));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.DELETE.toString())) {
				return MyGson.gson.toJson(delete(examAns));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.SELECT.toString())) {
				return MyGson.gson.toJson(select(examAns));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.SELECT_ALL.toString())) {
				return MyGson.gson.toJson(selectAll());
			}
			else {
				return MyGson.gson.toJson("Unknown action " + msg.getHeader().getActionType());
			}
		}
		catch (Exception e) {
			return MyGson.gson.toJson(e.getLocalizedMessage());
		}

	}

	private ExamAnswer select(ExamAnswer examAns) throws Exception {
		try {
			return examAnswerRepo.findByExamAnsKey(examAns.getExamAnsKey());
		}
		catch (Exception e) {
			log.error("Exception saving ExamAnswer [{}]", examAns.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private List<ExamAnswer> selectAll() throws Exception {
		try {
			return examAnswerRepo.findAll();
		}
		catch (Exception e) {
			log.error("Exception selecting all ExamAnswer [{}]", e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private ExamAnswer save(ExamAnswer examAns) throws Exception {
		try {
			return examAnswerRepo.save(examAns);
		}
		catch (Exception e) {
			log.error("Exception saving ExamAnswer [{}]", examAns.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private ExamAnswer update(ExamAnswer examAns) throws Exception {
		try {
			if (examAns.getExamAnsKey() == null) {
				throw new Exception("Exam key not found in Object, First Find the try to update");
			}
			return examAnswerRepo.save(examAns);
		}
		catch (Exception e) {
			log.error("Exception updating ExamAnswer [{}]", examAns.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private ExamAnswer delete(ExamAnswer examAns) throws Exception {
		try {
			examAnswerRepo.delete(examAns);
			return examAns;
		}
		catch (Exception e) {
			log.error("Exception deleting ExamAnswer [{}]", examAns.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

}
