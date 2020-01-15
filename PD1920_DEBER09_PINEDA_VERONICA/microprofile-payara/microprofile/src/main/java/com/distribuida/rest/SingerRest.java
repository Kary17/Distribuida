package com.distribuida.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.distribuida.dto.SingerDTO;
import com.distribuida.modelo.Singer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/singer")
public class SingerRest {

	@Inject
	private SingerDTO singerService;

	@GET
	@Path("/getSingers")
	@Produces("application/json")
	public String getSingers() {

		List<Singer> singers = singerService.listar();
		String resp = "";
		if (!singers.isEmpty()) {

			ObjectMapper mapper = new ObjectMapper();
			for (Singer s : singers) {
				try {
					resp = resp + mapper.writeValueAsString(s);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}

		}
		return resp;
	}

	@GET
	@Path("/buscarSinger/{id}")
	@Produces("application/json")
	public String buscarSinger(@PathParam("id") int id) {

		Singer singer = singerService.listarPorId(id);

		String resp = "";
		if (singer != null) {
			ObjectMapper mapper = new ObjectMapper();

			try {
				resp = resp + mapper.writeValueAsString(singer);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

		}
		return resp;

	}

	@POST
	@Path("/crearSinger")
	@Produces("application/json")
	@Consumes("application/json")
	public Singer crearSinger(Singer singer) {
		singer = singerService.agregar(singer);
		return singer;

	}

	@PUT
	@Path("/actualizarSinger/{id}")
	@Consumes("application/json")
	public Singer actualizarSinger(@PathParam("id") int id, Singer singer) {

		singer.setId(id);
		singer = singerService.actualizar(singer);
		return singer;
	}

	@DELETE
	@Path("/borrarSinger/{id}")
	public void borrarSinger(@PathParam("id") int id) {

		singerService.eliminar(id);
	}
}
