//package com.board.springboot.security;
//
//import com.board.springboot.security.jwt.JwtTokenUtils;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
//
//
//public class FormLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
//
//    public static final String AUTH_HEADER = "Authorization";
//    public static final String REFRESH_HEADER = "RefreshToken";
//    public static final String TOKEN_TYPE = "BEARER";
//
//
//    //성공시 응답에 토큰을 추가하는 핸들러
//    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) {
//        final UserDetailsImpl userDetails = ((UserDetailsImpl) authentication.getPrincipal());
//
//        final String token = JwtTokenUtils.generateJwtToken(userDetails);
////        final String refreshToken = JwtTokenUtils.generaterefreshToken(user);
//
//        response.addHeader(AUTH_HEADER, TOKEN_TYPE + " " + token);
//    }
//}
