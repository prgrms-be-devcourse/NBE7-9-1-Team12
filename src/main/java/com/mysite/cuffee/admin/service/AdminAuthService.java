package com.mysite.cuffee.admin.service;

import com.mysite.cuffee.admin.dto.AdminAuthDto;
import com.mysite.global.util.Ut;
import org.springframework.stereotype.Service;

import java.util.Map;

// 관리자 인증 서비스
@Service
public class AdminAuthService {

    private final String secret = "my-secret-key"; // application.yml에서 @Value로 주입 권장

    //사용자가 로그인 요청하면, username과 password 입력
    public AdminAuthDto.LoginResponse login(AdminAuthDto.LoginRequest req) {
        //username과 password가 admin, cuffee12가 아니면 예외 발생
        if (!"admin".equals(req.username()) || !"cuffee12".equals(req.password())) {
            throw new RuntimeException("관리자 인증 실패");
        }

        Map<String, Object> body = Map.of(
                "username", req.username(),
                "role", "ROLE_ADMIN"
        );

        String token = Ut.jwt.toString(secret, 3600, body);

        return new AdminAuthDto.LoginResponse(token, "ROLE_ADMIN");
    }
}
