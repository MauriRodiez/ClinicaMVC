package com.dh.clinicaMVC.service.implementation;



import com.dh.clinicaMVC.dao.IDao;
import com.dh.clinicaMVC.model.Paciente;

import java.util.List;

public class PacienteService {

    private IDao<Paciente> pacienteIDao;

    public PacienteService(){

    }

    public void setPacienteIDao(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    public Paciente guardar(Paciente paciente){
        return pacienteIDao.guardar(paciente);
    }

    public List<Paciente> listartodos(){
        return pacienteIDao.listarTodos();
    }

}
