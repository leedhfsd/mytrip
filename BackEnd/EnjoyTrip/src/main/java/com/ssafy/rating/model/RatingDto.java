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
public class RatingDto {
	private int ratingId;
	private int contentId;
	private String userId;
	private int rating;
	private String title;
	private String description;
	private String visitDate;
	private String updateDate;
	private List<RatingFileDto> files;
}
