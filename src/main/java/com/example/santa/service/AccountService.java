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

      validateEmail(accountDTO.getEmail());
      String email = accountDTO.getEmail().trim();
      accountDTO.setEmail(email);
        if(accountRepository.findByEmail(email).isPresent()){
            throw new DuplicateEntityException("Account with "
                    + email + " already exists!");
        }

        //check password and hash it
        validatePassword(accountDTO.getPassword());

       Account account = accountMapper.toEntity(accountDTO);
        String pass = accountDTO.getPassword();
        account.setPassword(passwordEncoder.encode(pass));

        return accountMapper.toResponseDto(accountRepository.save(account));

   }
/*
    public AccountResponseDTO find(AccountDTO accountDTO){
        //find by email
        validateEmail(accountDTO.getEmail());
        validatePassword(accountDTO.getPassword());

        Account account = accountRepository.findByEmail(accountDTO.getEmail().trim())
                .orElseThrow(
                        () -> new NotFoundException("Account with email: " + accountDTO.getEmail() + " not found")
                );
        //check pass

        if(passwordEncoder.matches(accountDTO.getPassword(), account.getPassword())){
            return accountMapper.toResponseDto(account);
        }else{
            throw new InvalidOperationException("Wrong password");
        }
    }
*/
    private void validatePassword(String password){
        if(password == null || password.isBlank()){
            throw new InvalidOperationException("Password must not be blank");
        }
        if(password.length() < 5){
            throw new InvalidOperationException("Password length must be at least 5");
        }
   }
   private void validateEmail(String email){
       if(email == null || email.isBlank()){
           throw new InvalidOperationException("Email must not be blank");
       }

   }


}
