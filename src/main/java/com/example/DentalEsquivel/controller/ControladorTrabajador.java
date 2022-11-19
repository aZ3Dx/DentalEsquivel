package com.example.DentalEsquivel.controller;

import com.example.DentalEsquivel.entitys.Ciudad;
import com.example.DentalEsquivel.entitys.Doctor;
import com.example.DentalEsquivel.entitys.Doctorxturno;
import com.example.DentalEsquivel.entitys.Persona;
import com.example.DentalEsquivel.entitys.Recepcionista;
import com.example.DentalEsquivel.entitys.Rol;
import com.example.DentalEsquivel.entitys.Usuario;
import com.example.DentalEsquivel.interfaceservice.ICiudadService;
import com.example.DentalEsquivel.interfaceservice.IDoctorService;
import com.example.DentalEsquivel.interfaceservice.IDoctorxturnoService;
import com.example.DentalEsquivel.interfaceservice.IPersonaService;
import com.example.DentalEsquivel.interfaceservice.IRecepcionistaService;
import com.example.DentalEsquivel.interfaceservice.IRolService;
import com.example.DentalEsquivel.interfaceservice.IUsuarioService;
import java.util.List;
import java.util.Objects;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/trabajador")
public class ControladorTrabajador {

    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Autowired
    private IPersonaService iPersona;
    @Autowired
    private IUsuarioService iUsuario;
    @Autowired
    private ICiudadService iCiudad;
    @Autowired
    private IRolService iRol;
    @Autowired
    private IDoctorService iDoctor;
    @Autowired
    private IRecepcionistaService iRecepcionista;
    @Autowired
    private IDoctorxturnoService iDoctorxturno;

    //Método para agregar variables al model a nivel de este controlador
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("vista", 4);
        model.addAttribute("title", "Trabajador");
        model.addAttribute("titulo", "TRABAJADORES");
    }

    //Mapeo PRINCIPAL - Lista de trabajadores
    @GetMapping("")
    public String listaTrabajador(Model model) {
        //Se envía lista de Trabajadores
        List<Persona> personas = iPersona.ListarSoloTrabajadores();
        model.addAttribute("Personas", personas);
        return "/trabajador/lista";
    }

    //Mapeo de nuevo trabajador - Formulario nuevo trabajador
    @GetMapping("/nuevo")
    public String nuevoTrabajador(Model model) {

        Persona persona = new Persona();
        model.addAttribute("Persona", persona);

        List<Ciudad> ciudades = iCiudad.Listar();
        model.addAttribute("Ciudades", ciudades);

        return "/trabajador/nuevo";
    }

    //Mapeo guardar trabajador - Se valida formulario nuevo trabajador
    //@PostMapping("/nuevo/guardar")
    @RequestMapping(value = "/nuevo/guardar", method = RequestMethod.POST)
    public String guardarTrabajador(
            @Valid
            @ModelAttribute("Persona") Persona persona,
            BindingResult result,
            RedirectAttributes attr,
            Model model) {

        if (result.hasErrors()) {

            List<Ciudad> ciudades = iCiudad.Listar();
            model.addAttribute("Ciudades", ciudades);

            model.addAttribute("Persona", persona);
            return "/trabajador/nuevo";
        }
        
        iPersona.Guardar(persona);
        attr.addFlashAttribute("mensaje", "Trabajador guardado con éxito!")
                .addFlashAttribute("clase", "success")
                .addFlashAttribute("ico", "person-plus-fill");
        return "redirect:/trabajador";
    }

    //Mapeo editar trabajador - Form editar trabajador
    @GetMapping("/editar")
    public String editarTrabajador(Model model,
            @RequestParam("id") int id_persona) {

        Persona persona = iPersona.ListarId(id_persona);
        model.addAttribute("Persona", persona);

        List<Ciudad> ciudades = iCiudad.Listar();
        model.addAttribute("Ciudades", ciudades);

        List<Rol> roles = iRol.Listar();
        model.addAttribute("Roles", roles);

        return "/trabajador/editar";
    }

    //Mapeo actualizar trabajador - Validación de form actualizar trabajador
    @RequestMapping(value = "/actualizar", method = RequestMethod.POST)
    public String actualizarTrabajador(Model model,
            @Valid
            @ModelAttribute("Persona") Persona persona,
            BindingResult result,
            RedirectAttributes attr) {

        if (result.hasErrors()) {
            
            model.addAttribute("Persona", persona);

            List<Ciudad> ciudades = iCiudad.Listar();
            model.addAttribute("Ciudades", ciudades);

            List<Rol> roles = iRol.Listar();
            model.addAttribute("Roles", roles);
            return "/trabajador/editar";
        }
        if (Objects.nonNull(persona.getIdusuario())) {
            if (persona.getIdusuario().getIdrol().getRol().equals("ROLE_Doctor")) {
                if (!persona.getIdusuario().getActivo()) {
                    List<Doctorxturno> dxtS = iDoctorxturno.Listar();
                    //System.out.println("ID_dxt | ID_doctor | ID_persona | ID_docPer");
                    for (Doctorxturno dxt : dxtS) {
                        /*System.out.print(dxt.getId_doctorxturno() + "     |");
                    System.out.print(dxt.getIddoctor().getId_doctor() + "          |");
                    System.out.print(persona.getId_persona() + "          |");
                    System.out.println(dxt.getIddoctor().getIdpersona().getId_persona());*/
                        if (dxt.getIddoctor().getIdpersona().getId_persona() == persona.getId_persona()) {
                            iDoctorxturno.Eliminar(dxt.getId_doctorxturno());
                        }
                    }
                }
            }
        }
        
        iPersona.Guardar(persona);
        //Se envía mensaje de éxito!
        attr.addFlashAttribute("mensaje", "Datos actualizados correctamente!")
                .addFlashAttribute("clase", "success")
                .addFlashAttribute("ico", "patch-check-fill");
        return "redirect:/trabajador";
    }

    //Mapeo asignar trabajador - Form asignar USER trabajador
    @GetMapping("/asignar")
    public String asignarUsuarioTrabajador(Model model,
            @RequestParam("id") int id_persona,
            RedirectAttributes attr) {
        //Se instancia y agrega Persona al model solo sí no tiene USER
        Persona persona = iPersona.ListarId(id_persona);
        if (persona.getIdusuario() != null) {
            //Se envía mensaje de éxito!
        attr.addFlashAttribute("mensaje", "El Trabajador seleccionado ya cuenta con un Usuario asignado!")
                .addFlashAttribute("clase", "warning")
                .addFlashAttribute("ico", "exclamation-diamond-fill");
        return "redirect:/trabajador";
        }
        model.addAttribute("Persona", persona);
        //Se instancia y agrega Usuario al model
        Usuario usuario = new Usuario();
        model.addAttribute("Usuario", usuario);
        //Se envía lista de roles
        List<Rol> roles = iRol.Listar();
        roles.remove(0);
        roles.remove(0);
        roles.remove(0);
        model.addAttribute("Roles", roles);
        //Se envía indicador de contraseñas coincidentes
        model.addAttribute("coinciden", true);
        return "/trabajador/asignar";
    }

    //Mapeo usuario asignado trabajador - Validación de asignación
    @PostMapping("/asignado")
    public String asignadoTrabajador(Model model,
            @RequestParam("id_persona") int id_persona,
            @RequestParam("passwordcheck") String passwordcheck,
            @Valid
            @ModelAttribute("Usuario") Usuario usuario,
            BindingResult result,
            RedirectAttributes attr) {
        Persona persona = iPersona.ListarId(id_persona);
        //Se verifica coincidencia de contraseñas
        boolean coinciden = false;
        if (usuario.getPassword().equals(passwordcheck)) {
            coinciden = true;
        }
        //Si existen errores
        if (result.hasErrors() || !coinciden) {
            //Se reenvía instancia Persona
            model.addAttribute("Persona", persona);
            //Se envía lista de roles
            List<Rol> roles = iRol.Listar();
            roles.remove(0);
            roles.remove(0);
            roles.remove(0);
            model.addAttribute("Roles", roles);
            //Se reenvía instancia Usuario
            model.addAttribute("Usuario", usuario);
            //Se envía coincidencia de contraseñas
            model.addAttribute("coinciden", coinciden);
            if (usuario.getPassword().equals(passwordcheck)) {
                model.addAttribute("coinciden", true);
            } else {
                model.addAttribute("coinciden", false);
            }
            return "/trabajador/asignar";
        }
        //Se codifica la contraseña y se guarda el Usuario en la BD
        usuario.setPassword(passEncoder.encode(usuario.getPassword()));
        usuario.setActivo(Boolean.TRUE);
        iUsuario.Guardar(usuario);

        persona.setIdusuario(usuario);

        iPersona.Guardar(persona);

        if(usuario.getIdrol().getRol().equals("ROLE_Doctor")){
            Doctor doctor = new Doctor();
            doctor.setIdpersona(persona);
            iDoctor.Guardar(doctor);
        } else if (usuario.getIdrol().getRol().equals("ROLE_Recepcionista")){
            Recepcionista recepcionista = new Recepcionista();
            recepcionista.setIdpersona(persona);
            iRecepcionista.Guardar(recepcionista);
        }
        //Se envía mensaje de éxito!
        attr.addFlashAttribute("mensaje", "Usuario y contraseña generado correctamente!")
                .addFlashAttribute("clase", "success")
                .addFlashAttribute("ico", "shield-lock-fill");
        return "redirect:/trabajador";
    }   
}
