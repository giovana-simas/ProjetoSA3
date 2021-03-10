package com.sa.model;

public class MessageModel {
	
	
	private String mensagem;
	private String fromLogin;
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
	@Override
	public String toString() {
		return "Mensagem: " + mensagem + "De: " + fromLogin;
	}
	
	

}
