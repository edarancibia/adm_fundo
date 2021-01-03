package com.fundo.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fundo.entities.Usuario;
import com.fundo.serviceImpl.EstablecimientoServiceImpl;
import com.fundo.serviceImpl.UsuarioServiceImpl;

import jdk.internal.org.jline.utils.Log;


@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@SessionAttributes({"idusuario","role","username","rutusu"})
@RequestMapping("/")
public class HomeController {

	private static final org.apache.commons.logging.Log LOG = LogFactory.getLog(HomeController.class);
	
	@Autowired
	public UsuarioServiceImpl usuarioService;
	
	@Autowired
	public EstablecimientoServiceImpl estabService;
	
	@GetMapping("")
	public ModelAndView login(Usuario usuario){
		ModelAndView mv = new ModelAndView("user/login");
		return mv;
	}
	
	@GetMapping("/register")
	public ModelAndView index(Model model,@ModelAttribute(name="usuario") Usuario usuario,@RequestParam(name="errormail",required = false)String errormail,
			@RequestParam(name="success",required = false)String success) {
		ModelAndView mv = new ModelAndView("user/user-register");
		model.addAttribute("success",success);
		model.addAttribute("errormail",errormail);
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView addUser(@ModelAttribute(name = "usuario") Usuario usuario, Model model,
			@RequestParam("email") String email){
		ModelAndView mv = new ModelAndView("user/user-register");
		Usuario userDb = usuarioService.findByEmail(email);
		
		if(userDb != null) {
			model.addAttribute("errormail","el correo ya existe");
			//return "redirect:/user-register?errormail=1";
			return mv;
		}else {
			usuario.setVigente("S");
			usuarioService.addUsuario(usuario);
			model.addAttribute("result", 1);
			model.addAttribute("success","El usuario fue registrado exitosamente!");
			//return "redirect:/user-register?success=1";
			return mv;
		}
		
	}
	
	@PostMapping("/home")
	public ModelAndView login(@ModelAttribute(name="usuario") Usuario usuario,
			@RequestParam("email") String email,
			@RequestParam("clave") String password,
			HttpSession session, Model model) {
		
		String vista;
		//ModelAndView mv = new ModelAndView(vista);
		String vigente = "S";
		Usuario usuDb = usuarioService.findByEmailAndPassword(email, password);
		
		if(null !=  usuDb) {
			Usuario usu = usuarioService.findByEmail(email);
			session.setAttribute("idusuario", usu.getIdUsuario().toString());
			session.setAttribute("username", usu.getNombre()+' '+usu.getApellido());
			model.addAttribute("username", session.getAttribute("username"));
			model.addAttribute("idusuario", session.getAttribute("idusuario"));
			LOG.info("usuario : "+ usu.getIdUsuario().toString());
			
			model.addAttribute("estabs",this.getEstabs(usu.getIdUsuario()));
			vista = "user/select-estab";
			return new ModelAndView(vista);
			
		}else {
			vista = "user/login";
			model.addAttribute("errormail","Usuario o contraseña incorrectos");
			return new ModelAndView(vista);
		}
		
	}
	
	@GetMapping("/get-estabs/{idusuario}")
	public List<Map<String, Object>> getEstabs(@PathVariable Long idusuario){
		List<Map<String, Object>> estabs = estabService.getEstablecimientosByUser(idusuario);
		return estabs;
	}

}
