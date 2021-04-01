package com.example.onlineExam.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.onlineExam.constants.ActionType;
import com.example.onlineExam.model.Option;
import com.example.onlineExam.model.RequestMessage;
import com.example.onlineExam.repository.OptionRepository;
import com.example.onlineExam.utils.MyGson;

public class OptionService extends AbstractService<Option> {

	private static Logger log = LogManager.getLogger(ExamService.class);

	@Autowired
	OptionRepository optionRepo;

	@Override
	public String serviceSingle(RequestMessage msg) {
		try {
			Option option = MyGson.gson.fromJson(msg.getPayload(), Option.class);
			if (msg.getHeader().getActionType().matches(ActionType.NEW.toString())) {
				return MyGson.gson.toJson(save(option));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.UPDATE.toString())) {
				return MyGson.gson.toJson(update(option));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.DELETE.toString())) {
				return MyGson.gson.toJson(delete(option));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.SELECT.toString())) {
				return MyGson.gson.toJson(select(option));
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

	private Option select(Option option) throws Exception {
		try {
			return optionRepo.findByOptionKey(option.getOptionKey());
		}
		catch (Exception e) {
			log.error("Exception saving Option [{}]", option.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private List<Option> selectAll() throws Exception {
		try {
			return optionRepo.findAll();
		}
		catch (Exception e) {
			log.error("Exception selecting all Option [{}]", e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private Option save(Option option) throws Exception {
		try {
			return optionRepo.save(option);
		}
		catch (Exception e) {
			log.error("Exception saving Option [{}]", option.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private Option update(Option option) throws Exception {
		try {
			if (option.getOptionKey() == null) {
				throw new Exception("Exam key not found in Object, First Find the try to update");
			}
			return optionRepo.save(option);
		}
		catch (Exception e) {
			log.error("Exception updating Option [{}]", option.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private Option delete(Option option) throws Exception {
		try {
			optionRepo.delete(option);
			return option;
		}
		catch (Exception e) {
			log.error("Exception deleting Option [{}]", option.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

}
