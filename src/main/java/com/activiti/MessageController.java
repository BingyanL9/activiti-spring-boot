package com.activiti;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.activiti.model.Message;
import com.activiti.service.MessageService;
import com.activiti.service.UserService;

@Controller
@ComponentScan("com.activiti.service")
public class MessageController {

private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

  @Autowired
  private MessageService messageService;
  
  @RequestMapping(value = "/messages", method = RequestMethod.POST)
  public String createMessage( Message message) {
    logger.debug("Start create a message!");
    Message newMessage = message;
    messageService.save(newMessage);
    return "redirect:/issue";
  }
  
  @RequestMapping(value = "/messages/{messageId}", method = RequestMethod.GET)
  public String getMessage(Map<String, Object> model, 
      @PathVariable("messageId") Long messageId){
    logger.debug("Start get a message by id: " + messageId);
    Message newMessage = messageService.getMessageById(messageId);
    model.put("message", newMessage);
    return "fragments/messageInfo :: message_content";
  }
  
  @RequestMapping(value = "/messages/page/{page}/{position}", method = RequestMethod.GET)
  public String getMessage(Map<String, Object> model, 
      @PathVariable("page") int page, @PathVariable("position") String position){
    logger.debug("Start get a message list by page no : " + page);
    List<Message> messages =  messageService.getMessages(page, messageService.PAZESIZE);
    model.put("messages", messages);
    if(page == 0) {
      model.put("pagefirst", "true");
    }
    if(messageService.getPageSize()-1 == page) {
      model.put("pagelast", "true");
    }
    
    if(position.equals("home")) {
      return "fragments/messageInfo :: message_list";
    }else {
      return "fragments/messageInfo :: message_list_delete";
    }
  }
  
  @RequestMapping(value = "/messages/{messageId}", method = RequestMethod.DELETE)
  public String deleteMessage(Map<String, Object> model, 
      @PathVariable("messageId") Long messageId){
    logger.debug("Start delete a message by id: " + messageId);
    messageService.delete(messageId);
    List<Message> messages =  messageService.getMessages(0, messageService.PAZESIZE);
    model.put("messages", messages);
    model.put("pagefirst", "true");
    if(messageService.getPageSize() == 1 || messageService.getPageSize() == 0) {
      model.put("pagelast", "true");
    }
    return "fragments/messageInfo :: message_list_delete";
  }
}
