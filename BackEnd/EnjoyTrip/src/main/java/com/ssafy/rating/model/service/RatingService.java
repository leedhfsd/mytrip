package com.ssafy.rating.model.service;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.rating.model.RatingDto;
import com.ssafy.rating.model.RatingListDto;

public interface RatingService {
	RatingListDto attractionRating(int contentId, Map<String, String> map) throws SQLException;
	void insertRating(RatingDto ratingDto) throws SQLException;
	void updateRating(RatingDto ratingDto) throws SQLException;
}
