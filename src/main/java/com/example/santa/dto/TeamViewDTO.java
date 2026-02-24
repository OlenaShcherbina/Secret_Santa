package com.example.santa.dto;

import com.example.santa.entity.TeamStatus;
import lombok.Getter;

import java.util.List;

@Getter
public class TeamViewDTO {
    private final String code;
    private final TeamStatus status;
    private final String ownerName;
    private final List<String> members;

    public TeamViewDTO(String code, TeamStatus status,  String ownerName, List<String> members){
        this.code = code;
        this.status = status;
        this.ownerName = ownerName;
        this.members = List.copyOf(members);
    }
}
