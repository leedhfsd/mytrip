package com.ssafy.attraction.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.attraction.model.AttractionContentDto;
import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.attraction.model.AttractionListDto;
import com.ssafy.attraction.model.AttractionLocationDto;
import com.ssafy.attraction.model.mapper.AttractionMapper;
import com.ssafy.util.AlgorithmService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AttractionServiceImpl implements AttractionService {
	
	private final AttractionMapper attractionMapper;
//	private final AlgorithmService algoService;
	
	public AttractionServiceImpl(AttractionMapper attractionMapper, AlgorithmService algoService) {
		super();
		this.attractionMapper = attractionMapper;
//		this.algoService = algoService;
	}

//	@Override
//	public List<AttractionDtoBefore> getFilteredList(List<String> sido, List<String> typeId, double user_lat, double user_long) throws SQLException {
//		log.debug("service getFilteredList call");
//		
//		if(user_lat == 0 && user_long == 0) return attractionMapper.fetchFilteredList(sido, typeId);
//		return algoService.attractionSortList(algoService.getDistance(attractionMapper.fetchFilteredList(sido, typeId), user_lat, user_long));
//		
//	}


	@Override
	public AttractionListDto listAttraction(Map<String, String> map) throws SQLException {
		log.debug("service listAttraction call");
		//pgno spp word sort sido gugun contentType
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		param.put("sort", map.get("sort") == null ? "" : map.get("sort"));
		param.put("userId", map.get("userId") == null ? "" : map.get("userId"));
		int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
		int sizePerPage = Integer.parseInt(map.get("spp") == null ? "20" : map.get("spp"));
		int start = currentPage * sizePerPage - sizePerPage;
		
		int sido = Integer.parseInt(map.get("sido") == null ? "0" : map.get("sido"));
		int gugun = Integer.parseInt(map.get("gugun") == null ? "0" : map.get("gugun"));
		int contentTypeId = Integer.parseInt(map.get("contentTypeId") == null ? "0" : map.get("contentTypeId"));
		
		param.put("start", start);
		param.put("listsize", sizePerPage);
		param.put("sido", sido);
		param.put("gugun", gugun);
		param.put("contentTypeId", contentTypeId);
		
		List<AttractionDto> list = attractionMapper.listAttraction(param);
		int totalAttractionCount = attractionMapper.getTotalAttraction(param);
		int totalPageCount = (totalAttractionCount - 1) / sizePerPage + 1;

		AttractionListDto attractionListDto = new AttractionListDto();
		attractionListDto.setAttractions(list);
		attractionListDto.setCurrentPage(currentPage);
		attractionListDto.setTotalPageCount(totalPageCount);
		attractionListDto.setTotalAttractionCount(totalAttractionCount);
		
		return attractionListDto;
	}


	@Override
	public AttractionDto attractionDetail(int contentId, String userId) throws SQLException {
		log.debug("service attractionDetail call");
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("contentId", contentId);
		param.put("userId", userId);
		
		AttractionDto attractionDto = attractionMapper.attractionDetail(param);
		return attractionDto;
	}

	@Override
	public void attractionLike(int contentId, String userId) throws SQLException {
		log.debug("service attractionLike call");
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("contentId", contentId);
		param.put("userId", userId);
		
		attractionMapper.attractionLike(param);
	}

	@Override
	public void attractionDislike(int contentId, String userId) throws SQLException {
		log.debug("service attractionDislike call");
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("contentId", contentId);
		param.put("userId", userId);
		
		attractionMapper.attractionDislike(param);
	}

	@Override
	public List<AttractionLocationDto> attractionLocation() throws SQLException {
		log.debug("service attractionLocation call");
		
		return attractionMapper.attractionLocation();
	}

	@Override
	public List<AttractionContentDto> attractionContent() throws SQLException {
		log.debug("service attractionContent call");
		
		return attractionMapper.attractionContent();
	}

	@Override
	public List<AttractionDto> attractionPlan(int planId) throws SQLException {
		log.debug("service attractionPlan call - {}", planId);
		return attractionMapper.attractionPlan(planId);
	}


}
