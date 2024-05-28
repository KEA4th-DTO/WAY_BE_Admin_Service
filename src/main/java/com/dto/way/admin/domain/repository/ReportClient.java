package com.dto.way.admin.domain.repository;

import com.dto.way.admin.domain.entity.ReportStatus;
import com.dto.way.admin.global.config.FeignClientConfig;
import com.dto.way.admin.web.dto.ReportResponseDto;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "post-service", url = "http://210.109.55.124/post-service", configuration = FeignClientConfig.class)
public interface ReportClient {
    @GetMapping("/report")
    List<ReportResponseDto.GetReportResultDto> findReportByEmail(ReportStatus reportStatus);

    @PutMapping("/report/{reportId}")
    ReportResponseDto.GetReportResultDto setReportByStatus(@PathVariable Long reportId, @RequestParam(value="reportStatus") ReportStatus reportStatus);

//    @PostMapping("/member-info/{id}")
//    ReportResponseDto.GetReportResultDto changeReportStatus(@PathVariable Long id, ReportStatus reportStatus);
}
