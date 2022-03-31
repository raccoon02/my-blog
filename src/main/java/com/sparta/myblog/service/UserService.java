package com.sparta.myblog.service;


import com.sparta.myblog.dto.SignupRequestDto;
import com.sparta.myblog.models.User;
import com.sparta.myblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired // 필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }
    public void registerUser(SignupRequestDto requestDto){
        String nickname = requestDto.getNickname();
        String  email = requestDto.getEmail();

        String password = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(nickname, password, email);
        userRepository.save(user);
    }
}
