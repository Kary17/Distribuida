package com.distribuida.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.distribuida.conexion.Conexion;
import com.distribuida.dto.AlbumDTO;
import com.distribuida.modelo.Album;

public class AlbumDTOImpl extends Conexion implements AlbumDTO {

	@Override
	public Album agregar(Album t) {

		try {
			this.conectar();
			String sql = "INSERT INTO album(singer_id, title, release_date) VALUES (?,?,?)";
			PreparedStatement preparedStatement = this.cx.prepareStatement(sql);
			preparedStatement.setInt(1, t.getSingerID());
			preparedStatement.setString(2, t.getTitle());
			preparedStatement.setDate(3, (Date) t.getReleaseDate());
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
	public boolean eliminar(int id) {
		boolean res;
		try {
			this.conectar();
			String sql = "DELETE FROM album WHERE id = ?";
			PreparedStatement preparedStatement = this.cx.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			res = true;
		} catch (Exception e) {
			res = false;
			e.printStackTrace();
		} finally {
			this.desconectar();
		}
		return res;
	}

	@Override
	public Album actualizar(Album t) {
		try {
			this.conectar();
			String sql = "UPDATE album SET singer_id = ?, title = ?, release_date = ? WHERE id = ?";
			PreparedStatement preparedStatement = this.cx.prepareStatement(sql);
			preparedStatement.setInt(1, t.getSingerID());
			preparedStatement.setString(2, t.getTitle());
			preparedStatement.setDate(3, (Date) t.getReleaseDate());
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
	public List<Album> listar() {
		List<Album> albumes = new ArrayList<>();

		try {
			this.conectar();
			Statement statement = this.cx.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM album");
			while (resultSet.next()) {
				Album album = new Album();
				album.setId(resultSet.getInt("id"));
				album.setSingerID(resultSet.getInt("singer_id"));
				album.setTitle(resultSet.getString("title"));
				album.setReleaseDate(resultSet.getDate("release_date"));
				albumes.add(album);
			}
			resultSet.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.desconectar();
		}
		return albumes;
	}

	@Override
	public Album listarPorId(int id) {
		Album album = new Album();
		try {
			this.conectar();
			String query = "SELECT * FROM album WHERE id =?";
			PreparedStatement preparedStatement = this.cx.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				album.setId(resultSet.getInt("id"));
				album.setTitle(resultSet.getString("title"));
				album.setReleaseDate(resultSet.getDate("release_date"));
			}
			resultSet.close();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.desconectar();
		}
		return album;
	}

}
