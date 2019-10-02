package com.cg.testmanagement.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.soap.SOAPBinding.Use;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.testmanagement.dto.OnlineTest;
import com.cg.testmanagement.dto.Question;
import com.cg.testmanagement.dto.User;

import com.cg.testmanagement.exception.UserException;
import com.cg.testmanagement.service.OnlineTestService;

@Controller
public class TestManagementController {

	@Autowired
	OnlineTestService testservice;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String displayHomePage(@ModelAttribute("user") User user) {
		return "home";
	}
	@RequestMapping(value = "/addtest", method = RequestMethod.GET)
	public String showAddTest() {	
	return "AddTest";
	}
	@RequestMapping(value = "/addtestsubmit", method = RequestMethod.POST)
	public String addTest(@RequestParam("testName") String name, @RequestParam("testDuration") @DateTimeFormat(pattern="HH:mm:ss") LocalTime duration, @RequestParam(name="startTime") @DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss") LocalDateTime startTime, @RequestParam("endTime") @DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss") LocalDateTime endTime) {
		try {
			OnlineTest test = new OnlineTest();
			Set<Question> question = new HashSet<Question>();
			test.setTestName(name);
			test.setTestTotalMarks(new Double(0));
			test.setTestDuration(duration);
			test.setStartTime(startTime);
			test.setEndTime(endTime);
			test.setTestMarksScored(new Double(0));
			test.setIsdeleted(false);
			test.setIsTestAssigned(false);
			test.setTestQuestions(question);
			testservice.addTest(test);
		} catch (UserException e) {
			System.out.println(e.getMessage());
		}
		return "home";
	}
	@RequestMapping(value = "/addtestsubmit", method = RequestMethod.POST)
	public String addTest(@Valid @ModelAttribute("test") OnlineTest onlineTest, BindingResult result,
			Map<String, Object> model) throws UserException {
		if ((result.hasErrors())) {
			model.put("error", "Please try again..");
			return "addTest";
		}
			else {
				testservice.addTest(onlineTest);
				
				model.put("message", "Added test successfully");
				return "addTest";
				
			}
			
		}
			
		
	@RequestMapping(value="/addQuestion", method=RequestMethod.GET)
	public String showAddQuestion(@ModelAttribute("question") Question question) {
		return "AddQuestion";
	}
	@RequestMapping(value="/addquestionsubmit", method=RequestMethod.POST)
	public String addQuestion(@RequestParam("testid") long id) {
		try {
//			OnlineTest test = testservice.searchTest(id);
//			question.setChosenAnswer(0);
//			question.setIsDeleted(false);
//			question.setMarksScored(new Double(0));
//			question.setOnlinetest(test);
//			testservice.addQuestion(id, question);
			testservice.readFromExcel(id);
		} catch (UserException | IOException e) {
			System.out.println(e.getMessage());
		}
		return "home";
	}
	@RequestMapping(value = "/addquestionsubmit", method = RequestMethod.POST)
	public String addQuestion(@Valid @ModelAttribute("question") Question question,BindingResult result,
			Map<String, Object> model) throws UserException {
	 if (result.hasErrors()) {
		model.put("error", "Please try again..");
			return "addQuestion";
	 }
	 else {
		 testservice.addQuestion(question.getOnlinetest().getTestId(), question);
			model.put("message", "Added question successfully");
			return "addQuestion";
		}
	}
	@RequestMapping(value="/adduser", method=RequestMethod.GET)
	public String showAddUser(@ModelAttribute("user") User user) {
		return "AddUser";
	}
	@RequestMapping(value="/addusersubmit", method=RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user) {
		try {
			user.setUserTest(null);
			user.setIsAdmin(false);
			user.setIsDeleted(false);
			user.setUserTest(null);
			testservice.registerUser(user);
		} catch (UserException e) {
			System.out.println(e.getMessage());
		}
		return "home";
	}
	@RequestMapping(value = "/addusersubmit", method = RequestMethod.POST)
	public String addUser(@Valid @ModelAttribute("user") User user, BindingResult result,
			Map<String, Object> model) {
		if (result.hasErrors()) {
			model.put("error", "Please try again..");
			return "showAddUser";
		} else {
			testservice.getUsers();
			model.put("message", "Added user successfully");
			return "showAddUser";
		}
	}
	@RequestMapping(value="/showalltests", method=RequestMethod.GET)
	public ModelAndView showTest() {
		List<OnlineTest> testList = testservice.getTests();
		return new ModelAndView("ShowTest", "testdata", testList);
	}
	@RequestMapping(value="/showallusers", method=RequestMethod.GET)
	public ModelAndView showUser() {
		List<User> userList = testservice.getUsers();
		return new ModelAndView("ShowUser", "userdata", userList);
	}
	@RequestMapping(value="/removetest", method=RequestMethod.GET)
	public String showRemoveTest() {
		return "RemoveTest";
	}
	@RequestMapping(value="removetestsubmit", method=RequestMethod.POST)
	public String removeTest(@RequestParam("testid") long id) {
		try {
			OnlineTest deleteTest = testservice.searchTest(id);
			testservice.deleteTest(deleteTest.getTestId());
		} catch (UserException e) {
			System.out.println(e.getMessage());
		}
		return "home";
	}
	@RequestMapping(value="/removequestion", method=RequestMethod.GET)
	public String showRemoveQuestion() {
		return "RemoveQuestion";
	}
	@RequestMapping(value="removequestionsubmit", method=RequestMethod.POST)
	public String removeQuestion(@RequestParam("questionid") long id) {
		try {
			Question question = testservice.searchQuestion(id);
			testservice.deleteQuestion(question.getOnlinetest().getTestId(), question.getQuestionId());
		} catch (UserException e) {
			System.out.println(e.getMessage());
		}
		return "home";
	}
	@RequestMapping(value="/givetest", method=RequestMethod.GET)
	public String showQuestion() {
		return "GiveTest";
	}
	@RequestMapping(value="assigntest", method=RequestMethod.GET)
	public String showAssignTest() {
		return "AssignTest";
	}
	@RequestMapping(value="assigntestsubmit", method=RequestMethod.POST)
	public String assignTest(@RequestParam("testid") long testId, @RequestParam("userid") long userId) {
		try {
			testservice.assignTest(userId, testId);
		} catch (UserException e) {
			System.out.println(e.getMessage());
		}
		return "home";
	}
	@RequestMapping(value="/getresult", method=RequestMethod.GET)
	public String showGetResult() {
		return "GetResult";
	}
	@RequestMapping(value="/getresultsubmit", method=RequestMethod.POST)
	public ModelAndView getResult(@RequestParam("testid") long id) {
		try {
			OnlineTest test = testservice.searchTest(id);
			double marks = testservice.getResult(test);
			return new ModelAndView("ShowResult", "result", marks);
		} catch (UserException e) {
			System.out.println(e.getMessage());
		}
		return new ModelAndView("home");		
	}
	@RequestMapping(value="/updatetest", method=RequestMethod.GET)
	public String showUpdateTest() {
		return "UpdateTest";
	}
	@RequestMapping(value="/updatetestinput", method=RequestMethod.POST)
	public ModelAndView updateTest(@RequestParam("testid") long id) {
		OnlineTest test;
		try {
			test = testservice.searchTest(id);
			return new ModelAndView("UpdateTest", "Update", test);
		} catch (UserException e) {
			System.out.println(e.getMessage());
		}
		return new ModelAndView("home");
	}
	@RequestMapping(value="/updatetestsubmit", method=RequestMethod.POST)
	public String actualUpdate(@RequestParam("testId") long id, @RequestParam("testName") String name, @RequestParam("testDuration") @DateTimeFormat(pattern="HH:mm:ss") LocalTime duration, @RequestParam(name="startTime") @DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss") LocalDateTime startTime, @RequestParam("endTime") @DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss") LocalDateTime endTime) {
		OnlineTest test = new OnlineTest();
		Set<Question> questions = new HashSet<Question>();
		test.setTestId(id);
		test.setTestName(name);
		test.setTestDuration(duration);
		test.setStartTime(startTime);
		test.setEndTime(endTime);
		test.setIsdeleted(false);
		test.setTestMarksScored(new Double(0));
		test.setTestTotalMarks(new Double(0));
		test.setTestQuestions(questions);
		try {
			testservice.updateTest(id, test);
		} catch (UserException e) {
			System.out.println(e.getMessage());
		}
		return "home";
	}
	
	@RequestMapping(value="/updatequestion", method=RequestMethod.GET)
	public String showUpdateQuestion(@ModelAttribute("question") Question question) {
		return "UpdateQuestion";
	}
	@RequestMapping(value="/updatequestioninput", method=RequestMethod.POST)
	public ModelAndView updateQuestion(@RequestParam("questionid") long id, @ModelAttribute("question") Question question) {
		Question questionOne;
		try {
			questionOne = testservice.searchQuestion(id);
			return new ModelAndView("UpdateQuestion", "Update", questionOne);
		} catch (UserException e) {
			System.out.println(e.getMessage());
		}
		return new ModelAndView("home");
	}
	@RequestMapping(value="/updatequestionsubmit", method=RequestMethod.POST)
	public String actualUpdate(@RequestParam("testId") long testid, @ModelAttribute("question") Question question) {
		
		OnlineTest test;
		try {
			test = testservice.searchTest(testid);
			Question questionOne = new Question();
			questionOne.setQuestionId(question.getQuestionId());
			questionOne.setQuestionTitle(question.getQuestionTitle());
			questionOne.setQuestionOptions(question.getQuestionOptions());
			questionOne.setQuestionAnswer(question.getQuestionAnswer());
			questionOne.setQuestionMarks(question.getQuestionMarks());
			questionOne.setChosenAnswer(0);
			questionOne.setIsDeleted(false);
			questionOne.setMarksScored(new Double(0));
			questionOne.setOnlinetest(test);
			testservice.updateQuestion(testid, question.getQuestionId(), questionOne);
		} catch (UserException e1) {
			System.out.println(e1.getMessage());
		}
		return "home";
	}
	
	@RequestMapping(value="/updateuser", method=RequestMethod.GET)
	public String showUpdateQuestion(@ModelAttribute("user") User user) {
		return "UpdateUser";
	}
	@RequestMapping(value="/updateuserinput", method=RequestMethod.POST)
	public ModelAndView updateUser(@RequestParam("userid") long id, @ModelAttribute("user") User user) {
		User userOne;
		try {
			userOne = testservice.searchUser(id);
			return new ModelAndView("UpdateUser", "Update", userOne);
		} catch (UserException e) {
			System.out.println(e.getMessage());
		}
		return new ModelAndView("home");
	}
	@RequestMapping(value="/updateusersubmit", method=RequestMethod.POST)
	public String actualUpdate(@RequestParam("userId") long id, @RequestParam("testId") long testid, @ModelAttribute("user") User user) {
		
		try {
			User userOne = testservice.searchUser(id);
			userOne.setUserId(id);
			userOne.setUserName(user.getUserName());
			userOne.setUserPassword(user.getUserPassword());
			userOne.setIsDeleted(false);
			userOne.setIsAdmin(false);
			testservice.updateProfile(userOne);
		} catch (UserException e) {
			System.out.println(e.getMessage());
		}
		return "home";
	}
	
	@RequestMapping(value = "/onlogin" ,method = RequestMethod.POST)
	public String onLogin(@ModelAttribute("user") User user) {
		User foundUser = testservice.login(user.getUserName(), user.getUserPassword());
		if(foundUser != null) {
			if(foundUser.getIsAdmin()) {
				return "admin";
			}
			else {
				return "user";
			}
		}
		else {
			return "home";
		}
		
	}
}
