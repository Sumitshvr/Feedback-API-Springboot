package com.example.feedback.controller;

import com.example.feedback.model.Feedback;
import com.example.feedback.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    // Accept SINGLE feedback
    @PostMapping
    public ResponseEntity<Feedback> submitFeedback(@Valid @RequestBody Feedback feedback) {
        Feedback saved = feedbackService.saveFeedback(feedback);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    //  Accept MULTIPLE feedbacks
    @PostMapping("/batch")
    public ResponseEntity<List<Feedback>> submitMultipleFeedback(
            @Valid @RequestBody List<Feedback> feedbackList) {
        List<Feedback> saved = feedbackService.saveFeedbackList(feedbackList);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    //  Get all feedbacks
    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedback() {
        return ResponseEntity.ok(feedbackService.getAllFeedback());
    }

    // For testing: delete all feedbacks
    @DeleteMapping
    public ResponseEntity<Void> deleteAllFeedback() {
        feedbackService.deleteAllFeedback();
        return ResponseEntity.noContent().build();
    }
}
