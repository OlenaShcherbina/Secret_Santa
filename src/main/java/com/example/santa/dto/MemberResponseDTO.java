package com.example.santa.dto;

import lombok.Getter;

import java.util.List;
@Getter
public class MemberResponseDTO {
    private final Long id;
    private final String name;
    private final String email;
    private final String teamCode;
    private final List<String> interests;

    public MemberResponseDTO(Long id, String name, String email, String teamCode, List<String> interests) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.teamCode = teamCode;
        this.interests = List.copyOf(interests);
    }
}
