package com.dh.clinicaMVC.controller;

import com.dh.clinicaMVC.model.Odontologo;
import com.dh.clinicaMVC.service.IOdontologoService;
import com.dh.clinicaMVC.service.implementation.OdontologoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/odontologo")
public class OdontologoController {
    private IOdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService){
        this.odontologoService = odontologoService;
    }
    @GetMapping("/id")
    public String buscarOdontologoPorId(Model model, @RequestParam Integer id){
        Odontologo odontologo = odontologoService.buscarPorId(id);
        model.addAttribute("nombre",odontologo.getNombre());
        model.addAttribute("apellido", odontologo.getApellido());
        return "index";
    }
}
