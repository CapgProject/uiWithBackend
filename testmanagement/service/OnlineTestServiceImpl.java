package com.cg.testmanagement.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.cg.testmanagement.dao.OnlineTestDao;
import com.cg.testmanagement.dto.OnlineTest;
import com.cg.testmanagement.dto.Question;
import com.cg.testmanagement.dto.User;
import com.cg.testmanagement.exception.ExceptionMessage;
import com.cg.testmanagement.exception.UserException;
@Service("testservice")
@Transactional
public class OnlineTestServiceImpl implements OnlineTestService{

	@Autowired
	OnlineTestDao testdao;
	
	@Override
	public User registerUser(User user) throws UserException {
		User returnedUser = testdao.saveUser(user);
		if (returnedUser != null)
			return user;
		else {
			throw new UserException(ExceptionMessage.DATABASEMESSAGE);
		}
	}

	@Override
	public Boolean answerQuestion(OnlineTest onlineTest, Question question, Integer chosenAnswer) throws UserException {
		if (!onlineTest.getTestQuestions().contains(question)) {
			throw new UserException(ExceptionMessage.QUESTIONMESSAGE);
		}
		question.setChosenAnswer(chosenAnswer);
		if (question.getChosenAnswer() == question.getQuestionAnswer()) {
			question.setMarksScored(question.getQuestionMarks());
		} else {
			question.setMarksScored(new Double(0.0));
		}
		testdao.updateQuestion(question);
		return true;
	}

	@Override
	public Question showQuestion(OnlineTest onlineTest, Long questionId) throws UserException {
		Question question = testdao.searchQuestion(questionId);
		if (question == null || !onlineTest.getTestQuestions().contains(question)) {
			throw new UserException(ExceptionMessage.QUESTIONMESSAGE);
		}
		return question;
	}

	@Override
	public Boolean assignTest(Long userId, Long testId) throws UserException {
		User user = testdao.searchUser(userId);
		OnlineTest onlineTest = testdao.searchTest(testId);
		if (user == null) {
			throw new UserException(ExceptionMessage.USERMESSAGE);
		}
		if (user.getIsAdmin()) {
			throw new UserException(ExceptionMessage.ADMINMESSAGE);
		}
		if (onlineTest == null) {
			throw new UserException(ExceptionMessage.TESTMESSAGE);
		}
		if (onlineTest.getIsTestAssigned()) {
			throw new UserException(ExceptionMessage.TESTASSIGNEDMESSAGE);
		} else {
			user.setUserTest(onlineTest);
			onlineTest.setIsTestAssigned(true);
		}
		testdao.updateTest(onlineTest);
		testdao.updateUser(user);
		return true;
	}

	@Override
	public OnlineTest addTest(OnlineTest onlineTest) throws UserException {
		Set<Question> mySet = new HashSet<Question>();
		onlineTest.setTestQuestions(mySet);
		onlineTest.setIsTestAssigned(false);
		OnlineTest returnedTest = testdao.saveTest(onlineTest);
		if (returnedTest == null) {
			throw new UserException(ExceptionMessage.DATABASEMESSAGE);
		}
		return returnedTest;
	}

	@Override
	public OnlineTest updateTest(Long testId, OnlineTest onlineTest) throws UserException {
		OnlineTest temp = testdao.searchTest(testId);
		if (temp != null) {
			onlineTest.setIsTestAssigned(temp.getIsTestAssigned());
			onlineTest.setTestTotalMarks(temp.getTestTotalMarks());
			testdao.updateTest(onlineTest);
			return onlineTest;
		} else
			throw new UserException(ExceptionMessage.TESTMESSAGE);
	}

	@Override
	public OnlineTest deleteTest(Long testId) throws UserException {
		OnlineTest returnedTest = testdao.removeTest(testId);
		if (returnedTest == null) {
			throw new UserException(ExceptionMessage.TESTMESSAGE);
		}
	return returnedTest;
	
	}

	@Override
	public Question addQuestion(Long testId, Question question) throws UserException {
		OnlineTest temp = testdao.searchTest(testId);
	
		if (temp != null) {
			question.setChosenAnswer(-1);
			question.setMarksScored(0.0);
			question.setOnlinetest(temp);
			temp.setTestTotalMarks(temp.getTestTotalMarks() + question.getQuestionMarks());
			testdao.saveQuestion(question);
			testdao.updateTest(temp);
			return question;
		} else
			throw new UserException(ExceptionMessage.TESTMESSAGE);
	}

	@Override
	public Question updateQuestion(Long testId, Long questionId, Question question) throws UserException {
		OnlineTest temp = testdao.searchTest(testId);
		if (temp != null) {
			Set<Question> quests = temp.getTestQuestions();
			Question tempQuestion = testdao.searchQuestion(questionId);
			if (tempQuestion != null && quests.contains(tempQuestion)) {
				question.setChosenAnswer(tempQuestion.getChosenAnswer());
				question.setMarksScored(tempQuestion.getMarksScored());
				question.setQuestionId(questionId);
				testdao.updateQuestion(question);
				temp.setTestTotalMarks(
						temp.getTestTotalMarks() - tempQuestion.getQuestionMarks() + question.getQuestionMarks());
				testdao.updateTest(temp);
				return question;
			} else
				throw new UserException(ExceptionMessage.QUESTIONMESSAGE);
		} else
			throw new UserException(ExceptionMessage.TESTMESSAGE);
	}

	@Override
	public Question deleteQuestion(Long testId, Long questionId) throws UserException {
		OnlineTest temp = testdao.searchTest(testId);
		if (temp != null) {
			Set<Question> quests = temp.getTestQuestions();
			Question tempQuestion = testdao.searchQuestion(questionId);
			if (tempQuestion != null && quests.contains(tempQuestion)) {
				temp.setTestTotalMarks(temp.getTestTotalMarks() - tempQuestion.getQuestionMarks());
				testdao.updateTest(temp);
				return testdao.removeQuestion(questionId);
			} else
				throw new UserException(ExceptionMessage.QUESTIONMESSAGE);
		} else
			throw new UserException(ExceptionMessage.TESTMESSAGE);
	}

	@Override
	public Double getResult(OnlineTest onlineTest) throws UserException {
		Double score = calculateTotalMarks(onlineTest);
		onlineTest.setIsTestAssigned(false);
		testdao.updateTest(onlineTest);
		return score;
	}

	@Override
	public Double calculateTotalMarks(OnlineTest onlineTest) throws UserException {
		Double score = new Double(0.0);
		for (Question question : onlineTest.getTestQuestions()) {
			score = score + question.getMarksScored();
		}
		onlineTest.setTestMarksScored(score);
		testdao.updateTest(onlineTest);
		return score;
	}

	@Override
	public User searchUser(Long userId) throws UserException {
		User returnedUser = testdao.searchUser(userId);
		if (returnedUser != null) {
			return returnedUser;
		} else {
			throw new UserException(ExceptionMessage.USERMESSAGE);
		}

	}

	@Override
	public OnlineTest searchTest(Long testId) throws UserException {
		OnlineTest returnedTest = testdao.searchTest(testId);
		if (returnedTest != null) {
			return returnedTest;
		} else {
			throw new UserException(ExceptionMessage.TESTMESSAGE);
		}
	}


	@Override
	public User updateProfile(User user) throws UserException {

		User returnedUser = testdao.updateUser(user);
		if (returnedUser == null) {
			throw new UserException(ExceptionMessage.USERMESSAGE);
		}
		return returnedUser;
	}

	@Override
	public List<User> getUsers() {
		return testdao.getUsers();
	}

	@Override
	public List<OnlineTest> getTests() {
		return testdao.getTests();
	}

	@Override
	public Question searchQuestion(Long questionId) throws UserException {
		Question question = testdao.searchQuestion(questionId);
		if(question != null) {
			return question;
		}
		else {
			throw new UserException(ExceptionMessage.QUESTIONNOTFOUNDMESSAGE);
		}
	}
	
	@Override
	public User login(String userName, String pass) {
		return testdao.login(userName, pass);
	}
	
	public void readFromExcel(long id) throws IOException, UserException {
		File dataFile = new File("C:\\Users\\Admin\\Desktop\\TestData.xlsx");
		FileInputStream fis = new FileInputStream(dataFile);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Row row;
		System.out.println("hello world");
		for(int i=1; i<=sheet.getLastRowNum(); i++) {
			row = (Row) sheet.getRow(i);
			System.out.println("hello");
			String title;
			if(row.getCell(0).toString() == null) {
				title = "0";
			}
			else {
				title = row.getCell(0).toString();
			}
			String marks;
			if(row.getCell(1).toString() == null) {
				marks = "0";
			}
			else {
				marks = row.getCell(1).toString();
			}
			String options;
			if(row.getCell(2).toString() == null) {
				options = "0";
			}
			else {
				options = row.getCell(2).toString();
			}
			String answer;
			if(row.getCell(3).toString() == null) {
				answer = "0";
			}
			else {
				answer = row.getCell(3).toString();
			}
			System.out.println(title);
			System.out.println(marks);
			Question question = new Question();
			OnlineTest test = testdao.searchTest(id);
			String option[] = new String[4];
			question.setQuestionTitle(title);
			question.setQuestionMarks(Double.parseDouble(marks));
			//question.setQuestionOptions(options);
			StringTokenizer token = new StringTokenizer(options, ",");
			int k = 0;
		    while (token.hasMoreTokens()) {
		    	option[k] = token.nextToken();
		    }
		    question.setQuestionOptions(option);
		    question.setQuestionAnswer((int) Double.parseDouble(answer));
		    question.setChosenAnswer(0);
		    question.setIsDeleted(false);
		    question.setMarksScored(new Double(0));
		    question.setOnlinetest(test);
		    testdao.saveQuestion(question);
		}
		fis.close();
	}

	@Override
	public void validateDate(LocalDateTime startDate, LocalDateTime endDate) throws UserException {
		if (startDate.isAfter(endDate)) {
			throw new UserException(ExceptionMessage.TIMEMESSAGE);
		}
	}

	@Override
	public void validateTestDuration(LocalTime duration, LocalDateTime startDate, LocalDateTime endDate)
			throws UserException {
		long hours = ChronoUnit.HOURS.between(startDate, endDate);
		if (duration.getHour() > hours) {
			throw new UserException(ExceptionMessage.DURATIONMESSAGE);
		}
	}

	@Override
	public void validateEndTime(LocalDateTime endDate) throws UserException {
		if (endDate.isBefore(LocalDateTime.now())) {
			throw new UserException(ExceptionMessage.ENDTIMEMESSAGE);
		}
	}

	
}
