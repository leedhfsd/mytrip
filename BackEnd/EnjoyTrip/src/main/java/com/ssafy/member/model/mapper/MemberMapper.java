package com.ssafy.member.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.member.model.MemberDto;

@Mapper
public interface MemberMapper {
	void join(MemberDto member) throws SQLException;
	MemberDto findUserById(String userId) throws SQLException;
	String findUserIdByEmail(Map<String, String> map) throws SQLException;
	void modify(MemberDto member) throws SQLException;
	void withdrawl(MemberDto member) throws SQLException;
	MemberDto login(MemberDto member) throws SQLException;
	void saveRefreshToken(Map<String, String> map) throws SQLException;
	Object getRefreshToken(String userid) throws SQLException;
	void deleteRefreshToken(Map<String, String> map) throws SQLException;
}
