package com.dto.way.admin.web.dto;

import com.dto.way.admin.domain.entity.ReportStatus;
import com.dto.way.admin.domain.entity.ReportType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReportDTO {
    @NotBlank(message = "Report ID는 필수 입력 값입니다.")
    private Long id;
    private String title;
    private ReportType type;
    private ReportStatus reportStatus;
    private String memberEmail;
    private Long targetId;
}
