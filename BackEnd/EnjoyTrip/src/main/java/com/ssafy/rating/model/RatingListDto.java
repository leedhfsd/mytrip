package com.ssafy.rating.model;

import java.util.List;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RatingListDto {
	private List<RatingDto> ratings;
	private RatingCountDto ratingCnt;
}
