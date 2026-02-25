package com.example.santa.mapper;

import com.example.santa.dto.AccountDTO;
import com.example.santa.dto.AccountResponseDTO;
import com.example.santa.dto.AccountViewDTO;
import com.example.santa.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account toEntity(AccountDTO accountDto);

    AccountDTO toDto(Account account);
    AccountResponseDTO toResponseDto(Account account);

    @Mapping(target = "teams", ignore = true)
    AccountViewDTO toViewDto(Account account);
}
