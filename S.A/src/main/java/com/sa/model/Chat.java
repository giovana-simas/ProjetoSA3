package com.sa.model;

import java.util.List;

import javax.persistence.*;


@Entity
public class Chat {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	@OneToMany(targetEntity = Message.class, mappedBy = "chat")
	public List<Message> message;

	@OneToMany(mappedBy = "chat")
	public List<UsuarioChat> usuarioChats;


	public List<UsuarioChat> getUsuarioChats() {
		return usuarioChats;
	}

	public void setUsuarioChats(List<UsuarioChat> usuarioChats) {
		this.usuarioChats = usuarioChats;
	}

	public void addUsuarioChats(UsuarioChat usuarioChats) {
		this.usuarioChats.add(usuarioChats) ;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Message> getMessage() {
		return message;
	}

	public void setMessage(List<Message> message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Chat{" +
				"id=" + id +
				", message=" + message + "}"
				;
	}
}
