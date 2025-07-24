package com.example.feedback.service;

import com.example.feedback.model.Feedback;
import com.example.feedback.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public List<Feedback> saveFeedbackList(List<Feedback> feedbackList) {
        return feedbackRepository.saveAll(feedbackList);
    }

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public void deleteAllFeedback() {
        feedbackRepository.deleteAll();
    }
}
