package com.ssafy.rating.controller;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.rating.model.RatingDto;
import com.ssafy.rating.model.RatingListDto;
import com.ssafy.rating.model.service.RatingService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("/rating")
public class RatingController {
	private final RatingService ratingService;
	
	public RatingController(RatingService ratingService) {
		super();
		this.ratingService = ratingService;
	}
	
	@GetMapping("/{contentId}")
	public ResponseEntity<?> attractionRateInfo(
			@PathVariable("contentId") int contentId,
			@RequestParam(required = true) Map<String, String> map) {
		log.info("attractionRate contentId - {}", contentId);
		log.info("attractionRate map - {}", map);
		try {
			RatingListDto list = ratingService.attractionRating(contentId, map);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			
			return ResponseEntity.ok().headers(headers).body(list);
		}catch(Exception e) {
			return exceptionHandling(e);
		}
    }
	
	@GetMapping("/myPage")
	public ResponseEntity<?> myRateList(
			@RequestParam(required = true) Map<String, String> map) {
		log.info("myRateList map - {}", map);
		try {
			//RatingListDto list = ratingService.attractionRating(contentId);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			
			return ResponseEntity.ok().headers(headers).body(null);
		}catch(Exception e) {
			return exceptionHandling(e);
		}
    }
	
	@PostMapping
	public ResponseEntity<?> insertRate(
			@RequestBody(required = true) RatingDto ratingDto){
		log.info("insertRate ratingDto - {}", ratingDto);
		try {
			ratingService.insertRating(ratingDto);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}catch(Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PutMapping
	public ResponseEntity<?> updateRate(
			@RequestBody(required = true) RatingDto ratingDto){
		log.info("update ratingDto - {}", ratingDto);
		try {
			//ratingService.insertRating(ratingDto);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}catch(Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
