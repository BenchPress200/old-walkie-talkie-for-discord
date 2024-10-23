package com.bluestarfish.walkietalkie.attendance.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRanking {
    private String userId;
    private String nickname;
    private Long attendanceCount;

    public UserRanking(String userId, Long attendanceCount) {
        this.userId = userId;
        this.attendanceCount = attendanceCount;
    }
}
