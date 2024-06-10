package com.ssafy.rating.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.rating.model.RatingCountDto;
import com.ssafy.rating.model.RatingDto;

@Mapper
public interface RatingMapper {
	//List<RatingDto> attractionRating(int contentId, Map<String, Object> param) throws SQLException;
	List<RatingDto> attractionRating(Map<String, Object> param) throws SQLException;
	RatingCountDto countRating(int contentId) throws SQLException;
	int insertRating(RatingDto ratingDto) throws SQLException;
	int updateRating(RatingDto ratingDto) throws SQLException;
	void insertFiles(RatingDto ratingDto) throws SQLException;
}
