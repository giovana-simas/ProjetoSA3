package com.sa.model;


import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

@Entity
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NonNull
	private String mensagem;
	
	@NonNull
	private String fromLogin;


	private Timestamp hrmsg;


	@ManyToOne
	@JoinColumn(name = "chat_id", referencedColumnName = "id")
	@JsonIgnore
	public Chat chat;

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getFromLogin() {
		return fromLogin;
	}
	public void setFromLogin(String fromLogin) {
		this.fromLogin = fromLogin;
	}
	
	public Timestamp getHrmsg() {
		return hrmsg;
	}
	public void setHrmsg(Timestamp hrmsg) {
		this.hrmsg = hrmsg;
	}

	@Override
	public String toString() {
		return "Message{" +
				"id=" + id +
				", mensagem='" + mensagem + '\'' +
				", fromLogin='" + fromLogin + '\'' +
				", hrmsg=" + hrmsg +
				//", chat=" + chat +
				'}';
	}
}
