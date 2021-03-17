package com.sa.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	@NonNull
	private Date hrmsg;
	
	
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
	
	public Date getHrmsg() {
		return hrmsg;
	}
	public void setHrmsg(Date hrmsg) {
		this.hrmsg = hrmsg;
	}
	@Override
	public String toString() {
		return "Mensagem: " + mensagem + "De: " + fromLogin;
	}
	
	

}
