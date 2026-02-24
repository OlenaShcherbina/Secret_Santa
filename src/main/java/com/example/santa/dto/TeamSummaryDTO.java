package com.example.santa.dto;

import com.example.santa.entity.TeamStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TeamSummaryDTO {
    private final String code;
    private final TeamStatus status;
}
