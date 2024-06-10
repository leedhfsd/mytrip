package com.ssafy.plan.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PlanDetailDto {
	private int planId;
	private int planDay;
	private int planIndex;
	private int contentId;
	private String startTime;
	private String endTime;
	private String memo;
	private String firstImage;
}
