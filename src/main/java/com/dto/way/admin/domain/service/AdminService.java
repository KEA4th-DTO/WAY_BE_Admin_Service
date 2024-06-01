package com.dto.way.admin.domain.service;

import com.dto.way.admin.domain.entity.*;
import com.dto.way.admin.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

//    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;



    public boolean changeMemberStatus(String email, MemberStatus memberStatus){
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        if (optionalMember.isEmpty()) {
            return false; // Member not found
        }
        Member member = optionalMember.get();

        //set
        member.setMemberStatus(memberStatus);

        //update
        memberRepository.save(member);

        return true;
    }

    public boolean changeMemberAuth(String nickname, MemberAuth memberAuth){
        Optional<Member> optionalMember = memberRepository.findByEmail(nickname);
        if (optionalMember.isEmpty()) {
            return false; // Member not found
        }
        Member member = optionalMember.get();

        //set
        member.setMemberAuth(memberAuth);
        log.info(memberAuth.toString()+ " in service");

        //update
        memberRepository.save(member);

        return true;
    }


    public Optional<Member> getMemberStatusList(MemberStatus memberStatus){
        return memberRepository.findByMemberStatus(memberStatus);
    }

    public String getStatusWithNickname(String nickName){

        Optional<Member> optionalMember= memberRepository.findByNickname(nickName);
        if (optionalMember.isEmpty()) {
            return "UnkownUser"; // Member not found
        }else{
            Member member = optionalMember.get();
            return member.getMemberStatus().toString();
        }
    }




}
