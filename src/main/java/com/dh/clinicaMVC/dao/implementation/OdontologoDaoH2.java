package com.dh.clinicaMVC.dao.implementation;


import com.dh.clinicaMVC.db.databaseConnection;
import com.dh.clinicaMVC.dao.IDao;
import com.dh.clinicaMVC.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {

    private static final Logger LOGGER =  Logger.getLogger(OdontologoDaoH2.class);
    private static final String SQL_INSERT_ODONTOLOGO = "INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES (?,?,?)";
    private static final String SQL_SELECT = "SELECT * FROM ODONTOLOGOS";

    private static final String SQL_SELECT_XID = "SELECT * FROM ODONTOLOGOS WHERE ID = ?";


    public OdontologoDaoH2(){
        //Database.createTable();
    }
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        LOGGER.info("Estamos guardando un odontologo");
        Connection connection = null;

        try {
            connection = databaseConnection.getDbConnect();

            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT_ODONTOLOGO, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, odontologo.getMatricula());
            psInsert.setString(2, odontologo.getNombre());
            psInsert.setString(3, odontologo.getApellido());

            psInsert.execute();

            ResultSet rsInsert = psInsert.getGeneratedKeys();
            while(rsInsert.next()){
                odontologo.setId(rsInsert.getLong(1));
            }

        } catch (Exception e) {
            LOGGER.error("Se produjo un error y no es posible cargar los datos " + e.getMessage());
        } finally {
            try {
                connection.close();
                LOGGER.info("Se cerro la conexion!");
            } catch (SQLException e) {
                LOGGER.error("No se pudo cerrar la conexion " + e.getMessage());
            }
        }

        LOGGER.info("Se ha creado un registro para odontologo " + odontologo);

        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        LOGGER.info("Estamos consultando todos los odontologos");
        Connection connection = null;
        List<Odontologo> listadoOdontologos = new ArrayList<>();
        Odontologo odontologo = null;

        try {
            connection = databaseConnection.getDbConnect();

            PreparedStatement psListar = connection.prepareStatement(SQL_SELECT);

            ResultSet rsListar = psListar.executeQuery();

            while (rsListar.next()){
                odontologo = new Odontologo();
                odontologo.setId(rsListar.getLong(1));
                odontologo.setMatricula(rsListar.getString(2));
                odontologo.setNombre(rsListar.getString(3));
                odontologo.setApellido(rsListar.getString(4));
                listadoOdontologos.add(odontologo);
            }

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Se produjo un error y no es posible cargar los datos " + e.getMessage());
        } finally {
            try {
                connection.close();
                LOGGER.info("Se cerro la conexion!");
            } catch (SQLException e) {
                LOGGER.error("No se pudo cerrar la conexion " + e.getMessage());
            }
        }

        LOGGER.info("Se listan todos los odontologos" + listadoOdontologos);

        return listadoOdontologos;
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        LOGGER.info("Estamos buscando un odontologo por id");
        Connection connection = null;
        Odontologo odontologo = null;

        try {
            connection = databaseConnection.getDbConnect();

            PreparedStatement psBuscarXID = connection.prepareStatement(SQL_SELECT_XID);
            psBuscarXID.setInt(1, id);

            ResultSet rs = psBuscarXID.executeQuery();
            while (rs.next()){
                odontologo = new Odontologo();
                odontologo.setId(rs.getLong(1));
                odontologo.setMatricula(rs.getString(2));
                odontologo.setNombre(rs.getString(3));
                odontologo.setApellido(rs.getString(4));
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return odontologo;
    }
}
