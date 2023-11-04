//package com.board.springboot.security.provider;
//
//
//import com.board.springboot.exceptionHandler.CustomException;
//import com.board.springboot.security.UserDetailsImpl;
//import com.board.springboot.security.jwt.JwtDecoder;
//import com.board.springboot.user.User;
//import com.board.springboot.user.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Component;
//import com.board.springboot.exceptionHandler.ErrorCode;
//
//
//@Component
//@RequiredArgsConstructor
//public class JWTAuthProvider implements AuthenticationProvider {
//
//        private final JwtDecoder jwtDecoder;
//
//        private final UserRepository userRepository;
//
//        @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String token = (String) authentication.getPrincipal();
//        String username = jwtDecoder.decodeUsername(token);
//
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));
//        UserDetailsImpl userDetails = new UserDetailsImpl(user);
//        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//    }
//}
//
