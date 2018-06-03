package com.activiti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activiti.model.Feedback;
import com.activiti.repository.FeedbackRepository;

@Service
public class FeedbackService {

  @Autowired
  private FeedbackRepository feedbackRepository;
  
  public List<Feedback> getAllFeedback(){
    return feedbackRepository.findAll();
  }
  
  public void deleteFeedbacks(List<Feedback> feedbacks) {
    feedbackRepository.delete(feedbacks);
  }
  
  public Feedback findById(Long id) {
    return feedbackRepository.findOne(id);
  }
  
  public void saveFeedback(Feedback fd) {
    feedbackRepository.save(fd);
  }
}
