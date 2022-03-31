package com.sparta.myblog.validator;

import com.sparta.myblog.dto.SignupRequestDto;
import com.sparta.myblog.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class SignupValidator {

    private final UserRepository userRepository;

    public SignupValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public String getvalidMessage(SignupRequestDto requestDto, Errors errors){
        if (errors.hasErrors()){
            Map<String,String> validtorResult = validateHandling(errors);
            return validtorResult.get("message");
        } else if(checkEmailDuplicate(requestDto.getEmail())){
            return "이미 가입된 이메일 주소입니다.";
        } else if(checkNicknameDuplicate(requestDto.getNickname())){
            return  "이미 사용중인 닉네임입니다.";
        } else if(!requestDto.getPassword().equals(requestDto.getPasswordCheck())){
            return "비밀번호가 일치하지 않습니다.";
        } else if(requestDto.getNickname().contains(requestDto.getPassword())){
            return "비밀번호는 닉네임을 포함 할수 없습니다";
        } else
            return "회원가입 성공";
    }
    public Map<String,String> validateHandling(Errors errors){
        Map<String,String> validtorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()){
            String validKeyName = "message";
            validtorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validtorResult;
    }
    //email 중복체크
    public boolean checkEmailDuplicate(String email) {return userRepository.existsByEmail(email);}
    //nickname 중복체크
    public boolean checkNicknameDuplicate(String nickname) {return  userRepository.existsByNickname(nickname);}

    //------------------------------------------------------------------------------------------------------------------
    //test 유효성검사
    public String getValidMessageForTest(SignupRequestDto requestDto) {
        if (!Pattern.matches("^[A-Za-z0-9]{3,}", requestDto.getNickname())) {
            return "닉네임을 확인하세요.";
        } else if (!Pattern.matches("^[A-Za-z0-9]{4,}", requestDto.getPassword())){
            return "비밀번호를 확인하세요.";
        } else if(checkEmailDuplicate(requestDto.getEmail())) {
            return "이미 가입된 이메일 주소입니다.";
        } else if(checkNicknameDuplicate(requestDto.getNickname())) {
            return "이미 사용중인 닉네임입니다.";
        } else if(!requestDto.getPassword().equals(requestDto.getPasswordCheck())) {
            return "비밀번호가 일치하지 않습니다.";
        } else if(requestDto.getPassword().contains(requestDto.getNickname())) {
            return "비밀번호는 닉네임을 포함할 수 없습니다.";
        } else
            return "회원가입 성공";
    }
}
