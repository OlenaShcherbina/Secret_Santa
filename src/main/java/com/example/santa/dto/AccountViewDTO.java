package com.example.santa.dto;

import lombok.Getter;
import java.util.List;

@Getter
public class AccountViewDTO {

    private final String name;
    private final String email;
    private final List<TeamSummaryDTO> teams;

    public AccountViewDTO(String name, String email, List<TeamSummaryDTO> teams){
        this.name = name;
        this.email = email;
        this.teams = List.copyOf(teams);
    }
}
