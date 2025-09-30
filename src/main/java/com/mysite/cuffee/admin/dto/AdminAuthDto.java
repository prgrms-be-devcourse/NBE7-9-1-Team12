package com.mysite.cuffee.admin.dto;

// 관리자 로그인 요청 DTO

public class AdminAuthDto {
    public record LoginRequest(
            String username,
            String password
    ){}

    public record LoginResponse(
            String token,
            String role
    ){}
}
