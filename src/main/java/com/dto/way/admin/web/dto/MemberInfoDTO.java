package com.dto.way.admin.web.dto;

import com.dto.way.admin.domain.entity.MemberStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberInfoDTO {
    private Long id;
    private String email;
    private MemberStatus memberStatus;
    private String nickname;

}
