package com.dh.clinicaMVC.service.implementation;



import com.dh.clinicaMVC.dao.IDao;
import com.dh.clinicaMVC.dao.implementation.DomicilioDaoH2;
import com.dh.clinicaMVC.model.Domicilio;
import com.dh.clinicaMVC.service.IDomicilioService;

import java.util.List;

public class DomicilioService implements IDomicilioService {
    private IDao<Domicilio> iDao;

    public DomicilioService() {
        this.iDao = new DomicilioDaoH2();
    }

    @Override
    public Domicilio guardar(Domicilio domicilio) {
        return iDao.guardar(domicilio);
    }

    @Override
    public List<Domicilio> listarTodos() {
        return iDao.listarTodos();
    }
}
