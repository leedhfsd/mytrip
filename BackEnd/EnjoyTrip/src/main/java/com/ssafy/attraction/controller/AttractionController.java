package com.ssafy.attraction.controller;

import com.ssafy.attraction.model.AttractionContentDto;
import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.attraction.model.AttractionListDto;
import com.ssafy.attraction.model.AttractionLocationDto;
import com.ssafy.attraction.model.service.AttractionService;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("/attraction")
public class AttractionController{
    private final AttractionService attractionService;
    
    public AttractionController(AttractionService attractionService) {
    	super();
    	this.attractionService = attractionService;
    }
    
	@GetMapping
	public ResponseEntity<?> listAttraction(
			@RequestParam(required = true) Map<String, String> map) {
		log.info("listAttraction map - {}", map);
		try {
			AttractionListDto list = attractionService.listAttraction(map);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			
			return ResponseEntity.ok().headers(headers).body(list);
		}catch(Exception e) {
			return exceptionHandling(e);
		}
    }
	
	@GetMapping("/{contentId}")
	public ResponseEntity<?> attractionDetail(
			@PathVariable("contentId") int contentId,
			@RequestParam(required = true) Map<String, String> map) {
		log.info("attractionDetail contentId - {}", contentId);
		try {
			String userId = map.get("userId");
			AttractionDto attractionDto = attractionService.attractionDetail(contentId, userId);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			
			return ResponseEntity.ok().headers(headers).body(attractionDto);
		}catch(Exception e) {
			return exceptionHandling(e);
		}
    }
	
	@PostMapping("/like/{contentId}")
	public ResponseEntity<?> attractionLike(
			@PathVariable("contentId") int contentId,
			@RequestBody(required = true) Map<String, String> map) {
		log.info("attractionLike contentId - {}", contentId);
		log.info("attractionLike userId - {}", map);
		try {
			String userId = map.get("userId");
			if(userId == null || userId.isEmpty()) {
				log.error("userId is empty");
				return new ResponseEntity<Void>(HttpStatus.CREATED);
			}
			else {
				attractionService.attractionLike(contentId, userId);
				return new ResponseEntity<Void>(HttpStatus.CREATED);
			}
		}catch(Exception e) {
			return exceptionHandling(e);
		}
    }
	
	@DeleteMapping("/dislike/{contentId}/{userId}")
	public ResponseEntity<?> attractionDislike(
			@PathVariable("contentId") int contentId,
			@PathVariable("userId") String userId) {
		log.info("attractionLike contentId - {}", contentId);
		log.info("attractionLike userId - {}", userId);
		try {
			//String userId = map.get("userId");
			if(userId == null || userId.isEmpty()) {
				log.error("userId is empty");
				return new ResponseEntity<Void>(HttpStatus.CREATED);
			}
			else {
				attractionService.attractionDislike(contentId, userId);
				return new ResponseEntity<Void>(HttpStatus.CREATED);
			}
		}catch(Exception e) {
			return exceptionHandling(e);
		}
    }
	
	@GetMapping("/location")
	public ResponseEntity<?> attractionLocation() {
		log.info("attractionLocation");
		try {
			List<AttractionLocationDto> list = attractionService.attractionLocation();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			
			return ResponseEntity.ok().headers(headers).body(list);
		}catch(Exception e) {
			return exceptionHandling(e);
		}
    }
	
	@GetMapping("/contentType")
	public ResponseEntity<?> attractionContentType() {
		log.info("attractionLocation");
		try {
			List<AttractionContentDto> list = attractionService.attractionContent();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			
			return ResponseEntity.ok().headers(headers).body(list);
		}catch(Exception e) {
			return exceptionHandling(e);
		}
    }
	
	@GetMapping("/plan/{planId}")
	public ResponseEntity<?> attractionPlan(
			@PathVariable("planId") int planId) {
		log.info("attractionPlan planId - {}", planId);
		try {
			List<AttractionDto> list = attractionService.attractionPlan(planId);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			
			return ResponseEntity.ok().headers(headers).body(list);
		}catch(Exception e) {
			return exceptionHandling(e);
		}
    }
    
    private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}