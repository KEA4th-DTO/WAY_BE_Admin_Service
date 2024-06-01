package com.dto.way.admin.web.dto;

import com.dto.way.admin.domain.entity.MemberAuth;
import com.dto.way.admin.domain.entity.MemberStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberInfoDTO {
    private Long id;
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;
    private MemberStatus memberStatus;
    private String nickname;
    private MemberAuth memberAuth;

}
