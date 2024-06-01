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
import com.dto.way.admin.web.response.ApiResponse;
import com.dto.way.admin.web.response.code.ErrorReasonDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.dto.way.admin.web.response.code.status.ErrorStatus.*;
import static com.dto.way.admin.web.response.code.status.SuccessStatus.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    public final ReportClient reportClient;

    @PostMapping("/changeMemberStatus")
    public ApiResponse<MemberInfoDTO> changeUserStatus(@Valid @RequestBody MemberInfoDTO memberInfoDTO) {
        String email=memberInfoDTO.getEmail();
        MemberStatus memberStatus=memberInfoDTO.getMemberStatus();

        if(memberStatus==null){
            return ApiResponse.onFailure(MEMBER_STATUS_NOT_VALID.getCode(), MEMBER_STATUS_NOT_VALID.getMessage(), memberInfoDTO);
        }

        if(adminService.changeMemberStatus(email, memberStatus)){
            return ApiResponse.of(MEMBER_STATUS_CHANGED, memberInfoDTO);
        }else{
            return ApiResponse.onFailure(MEMBER_EMAIL_NOT_FOUND.getCode(), MEMBER_EMAIL_NOT_FOUND.getMessage(), memberInfoDTO);
        }
    }

    @PostMapping("/grantMember")
    public ApiResponse<MemberInfoDTO> grantMember(@Valid @RequestBody MemberInfoDTO memberInfoDTO){
        String nickName=memberInfoDTO.getEmail();
        //log.info(nickName + memberInfoDTO.getMemberAuth().toString());

        if(memberInfoDTO.getMemberAuth()==null){
            return ApiResponse.onFailure(MEMBER_GRANT_NOT_VALID.getCode(), MEMBER_GRANT_NOT_VALID.getMessage(), memberInfoDTO);
        }

        if(!adminService.changeMemberAuth(nickName, memberInfoDTO.getMemberAuth())){
            return ApiResponse.onFailure(MEMBER_EMAIL_NOT_FOUND.getCode(), MEMBER_EMAIL_NOT_FOUND.getMessage(), memberInfoDTO);
        }
        return ApiResponse.of(MEMBER_GRANTED, memberInfoDTO);
    }

    @PostMapping("/changeReportStatus")
    public ApiResponse<ReportDTO> changeReportStatus(@Valid @RequestBody ReportDTO reportDTO){
        Long report_id= reportDTO.getId();

        ReportStatus reportStatus = reportDTO.getReportStatus();


        log.info(reportStatus.toString());
        ReportResponseDto.GetReportResultDto reportDto=reportClient.setReportByStatus(report_id, reportStatus);
        return ApiResponse.of(REPORT_STATUS_CHANGED, reportDTO);
    }

}
