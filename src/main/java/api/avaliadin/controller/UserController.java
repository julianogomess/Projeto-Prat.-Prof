package api.avaliadin.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import api.avaliadin.model.User;
import api.avaliadin.repository.UserRepository;




@Controller
@RequestMapping
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping("/cadastro")
	public String showCadastro(User user) {
	    return "cadastro";
	}
	@PostMapping("/cadastro")
	public String addUser(@RequestParam String username, @RequestParam String senha,@RequestParam String nome,@RequestParam String cidade, @RequestParam String uf, @RequestParam String dtNasc) throws ParseException {
		User t = userRepository.findByUsername(username);
		if(t!=null){
			return "redirect:/cadastro?error";
		}else {
			User u = new User();
		    u.setUsername(username);
		    u.setSenha(criptografar(senha));
		    u.setNome(nome);
		    u.setCidade(cidade);
		    u.setUf(uf);
		    SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		    if(dtNasc!="") {
		    	Date d = formatter.parse(dtNasc);
		    	u.setDtNasc(d);
		    }
		    Date b = new Date();
			u.setDtCad(b);
			u.setRole("ROLE USER");
			u.setEnabled(true);
		    userRepository.save(u);
		    return "redirect:/login";
		}
	}
	@GetMapping("/alterarCadastro")
	public String alterarCadastro(User user) {
	    return "alterarCadastro";
	}
	@PostMapping("/alterarcadastro")
	public String updateUser(Authentication authentication,@RequestParam String username, @RequestParam String senha,@RequestParam String nome,@RequestParam String cidade, @RequestParam String uf, @RequestParam String dtNasc) throws ParseException {
		String username_ = authentication.getName();
		User t = userRepository.findByUsername(username_);
		if (!username.isBlank()){
			t.setUsername(username);
		}
		if (!senha.isBlank()){
			t.setSenha(criptografar(senha));
		}
		if (!nome.isBlank()){
			t.setNome(nome);
		}
		if (!cidade.isBlank()){
			t.setCidade(cidade);
		}
		if (!uf.isBlank()){
			t.setUf(uf);
		}
		if (!dtNasc.isBlank()){
			SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		    if(dtNasc!="") {
		    	Date d = formatter.parse(dtNasc);
		    	t.setDtNasc(d);
		    }
		}
	
		userRepository.save(t);
		return "redirect:/listaUser";
		
	}
	@RequestMapping(value = "/listaUser", method = RequestMethod.GET)
    public String listaUsers(Model model) {
		Iterable<User> listaUser = userRepository.findAll();
        if (listaUser != null) {
        	model.addAttribute("users", listaUser);
        }
        return "listaUser";
    }
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
    public static String criptografar(String rawPassword) {
   	 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(rawPassword);
        return encodedPassword;
   }
}	