package com.example.be.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable(); // Tắt CSRF protection và bật CORS

        // Cấu hình các rule phù hợp với ứng dụng của bạn
        http.authorizeRequests()
              .antMatchers("/api/public").permitAll() // Cho phép truy cập vào các endpoint public
              .antMatchers("/api/private").authenticated(); // Yêu cầu xác thực cho các endpoint private

        // Cấu hình xác thực bằng JWT (hoặc các phương pháp xác thực khác)
        // Đảm bảo rằng bạn có một bean để xử lý xác thực (ví dụ: UserDetailsService, AuthenticationProvider)
    }
}
