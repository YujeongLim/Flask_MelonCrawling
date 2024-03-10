package com.uni.melon.member.controller;

import com.uni.melon.member.model.dao.MemberDAO;
import com.uni.melon.member.model.dto.MemberDTO;
import com.uni.melon.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("member")
public class MemberController {

    @Autowired
    private MemberDAO memberDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }
}


