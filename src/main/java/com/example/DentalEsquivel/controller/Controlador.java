package com.example.DentalEsquivel.controller;

import com.example.DentalEsquivel.entitys.Usuario;
import com.example.DentalEsquivel.interfaceservice.IUsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class Controlador {
    
    @Autowired
    private BCryptPasswordEncoder passEncoder;
    
    @Autowired
    private IUsuarioService iUsuario;
    
    //Mapeo de página personalizada de Login
    @GetMapping("/login")
    public String login(Model model,
            @RequestParam(value="error",required=false) String error){
        if(error!=null){
            model.addAttribute("error",true);
        }
        return "login";
    }
    //Mapeo de página principal y posterior redireccionamiento a Home
    @GetMapping("")
    public String bienvenida(Model model, RedirectAttributes redirectAttrs){
        redirectAttrs
                .addFlashAttribute("mensaje", "Acceso Exitoso")
                .addFlashAttribute("clase", "success")
                .addFlashAttribute("ico", "check-circle-fill");
        return "redirect:/home";
    }
    //Mapeo de página Home
    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("vista", 1);
        model.addAttribute("title", "Inicio");
        model.addAttribute("titulo", "BIENVENIDO");
        return "home";
    }
    
    @PostMapping("/cambiar")
    public String cambiar(Model model,
            @RequestParam("userHidden") String username){
        List<Usuario> usuarios = iUsuario.Listar();
        for(Usuario usuario : usuarios){
            if(usuario.getUsername().equals(username)){
                model.addAttribute("Usuario", usuario);
            }
        }
        
        return "cambiar";
    }
    
    @PostMapping("/actualizarContra")
    public String actualizar(Model model,
            @ModelAttribute("Usuario") Usuario usuario,
            @RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword){
        String oldPass;
        oldPass = usuario.getPassword();
        if(!passEncoder.matches(oldPassword, oldPass) || newPassword.isEmpty()){
            model.addAttribute("error", "error");
            model.addAttribute("Usuario", usuario);
            return "/cambiar";
        }
        usuario.setPassword(passEncoder.encode(newPassword));
        iUsuario.Guardar(usuario);
        
        return "redirect:/logout";
    }

}
