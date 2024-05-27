package com.dto.way.admin.domain.repository;

import com.dto.way.admin.domain.entity.ReportStatus;
import com.dto.way.admin.global.config.FeignClientConfig;
import com.dto.way.admin.web.dto.ReportDTO;
import com.dto.way.admin.web.dto.ReportResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "post-service", url = "${config.feign.post-service-url}", configuration = FeignClientConfig.class)
public interface ReportClient {
    @GetMapping("/member-info/{email}")
    List<ReportResponseDto.GetReportResultDto> findReportByEmail(@PathVariable String email);

    @PostMapping("/member-info/{email}")
    ReportResponseDto.GetReportResultDto setReportById(@PathVariable String email);

    @PostMapping("/member-info/{id}")
    ReportResponseDto.GetReportResultDto changeReportStatus(@PathVariable Long id, ReportStatus reportStatus);
}
