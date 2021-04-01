package com.example.onlineExam.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.onlineExam.constants.ActionType;
import com.example.onlineExam.model.Examiner;
import com.example.onlineExam.model.RequestMessage;
import com.example.onlineExam.repository.ExaminerRepository;
import com.example.onlineExam.utils.MyGson;

public class ExaminerService extends AbstractService<Examiner> {

	private static Logger log = LogManager.getLogger(ExamerService.class);

	@Autowired
	ExaminerRepository examinerRepo;

	@PostConstruct
	public void init() throws Exception {
		Examiner examiner = new Examiner();
		examiner.setName("Sohan");
		examiner.setEmail("email@gmai.com");
		examiner.setRole("EXAMINER");
		examiner.setAllowedToPrepareExam(true);

		save(examiner);
	}

	@Override
	public String serviceSingle(RequestMessage msg) {
		try {
			Examiner examiner = MyGson.gson.fromJson(msg.getPayload(), Examiner.class);
			if (msg.getHeader().getActionType().matches(ActionType.NEW.toString())) {
				return MyGson.gson.toJson(save(examiner));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.UPDATE.toString())) {
				return MyGson.gson.toJson(update(examiner));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.DELETE.toString())) {
				return MyGson.gson.toJson(delete(examiner));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.SELECT.toString())) {
				return MyGson.gson.toJson(select(examiner));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.SELECT_ALL.toString())) {
				return MyGson.gson.toJson(selectAll());
			}
			else if (msg.getHeader().getActionType().matches("SELECT_BY_EMAIL")) {
				return MyGson.gson.toJson(selectByEmail(examiner));
			}
			else {
				return MyGson.gson.toJson("Unknown action " + msg.getHeader().getActionType());
			}
		}
		catch (Exception e) {
			return MyGson.gson.toJson(e.getLocalizedMessage());
		}

	}

	private Examiner select(Examiner examiner) throws Exception {
		try {
			return examinerRepo.findByExaminerKey(examiner.getExaminerKey());
		}
		catch (Exception e) {
			log.error("Exception saving Examiner [{}]", examiner.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private List<Examiner> selectByEmail(Examiner examiner) throws Exception {
		try {
			return examinerRepo.findByEmail(examiner.getEmail());
		}
		catch (Exception e) {
			log.error("Exception saving Examiner [{}]", examiner.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private List<Examiner> selectAll() throws Exception {
		try {
			return examinerRepo.findAll();
		}
		catch (Exception e) {
			log.error("Exception selecting all Examiner [{}]", e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private Examiner save(Examiner examiner) throws Exception {
		try {
			return examinerRepo.save(examiner);
		}
		catch (Exception e) {
			log.error("Exception saving Examiner [{}]", examiner.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private Examiner update(Examiner examiner) throws Exception {
		try {
			if (examiner.getExaminerKey() == null) {
				throw new Exception("Exam key not found in Object, First Find the try to update");
			}
			return examinerRepo.save(examiner);
		}
		catch (Exception e) {
			log.error("Exception updating Examiner [{}]", examiner.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private Examiner delete(Examiner examiner) throws Exception {
		try {
			examinerRepo.delete(examiner);
			return examiner;
		}
		catch (Exception e) {
			log.error("Exception deleting Examiner [{}]", examiner.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

}
