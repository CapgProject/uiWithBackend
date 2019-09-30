package com.cg.testmanagement.dao;
import java.util.List;
import com.cg.testmanagement.dto.OnlineTest;
import com.cg.testmanagement.dto.Question;
import com.cg.testmanagement.dto.User;
import com.cg.testmanagement.exception.UserException;

public interface OnlineTestDao {

	public OnlineTest saveTest(OnlineTest onlineTest) throws UserException;
	public OnlineTest searchTest(Long testId) throws UserException;
	public OnlineTest removeTest(Long testId) throws UserException;
	public Question saveQuestion(Question question) throws UserException;
	public Question searchQuestion(Long questId) throws UserException;
	public Question removeQuestion(Long questId) throws UserException;
	public Question updateQuestion(Question question) throws UserException;
	public User searchUser(Long userId) throws UserException;
	public User saveUser(User user) throws UserException;	
	public User removeUser(Long userId) throws UserException;
	public User updateUser(User user) throws UserException;
	public OnlineTest updateTest(OnlineTest test) throws UserException;
	public List<User> getUsers();
	public List<OnlineTest> getTests();
	public User login(String userName, String pass);


}
