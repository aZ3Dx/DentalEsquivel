package com.example.DentalEsquivel.controller;

import com.example.DentalEsquivel.entitys.Ciudad;
import com.example.DentalEsquivel.entitys.Paciente;
import com.example.DentalEsquivel.entitys.Persona;
import com.example.DentalEsquivel.entitys.Rol;
import com.example.DentalEsquivel.entitys.Usuario;
import com.example.DentalEsquivel.interfaceservice.ICiudadService;
import com.example.DentalEsquivel.interfaceservice.IPacienteService;
import com.example.DentalEsquivel.interfaceservice.IPersonaService;
import com.example.DentalEsquivel.interfaceservice.IRolService;
import com.example.DentalEsquivel.interfaceservice.IUsuarioService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("paciente")
public class ControladorPaciente {
    
    @Autowired
    private BCryptPasswordEncoder passEncoder;
    
    @Autowired
    private IPersonaService iPersona;
    @Autowired
    private IPacienteService iPaciente;
    @Autowired
    private ICiudadService iCiudad;
    @Autowired
    private IUsuarioService iUsuario;
    @Autowired
    private IRolService irol;
    
    //Método para agregar variables al model a nivel de este controlador
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("vista", 2);
        model.addAttribute("title", "Paciente");
        model.addAttribute("titulo", "PACIENTES");
    }

    //Mapeo PRINCIPAL - Lista de trabajadores
    @GetMapping("")
    public String listaPaciente(Model model) {
        //Se envía lista de pacientes
        List<Paciente> pacientes = iPaciente.Listar();
        model.addAttribute("Pacientes", pacientes);
        return "paciente/lista";
    }
    
    //Mapeo de nuevo trabajador - Formulario nuevo trabajador
    @GetMapping("/nuevo")
    public String nuevoPaciente(Model model) {

        Paciente paciente = new Paciente();
        model.addAttribute("Paciente", paciente);

        Persona persona = new Persona();
        model.addAttribute("Persona", persona);

        List<Ciudad> ciudades = iCiudad.Listar();
        model.addAttribute("Ciudades", ciudades);
        //Variable para recibir la clave
        String claveBox = "";
        model.addAttribute("claveBox", claveBox);
        return "paciente/nuevo";
    }
    
    @PostMapping("/nuevo/guardar")
    public String guardarPaciente(
            @RequestParam("claveBox") String claveBox,
            @ModelAttribute("Paciente") Paciente paciente,
            @Valid
            @ModelAttribute("Persona") Persona persona,
            BindingResult result,
            RedirectAttributes attr,
            Model model) {
        //Si se encuentran errores
        if (result.hasErrors()) {
            //Se envía lista de ciudades
            List<Ciudad> ciudades = iCiudad.Listar();
            model.addAttribute("Ciudades", ciudades);
            //Se reenvía la instancia Paciente
            model.addAttribute("Paciente", paciente);
            //Se reenvía la instancia Persona
            model.addAttribute("Persona", persona);
            model.addAttribute("claveBox", claveBox);
            return "paciente/nuevo";
        }
        //BLOQUE DE CREACIÓN AUTOMÁTICA DE USUARIO -------->
        Usuario usuario = new Usuario();
        List<Rol> roles = irol.Listar();
        for (Rol rol : roles) {
            if (rol.getRol().equals("ROLE_Paciente")) {
                usuario.setIdrol(rol);
            }
        }
        usuario.setUsername(claveBox);
        usuario.setPassword(passEncoder.encode(claveBox));
        usuario.setActivo(Boolean.TRUE);
        iUsuario.Guardar(usuario);
        //FIN DE BLOQUE DE CREACIÓN AUTOMÁTICA DE USUARIO -->

        //Se inserta Persona en la BD
        persona.setIdusuario(usuario);
        iPersona.Guardar(persona);
        //Se inserta Paciente en la BD
        paciente.setIdpersona(persona);
        iPaciente.Guardar(paciente);

        attr.addFlashAttribute("mensaje", "Paciente registrado con éxito!")
                .addFlashAttribute("clase", "success")
                .addFlashAttribute("ico", "person-heart");
        return "redirect:/paciente";
    }

    //Mapeo para ver todos los datos del paciente
    @GetMapping("/editar")
    public String verPaciente(Model model,
            @RequestParam("id") int id_paciente) {
        //Se instancia y agrega Paciente al model
        Paciente paciente = iPaciente.ListarId(id_paciente);
        model.addAttribute("Paciente", paciente);
        //Se instancia y agrega Persona al model
        Persona persona = paciente.getIdpersona();
        model.addAttribute("Persona", persona);
        //Se envía lista de ciudades
        List<Ciudad> ciudades = iCiudad.Listar();
        model.addAttribute("Ciudades", ciudades);

        return "paciente/editar";
    }
    
    @PostMapping("/actualizar")
    public String actualizarPaciente(
            @Valid
            @ModelAttribute("Paciente") Paciente paciente,
            BindingResult result2,
            @Valid
            @ModelAttribute("Persona") Persona persona,
            BindingResult result,
            RedirectAttributes attr,
            Model model){
        //Si se encuentran errores
        if (result.hasErrors()) {
            //Se envía lista de ciudades
            List<Ciudad> ciudades = iCiudad.Listar();
            model.addAttribute("Ciudades", ciudades);
            //Se reenvía la instancia Paciente
            model.addAttribute("Paciente", paciente);
            //Se reenvía la instancia Persona
            model.addAttribute("Persona", persona);
            return "paciente/editar";
        }

        iPersona.Guardar(persona);

        paciente.setIdpersona(persona);
        iPaciente.Guardar(paciente);

        attr.addFlashAttribute("mensaje", "Datos del paciente actualizados correctamente!")
                .addFlashAttribute("clase", "success")
                .addFlashAttribute("ico", "patch-check-fill");       
        return "redirect:/paciente";
    }
}
