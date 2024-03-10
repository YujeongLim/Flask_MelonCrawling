package com.uni.melon.service;

import com.uni.melon.authentication.AuthenticationService;
import com.uni.melon.member.model.dao.MemberDAO;
import com.uni.melon.member.model.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberDAO memberDAO;

    @Autowired
    public MemberService(MemberDAO memberDAO, AuthenticationService authenticationService, PasswordEncoder passwordEncoder) {
        this.memberDAO = memberDAO;
    }

    public MemberDTO login(String id, String password) {
        MemberDTO member = memberDAO.findMemberById(id);

        if(member != null && member.getMemberPwd().equals(password)) {
            return member;
        } else {
            return null;
        }
    }
}
