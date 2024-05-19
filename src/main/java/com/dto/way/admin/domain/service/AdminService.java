package com.dto.way.admin.domain.service;

import com.dto.way.admin.domain.entity.Member;
import com.dto.way.admin.domain.entity.MemberStatus;
import com.dto.way.admin.domain.entity.ReportType;
import com.dto.way.admin.domain.repository.MemberRepository;
import com.dto.way.admin.domain.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.sound.midi.MetaMessage;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;



    private boolean changeMemeberStatus(String email, MemberStatus memberStatus){
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

    private List<Member> getMemberStatusList(MemberStatus memberStatus){
        return memberRepository.findByMemberStatus(memberStatus);
    }






}
