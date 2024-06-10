package com.ssafy.rating.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RatingFileDto {
	private int idx;
	private String saveFolder;
	private String originalFile;
	private String saveFile;
}
