package com.sa.repository;

import com.sa.model.Chat;
import com.sa.model.Usuario;
import com.sa.model.UsuarioChat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long>{

    public Chat findByUsuarioChats(UsuarioChat usuarioChat);
    public Chat findById(long id);

}
