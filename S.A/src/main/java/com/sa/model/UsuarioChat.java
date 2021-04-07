package com.sa.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UsuarioChat implements Serializable{





    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id1")
    Usuario usuario1;


    @ManyToOne
    @MapsId("chatId")
    @JoinColumn(name = "chat_Id")
    Chat chat;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id2")
    Usuario usuario2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Usuario getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(Usuario usuario2) {
        this.usuario2 = usuario2;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
