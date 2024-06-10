package com.ssafy.attraction.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AttractionListDto {
	private List<AttractionDto> attractions;
	private int currentPage;
	private int totalPageCount;
	private int totalAttractionCount;
}
