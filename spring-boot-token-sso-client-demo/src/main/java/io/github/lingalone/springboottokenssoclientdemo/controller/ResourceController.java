package io.github.lingalone.springboottokenssoclientdemo.controller;


import io.github.lingalone.springboottokenssoclientdemo.util.CookieUtil;
import io.github.lingalone.springboottokenssoclientdemo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述：
 * <p>
 *
 * @author: 赵新国
 * @date: 2018/5/21 11:35
 */
@Controller
public class ResourceController {

    @Autowired
    JwtUtil jwtUtil;

    private static final String jwtTokenCookieName = "JWT-TOKEN";

    @RequestMapping("/")
    public String home() {
        return "redirect:/protected-resource";
    }

    @RequestMapping("/protected-resource")
    public String protectedResource() {
        return "protected-resource";
    }


    @RequestMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        jwtUtil.invalidateRelatedTokens(httpServletRequest);
        CookieUtil.clear(httpServletResponse, jwtTokenCookieName);
        return "redirect:/";
    }

}
