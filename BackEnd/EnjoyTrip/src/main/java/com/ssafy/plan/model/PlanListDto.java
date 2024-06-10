package com.ssafy.plan.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PlanListDto {
	private int planId;
	private String userId;
	private String title;
	private String updateDate;
	private String fromDate;
	private String toDate;
	private String firstImage;
}
