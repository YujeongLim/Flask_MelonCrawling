package com.uni.melon.member.model.dao;

import com.uni.melon.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {

    MemberDTO findMemberById(String memberId);
}