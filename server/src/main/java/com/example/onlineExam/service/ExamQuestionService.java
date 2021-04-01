package com.example.onlineExam.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.onlineExam.constants.ActionType;
import com.example.onlineExam.model.ExamQuestion;
import com.example.onlineExam.model.RequestMessage;
import com.example.onlineExam.repository.ExamQuestionRepository;
import com.example.onlineExam.utils.MyGson;

public class ExamQuestionService extends AbstractService<ExamQuestion> {

	private static Logger log = LogManager.getLogger(ExamService.class);

	@Autowired
	ExamQuestionRepository examQuestionRepo;

	@Override
	public String serviceSingle(RequestMessage msg) {
		try {
			ExamQuestion examQues = MyGson.gson.fromJson(msg.getPayload(), ExamQuestion.class);
			if (msg.getHeader().getActionType().matches(ActionType.NEW.toString())) {
				return MyGson.gson.toJson(save(examQues));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.UPDATE.toString())) {
				return MyGson.gson.toJson(update(examQues));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.DELETE.toString())) {
				return MyGson.gson.toJson(delete(examQues));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.SELECT.toString())) {
				return MyGson.gson.toJson(select(examQues));
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

	private ExamQuestion select(ExamQuestion examQus) throws Exception {
		try {
			return examQuestionRepo.findByExamQusKey(examQus.getExamQusKey());
		}
		catch (Exception e) {
			log.error("Exception saving ExamQuestion [{}]", examQus.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private List<ExamQuestion> selectAll() throws Exception {
		try {
			return examQuestionRepo.findAll();
		}
		catch (Exception e) {
			log.error("Exception selecting all ExamQuestion [{}]", e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private ExamQuestion save(ExamQuestion examQus) throws Exception {
		try {
			return examQuestionRepo.save(examQus);
		}
		catch (Exception e) {
			log.error("Exception saving ExamQuestion [{}]", examQus.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private ExamQuestion update(ExamQuestion examQus) throws Exception {
		try {
			if (examQus.getExamQusKey() == null) {
				throw new Exception("Exam key not found in Object, First Find the try to update");
			}
			return examQuestionRepo.save(examQus);
		}
		catch (Exception e) {
			log.error("Exception updating Exam [{}]", examQus.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private ExamQuestion delete(ExamQuestion examQus) throws Exception {
		try {
			examQuestionRepo.delete(examQus);
			return examQus;
		}
		catch (Exception e) {
			log.error("Exception deleting ExamQuestion [{}]", examQus.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

}
