package com.example.santa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


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
