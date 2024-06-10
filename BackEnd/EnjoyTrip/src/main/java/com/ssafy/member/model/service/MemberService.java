package com.ssafy.member.model.service;

import com.ssafy.member.model.MemberDto;

public interface MemberService {
	void join(MemberDto member) throws Exception;
	MemberDto findUserById(String userId) throws Exception;
	String findUserIdByEmail(String emailId, String emailDomain) throws Exception;
	void modify(MemberDto member) throws Exception;
	void withdrawl(MemberDto member) throws Exception;
	MemberDto login(MemberDto member) throws Exception;
	void saveRefreshToken(String userId, String refreshToken) throws Exception;
	Object getRefreshToken(String userId) throws Exception;
	void deleteRefreshToken(String userId) throws Exception;
}
