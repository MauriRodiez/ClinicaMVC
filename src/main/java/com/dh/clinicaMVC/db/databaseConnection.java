package com.dh.clinicaMVC.db;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class databaseConnection {

    private static Connection dbConnect;
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:D:/CTD/DataBase/Clinica";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    private static final Logger LOGGER = Logger.getLogger(databaseConnection.class);

    private static final String SQL_DROP_CREATE_DOMICILIOS = "DROP TABLE IF EXISTS " +
            "DOMICILIOS; CREATE TABLE DOMICILIOS (ID INT AUTO_INCREMENT PRIMARY KEY," +
            " CALLE VARCHAR(100) NOT NULL," +
            " NUMERO INT NOT NULL," +
            " LOCALIDAD VARCHAR(100) NOT NULL," +
            " PROVINCIA VARCHAR(100) NOT NULL" +
            ")";

    private static final String SQL_DROP_CREATE_PACIENTES = "DROP TABLE IF EXISTS " +
            "PACIENTES; CREATE TABLE PACIENTES (ID INT AUTO_INCREMENT PRIMARY KEY," +
            " NOMBRE VARCHAR(100) NOT NULL," +
            " APELLIDO VARCHAR(100)  NOT NULL," +
            " DNI VARCHAR(100) NOT NULL ," +
            " FECHA_INGRESO DATE NOT NULL," +
            " DOMICILIO_ID INT NOT NULL)";

    private static final String SQL_DROP_CREATE_ODONTOLOGOS = "DROP TABLE IF EXISTS ODONTOLOGOS;" +
            "CREATE TABLE ODONTOLOGOS(" +
            "ID INT AUTO_INCREMENT NOT NULL," +
            "MATRICULA VARCHAR(50)," +
            "NOMBRE VARCHAR(100)," +
            "APELLIDO VARCHAR(150))";

    private static final String SQL_INSERT_ODONTOLOGO = "INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES ('B34','Mauricio','Rodriguez')";

    static {

        try {
            Class.forName(DB_DRIVER);
            try {

                dbConnect = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                LOGGER.info("Se conecto a la base de datos");
                Statement statement = dbConnect.createStatement();
                statement.execute(SQL_DROP_CREATE_DOMICILIOS);
                statement.execute(SQL_DROP_CREATE_PACIENTES);
                statement.execute(SQL_DROP_CREATE_ODONTOLOGOS);
                statement.execute(SQL_INSERT_ODONTOLOGO);

            } catch (SQLException e) {
                throw new RuntimeException("No es posible establecer una conexión a la base de datos", e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se pudo encontrar el driver JDBC para la base de datos", e);
        }
    }



    public static Connection getDbConnect() {
        return dbConnect;
    }
}
