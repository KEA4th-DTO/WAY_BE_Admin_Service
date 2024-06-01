package com.dto.way.admin.domain.service;

import com.dto.way.admin.domain.entity.Member;
import com.dto.way.admin.domain.repository.MemberRepository;
import com.dto.way.admin.global.JwtTokenProvider;
import com.dto.way.admin.web.dto.JwtToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.dto.way.admin.domain.entity.MemberAuth.*;
import static com.dto.way.admin.web.response.code.status.ErrorStatus.*;
import static com.dto.way.admin.web.response.code.status.SuccessStatus.*;


@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RedisService redisService;


    @Transactional(readOnly = true)
    public Member findMemberByEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        return member.orElse(null);
    }

    @Transactional(readOnly = true)
    public Member findMemberByNickname(String nickname) {
        Optional<Member> member = memberRepository.findByNickname(nickname);
        return member.orElse(null);
    }


}
