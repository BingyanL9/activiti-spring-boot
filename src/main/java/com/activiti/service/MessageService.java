package com.activiti.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.activiti.model.Message;
import com.activiti.repository.MessageRepository;

@Service
public class MessageService {

  @Autowired
  private MessageRepository messageRepository;
  
  public static final int PAZESIZE = 10;
  
  public List<Message> getAllMessage(){
    return messageRepository.findAll();
  }
  
  public void save(Message message) {
    messageRepository.save(message);
  }
  
  public Message getMessageById(Long id) {
   return  messageRepository.findOne(id);
  }
  
  public List<Message> getMessages(int offset, int limit) {
    return messageRepository.findMessage(new PageRequest(offset, limit));
  }
  
  public int getPageSize() {
    return (getAllMessage().size()  +  PAZESIZE  - 1) / PAZESIZE; 
  }
  
  public void delete(Long id) {
    messageRepository.delete(id);
  }
}
