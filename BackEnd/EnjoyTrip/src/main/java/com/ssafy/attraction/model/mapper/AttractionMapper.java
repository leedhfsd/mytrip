package com.ssafy.attraction.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.attraction.model.AttractionContentDto;
import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.attraction.model.AttractionLocationDto;

@Mapper
public interface AttractionMapper {
	List<AttractionDto> listAttraction(Map<String, Object> param) throws SQLException;
	int getTotalAttraction(Map<String, Object> param) throws SQLException;
	AttractionDto attractionDetail(Map<String, Object> param) throws SQLException;
	void attractionLike(Map<String, Object> param) throws SQLException;
	void attractionDislike(Map<String, Object> param) throws SQLException;
	
	List<AttractionLocationDto> attractionLocation() throws SQLException;
	List<AttractionContentDto> attractionContent() throws SQLException;
	List<AttractionDto> attractionPlan(int planId) throws SQLException;
}
