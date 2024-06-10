package com.ssafy.rating.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.rating.model.RatingCountDto;
import com.ssafy.rating.model.RatingDto;
import com.ssafy.rating.model.RatingFileDto;
import com.ssafy.rating.model.RatingListDto;
import com.ssafy.rating.model.mapper.RatingMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RatingServiceImpl implements RatingService {
	
	private final RatingMapper ratingMapper;
	
	public RatingServiceImpl(RatingMapper ratingMapper) {
		super();
		this.ratingMapper = ratingMapper;
	}

	@Override
	public RatingListDto attractionRating(int contentId, Map<String, String> map) throws SQLException {
		log.debug("service attractionRating - {}", contentId);
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("order", map.get("order") == null ? "" : map.get("order"));
		int rate = Integer.parseInt(map.get("rate") == null ? "0" : map.get("rate"));
		
		param.put("rate", rate);
		param.put("contentId", contentId);
		log.debug("before mapper - {}", param);
		List<RatingDto> ratings = ratingMapper.attractionRating(param);
		RatingCountDto ratingCnt = ratingMapper.countRating(contentId);
		
		RatingListDto list = new RatingListDto();
		list.setRatings(ratings);
		list.setRatingCnt(ratingCnt);
		
		return list;
	}

	@Override
	public void insertRating(RatingDto ratingDto) throws SQLException {
		log.debug("service insertRating - {}", ratingDto);
		ratingMapper.insertRating(ratingDto);
		List<RatingFileDto> files = ratingDto.getFiles();
		if(files != null && !files.isEmpty()) {
			
		}
	}

	@Override
	public void updateRating(RatingDto ratingDto) throws SQLException {
		log.debug("service insertRating - {}", ratingDto);
		ratingMapper.updateRating(ratingDto);
		
	}

}
