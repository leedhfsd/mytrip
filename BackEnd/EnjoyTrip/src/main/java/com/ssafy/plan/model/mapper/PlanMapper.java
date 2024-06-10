package com.ssafy.plan.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.plan.model.PlanDetailDto;
import com.ssafy.plan.model.PlanListDto;
import com.ssafy.plan.model.UserPlanDto;

@Mapper
public interface PlanMapper {
	int insertUserPlan(UserPlanDto userPlanDto) throws SQLException;
	int insertPlanDetail(UserPlanDto userPlanDto) throws SQLException;
	List<PlanListDto> userPlanList(String userId) throws SQLException;
	List<PlanDetailDto> planDetailList(int planId) throws SQLException;
	PlanListDto userPlan(int planId) throws SQLException;
	void updateUserPlan(UserPlanDto userPlanDto) throws SQLException;
	void deletePlanDetail(int planId) throws SQLException;
}
