//package com.board.springboot.security;
//
//import com.board.springboot.security.filter.FormLoginFilter;
//import com.board.springboot.security.filter.JwtAuthFilter;
//import com.board.springboot.security.jwt.HeaderTokenExtractor;
//import com.board.springboot.security.provider.FormLoginAuthProvider;
//import com.board.springboot.security.provider.JWTAuthProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.filter.CorsFilter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//import static org.springframework.web.servlet.function.RequestPredicates.headers;
//
//@Configuration
//@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
//@EnableGlobalMethodSecurity(securedEnabled = true) // @Secured 어노테이션 활성화
//public class WebSecurityConfig {
//
//    private final JWTAuthProvider jwtAuthProvider;
//    private final HeaderTokenExtractor headerTokenExtractor;
//    private final CorsFilter corsFilter;
//
//    public WebSecurityConfig(
//            JWTAuthProvider jwtAuthProvider,
//            HeaderTokenExtractor headerTokenExtractor,
//            CorsFilter corsFilter) {
//        this.jwtAuthProvider = jwtAuthProvider;
//        this.headerTokenExtractor = headerTokenExtractor;
//        this.corsFilter = corsFilter;
//    }
//
//    @Bean
//    public BCryptPasswordEncoder encodePassword() {
//        return new BCryptPasswordEncoder();
//    }
//
////    @Bean
////    public void configureFilerChain(AuthenticationManagerBuilder auth) {
////        auth
////                .authenticationProvider(formLoginAuthProvider())
////                .authenticationProvider(jwtAuthProvider);
////    }
////    @Bean
////    public AuthenticationManager configureAuth(AuthenticationManagerBuilder auth) throws Exception{
////        auth
////                .authenticationProvider(formLoginAuthProvider())
////                .authenticationProvider(jwtAuthProvider);
////        return auth.build();
////    }
//
//    @Bean
//    protected SecurityFilterChain configureFilerChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf -> csrf.disable())
//        // 서버에서 인증은 JWT로 인증하기 때문에 Session의 생성을 막습니다.
//                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
////        http.headers().frameOptions().sameOrigin(); // 3버전부터 없어짐(안해도됨?)
//
//        /*
//         * 1.
//         * UsernamePasswordAuthenticationFilter 이전에 FormLoginFilter, JwtFilter 를 등록합니다.
//         * FormLoginFilter : 로그인 인증을 실시합니다.
//         * JwtFilter       : 서버에 접근시 JWT 확인 후 인증을 실시합니다.
//         */
//        http
//                .addFilter(corsFilter)
//                .addFilterBefore(formLoginFilter(), UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
//
//        http.authorizeRequests()
//                .anyRequest()
//                .permitAll()
////                .and()
////                // [로그아웃 기능]
////                .logout()
////                // 로그아웃 요청 처리 URL
////                .logoutUrl("/logout")
////                .permitAll()
//                    // "접근 불가" 페이지 URL 설정
////                .exceptionHandling((exceptionConfig) -> exceptionConfig.accessDeniedPage("/접근 불가 페이지 URL 생성"))
//        ;
//        return http.build();
//    }
//
//    @Bean
//    public FormLoginFilter formLoginFilter() throws Exception {
//        AuthenticationConfiguration authenticationConfiguration = null;
//        FormLoginFilter formLoginFilter = new FormLoginFilter(authenticationManager(authenticationConfiguration));
//        formLoginFilter.setFilterProcessesUrl("/api/users/login");
//        formLoginFilter.setAuthenticationSuccessHandler(formLoginSuccessHandler());
//        formLoginFilter.afterPropertiesSet();
//        return formLoginFilter;
//    }
//
//    @Bean
//    public FormLoginSuccessHandler formLoginSuccessHandler() {
//        return new FormLoginSuccessHandler();
//    }
//
//    @Bean
//    public FormLoginAuthProvider formLoginAuthProvider() {
//        return new FormLoginAuthProvider(encodePassword());
//    }
//
//    private JwtAuthFilter jwtFilter() throws Exception {
//        List<String> skipPathList = new ArrayList<>();
//
//        // h2-console 허용
//        skipPathList.add("GET,/h2-console/**");
//        skipPathList.add("POST,/h2-console/**");
//        // 회원 관리 API 허용
//        skipPathList.add("POST,/api/users/signup");
//        //닉네임 중복 체크
//        skipPathList.add("POST,/api/users/nickname/**");
//        skipPathList.add("PATCH,/api/users/nickname/**");
//        //이메일 중복체크 및 인증메일 발송
//        skipPathList.add("POST,/api/users/emails");
//        skipPathList.add("PATCH,/api/users/password/**");
//        skipPathList.add("POST,/api/users/emails/**");
//        //비밀번호 찾기 이메일 전송
//        skipPathList.add("POST,/api/users/passwordFind");
//        skipPathList.add("PATCH,/api/passwordChange");
//        // 소켓 통신
//        skipPathList.add("GET,/ws-stomp/**/**");
//        skipPathList.add("GET,/ws-stomp/**");
//        //무중단 healthCheck
//        skipPathList.add("GET,/");
//        skipPathList.add("GET,/health");
//        FilterSkipMatcher matcher = new FilterSkipMatcher(
//                skipPathList,
//                "/**"
//        );
//
//        JwtAuthFilter filter = new JwtAuthFilter(
//                matcher,
//                headerTokenExtractor
//        );
//        AuthenticationConfiguration authenticationConfiguration = null;
//        filter.setAuthenticationManager(authenticationManager(authenticationConfiguration));
//
//        return filter;
//    }
//
////    @Bean
////    @Override
////    public AuthenticationManager authenticationManagerBean() throws Exception {
////        return super.authenticationManagerBean();
////    }
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
//            throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//}
