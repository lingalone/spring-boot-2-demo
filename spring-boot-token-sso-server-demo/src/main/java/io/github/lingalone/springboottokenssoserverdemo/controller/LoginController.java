package io.github.lingalone.springboottokenssoserverdemo.controller;


import io.github.lingalone.springboottokenssoserverdemo.util.CookieUtil;
import io.github.lingalone.springboottokenssoserverdemo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 * <p>
 *
 * @author: 赵新国
 * @date: 2018/5/21 11:03
 */
@Controller
public class LoginController {

    @Autowired
    JwtUtil jwtUtil;

    private static final String jwtTokenCookieName = "JWT-TOKEN";
    // 签名key
    private static final String signingKey = "signingKey";

    private static final Map<String, String> credentials = new HashMap<>();

    // 构造器初始化账号信息
    public LoginController() {
        credentials.put("admin", "admin");
    }

    @RequestMapping("/")
    public String home() {
        return "login";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login?logout")
    public String logout() {
        return "login";
    }


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(HttpServletResponse httpServletResponse, String username, String password, String redirect, Model model) {
        if (username == null || !credentials.containsKey(username) || !credentials.get(username).equals(password)) {
            model.addAttribute("error", "Invalid username or password!");
            return "login";
        }
        String token = jwtUtil.generateToken(signingKey, username);
        CookieUtil.create(httpServletResponse, jwtTokenCookieName, token, false, -1, "localhost");
        return "redirect:" + redirect;
    }

}
