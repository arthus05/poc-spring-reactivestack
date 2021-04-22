package com.poc.reactivecrud.services;

import com.poc.reactivecrud.models.User;
import com.poc.reactivecrud.repositories.DepartmentRepository;
import com.poc.reactivecrud.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public Mono<User> createUser(User user){
        return userRepository.save(user);
    }

    public Flux<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Mono<User> findById(Integer userId){
        return userRepository.findById(userId);
    }

    public Mono<User> updateUser(Integer userId,  User user){
        return userRepository.findById(userId)
                .flatMap(dbUser -> {
                    dbUser.setAge(user.getAge());
                    dbUser.setSalary(user.getSalary());
                    return userRepository.save(dbUser);
                });
    }

    public Mono<User> deleteUser(Integer userId){
        return userRepository.findById(userId)
                .flatMap(existingUser -> userRepository.delete(existingUser)
                        .then(Mono.just(existingUser)));
    }

}
