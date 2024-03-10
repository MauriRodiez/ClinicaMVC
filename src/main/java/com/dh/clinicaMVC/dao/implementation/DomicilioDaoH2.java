package com.dh.clinicaMVC.dao.implementation;



import com.dh.clinicaMVC.db.databaseConnection;
import com.dh.clinicaMVC.dao.IDao;
import com.dh.clinicaMVC.model.Domicilio;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DomicilioDaoH2 implements IDao<Domicilio> {

    private static final Logger LOGGER = Logger.getLogger(DomicilioDaoH2.class);

    private static final String INSERT_DOMICILIO = "INSERT INTO DOMICILIOS (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES (?,?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM DOMICILIOS";
    private static final String SQL_SELECT_XID = "SELECT * FROM DOMICILIOS WHERE ID = ?";

    @Override
    public Domicilio guardar(Domicilio domicilio) {
        LOGGER.info("Estamos guardando un domicilio");
        Connection connection = null;

        try {

            connection = databaseConnection.getDbConnect();
            PreparedStatement psInsert = connection.prepareStatement(INSERT_DOMICILIO, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, domicilio.getCalle());
            psInsert.setInt(2, domicilio.getNumero());
            psInsert.setString(3, domicilio.getLocalidad());
            psInsert.setString(4, domicilio.getProvincia());

            psInsert.execute();

            ResultSet resultSet = psInsert.getGeneratedKeys();
            while (resultSet.next()) {
                domicilio.setId(resultSet.getInt(1));
            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LOGGER.info("Este es el id: " + domicilio.getId());
        return domicilio;
    }



    @Override
    public List<Domicilio> listarTodos() {
        LOGGER.info("Estamos consultando todos los domicilios");
        Connection connection = null;
        List<Domicilio> domicilioList = new ArrayList<>();
        Domicilio domicilio = null;

        try {
            connection = databaseConnection.getDbConnect();
            PreparedStatement psSelect = connection.prepareStatement(SELECT_ALL);
            ResultSet rs = psSelect.executeQuery();

            while (rs.next()) {
                //completamos el domicilio
                domicilio = new Domicilio(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getString(4), rs.getString(5));

                //lo guardamos en la lista
                domicilioList.add(domicilio);
            }

        } catch (Exception e) {
            LOGGER.error("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                //connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LOGGER.info("Esta es la lista que se está devolviendo??? " + domicilioList);
        return domicilioList;
    }

    @Override
    public Domicilio buscarPorId(Integer id) {
        LOGGER.info("Estamos buscando un odontologo por id");
        Connection connection = null;
        Domicilio domicilio = null;

        try {
            connection = databaseConnection.getDbConnect();

            PreparedStatement psBuscarXID = connection.prepareStatement(SQL_SELECT_XID);
            psBuscarXID.setInt(1, id);

            ResultSet rs = psBuscarXID.executeQuery();
            while (rs.next()){
                domicilio = new Domicilio();
                domicilio.setId(rs.getInt(1));
                domicilio.setCalle(rs.getString(2));
                domicilio.setNumero(rs.getInt(3));
                domicilio.setLocalidad(rs.getString(4));
                domicilio.setProvincia(rs.getString(5));
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

        return domicilio;
    }
}
