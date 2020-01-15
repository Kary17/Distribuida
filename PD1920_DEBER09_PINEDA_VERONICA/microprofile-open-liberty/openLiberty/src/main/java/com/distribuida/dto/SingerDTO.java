package com.distribuida.dto;

import java.util.List;

import com.distribuida.modelo.Singer;

public interface SingerDTO {

	Singer agregar(Singer singer);

	boolean eliminar(int singerId);

	Singer actualizar(Singer singer);

	List<Singer> listar();

	Singer listarPorId(int id);
}
