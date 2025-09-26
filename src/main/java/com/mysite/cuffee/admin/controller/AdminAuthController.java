package com.mysite.cuffee.admin.controller;

import com.mysite.cuffee.admin.dto.AdminAuthDto;
import com.mysite.cuffee.admin.service.AdminAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 관리자 인증 관리 컨트롤러
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminAuthController {

    private final AdminAuthService adminAuthService;

    @PostMapping("/login")
    public AdminAuthDto.LoginResponse login(@RequestBody AdminAuthDto.LoginRequest req) {
        return adminAuthService.login(req);
    }
}
