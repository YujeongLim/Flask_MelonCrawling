package com.uni.melon.authentication.model.dto;

import com.uni.melon.member.model.dto.MemberDTO;
import com.uni.melon.member.model.dto.MemberRoleDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

public class CustomUser extends User {

    private int memberNo;                                          // 회원번호
    private String memberName;                                     // 회원이름
    private List<MemberRoleDTO> memberRoleList;              // 회원 권한 목록

    public CustomUser(MemberDTO member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getMemberId(), member.getMemberPwd(), authorities);
        this.memberName = member.getMemberName();
        setDetails(member);
    }

    private void setDetails(MemberDTO member) {
        this.memberNo = member.getMemberNo();
        this.memberName = member.getMemberName();
        this.memberRoleList = member.getMemberRoleList();
    }

    // Getter and Setter methods for the new fields
    // Note: getUsername() and getPassword() methods are inherited from User class

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public List<MemberRoleDTO> getMemberRoleList() {
        return memberRoleList;
    }

    public void setMemberRoleList(List<MemberRoleDTO> memberRoleList) {
        this.memberRoleList = memberRoleList;
    }

    @Override
    public String toString() {
        return "CustomUser{" +
                "memberNo=" + memberNo +
                ", memberName='" + memberName + '\'' +
                ", memberRoleList=" + memberRoleList +
                '}';
    }
}
