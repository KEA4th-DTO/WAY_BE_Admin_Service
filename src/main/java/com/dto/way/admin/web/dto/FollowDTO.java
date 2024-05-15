package com.dto.way.admin.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FollowDTO {

    private String profileImageUrl;
    private String nickname;
    private String name;
    private String status;
    private String memberId;

}
