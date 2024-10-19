package com.jony.spring_boot.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jony.spring_boot.entity.User;
import com.jony.spring_boot.repository.UserRepo;
import com.jony.spring_boot.service.UserDao;

@Controller
public class MyController {

	@Autowired
	UserDao dao;

	@Autowired
	UserRepo repo;

	@GetMapping("/")
	public String home() {

		return "home";
	}

	@GetMapping("registrationForm")
	public String registrationForm() {

		return "registrationForm";

	}

	@GetMapping("saveAll")
	public String saveAll(@ModelAttribute User user) {
		dao.saveAll(user);

		return "save";

	}

	@GetMapping("loginForm")
	public String loginForm() {

		return "loginForm";

	}

	@PostMapping("loginValidation")
	public String loginValidation(@RequestParam String name, @RequestParam String pass, Model model) {

		User user = dao.checkUser(name);

		if (user == null) { // ============check user ===========
			model.addAttribute("msg", "No user Found");

			return "loginFailed";
		}
	//	if (user.getLockTime() != null && LocalDateTime.now().compareTo(user.getLockTime()) < 0) { // check user account
		if (user.getLockTime() != null && LocalDateTime.now().isBefore(user.getLockTime())) { 																							// is lock or not

			model.addAttribute("msg", "Account Locked, Please try again later");
			return "loginFailed";

		}
		if (user.getPass().equals(pass)) { // =======check name and pass

			model.addAttribute("msg", "Login Success");
			user.setFailedAttempts(0);
			repo.save(user);

			model.addAttribute("name", user.getName());
			return "loginSuccess";
		} else { // ======if pass Wrong
			user.setFailedAttempts(user.getFailedAttempts() + 1);
			repo.save(user);
			model.addAttribute("msg", "Invalid details!. Attempts left :" + (3 - user.getFailedAttempts()));

			if (user.getFailedAttempts() == 3) {
				user.setLockTime(LocalDateTime.now().plusMinutes(2));

				user.setFailedAttempts(0);
				repo.save(user);
				model.addAttribute("msg", "Account Locked, for 2 minutes");

			}
			return "loginFailed";
		}

		// return "loginFailed";
	}

	@GetMapping("logout")
	public String logout() {

		return "redirect:/loginForm";

	}

}
