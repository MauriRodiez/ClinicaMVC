package com.dh.clinicaMVC.service;

import com.dh.clinicaMVC.model.Domicilio;

import java.util.List;

public interface IDomicilioService {

    public Domicilio guardar (Domicilio domicilio);

    public List<Domicilio> listarTodos();


}
