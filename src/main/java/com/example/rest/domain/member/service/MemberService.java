package com.example.rest.domain.member.service;

import com.example.rest.domain.member.entity.Member;
import com.example.rest.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void join(String username, String password) {
        Member member = Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();

        this.memberRepository.save(member);
    }

    public Member getMember(String username) {
        return this.memberRepository.findByUsername(username);
    }
}
