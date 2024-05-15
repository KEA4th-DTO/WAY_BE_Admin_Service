package com.dto.way.admin.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmailCertificationResponse {

    private String email;
    private String certificationNumber;
}
