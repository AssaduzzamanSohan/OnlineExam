package com.example.onlineExam.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.onlineExam.constants.ActionType;
import com.example.onlineExam.model.RequestMessage;
import com.example.onlineExam.model.RunningExam;
import com.example.onlineExam.repository.RunningExamRepository;
import com.example.onlineExam.utils.MyGson;

public class RunningExamService extends AbstractService<RunningExam> {

	private static Logger log = LogManager.getLogger(RunningExamService.class);

	@Autowired
	RunningExamRepository runningExamRepo;

	@Override
	public String serviceSingle(RequestMessage msg) {
		try {
			RunningExam runningExam = MyGson.gson.fromJson(msg.getPayload(), RunningExam.class);
			if (msg.getHeader().getActionType().matches(ActionType.NEW.toString())) {
				return MyGson.gson.toJson(save(runningExam));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.UPDATE.toString())) {
				return MyGson.gson.toJson(update(runningExam));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.DELETE.toString())) {
				return MyGson.gson.toJson(delete(runningExam));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.SELECT.toString())) {
				return MyGson.gson.toJson(select(runningExam));
			}
			else if (msg.getHeader().getActionType().matches("SELECT_BY_EXAMER")) {
				return MyGson.gson.toJson(selectExamer(runningExam));
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

	private RunningExam select(RunningExam runningExam) throws Exception {
		try {
			return runningExamRepo.findByRunningExamKey(runningExam.getRunningExamKey());
		}
		catch (Exception e) {
			log.error("Exception saving RunningExam [{}]", runningExam.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private List<RunningExam> selectExamer(RunningExam runningExam) throws Exception {
		try {
			return runningExamRepo.findByExamerKey(runningExam.getExamerKey());
		}
		catch (Exception e) {
			log.error("Exception saving RunningExam [{}]", runningExam.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private List<RunningExam> selectAll() throws Exception {
		try {
			return runningExamRepo.findAll();
		}
		catch (Exception e) {
			log.error("Exception selecting all RunningExam [{}]", e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private RunningExam save(RunningExam runningExam) throws Exception {
		try {
			return runningExamRepo.save(runningExam);
		}
		catch (Exception e) {
			log.error("Exception saving RunningExam [{}]", runningExam.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private RunningExam update(RunningExam runningExam) throws Exception {
		try {
			if (runningExam.getRunningExamKey() == null) {
				throw new Exception("Exam key not found in Object, First Find the try to update");
			}
			return runningExamRepo.save(runningExam);
		}
		catch (Exception e) {
			log.error("Exception updating RunningExam [{}]", runningExam.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private RunningExam delete(RunningExam runningExam) throws Exception {
		try {
			runningExamRepo.delete(runningExam);
			return runningExam;
		}
		catch (Exception e) {
			log.error("Exception deleting RunningExam [{}]", runningExam.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

}
