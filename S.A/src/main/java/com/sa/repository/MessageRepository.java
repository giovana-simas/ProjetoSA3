package com.sa.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	
	

}
