package org.tykfa90.microbloggingapp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @PostMapping(path = "/login")
    public void login() {}
}
