package com.example.DentalEsquivel.controller;

import com.example.DentalEsquivel.entitys.Doctor;
import com.example.DentalEsquivel.entitys.Doctorxturno;
import com.example.DentalEsquivel.entitys.Turno;
import com.example.DentalEsquivel.interfaceservice.IDoctorService;
import com.example.DentalEsquivel.interfaceservice.IDoctorxturnoService;
import com.example.DentalEsquivel.interfaceservice.ITurnoService;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("doctor")
public class ControladorDoctor {
    
    @Autowired
    private IDoctorxturnoService iDoctorxturno;
    @Autowired
    private IDoctorService iDoctor;
    @Autowired
    private ITurnoService iTurno;
    
    //Método para agregar variables al model a nivel de este controlador
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("vista", 5);
        model.addAttribute("title", "Doctor");
        model.addAttribute("titulo", "TURNOS");
    }
    
    //Mapeo PRINCIPAL - Lista de trabajadores
    @GetMapping("")
    public String listaPaciente(Model model) {
        List<Doctorxturno> turnosDocActivos = iDoctorxturno.ListaTurnosDocActivos();
        List<Doctor> doctoresDisponibles = iDoctor.ListaActiveDoctors();
        List<Turno> turnosOrdenados = iTurno.ListaTurnosOrderByHora();
        
        List<String> doctores = new ArrayList<>();
        List<Integer> ids_dxt = new ArrayList<>();
        List<Character> dias = List.of('A', 'B', 'C', 'D', 'E', 'F');
        
        List<Turno> turnos = iTurno.Listar();
        List<java.sql.Time> horas = new ArrayList<>();
        for(int i = 0; i<=8;i++){
            horas.add((Time) turnos.get(i).getHora());
        }
        model.addAttribute("horas", horas);
        
        int iterator = 0;
        for (java.sql.Time hora : horas) {
            //System.out.println("Se está analizando la hora " + hora);
            for (char dia : dias) {
                //System.out.println("Y el día: " + dia);
                //System.out.println(" de la persona con id_dxt = " + turnosDocActivos.get(iterator).getId_doctorxturno()
                //+ " - " + turnosDocActivos.get(iterator).getIdturno().getDia()
                //+ " - " + turnosDocActivos.get(iterator).getIdturno().getHora());
                if (!turnosDocActivos.isEmpty()) {
                    if (turnosDocActivos.get(iterator).getIdturno().getDia() == dia
                            && turnosDocActivos.get(iterator).getIdturno().getHora().compareTo(hora) == 0) {
                        String nombres = turnosDocActivos.get(iterator).getIddoctor().getIdpersona().getNombres();
                        String[] nombresSeparados = nombres.split(" ");
                        String apellidos = turnosDocActivos.get(iterator).getIddoctor().getIdpersona().getApellidos();
                        doctores.add(nombresSeparados[0] + " " + apellidos.charAt(0) + ".");
                        //nombres.substring(0, nombres.indexOf(" "))
                        ids_dxt.add(turnosDocActivos.get(iterator).getId_doctorxturno());
                        if (iterator < turnosDocActivos.size() - 1) {
                            iterator++;
                        }
                    } else {
                        doctores.add("-");
                        ids_dxt.add(0);
                    }
                } else {
                    doctores.add("-");
                    ids_dxt.add(0);
                }
            }
        } 
        
        //Se envía Lista (String) Turnos Ordenados
        model.addAttribute("TurnosOrdenados", turnosOrdenados);
        //Se envía Lista (String) Doctores con Turnos Ordenados
        model.addAttribute("DoctoresTurnos", doctores);
        //Se envía Lista IDs de DXT Ordenados
        model.addAttribute("Ids", ids_dxt);
        //Se envía Lista Doctores Disponibles (¡filtrar activos!)
        model.addAttribute("DoctoresDisponibles", doctoresDisponibles);
        
        return "/doctor/turnos";
    }
    
    @GetMapping("/quitar")
    public String quitarTurnoDoctor(Model model,
            @RequestParam("id_dxt") int id_dxt,
            RedirectAttributes attr){
        //Si existía un turno
        if(id_dxt != 0){
            //Se eliminar el turno y envía el mensaje de éxito
            iDoctorxturno.Eliminar(id_dxt);
            attr.addFlashAttribute("mensaje", "Turno eliminado correctamente")
                .addFlashAttribute("clase", "success")
                .addFlashAttribute("ico", "calendar-x-fill");
            return "redirect:/doctor";
        } else {
            //De lo contrario se advierte de la acción realizada
            attr.addFlashAttribute("mensaje", "La casilla seleccionada no cuenta con un turno")
                .addFlashAttribute("clase", "danger")
                .addFlashAttribute("ico", "exclamation-octagon-fill");
            return "redirect:/doctor";
        }
    }
    
    @PostMapping("/agregar")
    public String agregarTurnoDoctor(Model model,
            @RequestParam("id_turno") int id_turno,
            @RequestParam("id_doctor") int id_doctor,
            RedirectAttributes attr){
        //Si existía un turno
        if(id_doctor == 0){
            //Se advierte de la acción realizada
            attr.addFlashAttribute("mensaje", "Es necesario seleccionar un Doctor")
                .addFlashAttribute("clase", "danger")
                .addFlashAttribute("ico", "exclamation-octagon-fill");
            return "redirect:/doctor";
        } else {
            Doctor doctor = iDoctor.ListarId(id_doctor);
            Turno turno = iTurno.ListarId(id_turno);
            Doctorxturno doctorxturno = new Doctorxturno();
            doctorxturno.setIddoctor(doctor);
            doctorxturno.setIdturno(turno);
            iDoctorxturno.Guardar(doctorxturno);
            attr.addFlashAttribute("mensaje", "Turno asignado correctamente")
                .addFlashAttribute("clase", "success")
                .addFlashAttribute("ico", "calendar2-check-fill");
            return "redirect:/doctor";
        }
    }
    
}
