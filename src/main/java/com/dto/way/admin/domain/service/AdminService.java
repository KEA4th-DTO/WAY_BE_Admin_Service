package com.dto.way.admin.domain.service;

import com.dto.way.admin.domain.entity.*;
import com.dto.way.admin.domain.repository.MemberRepository;
import com.dto.way.admin.domain.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Optional<Member> optionalMember = memberRepository.findByNickname(nickname);
        if (optionalMember.isEmpty()) {
            return false; // Member not found
        }
        Member member = optionalMember.get();

        //set
        member.setMemberAuth(memberAuth);

        //update
        memberRepository.save(member);

        return true;
    }

//    public Boolean changeReportStatus(Long report_id, ReportStatus reportStatus){
//        //PROCESS, DONE -> report의 상태를 변경
//
//        Optional<Report> optionalReport = reportRepository.findById(report_id);
//        if (optionalReport.isEmpty()) {
//            return false; // Member not found
//        }
//        Report report = optionalReport.get();
//
//        //set
//        report.setStatus(reportStatus);
//
//        //update
//        reportRepository.save(report);
//
//        return true;
//    }

    public Optional<Member> getMemberStatusList(MemberStatus memberStatus){
        return memberRepository.findByMemberStatus(memberStatus);
    }

//    public Optional<Report> getReportWithStatus(ReportStatus reportStatus){
//        return reportRepository.findByStatus(reportStatus);
//    }

//    public Optional<Report> getReportWithNickName(String nickName){
//        Optional<Member> optionalMember = memberRepository.findByNickname(nickName);
//        if (optionalMember.isEmpty()) {
//            Report report = new Report();
//
//            return Optional.of(report); // Member not found
//        }
//
//        Member member = optionalMember.get();
//
//        return reportRepository.findById(member.getId());
//    }

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
