package com.ssafy.plan.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.plan.model.PlanDetailDto;
import com.ssafy.plan.model.PlanListDto;
import com.ssafy.plan.model.UserPlanDto;
import com.ssafy.plan.model.service.PlanService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("/plan")
public class PlanController {
	private final PlanService planService;
	
	public PlanController(PlanService planService) {
		super();
		this.planService = planService;
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> userPlanList(
			@PathVariable("userId") String userId) {
		log.info("attractionRate contentId - {}", userId);
		try {
			List<PlanListDto> list = planService.userPlanList(userId); 
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			
			return ResponseEntity.ok().headers(headers).body(list);
		}catch(Exception e) {
			return exceptionHandling(e);
		}
    }
	
	@GetMapping("/user/{planId}")
	public ResponseEntity<?> userPlan(
			@PathVariable("planId") int planId) {
		log.info("userPlan planId - {}", planId);
		try {
			PlanListDto userPlan = planService.userPlan(planId); 
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			
			return ResponseEntity.ok().headers(headers).body(userPlan);
		}catch(Exception e) {
			return exceptionHandling(e);
		}
    }
	
	@PostMapping
	public ResponseEntity<?> insertPlan(
			@RequestBody(required = true) UserPlanDto userPlanDto){
		log.info("insertPlan userPlanDto - {}", userPlanDto);
		try {
			planService.insertPlan(userPlanDto);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}catch(Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PutMapping("/{planId}")
	public ResponseEntity<?> updatePlan(
			@PathVariable("planId") int planId,
			@RequestBody(required = true) UserPlanDto userPlanDto){
		log.info("updatePlan userPlanDto - {}", userPlanDto);
		try {
			userPlanDto.setPlanId(planId);
			//log.info("updatePlan userPlanDto - {}", userPlanDto);
			planService.updatePlan(userPlanDto);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}catch(Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/detail/{planId}")
	public ResponseEntity<?> planDetailList(
			@PathVariable("planId") int planId) {
		log.info("planDetailList planId - {}", planId);
		try {
			List<PlanDetailDto> list = planService.planDetailList(planId);
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
