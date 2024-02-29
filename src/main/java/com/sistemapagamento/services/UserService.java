package com.sistemapagamento.services;

import com.sistemapagamento.domain.user.User;
import com.sistemapagamento.domain.user.UserType;
import com.sistemapagamento.dtos.UserDTO;
import com.sistemapagamento.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Usuário do tipo logista não pode efetuar esse tipo de transação");
        }
        if(sender.getBalance().compareTo(amount)<0){
            throw new Exception("Usuário não possui saldo para efetuar a transação");
        }
    }

    public User findUserById(Long id) throws Exception {
        return this.userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public void saveUser(User user){
        this.userRepository.save(user);
    }

    public User createUser(UserDTO userDTO){
        User newUser = new User(userDTO);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();

    }
}
