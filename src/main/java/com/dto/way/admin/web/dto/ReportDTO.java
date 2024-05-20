package com.dto.way.admin.web.dto;

import com.dto.way.admin.domain.entity.ReportStatus;
import com.dto.way.admin.domain.entity.ReportType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReportDTO {
    private Long id;
    private String title;
    private ReportType type;
    private ReportStatus status;
    private String memberEmail;
    private Long targetId;
}
