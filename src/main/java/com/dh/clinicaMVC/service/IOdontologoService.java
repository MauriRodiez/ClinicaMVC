package com.dh.clinicaMVC.service;

import com.dh.clinicaMVC.model.Domicilio;
import com.dh.clinicaMVC.model.Odontologo;

import java.util.List;

public interface IOdontologoService {

    Odontologo guardar (Odontologo odontologo);

    List<Odontologo> listarTodos();

    Odontologo buscarPorId(Integer id);
}
