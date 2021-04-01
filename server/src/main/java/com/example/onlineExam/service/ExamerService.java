package com.example.onlineExam.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.onlineExam.constants.ActionType;
import com.example.onlineExam.model.Examer;
import com.example.onlineExam.model.RequestMessage;
import com.example.onlineExam.repository.ExamerRepository;
import com.example.onlineExam.utils.MyGson;

public class ExamerService extends AbstractService<Examer> {

	private static Logger log = LogManager.getLogger(ExamerService.class);

	@PostConstruct
	public void init() throws Exception {
		Examer examer = new Examer();
		examer.setName("Assaduzzaman Sohan");
		examer.setEmail("email@gmai.com");
		examer.setRole("EXAMER");

		save(examer);
	}

	@Autowired
	ExamerRepository examerRepo;

	@Override
	public String serviceSingle(RequestMessage msg) {
		try {
			Examer examer = MyGson.gson.fromJson(msg.getPayload(), Examer.class);
			if (msg.getHeader().getActionType().matches(ActionType.NEW.toString())) {
				return MyGson.gson.toJson(save(examer));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.UPDATE.toString())) {
				return MyGson.gson.toJson(update(examer));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.DELETE.toString())) {
				return MyGson.gson.toJson(delete(examer));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.SELECT.toString())) {
				return MyGson.gson.toJson(select(examer));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.SELECT_ALL.toString())) {
				return MyGson.gson.toJson(selectAll());
			}
			else if (msg.getHeader().getActionType().matches("SELECT_BY_EMAIL")) {
				return MyGson.gson.toJson(selectByEmail(examer));
			}
			else {
				return MyGson.gson.toJson("Unknown action " + msg.getHeader().getActionType());
			}
		}
		catch (Exception e) {
			return MyGson.gson.toJson(e.getLocalizedMessage());
		}

	}

	private Examer select(Examer exam) throws Exception {
		try {
			return examerRepo.findByExamerKey(exam.getExamerKey());
		}
		catch (Exception e) {
			log.error("Exception saving Exam [{}]", exam.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private List<Examer> selectByEmail(Examer exam) throws Exception {
		try {
			return examerRepo.findByEmail(exam.getEmail());
		}
		catch (Exception e) {
			log.error("Exception saving Exam [{}]", exam.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private List<Examer> selectAll() throws Exception {
		try {
			return examerRepo.findAll();
		}
		catch (Exception e) {
			log.error("Exception selecting all Exam [{}]", e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private Examer save(Examer examr) throws Exception {
		try {
			return examerRepo.save(examr);
		}
		catch (Exception e) {
			log.error("Exception saving Exam [{}]", examr.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private Examer update(Examer examr) throws Exception {
		try {
			if (examr.getExamerKey() == null) {
				throw new Exception("Exam key not found in Object, First Find the try to update");
			}
			return examerRepo.save(examr);
		}
		catch (Exception e) {
			log.error("Exception updating Exam [{}]", examr.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private Examer delete(Examer examr) throws Exception {
		try {
			examerRepo.delete(examr);
			return examr;
		}
		catch (Exception e) {
			log.error("Exception deleting Exam [{}]", examr.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

}
