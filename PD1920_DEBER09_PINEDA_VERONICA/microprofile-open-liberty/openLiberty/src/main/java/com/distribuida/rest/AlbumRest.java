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

import com.distribuida.dto.AlbumDTO;
import com.distribuida.modelo.Album;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/album")
public class AlbumRest {

	@Inject
	private AlbumDTO albumService;

	@GET
	@Path("/getAlbums")
	@Produces("application/json")
	public String getAlbums() {

		List<Album> albums = albumService.listar();
		String resp = "";
		if (!albums.isEmpty()) {

			ObjectMapper mapper = new ObjectMapper();
			for (Album a : albums) {
				try {
					resp = resp + mapper.writeValueAsString(a);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}

		}
		return resp;
	}

	@GET
	@Path("/buscarAlbum/{id}")
	@Produces("application/json")
	public String buscarAlbum(@PathParam("id") int id) {

		Album album = albumService.listarPorId(id);

		String resp = "";
		if (album != null) {
			ObjectMapper mapper = new ObjectMapper();

			try {
				resp = resp + mapper.writeValueAsString(album);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

		}
		return resp;

	}

	@POST
	@Path("/crearAlbum")
	@Produces("application/json")
	@Consumes("application/json")
	public Album crearAlbum(Album album) {

		album = albumService.agregar(album);
		return album;
	}

	@PUT
	@Path("/actualizarAlbum/{id}")
	@Consumes("application/json")
	public Album actualizarAlbum(@PathParam("id") int id, Album album) {
		album.setId(id);
		album = albumService.actualizar(album);
		return album;
	}

	@DELETE
	@Path("/borrarAlbum/{id}")
	public void borrarAlbum(@PathParam("id") int id) {

		albumService.eliminar(id);
	}
}
