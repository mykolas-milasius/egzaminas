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
import pagrindinis.projektas.UgdymoIstaiga;
import pagrindinis.projektas.UgdymoIstaigaRepository;

import org.springframework.stereotype.Controller;

@Controller
public class EgzaminasController
{
	@Autowired
	private UserRepository user_repository;
	
	@Autowired
	private FilmsRepository films_repository;
	
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
	
	@RequestMapping(path="/filmai")
	public String filmai(Model model)
	{
		model.addAttribute("film", films_repository.findAll() );
		
		return "filmai";
	}
	
	@RequestMapping(path="/filmai_prideti", method={ RequestMethod.GET, RequestMethod.POST })
	public String admin_prideti(
		@RequestParam(name="id", required=false, defaultValue="0") Integer id 
		, @RequestParam(name="pav", required=false, defaultValue="") String pav
		, @RequestParam(name="aprasymas", required=false, defaultValue="") String aprasymas
		, @RequestParam(name="rei", required=false, defaultValue="") String reitingas
		, @RequestParam(name="kat", required=false, defaultValue="nesaugoti") String kategorija
		, @RequestParam(name="prideti_name", required=false, defaultValue="neprideti") String prideti
		, Model model)
	{
		Films film = new Films();
		if(prideti.equals("prideti"))
		{
			Optional <Films> found = films_repository.findById(id);
			
			if(found.isPresent())
			{
				film = found.get();
				film.setId(id);
			}
	
			film.setPavadinimas(pav);
			film.setAprasas(aprasymas);
			film.setReitingas(reitingas);
			film.setKategorija(kategorija);
			
			films_repository.save(film);
		}
		model.addAttribute("film", films_repository.findAll() );
		
		return "filmai";
	}
	/*
	@RequestMapping(path="/filmai_prideti", method={ RequestMethod.GET, RequestMethod.POST })
	public String admin_prideti(
		@RequestParam(name="id", required=false, defaultValue="0") Integer id 
		, @RequestParam(name="pav", required=false, defaultValue="") String pav
		, @RequestParam(name="kodas", required=false, defaultValue="") String kodas
		, @RequestParam(name="adresas", required=false, defaultValue="") String adresas
		, @RequestParam(name="saugoti", required=false, defaultValue="nesaugoti") String saugoti
		, @RequestParam(name="prideti_name", required=false, defaultValue="neprideti") String prideti
		, Model model)
	{
		UgdymoIstaiga ugdymo_istaiga = new UgdymoIstaiga();
		if(prideti.equals("prideti"))
		{
			Optional <UgdymoIstaiga> found = ugdymo_istaiga_repository.findById(id);
			
			if(found.isPresent())
			{
				ugdymo_istaiga = found.get();
				ugdymo_istaiga.setId(id);
			}
	
			ugdymo_istaiga.setPavadinimas(pav);
			ugdymo_istaiga.setKodas(kodas);
			ugdymo_istaiga.setAdresas(adresas);
			
			ugdymo_istaiga_repository.save(ugdymo_istaiga);
		}
		model.addAttribute("istaigos", ugdymo_istaiga_repository.findAll());
			
		return "admin_prideti";
	}
	
	@RequestMapping(path="/ugdymo_istaiga_redaguoti")	
	public @ResponseBody UgdymoIstaiga ugdymoIstaigosDuom(@RequestParam(name="id", required=true, defaultValue="0") Integer id ) throws IOException {
		
		UgdymoIstaiga ugdymo_istaiga = new UgdymoIstaiga();
		
		if (id > 0)
		{
			Optional <UgdymoIstaiga> found = ugdymo_istaiga_repository.findById( id );
		
			if (found.isPresent())
			{
				ugdymo_istaiga = found.get();
				ugdymo_istaiga.setId ( id );
			}
		}
		return ugdymo_istaiga;
	}
	
	@RequestMapping(path="/salinti_istaiga")
	public String salintiIstaiga (
		@RequestParam Integer id_istaigos,
		@RequestParam(name="", required=false, defaultValue="") String salinti)
	{
		if(salinti.equals("salinti"))
		{
			Optional <UgdymoIstaiga> found = ugdymo_istaiga_repository.findById( id_istaigos );
			if (found.isPresent())
			{
				UgdymoIstaiga n = found.get();
				ugdymo_istaiga_repository.deleteById(id_istaigos);
			}
		}
		return "redirect:admin_prideti";
	}
	*/
}
