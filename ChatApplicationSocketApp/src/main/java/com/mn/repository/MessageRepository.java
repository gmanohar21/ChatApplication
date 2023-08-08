package com.mn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mn.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
