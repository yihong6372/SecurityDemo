package net.geekh.securitydemo.config;

import net.geekh.securitydemo.filter.JwtAuthenticationTokenFilter;
import net.geekh.securitydemo.handler.AccessDeniedHandlerImpl;
import net.geekh.securitydemo.handler.AuthenticationEntryPointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/13 00:30
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter,
                                                   AuthenticationEntryPointImpl authenticationEntryPoint,
                                                   AccessDeniedHandlerImpl accessDeniedHandler) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(authoroze -> authoroze
                .requestMatchers("/user/register","/user/login","/error","/sse/**","/test/hello2").permitAll()
                .anyRequest().authenticated()
        );

        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //自定义异常处理配置
        http.exceptionHandling(exception -> {
            exception.authenticationEntryPoint(authenticationEntryPoint);
            exception.accessDeniedHandler(accessDeniedHandler);
        });

        return http.build();
    }

    @Bean
    //把BCryptPasswordEncoder对象注入Spring容器中，SpringSecurity就会使用该PasswordEncoder来进行密码校验
    //注意也可以注入PasswordEncoder，效果是一样的，因为PasswordEncoder是BCry..的父类
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }




}
