package com.example.DentalEsquivel.controller;

import com.example.DentalEsquivel.entitys.Cita;
import com.example.DentalEsquivel.entitys.Doctorxturno;
import com.example.DentalEsquivel.entitys.Estado;
import com.example.DentalEsquivel.entitys.Paciente;
import com.example.DentalEsquivel.interfaceservice.ICitaService;
import com.example.DentalEsquivel.interfaceservice.IDoctorxturnoService;
import com.example.DentalEsquivel.interfaceservice.IEstadoService;
import com.example.DentalEsquivel.interfaceservice.IPacienteService;
import java.sql.Time;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("cita")
public class ControladorCita {
    
    @Autowired
    private ICitaService iCita;
    @Autowired
    private IPacienteService iPaciente;
    @Autowired
    private IDoctorxturnoService iDoctorxturno;
    @Autowired
    private IEstadoService iEstado;
    
    //Método para agregar variables al model a nivel de este controlador
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("vista", 3);
        model.addAttribute("title", "Cita");
        model.addAttribute("titulo", "Citas");
    }
    
    //Mapeo PRINCIPAL - Lista de trabajadores
    @GetMapping("")
    public String listaCita(Model model) {
        List<Cita> citas = iCita.ListaCloseCitas();
        model.addAttribute("Citas", citas);
        
        List<Estado> estados = iEstado.Listar();
        model.addAttribute("Estados",  estados);
        
        return "/cita/lista";
    }
    
    @GetMapping("/estado")
    public String listaCitaPorEstado(Model model,
            @RequestParam("est") String estado) {
        if (estado.equals("all")) {
            List<Cita> citas = iCita.ListaCloseCitasInOrder();
            model.addAttribute("Citas", citas);
        } else {
            List<Cita> citasFiltradas = iCita.ListaCloseCitasPerStates(estado);
            model.addAttribute("Citas", citasFiltradas);
        }
        List<Estado> estados = iEstado.Listar();
        model.addAttribute("Estados",  estados);

        return "/cita/estado";
    }

    @GetMapping("/nueva")
    public String nuevaCita(Model model,
            @RequestParam("id") int id_paciente){
        Paciente paciente = iPaciente.ListarId(id_paciente);

        Cita cita = new Cita();
        cita.setIdpaciente(paciente);
        model.addAttribute("Cita", cita);
        model.addAttribute("letraDia", 'G');
        
        return "/cita/nueva";
    }
    
    @PostMapping("/nueva/guardar")
    public String guardarCita(Model model,
            @RequestParam("letraDia") Character letraDia,
            @Valid
            @ModelAttribute("Cita") Cita cita,
            BindingResult result,
            RedirectAttributes attr){
        System.out.println(cita);
        if(result.hasErrors()){
            System.out.println(result);
            model.addAttribute("Cita", cita);
            List<Doctorxturno> horasDisponibles =
                    iDoctorxturno.ListHorasPorDia(letraDia);
            model.addAttribute("Horas", horasDisponibles);
            model.addAttribute("letraDia", letraDia);
            
            return "/cita/nueva";
        }
        Estado estado = iEstado.ListarId(1); //Nuevo
        cita.setIdestado(estado);
        iCita.Guardar(cita);
        
        return "redirect:/cita";
    }
    
    @GetMapping("/ver")
    public String verCita(Model model,
            @RequestParam("id") int id_cita){
        Cita cita = iCita.ListarId(id_cita);
        model.addAttribute("Cita", cita);
        
        return "/cita/ver";
    }
    
    @GetMapping("/nueva/obtenerHoras")
    public @ResponseBody List<Doctorxturno> horasDisponibles(
    @RequestParam(value = "dia", required = true) Character dia){
        
        return iDoctorxturno.ListHorasPorDia(dia);
    }

    @GetMapping("/nueva/obtenerDoctor")
    public @ResponseBody
    Doctorxturno doctorSeleccionado(
            @RequestParam(value = "dia", required = true) Character dia,
            @RequestParam(value = "hora", required = true) Time hora) {

        return iDoctorxturno.DoctorPorTurno(dia, hora);
    }
    
    @PostMapping("/cambiarEstado")
    public String cambiarEstadoCita(Model model,
            @RequestParam("id_cita") int id_cita,
            @RequestParam("id_estado") int id_estado,
            RedirectAttributes attr){
        Cita cita = iCita.ListarId(id_cita);
        Estado estado = iEstado.ListarId(id_estado);
        cita.setIdestado(estado);
        iCita.Guardar(cita);
        
        attr.addFlashAttribute("mensaje", "Estado modificado")
            .addFlashAttribute("clase", "warning")
            .addFlashAttribute("ico", "info-circle");

        return "redirect:/cita";
    }
}
