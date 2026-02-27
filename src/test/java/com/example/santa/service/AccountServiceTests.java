package com.example.santa.service;

import com.example.santa.dto.AccountDTO;
import com.example.santa.dto.AccountResponseDTO;
import com.example.santa.entity.Account;
import com.example.santa.exception.DuplicateEntityException;
import com.example.santa.exception.InvalidOperationException;
import com.example.santa.mapper.AccountMapper;
import com.example.santa.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AccountServiceTests {

    @Mock
    AccountRepository repository;

    @Mock
    AccountMapper mapper;

    @Mock
    PasswordEncoder encoder;

    @InjectMocks
    AccountService service;


    @Test
    void createWithValidCredentials(){
        AccountDTO dto = new AccountDTO(
                "John Doe", "john.doe@example.com", "myPass123");

        Account mappedAccount = new Account();
        mappedAccount.setName("John Doe");
        mappedAccount.setEmail("john.doe@example.com");
        mappedAccount.setPassword("myPass123");

        Account savedAccount = new Account();
        savedAccount.setId(1L);
        savedAccount.setName("John Doe");
        savedAccount.setPassword("encodedPass");

        AccountResponseDTO responseDTO = new AccountResponseDTO(
                1L, "John Doe", "john.doe@example.com");
        when(repository.findByEmail("john.doe@example.com")).thenReturn(Optional.empty());
        when(mapper.toEntity(dto)).thenReturn(mappedAccount);
        when(encoder.encode(dto.getPassword())).thenReturn("encodedPass");
        when(repository.save(mappedAccount)).thenReturn(savedAccount);
        when(mapper.toResponseDto(savedAccount)).thenReturn(responseDTO);

        AccountResponseDTO result = service.create(dto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("john.doe@example.com", result.getEmail());

        verify(repository, times(1)).findByEmail("john.doe@example.com");
        verify(mapper, times(1)).toEntity(dto);
        verify(encoder, times(1)).encode("myPass123");
        verify(repository, times(1)).save(mappedAccount);
        verify(mapper, times(1)).toResponseDto(savedAccount);

    }

    @Test
    void createWithBlankEmail(){
        AccountDTO dto = new AccountDTO(
                "John Doe", " ", "myPass123");

        InvalidOperationException exception = assertThrowsExactly(
                InvalidOperationException.class,
                () -> service.create(dto));

        assertEquals("Email must not be blank", exception.getMessage());

        verify(repository, never()).findByEmail(any());

    }

    @Test
    void createWithBlankPassword(){
        AccountDTO dto = new AccountDTO(
                "John Doe", "john.doe@example.com", " ");

        when(repository.findByEmail("john.doe@example.com")).thenReturn(Optional.empty());

        InvalidOperationException exception = assertThrowsExactly(
                InvalidOperationException.class,
                () -> service.create(dto));

        assertEquals("Password must not be blank", exception.getMessage());

        verify(repository, times(1)).findByEmail("john.doe@example.com");
        verify(mapper, never()).toEntity(any());

    }

    @Test
    void createWithShortPassword(){
        AccountDTO dto = new AccountDTO(
                "John Doe", "john.doe@example.com", " p1");

        when(repository.findByEmail("john.doe@example.com")).thenReturn(Optional.empty());

        InvalidOperationException exception = assertThrowsExactly(
                InvalidOperationException.class,
                () -> service.create(dto));

        assertEquals("Password length must be at least 5", exception.getMessage());

        verify(repository, times(1)).findByEmail("john.doe@example.com");
        verify(mapper, never()).toEntity(any());

    }

    @Test
    void createExistedAccount(){
        AccountDTO dto = new AccountDTO(
                "John Doe", "john.doe@example.com", "myPass123");
        Account existedAccount = new Account();
        existedAccount.setEmail("john.doe@example.com");

        when(repository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(existedAccount));

        DuplicateEntityException exception = assertThrowsExactly(
                DuplicateEntityException.class,
                () -> service.create(dto));

        assertEquals("Account with john.doe@example.com already exists!", exception.getMessage());
        verify(repository, times(1)).findByEmail("john.doe@example.com");
        verify(mapper, never()).toEntity(any());
    }
}
