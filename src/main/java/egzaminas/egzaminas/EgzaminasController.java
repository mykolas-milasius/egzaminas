package egzaminas.egzaminas;

import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import egzaminas.egzaminas.User;

import org.springframework.stereotype.Controller;

@Controller
public class EgzaminasController
{
	@Autowired
	private UserRepository user_repository;
	
	@RequestMapping(path="/pagrindinis") 
	public String home()
	{
		return "pagrindinis";
	}
	
	@RequestMapping("/")
	public String home_2()
	{
		return "pagrindinis";
	}
	
	@RequestMapping("/login")
	public String login(Model model)
	{		
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(Model model)
	{
		return "redirect:login";
	}
	
	@RequestMapping(path="/register")
	public String registration(Model model)
	{
		model.addAttribute("user", new User());
		
		return "register";
	}
	
	@RequestMapping(path="/process_register")
	public String processRegister(User user)
	{
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);
	     
	    user_repository.save(user);
	     
	    return "login";
	}
	
	@RequestMapping(path="/admin")
	public String admin()
	{
		return "admin";
	}
}
