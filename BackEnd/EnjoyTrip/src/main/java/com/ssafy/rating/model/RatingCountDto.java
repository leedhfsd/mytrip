package com.ssafy.rating.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RatingCountDto {
	private int rateCnt;
	private int rate1;
	private int rate2;
	private int rate3;
	private int rate4;
	private int rate5;
	private double avgRate;
	
	
	
}
