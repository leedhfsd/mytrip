package com.ssafy.member.model.service;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	private MemberMapper memberMapper;
	
	public MemberServiceImpl(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	@Override
	public void join(MemberDto member) throws Exception {
		memberMapper.join(member);
	}

	@Override
	public MemberDto findUserById(String userId) throws Exception {
		return memberMapper.findUserById(userId);
	}
	
	@Override
	public String findUserIdByEmail(String emailId, String emailDomain) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("emailId", emailId);
		map.put("emailDomain", emailDomain);
		return memberMapper.findUserIdByEmail(map);
	}

	@Override
	public void modify(MemberDto member) throws Exception {
		memberMapper.modify(member);
	}

	@Override
	public void withdrawl(MemberDto member) throws Exception {
		memberMapper.withdrawl(member);
	}

	@Override
	public MemberDto login(MemberDto member) throws Exception {
		return memberMapper.login(member);
	}

	@Override
	public void saveRefreshToken(String userId, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", refreshToken);
		memberMapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userId) throws Exception {
		return memberMapper.getRefreshToken(userId);
	}

	@Override
	public void deleteRefreshToken(String userId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", null);
		memberMapper.deleteRefreshToken(map);
	}

}
