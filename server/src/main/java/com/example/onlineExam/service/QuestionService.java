package com.example.onlineExam.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.onlineExam.constants.ActionType;
import com.example.onlineExam.model.Option;
import com.example.onlineExam.model.Question;
import com.example.onlineExam.model.RequestMessage;
import com.example.onlineExam.repository.OptionRepository;
import com.example.onlineExam.repository.QuestionRepository;
import com.example.onlineExam.utils.MyGson;

public class QuestionService extends AbstractService<Question> {
	private static Logger log = LogManager.getLogger(QuestionService.class);

	@Autowired
	QuestionRepository questionRepo;

	@Autowired
	OptionRepository optionRepo;

	@Override
	public String serviceSingle(RequestMessage msg) {
		try {
			Question qus = MyGson.gson.fromJson(msg.getPayload(), Question.class);
			if (msg.getHeader().getActionType().matches(ActionType.NEW.toString())) {
				return MyGson.gson.toJson(save(qus));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.UPDATE.toString())) {
				return MyGson.gson.toJson(update(qus));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.DELETE.toString())) {
				return MyGson.gson.toJson(delete(qus));
			}
			else if (msg.getHeader().getActionType().matches(ActionType.SELECT.toString())) {
				return MyGson.gson.toJson(select(qus));
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

	private Question select(Question question) throws Exception {
		try {
			Question qus = questionRepo.findByQuestionKey(question.getQuestionKey());
			qus.setOptionList(optionRepo.findByQuestionKey(question.getQuestionKey()));
			return qus;
		}
		catch (Exception e) {
			log.error("Exception saving Question [{}]", question.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private List<Question> selectAll() throws Exception {
		try {
			List<Question> qusList = questionRepo.findAll();
			qusList.parallelStream().forEach(x -> {
				x.setOptionList(optionRepo.findByQuestionKey(x.getQuestionKey()));
			});
			return qusList;
		}
		catch (Exception e) {
			log.error("Exception selecting all Question [{}]", e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private Question save(Question question) throws Exception {
		try {
			Question qus = questionRepo.save(question);
			List<Option> optionList = question.getOptionList();
			optionList.parallelStream().forEach(x -> x.setQuestionKey(qus.getQuestionKey()));
			qus.setOptionList(optionRepo.saveAll(optionList));
			return qus;
		}
		catch (Exception e) {
			log.error("Exception saving Question [{}]", question.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private Question update(Question question) throws Exception {
		try {
			if (question.getQuestionKey() == null) {
				throw new Exception("Question key not found in Object, First Find the try to update");
			}
			Question qus = questionRepo.save(question);
			qus.setOptionList(optionRepo.saveAll(question.getOptionList()));
			return qus;
		}
		catch (Exception e) {
			log.error("Exception updating Question [{}]", question.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private Question delete(Question question) throws Exception {
		try {
			questionRepo.delete(question);
			optionRepo.deleteAll(question.getOptionList());
			return question;
		}
		catch (Exception e) {
			log.error("Exception deleting Question [{}]", question.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

}
