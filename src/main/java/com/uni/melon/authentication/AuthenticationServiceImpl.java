package com.uni.melon.authentication;

import com.uni.melon.authentication.model.dto.CustomUser;
import com.uni.melon.member.model.dao.MemberDAO;
import com.uni.melon.member.model.dto.MemberDTO;
import com.uni.melon.member.model.dto.MemberRoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final MemberDAO memberDAO;

    @Autowired
    public AuthenticationServiceImpl(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDTO member = memberDAO.findMemberById(username);
        if (member == null) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        List<MemberRoleDTO> memberRoleList = member.getMemberRoleList();
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (MemberRoleDTO memberRole : memberRoleList) {
            authorities.add(new SimpleGrantedAuthority(memberRole.getAuthority().getName()));
        }

        CustomUser customUser = new CustomUser(member, authorities);
        System.out.println("username : "+username);
        return customUser;
    }

    @Override
    public Map<String, List<String>> getPermitListMap() {
        Map<String, List<String>> permitListMap = new HashMap<>();
        List<String> adminPermitList = new ArrayList<>();
        List<String> memberPermitList = new ArrayList<>();

        adminPermitList.add("/admin/dashboard");
        memberPermitList.add("/order/regist");

        permitListMap.put("ROLE_ADMIN", adminPermitList);
        permitListMap.put("ROLE_MEMBER", memberPermitList);

        return permitListMap;
    }
}
