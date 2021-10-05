package com.cev.adtema1.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cev.adtema1.domain.Review;
import com.cev.adtema1.repository.ReviewRepository;

@RestController
public class ReviewController {
	
	ReviewRepository reviewRepository;
	
	public ReviewController(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}

	@GetMapping(path = "/reviews")
	List<Review> getReviews(){
		return reviewRepository.findAll(); 
	}
	
	@PostMapping(path = "/reviews", consumes={"application/json"})
	Review altaReview(@RequestBody Review review) {
		return reviewRepository.save(review);
	}
	
	
}
