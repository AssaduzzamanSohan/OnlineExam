package com.example.onlineExam.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.onlineExam.constants.ActionType;
import com.example.onlineExam.model.ExamResult;
import com.example.onlineExam.model.RequestMessage;
import com.example.onlineExam.repository.ExamResultRepository;
import com.example.onlineExam.utils.MyGson;

public class ExamResultService extends AbstractService<ExamResult> {

	private static Logger log = LogManager.getLogger(ExamService.class);

	@Autowired
	ExamResultRepository examResultRepo;

	@Override
	public String serviceSingle(RequestMessage msg) {
		try {
			ExamResult examRes = MyGson.gson.fromJson(msg.getPayload(), ExamResult.class);
			if (msg.getHeader().getActionType().matches(ActionType.NEW.toString())) {
				return MyGson.gson.toJson(save(examRes));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.UPDATE.toString())) {
				return MyGson.gson.toJson(update(examRes));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.DELETE.toString())) {
				return MyGson.gson.toJson(delete(examRes));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.SELECT.toString())) {
				return MyGson.gson.toJson(select(examRes));
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

	private ExamResult select(ExamResult examRes) throws Exception {
		try {
			return examResultRepo.findByExamResultKey(examRes.getExamResultKey());
		}
		catch (Exception e) {
			log.error("Exception saving ExamResult [{}]", examRes.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private List<ExamResult> selectAll() throws Exception {
		try {
			return examResultRepo.findAll();
		}
		catch (Exception e) {
			log.error("Exception selecting all ExamResult [{}]", e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private ExamResult save(ExamResult examRes) throws Exception {
		try {
			return examResultRepo.save(examRes);
		}
		catch (Exception e) {
			log.error("Exception saving ExamResult [{}]", examRes.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private ExamResult update(ExamResult examRes) throws Exception {
		try {
			if (examRes.getExamResultKey() == null) {
				throw new Exception("Exam key not found in Object, First Find the try to update");
			}
			return examResultRepo.save(examRes);
		}
		catch (Exception e) {
			log.error("Exception updating ExamResult [{}]", examRes.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private ExamResult delete(ExamResult examRes) throws Exception {
		try {
			examResultRepo.delete(examRes);
			return examRes;
		}
		catch (Exception e) {
			log.error("Exception deleting ExamResult [{}]", examRes.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

}
