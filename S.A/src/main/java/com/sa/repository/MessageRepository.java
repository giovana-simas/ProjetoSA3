package com.sa.repository;


import com.sa.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.model.Message;

import javax.swing.*;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
	
	public List<Message> findByChat(Chat chat);

}
