package com.ssafy.plan.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.plan.model.PlanDetailDto;
import com.ssafy.plan.model.PlanListDto;
import com.ssafy.plan.model.UserPlanDto;
import com.ssafy.plan.model.mapper.PlanMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PlanServiceImpl implements PlanService {
	
	private final PlanMapper planMapper;
	
	public PlanServiceImpl(PlanMapper planMapper) {
		super();
		this.planMapper = planMapper;
	}

	@Override
	public void insertPlan(UserPlanDto userPlanDto) throws SQLException {
		log.debug("service insertPlan - {}", userPlanDto);
		planMapper.insertUserPlan(userPlanDto);
		
		List<PlanDetailDto> details = userPlanDto.getPlanDetails();
		if(details != null && !details.isEmpty()) {
			planMapper.insertPlanDetail(userPlanDto);
		}
	}

	@Override
	public List<PlanListDto> userPlanList(String userId) throws SQLException {
		log.debug("service userPlanList - {}", userId);
		
		return planMapper.userPlanList(userId);
	}
	
	@Override
	public List<PlanDetailDto> planDetailList(int planId) throws SQLException {
		log.debug("service userPlanList - {}", planId);
		
		return planMapper.planDetailList(planId);
	}

	@Override
	public PlanListDto userPlan(int planId) throws SQLException {
		log.debug("service userPlan - {}", planId);
		return planMapper.userPlan(planId);
	}

	@Override
	public void updatePlan(UserPlanDto userPlanDto) throws SQLException {
		log.debug("service updatePlan - {}", userPlanDto);
		
		planMapper.updateUserPlan(userPlanDto);
		planMapper.deletePlanDetail(userPlanDto.getPlanId());
		
		List<PlanDetailDto> details = userPlanDto.getPlanDetails();
		if(details != null && !details.isEmpty()) {
			planMapper.insertPlanDetail(userPlanDto);
		}
	}

}
