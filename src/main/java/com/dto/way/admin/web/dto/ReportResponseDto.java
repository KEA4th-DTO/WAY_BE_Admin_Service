package com.dto.way.admin.web.dto;

import com.dto.way.admin.domain.entity.ReportStatus;
import com.dto.way.admin.domain.entity.ReportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

public class ReportResponseDto {
    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetReportResultDto{
        private Long reportId;
        private String title;
        //private String description;
        private ReportType type;
        private ReportStatus ReportStatus;
        //private String memberEmail;
        private Long targetId;
        private Date reportedAt;
    }

}

