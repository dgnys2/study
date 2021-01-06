package com.coresecurity.demo.controller.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserController {
    @GetMapping(value="/mypage")
    public String myPage() throws Exception {
        return "user/mypage";
    }

}
