package com.ssafy.plan.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.plan.model.PlanDetailDto;
import com.ssafy.plan.model.PlanListDto;
import com.ssafy.plan.model.UserPlanDto;

public interface PlanService {
	void insertPlan(UserPlanDto userPlanDto) throws SQLException;
	List<PlanListDto> userPlanList(String userId) throws SQLException;
	List<PlanDetailDto> planDetailList(int planId) throws SQLException;
	PlanListDto userPlan(int planId) throws SQLException;
	void updatePlan(UserPlanDto userPlanDto) throws SQLException;
}
