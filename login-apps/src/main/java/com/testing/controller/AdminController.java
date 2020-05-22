package com.testing.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.testing.model.Admin;
import com.testing.model.Questions;
import com.testing.model.User;
import com.testing.model.UserAnswers;
import com.testing.service.AdminService;
import com.testing.service.UserService;
@RestController
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;

	@Autowired
	private UserService userService;
	
	
	@GetMapping(value="{id}")
	public Admin getAllAdmins(@PathVariable(value="id") Long id){
		return adminService.getAdminById(id);

	}
	@GetMapping(value="changeStatus")
	public void changeStatus(@RequestParam(name="id")Long id,@RequestParam(name="status") Boolean status) {
		User user=userService.getUserById(id);
		System.out.println(id+" - -"+status+"  --"+user);
		user.setStatus(!status);
		userService.saveUser(user);
		
	}
	@GetMapping(value="findAnswerByUser")
	public List<UserAnswers> getAllAnswersByUser(@RequestParam(name="userGmail")String userGmail){
		
		return adminService.findAllAnswersByUserName(userGmail);

	}
	@PostMapping(value="findAnswerByQuestion") 
	public List<UserAnswers> getAllAnswersByQuestion(@RequestBody Questions question){
		
		return adminService.findAllAnswersByQuestion(question);

	}
	
	
}
