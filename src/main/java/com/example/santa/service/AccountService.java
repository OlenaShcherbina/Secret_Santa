package com.example.santa.service;

import com.example.santa.dto.AccountDTO;
import com.example.santa.dto.AccountResponseDTO;
import com.example.santa.entity.Account;
import com.example.santa.exception.DuplicateEntityException;
import com.example.santa.exception.InvalidOperationException;
import com.example.santa.mapper.AccountMapper;
import com.example.santa.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
   public AccountResponseDTO create(AccountDTO accountDTO){
        //TODO: maybe add email validation
      if(accountDTO.getEmail() == null || accountDTO.getEmail().isBlank()){
          throw new InvalidOperationException("Email must not be blank");
      }
        String email = accountDTO.getEmail().trim();
      accountDTO.setEmail(email);

       if(accountRepository.findByEmail(accountDTO.getEmail()).isPresent()){
            throw new DuplicateEntityException("Account with "
                    + accountDTO.getEmail() + " already exists!");
       }

        //check password and hash it
        validatePassword(accountDTO.getPassword());

       Account account = accountMapper.toEntity(accountDTO);
        String pass = accountDTO.getPassword();
        account.setPassword(passwordEncoder.encode(pass));

        return accountMapper.toResponseDto(accountRepository.save(account));


   }





   private void validatePassword(String password){
        if(password == null || password.isBlank()){
            throw new InvalidOperationException("Password must not be blank");
        }
        if(password.length() < 5){
            throw new InvalidOperationException("Password length must be at least 5");
        }
   }


}
