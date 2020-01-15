package com.distribuida.conexion;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Conexion {

	protected Connection cx;

	public Connection conectar() {
		InputStream inputStream = Conexion.class.getClassLoader().getResourceAsStream("db.properties");
		Properties properties = new Properties();

		try {
			properties.load(inputStream);
			String driver = properties.getProperty("driver");
			String url = properties.getProperty("url");
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");
			Class.forName(driver);
			cx = DriverManager.getConnection(url, user, password);
			System.out.println("Se conectó a la base de datos");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("SE HA ABIERTO LA CONEXION");
		return cx;
	}

	public void desconectar() {
		if (cx == null) {
			return;
		}

		try {
			cx.close();
			System.out.println("SE HA CERRADO LA CONEXION");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
