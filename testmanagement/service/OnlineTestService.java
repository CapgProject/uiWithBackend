package com.cg.testmanagement.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


import com.cg.testmanagement.dto.OnlineTest;
import com.cg.testmanagement.dto.Question;
import com.cg.testmanagement.dto.User;
import com.cg.testmanagement.exception.UserException;

public interface OnlineTestService {
	
	public OnlineTest addTest(OnlineTest onlineTest) throws UserException;
	public OnlineTest updateTest(Long testId, OnlineTest onlineTest) throws UserException;
	public OnlineTest deleteTest(Long testId) throws UserException;
	public Question addQuestion(Long testId, Question question) throws UserException;
	public Question updateQuestion(Long testId, Long questionId, Question question) throws UserException;
	public Question deleteQuestion(Long testId, Long questionId) throws UserException;
	public User registerUser(User user) throws UserException;
	public Boolean answerQuestion(OnlineTest onlineTest, Question question, Integer chosenAnswer) throws UserException;
	public Question showQuestion(OnlineTest onlineTest, Long questionId) throws UserException;
	public Question searchQuestion(Long questionId) throws UserException;
	public Boolean assignTest(Long userId, Long testId) throws UserException;
	public Double getResult(OnlineTest onlineTest) throws UserException;
	public Double calculateTotalMarks(OnlineTest onlineTest) throws UserException;
	public OnlineTest searchTest(Long testId) throws UserException;
	public User searchUser(Long userId) throws UserException;
	public List<User> getUsers();
	public List<OnlineTest> getTests();
	public User updateProfile(User user) throws UserException;
	public User login(String userName, String pass);
	public void readFromExcel(long id) throws IOException, UserException;
	public void validateDate(LocalDateTime startDate, LocalDateTime endDate) throws UserException;
	public void validateTestDuration(LocalTime duration, LocalDateTime startDate, LocalDateTime endDate) throws UserException;
	public void validateEndTime(LocalDateTime endDate) throws UserException;
	
}



