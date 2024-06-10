package com.ssafy.plan.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserPlanDto {
	private int planId;
	private String userId;
	private String title;
	private String updateDate;
	private String fromDate;
	private String toDate;
	private List<PlanDetailDto> planDetails;
}
