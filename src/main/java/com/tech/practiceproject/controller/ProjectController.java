package com.tech.practiceproject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.tech.practiceproject.model.Registraion;
import com.tech.practiceproject.service.PracticeServiceInterface;


@Controller
@SessionAttributes({"email","password"})
public class ProjectController {
	
	@Autowired
	private PracticeServiceInterface practiceServiceInterface;
	
	@GetMapping({"/","/login"})
	public String loginPage() {
		return "login";
	}
	
	@GetMapping({"/logout"})
	public String logoutPage(SessionStatus session) {
		session.setComplete();
		return "login";
	}
	
	@PostMapping("/loginsubmit")
	public String loginSubmit(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		if (this.practiceServiceInterface.isAuthenticated(email, password)) {
			model.addAttribute("email", email);
			model.addAttribute("password", password);
			boolean forUpdate = false;
			if (this.practiceServiceInterface.isAdmin(email)) {
				forUpdate = true;
			}else {
				forUpdate =false;
			}
			model.addAttribute("update", forUpdate);
			return "home";
		}else {
			return "login";
		}
	}
	
	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("registration", new Registraion());
		return "registration";
	}
	
	@PostMapping("/registrationsubmit")
	public String getRegistrationForm(@ModelAttribute("registration") Registraion registration) {
		this.practiceServiceInterface.saving(registration);
		return "login";
	}
	
	@GetMapping("/home")
	public String homePage(HttpSession session, Model model) {
		
		if (session.getAttribute("email")==null || session.getAttribute("password")==null) {
			return "login";
		}else if (practiceServiceInterface.isAuthenticated((String)session.getAttribute("email"), (String)session.getAttribute("password"))) {
			boolean forUpdate = false;
			if (this.practiceServiceInterface.isAdmin((String)session.getAttribute("email"))) {
				forUpdate = true;
			}else {
				forUpdate =false;
			}
			model.addAttribute("update", forUpdate);
			return "home";
		}else {
			return "login";
		}	
	}
	
	@GetMapping("/cart")
	public String cartPage(HttpSession session) {
		if (session.getAttribute("email")==null || session.getAttribute("password")==null) {
			return "login";
		}else if (practiceServiceInterface.isAuthenticated((String)session.getAttribute("email"), (String)session.getAttribute("password"))) {
			return "cart";
		}else {
			return "login";
		}	
	}
	
	@GetMapping("/categorypage")
	public String categoryPage(HttpSession session) {
		if (session.getAttribute("email")==null || session.getAttribute("password")==null) {
			return "login";
		}else if (practiceServiceInterface.isAuthenticated((String)session.getAttribute("email"), (String)session.getAttribute("password"))) {
			return "categorypage";
		}else {
			return "login";
		}	
	}
	
	@GetMapping("/productpage")
	public String productPage(HttpSession session, Model model) {	
		if (session.getAttribute("email")==null || session.getAttribute("password")==null) {
			return "login";
		}else if (practiceServiceInterface.isAuthenticated((String)session.getAttribute("email"), (String)session.getAttribute("password"))) {
			boolean forUpdate = false;
			if (this.practiceServiceInterface.isAdmin((String)session.getAttribute("email"))) {
				forUpdate = true;
			}else {
				forUpdate =false;
			}
			model.addAttribute("update", forUpdate);
			return "productpage";
		}else {
			return "login";
		}	
	}
	
	@GetMapping("/productviewpage")
	public String productViewPage(HttpSession session) {
		if (session.getAttribute("email")==null || session.getAttribute("password")==null) {
			return "login";
		}else if (practiceServiceInterface.isAuthenticated((String)session.getAttribute("email"), (String)session.getAttribute("password"))) {
			if (this.practiceServiceInterface.isAdmin((String)session.getAttribute("email"))) {
				return "productviewpage";
			}else {
				return "home";
			}	
		}else {
			return "login";
		}	
	}
	
	
	
	
	
	
	
	

}
