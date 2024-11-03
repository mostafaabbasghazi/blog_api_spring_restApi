package com.example.demo.service.user;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repo.RoleRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.request.AddUserRequest;
import com.example.demo.request.ChangePassword;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Service
public class UserService implements UserServiceI{

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private RoleRepo roleRepo;
    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found!"));
    }

    @Override
    public User createUser(AddUserRequest request) {
      return Optional.of(request).filter(user -> !userRepository.existsByEmail(request.getEmail()))
      .map(req->{
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Random random = new Random();
        int pinCode = random.nextInt(10000,99999);
        user.setPin(pinCode);

        Role admin= roleRepo.findById(1L).get();
        Role user1=roleRepo.findById(2L).get();
        if(request.getIsAdmin()==true){
            user.getRoles().add(admin);
            user.getRoles().add(user1);
        }else{
            
            user.getRoles().add(user1);

        }
        return userRepository.save(user);

      }).orElseThrow(() -> new RuntimeException("User not found!"));
    }

    @Override
    public User updateUser(ChangePassword request, Long userId) {
        return  userRepository.findById(userId).map(existingUser ->{
            if(request.getPin()==existingUser.getPin()){
                existingUser.setPassword(passwordEncoder.encode(request.getPassword()));
                return userRepository.save(existingUser);
            }
            return null;
        }).orElseThrow(() -> new RuntimeException("User not found!"));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresentOrElse(userRepository :: delete, () ->{
            throw new RuntimeException("User not found!");
        });
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
        
    }

}
