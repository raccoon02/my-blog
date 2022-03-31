package com.sparta.myblog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.myblog.dto.SignupRequestDto;
import com.sparta.myblog.security.UserDetailsImpl;
import com.sparta.myblog.service.KakaoUserService;
import com.sparta.myblog.service.UserService;
import com.sparta.myblog.validator.SignupValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SignupValidator signupValidator;
    private final KakaoUserService kakaoUserService;

    @GetMapping("/user/login")
    public String login(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        if(userDetails != null) {
            model.addAttribute("loggedIn", true);
            model.addAttribute("message", "이미 로그인 하셨습니다.");
        } else
            model.addAttribute("loggedIn", false);
        return "login_and_signup";
    }
    @PostMapping("/user/signup")
    public String registerUser(@Valid SignupRequestDto requestDto, Errors errors, Model model) {
        String message = signupValidator.getvalidMessage(requestDto, errors);
        if(message.equals("회원가입 성공"))
            userService.registerUser(requestDto);
        model.addAttribute("message", message);
        return "login_and_signup";
    }
    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        // authorizedCode: 카카오 서버로부터 받은 인가 코드
        kakaoUserService.kakaoLogin(code);
        return "redirect:/";
    }
}
