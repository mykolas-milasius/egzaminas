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

import bandymas_databases.bandymas.Menu;
import bandymas_databases.bandymas.Uzsakymai;
import bandymas_databases.bandymas.UzsakymaiPrekes;
import egzaminas.egzaminas.User;

import org.springframework.stereotype.Controller;

@Controller
public class EgzaminasController
{
	@Autowired
	private UserRepository user_repository;
	
	@Autowired
	private FilmsRepository films_repository;
	
	@Autowired
	private KategorijaRepository kategorija_repository;
	
	@Autowired
	private FilmsKategorijaRepository films_kategorija_repository;
	
	@RequestMapping(path="/pagrindinis") 
	public String home(Model model)
	{
		model.addAttribute("filmai", films_repository.findAll() );
		return "pagrindinis";
	}
	
	@RequestMapping("/")
	public String home_2(Model model)
	{
		model.addAttribute("filmai", films_repository.findAll() );
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
	
	@RequestMapping(path="/admin", method={ RequestMethod.GET, RequestMethod.POST })
	public String admin_prid(
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
		model.addAttribute("filmai", films_repository.findAll() );
		
		return "admin";
	}
	
	@RequestMapping(path="/filmai", method={ RequestMethod.GET, RequestMethod.POST })
	public String filmai(
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
		
		model.addAttribute("filmai", films_repository.findAll() );
		
		return "filmai";
	}
	
	@RequestMapping(path="/filmai_2")	
	public @ResponseBody Films filmDuom(@RequestParam(name="id", required=true, defaultValue="0") Integer id ) throws IOException {
		
		Films film = new Films();
		
		if (id > 0)
		{
			Optional <Films> found = films_repository.findById( id );
		
			if (found.isPresent())
			{
				film = found.get();
				film.setId ( id );
			}
		}
		return film;
	}
	
	@RequestMapping(path="/salinti-filma")
	public String salintiFilma (
			@RequestParam Integer id_filmo,
			@RequestParam(name="", required=false, defaultValue="") String salinti
			)
	{
		if(salinti.equals("salinti"))
		{
			Optional <Films> found = films_repository.findById( id_filmo );
			if (found.isPresent())
			{
				   Films n = found.get();
				   films_repository.deleteById(id_filmo);
			}
		}
		return "redirect:filmai";
	}
	
	@RequestMapping(path="/salinti-filma-admin")
	public String salintiFilma_2 (
			@RequestParam Integer id_filmo,
			@RequestParam(name="", required=false, defaultValue="") String salinti
			)
	{
		if(salinti.equals("salinti"))
		{
			Optional <Films> found = films_repository.findById( id_filmo );
			if (found.isPresent())
			{
				   Films n = found.get();
				   films_repository.deleteById(id_filmo);
			}
		}
		return "redirect:admin";
	}
	
	@RequestMapping("/filmas1")
	public String uzsakymas1(@RequestParam(name="i", required=true, defaultValue="0") String id
			, @RequestParam(name="prideti_name", required=false, defaultValue="neprideti") String prideti
			, Model model
			)
	{
		model.addAttribute("filmai", films_repository.findAll() );
		return "redirect:filmas1?i="+id;
	}
	
	@RequestMapping(path="/kategorijos", method={ RequestMethod.GET, RequestMethod.POST })
	public String kategorijos(
		@RequestParam(name="id", required=false, defaultValue="0") Integer id 
		, @RequestParam(name="pav", required=false, defaultValue="") String pav
		, @RequestParam(name="prideti_name", required=false, defaultValue="neprideti") String prideti
		, Model model)
	{
		Kategorija kat = new Kategorija();
		if(prideti.equals("prideti"))
		{
			Optional <Kategorija> found = kategorija_repository.findById(id);
			
			if(found.isPresent())
			{
				kat = found.get();
				kat.setId(id);
			}
	
			kat.setPavadinimas(pav);
			
			kategorija_repository.save(kat);
		}
		
		model.addAttribute("kategorijos", kategorija_repository.findAll() );
		
		return "kategorijos";
	}
	
	@RequestMapping(path="/kategorijos_2")	
	public @ResponseBody Kategorija katDuom(@RequestParam(name="id_kategorijos", required=true, defaultValue="0") Integer id ) throws IOException {
		
		Kategorija kat = new Kategorija();
		
		if (id > 0)
		{
			Optional <Kategorija> found = kategorija_repository.findById( id );
		
			if (found.isPresent())
			{
				kat = found.get();
				kat.setId ( id );
			}
		}
		return kat;
	}
	
	@RequestMapping(path="/salinti-kat")
	public String salintiKat (
			@RequestParam Integer id_kategorijos,
			@RequestParam(name="", required=false, defaultValue="") String salinti
			)
	{
		if(salinti.equals("salinti"))
		{
			Optional <Kategorija> found = kategorija_repository.findById( id_kategorijos );
			if (found.isPresent())
			{
				   Kategorija n = found.get();
				   kategorija_repository.deleteById(id_kategorijos);
			}
		}
		return "redirect:kategorijos";
	}
}
