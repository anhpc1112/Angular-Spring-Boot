package com.example.be.login;

import com.example.be.BusinessException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class AuthController {
    @PostMapping("/login")
    public Object login(@RequestBody LoginRequest loginRequest) {
        // Xử lý đăng nhập tại đây, ví dụ kiểm tra username và password
        System.out.println("Username: " + loginRequest.getUsername());
        if ("admin".equals(loginRequest.getUsername()) && "admin".equals(loginRequest.getPassword())) {
            return loginRequest;
        } else {
            return new BusinessException("404", "Wrong username or password");
        }
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
