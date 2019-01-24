package com.caogen.blog.controller;

import com.caogen.blog.annotation.LogInfoAnno;
import com.caogen.blog.dto.InfoResult;
import com.caogen.blog.entity.User;
import com.caogen.blog.finals.ErrorFinal;
import com.caogen.blog.service.AuthService;
import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
@Api(tags = "鉴权接口")
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @PostMapping
    @LogInfoAnno("登录鉴权")
    @ApiOperation(value = "登录获取token", notes = "登录成功之后返回token")
    public InfoResult createAuthenticationToken(
            @RequestBody User user) {
        InfoResult result = new InfoResult(HttpServletResponse.SC_OK);
        try {
            if (!Strings.isNullOrEmpty(user.getUserName()) && !Strings.isNullOrEmpty(user.getPassword())) {
                String token = authService.login(user.getUserName(), user.getPassword());
                result.setData(token);
            }
        } catch (AuthenticationException e) {
            result = new InfoResult(HttpServletResponse.SC_BAD_REQUEST);
            String msg;
            if (e instanceof BadCredentialsException) {
                msg = "用户名或密码输入错误，登录失败!";
            } else if (e instanceof InternalAuthenticationServiceException) {
                msg = "账户被禁用，登录失败，请联系管理员!";
            } else {
                msg = "登录失败!";
            }
            result.setMsg(msg);
            logger.error("createAuthenticationToken: " + msg);
            e.printStackTrace();
        } catch (Exception e) {
            result = new InfoResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            result.setMsg(ErrorFinal.ERROR);
            logger.error("createAuthenticationToken: " + e);
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/refresh")
    @LogInfoAnno("token刷新")
    @ApiOperation(value = "刷新token", notes = "通过oldToken刷新生成newToken")
    public InfoResult refreshAndGetAuthenticationToken(
            HttpServletRequest request) {
        InfoResult result = new InfoResult(HttpServletResponse.SC_OK);
        try {
            String token = request.getHeader(tokenHeader);
            String refreshedToken = authService.refresh(token);
            if (!Strings.isNullOrEmpty(refreshedToken)) {
                result.setData(refreshedToken);
            }
        } catch (Exception e) {
            result = new InfoResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            result.setMsg(ErrorFinal.ERROR);
            logger.error("refreshAndGetAuthenticationToken: " + e);
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/register")
    @LogInfoAnno("用户注册")
    @ApiOperation(value = "用户注册")
    public InfoResult register(@RequestBody User user) {
        InfoResult result = new InfoResult(HttpServletResponse.SC_OK);
        try {
            user = authService.register(user);
            if (user != null && user.getId() != null) {
                result.setData(user);
            }
        } catch (Exception e) {
            result = new InfoResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            result.setMsg(ErrorFinal.ERROR);
            logger.error("register: " + e);
            e.printStackTrace();
        }
        return result;
    }

}
