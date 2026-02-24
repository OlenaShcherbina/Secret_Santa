package com.example.santa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AccountResponseDTO {
    private final Long id;
    private final String name;
    private final String email;
}
