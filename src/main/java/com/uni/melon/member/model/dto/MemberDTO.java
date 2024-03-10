package com.uni.melon.member.model.dto;

import lombok.Data;

import java.util.List;

public class MemberDTO {

    private int memberNo;											// 회원번호
    private String memberId;										// 회원아이디
    private String memberPwd;										// 회원비밀번호
    private String MemberName;									// 회원이름
    private List<MemberRoleDTO> memberRoleList;				// 회원별권한리스트 // 1대 다 관계


    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberPwd() {
        return memberPwd;
    }

    public void setMemberPwd(String memberPwd) {
        this.memberPwd = memberPwd;
    }

    public String getMemberName() {
        return MemberName;
    }

    public void setMemberName(String memberName) {
        MemberName = memberName;
    }

    public List<MemberRoleDTO> getMemberRoleList() {
        return memberRoleList;
    }

    public void setMemberRoleList(List<MemberRoleDTO> memberRoleList) {
        this.memberRoleList = memberRoleList;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "memberNo=" + memberNo +
                ", memberId='" + memberId + '\'' +
                ", memberPwd='" + memberPwd + '\'' +
                ", MemberName='" + MemberName + '\'' +
//                ", memberRoleList=" + memberRoleList +
                '}';
    }
}









