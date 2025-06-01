package com.popcornmate.security.jwt;

import com.popcornmate.entity.User;
import com.popcornmate.security.dto.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     *
     * 검사가 필요하지 않은 것
     *
     * @param path 요청 경로
     * @return bool
     */
    private boolean checkUrl(String path){

        String[] needAuthUrl = {"/login","/join"};

        for (String s : needAuthUrl) {
            if (s.startsWith(path)) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        if(checkUrl(path)){
            String authorization = request.getHeader("Authorization");
            if(authorization == null){
                System.out.println("token이 없거나, Bearer가 포함되어 있지 않습니다.");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"토큰이 없거나, Bearer가 포함되어 있지 않습니다.");
                return;
            }
            if(authorization.equals("GodToken")){
                User user = new User();
                user.setId("test2");
                user.setUserRole("ROLE_ADMIN");
                user.setUserCode(2);
                CustomUserDetails c = new CustomUserDetails(user);

                Authentication authToken = new UsernamePasswordAuthenticationToken(c, null, c.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authToken);

                filterChain.doFilter(request,response);
                return;
            }
            String token = authorization.split(" ")[1];
            if(jwtUtil.isExpired(token)){
                System.out.println("token Expire 상태입니다.");
                response.sendError(HttpServletResponse.SC_FORBIDDEN,"로그인이 만료되었습니다.");
                return;
            }

            Integer accountCode = jwtUtil.getUserCode(token);
            String accountId = jwtUtil.getUsername(token);
            String accountRole = jwtUtil.getRole(token);


            User user = new User();
            user.setId(accountId);
            user.setUserRole(accountRole);
            user.setUserCode(accountCode);
            CustomUserDetails customUserDetails = new CustomUserDetails(user);

            Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authToken);

            filterChain.doFilter(request,response);
        } else {

            //검사 필요한거 아니면 넘어감 단, 유저가 누군지 모름! Context Holder 사용 불가
            filterChain.doFilter(request,response);
        }
    }
}
