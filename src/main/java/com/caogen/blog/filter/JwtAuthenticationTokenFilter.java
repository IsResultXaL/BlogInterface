package com.caogen.blog.filter;

import com.alibaba.fastjson.JSON;
import com.caogen.blog.dto.InfoResult;
import com.caogen.blog.service.JwtUserDetailsService;
import com.caogen.blog.util.JwtTokenUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = httpServletRequest.getHeader(this.tokenHeader);
        logger.info("authHeader is " + authHeader);

        if (authHeader != null && authHeader.startsWith(this.tokenHead)) {
            try {
                final String authToken = authHeader.substring(tokenHead.length() + 1); //因为tokenHead后面要多个空格
                String userName = jwtTokenUtil.getUsernameFromToken(authToken);
                logger.info("checking authentication " + userName);

                if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(userName);

                    if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                        logger.info("authenticated user " + userName + ", setting security context");
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            }catch (JwtException e) {
                e.printStackTrace();
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpServletResponse.setContentType("application/json;charset=UTF-8");
                PrintWriter writer = httpServletResponse.getWriter();
                InfoResult infoResult = new InfoResult(HttpServletResponse.SC_UNAUTHORIZED);
                infoResult.setMsg(e.getMessage());
                writer.print(JSON.toJSONString(infoResult));
                return;
            }

        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
