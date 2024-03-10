package com.dh.clinicaMVC.service.implementation;



import com.dh.clinicaMVC.dao.IDao;
import com.dh.clinicaMVC.dao.implementation.OdontologoDaoH2;
import com.dh.clinicaMVC.model.Odontologo;
import com.dh.clinicaMVC.service.IOdontologoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private IDao<Odontologo> iDao;

    public OdontologoService() {
        iDao = new OdontologoDaoH2();
    }
    @Override
    public Odontologo guardar(Odontologo odontologo){
        return iDao.guardar(odontologo);
    }

    @Override
    public List<Odontologo> listarTodos() {
        return iDao.listarTodos();
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        return iDao.buscarPorId(id);
    }


}
