//package com.board.springboot.security.filter;
//
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//
//public class FormLoginFilter extends UsernamePasswordAuthenticationFilter {
//    final private ObjectMapper objectMapper;
//
//    public FormLoginFilter(final AuthenticationManager authenticationManager) {
//        super.setAuthenticationManager(authenticationManager);
//        objectMapper = new ObjectMapper()
//                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        UsernamePasswordAuthenticationToken authRequest;
//        try {
//            JsonNode requestBody = objectMapper.readTree(request.getInputStream());
//            String username = requestBody.get("username").asText();
//            String password = requestBody.get("password").asText();
//            authRequest = new UsernamePasswordAuthenticationToken(username, password);
//        } catch (Exception e) {
//            throw new RuntimeException("username, password 입력이 필요합니다. (JSON)");
//        }
//
//        setDetails(request, authRequest);
//        return this.getAuthenticationManager().authenticate(authRequest);
//    }
//}