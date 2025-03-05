package com.project.princeps.princeps_finance_bot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String getPage() {
        return "bot_admin";
    }
}
