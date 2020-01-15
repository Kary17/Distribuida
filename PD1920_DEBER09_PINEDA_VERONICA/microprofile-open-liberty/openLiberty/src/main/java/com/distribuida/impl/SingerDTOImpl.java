package com.distribuida.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.distribuida.conexion.Conexion;
import com.distribuida.dto.SingerDTO;
import com.distribuida.modelo.Singer;

public class SingerDTOImpl extends Conexion implements SingerDTO {

	@Override
	public Singer agregar(Singer t) {
		try {

			this.conectar();
			String sql = "INSERT INTO singer(first_name, last_name, birth_date) VALUES (?,?,?)";
			PreparedStatement preparedStatement = this.cx.prepareStatement(sql);
			preparedStatement.setString(1, t.getFirstName());
			preparedStatement.setString(2, t.getLastName());
			preparedStatement.setDate(3, (Date) t.getBirthDate());
			preparedStatement.executeUpdate();
			preparedStatement.close();

			System.out.println("se ha registrado");
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			this.desconectar();
		}
		return t;
	}

	@Override
	public boolean eliminar(int id) {
		boolean res;
		try {
			this.conectar();
			String sql = "DELETE FROM singer WHERE id = ?";
			PreparedStatement preparedStatement = this.cx.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			res = true;
		} catch (Exception e) {
			e.printStackTrace();
			res = false;
		} finally {
			this.desconectar();
		}
		return res;

	}

	@Override
	public Singer actualizar(Singer t) {

		try {
			this.conectar();
			String sql = "UPDATE singer SET first_name = ?, last_name = ?, birth_date = ? WHERE id = ?";
			PreparedStatement preparedStatement = this.cx.prepareStatement(sql);
			preparedStatement.setString(1, t.getFirstName());
			preparedStatement.setString(2, t.getLastName());
			preparedStatement.setDate(3, (Date) t.getBirthDate());
			preparedStatement.setInt(4, t.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			this.desconectar();
		}
		return t;

	}

	@Override
	public List<Singer> listar() {
		List<Singer> cantantes = new ArrayList<>();

		try {
			this.conectar();
			Statement statement = this.cx.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM singer");
			while (resultSet.next()) {
				Singer cantante = new Singer();
				cantante.setId(resultSet.getInt("id"));
				cantante.setFirstName(resultSet.getString("first_name"));
				cantante.setLastName(resultSet.getString("last_name"));
				cantante.setBirthDate(resultSet.getDate("birth_date"));
				cantantes.add(cantante);
			}
			System.out.println("*** " + cantantes.size());
			resultSet.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.desconectar();
		}
		return cantantes;

	}

	@Override
	public Singer listarPorId(int id) {
		Singer cantante = new Singer();
		try {
			this.conectar();
			String query = "SELECT * FROM singer WHERE id =?";
			PreparedStatement preparedStatement = this.cx.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				cantante.setId(resultSet.getInt("id"));
				cantante.setFirstName(resultSet.getString("first_name"));
				cantante.setLastName(resultSet.getString("last_name"));
				cantante.setBirthDate(resultSet.getDate("birth_date"));
			}
			resultSet.close();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.desconectar();
		}
		return cantante;
	}

}
