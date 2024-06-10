package com.ssafy.attraction.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.attraction.model.AttractionContentDto;
import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.attraction.model.AttractionListDto;
import com.ssafy.attraction.model.AttractionLocationDto;

public interface AttractionService {

	AttractionListDto listAttraction(Map<String, String> map) throws SQLException;
	AttractionDto attractionDetail(int contentId, String userId) throws SQLException;
	void attractionLike(int contentId, String userId) throws SQLException;
	void attractionDislike(int contentId, String userId) throws SQLException;
	
	List<AttractionLocationDto> attractionLocation() throws SQLException;
	List<AttractionContentDto> attractionContent() throws SQLException;
	List<AttractionDto> attractionPlan(int planId) throws SQLException;
}
