package com.example.onlineExam.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.onlineExam.constants.ActionType;
import com.example.onlineExam.model.Exam;
import com.example.onlineExam.model.ExamQuestion;
import com.example.onlineExam.model.RequestMessage;
import com.example.onlineExam.repository.ExamQuestionRepository;
import com.example.onlineExam.repository.ExamRepository;
import com.example.onlineExam.utils.MyGson;

public class ExamService extends AbstractService<Exam> {

	private static Logger log = LogManager.getLogger(ExamService.class);

	@Autowired
	ExamRepository examRepo;

	@Autowired
	ExamQuestionRepository examQuestionRepo;

	@Override
	public String serviceSingle(RequestMessage msg) {
		try {
			Exam exam = MyGson.gson.fromJson(msg.getPayload(), Exam.class);
			if (msg.getHeader().getActionType().matches(ActionType.NEW.toString())) {
				return MyGson.gson.toJson(save(exam));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.UPDATE.toString())) {
				return MyGson.gson.toJson(update(exam));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.DELETE.toString())) {
				return MyGson.gson.toJson(delete(exam));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.SELECT.toString())) {
				return MyGson.gson.toJson(select(exam));
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

	private Exam select(Exam exam) throws Exception {
		try {
			Exam exm = examRepo.findByExamKey(exam.getExamKey());
			exm.setExamQuestionList(examQuestionRepo.findByExamKey(exam.getExamKey()));
			return exm;
		}
		catch (Exception e) {
			log.error("Exception saving Exam [{}]", exam.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private List<Exam> selectAll() throws Exception {
		try {
			List<Exam> examList = examRepo.findAll();
			examList.parallelStream().forEach(x -> {
				x.setExamQuestionList(examQuestionRepo.findByExamKey(x.getExamKey()));
			});
			return examList;
		}
		catch (Exception e) {
			log.error("Exception selecting all Exam [{}]", e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private Exam save(Exam exam) throws Exception {
		try {
			Exam exm = examRepo.save(exam);
			List<ExamQuestion> examQusList = exam.getExamQuestionList();
			examQusList.parallelStream().forEach(x -> x.setExamKey(exm.getExamKey()));
			exm.setExamQuestionList(examQuestionRepo.saveAll(examQusList));
			return exm;
		}
		catch (Exception e) {
			log.error("Exception saving Exam [{}]", exam.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private Exam update(Exam exam) throws Exception {
		try {
			if (exam.getExamKey() == null) {
				throw new Exception("Exam key not found in Object, First Find the try to update");
			}
			Exam exm = examRepo.save(exam);
			exm.setExamQuestionList(examQuestionRepo.saveAll(exam.getExamQuestionList()));
			return exm;
		}
		catch (Exception e) {
			log.error("Exception updating Exam [{}]", exam.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private Exam delete(Exam exam) throws Exception {
		try {
			examRepo.delete(exam);
			examQuestionRepo.deleteAll(exam.getExamQuestionList());
			return exam;
		}
		catch (Exception e) {
			log.error("Exception deleting Exam [{}]", exam.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

}
