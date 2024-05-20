package com.dto.way.admin.domain.repository;

import com.dto.way.admin.domain.entity.Report;
import com.dto.way.admin.domain.entity.ReportStatus;
import com.dto.way.admin.domain.entity.ReportType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Optional<Report> findByType(ReportType type);

    Optional<Report> findByTargetId(Long targetId);

    Optional<Report> findByReportId(Long reportId);

    Optional<Report> findByStatus(ReportStatus reportStatus);
}
