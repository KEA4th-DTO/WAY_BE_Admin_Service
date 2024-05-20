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

    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;



    public boolean changeMemeberStatus(String email, MemberStatus memberStatus){
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

    public Boolean changeReportStatus(Long report_id, ReportStatus reportStatus){
        //PROCESS, DONE -> report의 상태를 변경

        Optional<Report> optionalReport = reportRepository.findByReportId(report_id);
        if (optionalReport.isEmpty()) {
            return false; // Member not found
        }
        Report report = optionalReport.get();

        //set
        report.setStatus(reportStatus);

        //update
        reportRepository.save(report);

        return true;
    }

    public Optional<Member> getMemberStatusList(MemberStatus memberStatus){
        return memberRepository.findByMemberStatus(memberStatus);
    }

    public Optional<Report> getReportWithStatus(ReportStatus reportStatus){
        return reportRepository.findByStatus(reportStatus);
    }






}
