package com.example.feedback.service;

import com.example.feedback.model.Feedback;
import com.example.feedback.repository.FeedbackRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FeedbackApplicationTests {

	@Mock
	private FeedbackRepository feedbackRepository;

	@InjectMocks
	private FeedbackService feedbackService;

	//  Valid feedback test
	@Test
	void testSaveValidFeedback() {
		Feedback feedback = new Feedback();
		feedback.setName("John Doe");
		feedback.setEmail("john@example.com");
		feedback.setMessage("This is valid feedback.");

		when(feedbackRepository.save(any())).thenReturn(feedback);

		Feedback saved = feedbackService.saveFeedback(feedback);

		assertNotNull(saved);
		assertEquals("John Doe", saved.getName());
		assertEquals("john@example.com", saved.getEmail());
		assertEquals("This is valid feedback.", saved.getMessage());
	}

	// Invalid feedback: null fields
	@Test
	void testSaveFeedbackWithNullFields() {
		Feedback feedback = new Feedback();
		feedback.setName(null);
		feedback.setEmail(null);
		feedback.setMessage(null);

		when(feedbackRepository.save(any())).thenReturn(feedback);

		Feedback saved = feedbackService.saveFeedback(feedback);

		assertNull(saved.getName());
		assertNull(saved.getEmail());
		assertNull(saved.getMessage());
	}

	// Invalid feedback: too short message/name
	@Test
	void testSaveFeedbackWithShortFields() {
		Feedback feedback = new Feedback();
		feedback.setName("AB"); // Less than 3 chars
		feedback.setEmail("short@example.com");
		feedback.setMessage("short"); // < 10 chars

		when(feedbackRepository.save(any())).thenReturn(feedback);

		Feedback saved = feedbackService.saveFeedback(feedback);

		assertTrue(saved.getName().length() < 3);
		assertTrue(saved.getMessage().length() < 10);
	}

	// Invalid email format test
	@Test
	void testSaveFeedbackWithInvalidEmail() {
		Feedback feedback = new Feedback();
		feedback.setName("Jane");
		feedback.setEmail("invalid-email"); // No @
		feedback.setMessage("Message is valid length");

		when(feedbackRepository.save(any())).thenReturn(feedback);

		Feedback saved = feedbackService.saveFeedback(feedback);

		assertFalse(saved.getEmail().contains("@"));
	}

	// Save multiple feedback entries
	@Test
	void testSaveFeedbackListBatch() {
		Feedback f1 = new Feedback();
		f1.setName("User One");
		f1.setEmail("one@example.com");
		f1.setMessage("A valid message from user one.");

		Feedback f2 = new Feedback();
		f2.setName("User Two");
		f2.setEmail("two@example.com");
		f2.setMessage("A valid message from user two.");

		List<Feedback> feedbackList = Arrays.asList(f1, f2);

		when(feedbackRepository.saveAll(any())).thenReturn(feedbackList);

		List<Feedback> saved = feedbackService.saveFeedbackList(feedbackList);

		assertEquals(2, saved.size());
	}

	// Batch: passing empty list
	@Test
	void testSaveFeedbackListWithEmptyList() {
		List<Feedback> feedbackList = Collections.emptyList();

		when(feedbackRepository.saveAll(feedbackList)).thenReturn(feedbackList);

		List<Feedback> saved = feedbackService.saveFeedbackList(feedbackList);

		assertTrue(saved.isEmpty());
	}

	// Batch: null list
	@Test
	void testSaveFeedbackListWithNullList() {
		when(feedbackRepository.saveAll(null)).thenThrow(new IllegalArgumentException("Null input"));

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			feedbackService.saveFeedbackList(null);
		});

		assertEquals("Null input", exception.getMessage());
	}

	// Simulate repository failure
	@Test
	void testRepositoryThrowsException() {
		Feedback feedback = new Feedback();
		feedback.setName("Broken");
		feedback.setEmail("broken@example.com");
		feedback.setMessage("Trigger exception");

		when(feedbackRepository.save(any())).thenThrow(new RuntimeException("Database error"));

		Exception exception = assertThrows(RuntimeException.class, () -> {
			feedbackService.saveFeedback(feedback);
		});

		assertEquals("Database error", exception.getMessage());
	}
}
