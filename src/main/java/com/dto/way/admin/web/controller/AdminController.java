package com.dto.way.admin.web.controller;


import com.dto.way.admin.domain.entity.Member;
import com.dto.way.admin.domain.entity.MemberStatus;
import com.dto.way.admin.domain.entity.Report;
import com.dto.way.admin.domain.entity.ReportStatus;
import com.dto.way.admin.domain.repository.ReportClient;
import com.dto.way.admin.domain.service.AdminService;
import com.dto.way.admin.web.dto.MemberInfoDTO;
import com.dto.way.admin.web.dto.ReportDTO;
import com.dto.way.admin.web.dto.ReportResponseDto;
import com.dto.way.admin.web.response.code.ErrorReasonDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    public final ReportClient reportClient;

    @PostMapping("/changeMemberStatus")
    public String changeUserStatus(@RequestBody MemberInfoDTO memberInfoDTO) {
        String email=memberInfoDTO.getEmail();
        MemberStatus memberStatus=memberInfoDTO.getMemberStatus();

        if(adminService.changeMemberStatus(email, memberStatus)){
            return "Status changed: "+ memberStatus.toString();
        }else{
            return "Member not Found";
        }
    }

    @PostMapping("/changeReportStatus")
    public String changeReportStatus(@RequestBody ReportDTO reportDTO){
        Long report_id= reportDTO.getId();
        ReportStatus reportStatus = reportDTO.getReportStatus();
        log.info(reportStatus.toString());
        ReportResponseDto.GetReportResultDto reportDto=reportClient.setReportByStatus(report_id, reportStatus);
        return "Done";
    }

//    @PostMapping("/test/changeReportStatus")
//    public String changeReportStatustest(@RequestBody ReportDTO reportDTO){
//        Long report_id= reportDTO.getId();
//        ReportStatus reportStatus = reportDTO.getReportStatus();
//        log.info(reportStatus.toString()+"reoport");
//        ReportResponseDto.GetReportResultDto reportDto=reportClient.setReportByStatus(report_id, reportStatus);
//        return "test";
//    }




//        if(adminService.changeReportStatus(report_id, reportStatus)){
//            return "Status Changed";
//        }else{
//            return "Report Id not Exist";
//        }
//    }

//    @PostMapping("/admin/getReportWithMember")
//    public ResponseEntity<?> getReportWithMember(@RequestBody MemberInfoDTO memberInfoDTO){
//        String nickName=memberInfoDTO.getNickname();
//        Optional<Report> optionalReport=adminService.getReportWithNickName(nickName);
//
//        if (optionalReport.isEmpty()) {
//            ErrorReasonDTO errorReasonDTO= ErrorReasonDTO.builder()
//                    .message("Report not found.\nMember_Nickname: "+nickName)
//                    .build();
//
//            return ResponseEntity
//                    .badRequest()
//                    .body(errorReasonDTO); // Member not found
//        }
//
//        Report report = optionalReport.get();
//
//        return ResponseEntity.ok().body(report);
//    }

    @PostMapping("/grantMember")
    public ResponseEntity<?> grantMember(@RequestBody MemberInfoDTO memberInfoDTO){
        String nickName=memberInfoDTO.getEmail();
        log.info(nickName + memberInfoDTO.getMemberAuth().toString());
        if(!adminService.changeMemberAuth(nickName, memberInfoDTO.getMemberAuth())){
            return ResponseEntity.ok().body("te");
        }
        log.info(memberInfoDTO.getMemberAuth().toString());
        return ResponseEntity.ok().body("success");
    }


}
