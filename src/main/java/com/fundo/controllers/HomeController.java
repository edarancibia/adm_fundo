package com.fundo.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import com.fundo.entities.Establecimiento;
import com.fundo.entities.Usuario;
import com.fundo.entities.UsuarioEstablecimiento;
import com.fundo.serviceImpl.EstablecimientoServiceImpl;
import com.fundo.serviceImpl.UsuarioEstbSerciceImpl;
import com.fundo.serviceImpl.UsuarioServiceImpl;


@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@SessionAttributes({"idusuario","role","username","rutusu","idestab","usernomestab"})
@RequestMapping("/")
public class HomeController {

	private static final org.apache.commons.logging.Log LOG = LogFactory.getLog(HomeController.class);
	
	@Autowired
	public UsuarioServiceImpl usuarioService;
	
	@Autowired
	public EstablecimientoServiceImpl estabService;
	
	@Autowired
	public UsuarioEstbSerciceImpl usEstSer;
	
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
			List<UsuarioEstablecimiento> usrEstab = usEstSer.findByIdusuario(usu.getIdUsuario().intValue());
			//Long idestab_ = (long) usrEstab.getIdestablecimiento();
			//Establecimiento estab = estabService.fingByIdestablecimiento(idestab_);
			//session.setAttribute("idestab", String.valueOf(usrEstab.getIdestablecimiento()));
			session.setAttribute("username", usu.getNombre()+' '+usu.getApellido());
			//session.setAttribute("usernomestab",session.getAttribute("username")+" - "+ estab.getDescripcion().toString());
			model.addAttribute("username", session.getAttribute("username"));
			model.addAttribute("idusuario", session.getAttribute("idusuario"));
			model.addAttribute("idestab", session.getAttribute("idestab"));
			model.addAttribute("usernomestab", session.getAttribute("usernomestab"));
			LOG.info("estab : "+ model.getAttribute("usernomestab")+' '+ model.getAttribute("idestab"));
			
			model.addAttribute("estabs",this.getEstabs(usu.getIdUsuario()));
			vista = "user/select-estab";
			return new ModelAndView(vista);
			
		}else {
			vista = "user/login";
			model.addAttribute("errormail","Usuario o contraseña incorrectos");
			return new ModelAndView(vista);
		}
		
	}
	
	@GetMapping("/selected-estab/{idusuario}")
	public ModelAndView selectEstab(@PathVariable int idusuario, HttpSession session, Model model,
			@RequestParam(name="cboEstabs")int cboEstabs) {
		
		ModelAndView mv = new ModelAndView("./animales/lista");
		Long idusuario_ = (long)idusuario;
		Usuario usu = usuarioService.findByIdUsuario(idusuario_);
		session.setAttribute("idusuario", idusuario);
		Long idestab_ = (long)cboEstabs;
		Establecimiento estab = estabService.fingByIdestablecimiento(idestab_);
		session.setAttribute("idestab", cboEstabs);
		session.setAttribute("username", usu.getNombre()+' '+usu.getApellido());
		session.setAttribute("usernomestab",session.getAttribute("username")+" - "+ estab.getDescripcion().toString());
		model.addAttribute("username", session.getAttribute("username"));
		model.addAttribute("idusuario", session.getAttribute("idusuario"));
		model.addAttribute("idestab", session.getAttribute("idestab"));
		model.addAttribute("usernomestab", session.getAttribute("usernomestab"));
		
		model.addAttribute("estabs",this.getEstabs(usu.getIdUsuario()));
		
		return mv;
	}
	
	@GetMapping("/get-estabs-by-user/{idusuario}")
	public ModelAndView getEstabs(@PathVariable int idusuario, Model model,HttpSession session) {
		ModelAndView mv = new ModelAndView("user/select-estab");
		
		Long idusuario_ = (long)idusuario;
		Usuario usu = usuarioService.findByIdUsuario(idusuario_);
		session.setAttribute("idusuario", idusuario);
		List<UsuarioEstablecimiento> usrEstab = usEstSer.findByIdusuario(usu.getIdUsuario().intValue());
		//Long idestab_ = (long) usrEstab.getIdestablecimiento();
		//Establecimiento estab = estabService.fingByIdestablecimiento(idestab_);
		//session.setAttribute("idestab", String.valueOf(usrEstab.getIdestablecimiento()));
		session.setAttribute("username", usu.getNombre()+' '+usu.getApellido());
		//session.setAttribute("usernomestab",session.getAttribute("username")+" - "+ estab.getDescripcion().toString());
		model.addAttribute("username", session.getAttribute("username"));
		model.addAttribute("idusuario", session.getAttribute("idusuario"));
		model.addAttribute("idestab", session.getAttribute("idestab"));
		model.addAttribute("usernomestab", session.getAttribute("usernomestab"));
		
		model.addAttribute("estabs",this.getEstabs(usu.getIdUsuario()));
		return mv;
	}
	
	
	@GetMapping("/get-estabs/{idusuario}")
	public List<Map<String, Object>> getEstabs(@PathVariable Long idusuario){
		List<Map<String, Object>> estabs = estabService.getEstablecimientosByUser(idusuario);
		return estabs;
	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request) {
	    HttpSession session = request.getSession(false);
	    if (session != null) {
	        session.invalidate();
	    }
	    return new ModelAndView("user/login") ;  //Where you go after logout here.
	}
	

}
