package com.uni.melon.main;

import com.uni.melon.authentication.model.dto.CustomUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @GetMapping(value={"/"})
    public String main(@AuthenticationPrincipal UserDetails userDetails, Model model){
        if(userDetails == null) {
            // 사용자가 로그인되어 있지 않음
            System.out.println("로그인안됨, userDetails : "+userDetails);
            return "member/login";
        } else {
            // 사용자가 로그인되어 있음
            CustomUser customUser = (CustomUser) userDetails;
            model.addAttribute("memberName", customUser.getMemberName());
            System.out.println("로그인완료, userDetails : "+userDetails);
            return "main/main";
        }
    }
}

