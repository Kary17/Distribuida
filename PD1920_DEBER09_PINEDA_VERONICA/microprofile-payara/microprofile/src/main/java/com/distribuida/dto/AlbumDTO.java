package com.distribuida.dto;

import java.util.List;

import com.distribuida.modelo.Album;

public interface AlbumDTO {

	Album agregar(Album album);

	boolean eliminar(int albumId);

	Album actualizar(Album album);

	List<Album> listar();

	Album listarPorId(int id);
}
