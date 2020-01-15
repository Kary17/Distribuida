package com.distribuida.modelo;

import java.util.Date;

public class Album {

	private int id;
	private int singerID;
	private String title;
	private Date releaseDate;

	public Album() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSingerID() {
		return singerID;
	}

	public void setSingerID(int singerID) {
		this.singerID = singerID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

}
