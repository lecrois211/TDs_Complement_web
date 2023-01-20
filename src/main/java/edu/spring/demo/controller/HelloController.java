package edu.spring.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("user")
public class HelloController {
	@GetMapping("/hello")
	@ResponseBody
	public String HelloAction() {
		return "Bonjour monde :)";
	}
	
	@GetMapping(path={"/msg/{content}", "/msg"})
	@ResponseBody
	public String msgAction(@PathVariable(name="content", required = false) String message) {
		return "Message : " + message;
	}
	
	@RequestMapping(path="/", method={RequestMethod.POST, RequestMethod.GET})
	public String viewAction() {
		return "index";
	}
	
	@PostMapping("/login")
	public RedirectView loginAction(HttpSession session, @ModelAttribute("login") String login) {
		session.setAttribute("user", login);
		return new RedirectView("/");
	}
	
	@GetMapping("/logout")
	public RedirectView logoutAction(HttpSession session) {
		session.invalidate();
		return new RedirectView("/");
	}

}
