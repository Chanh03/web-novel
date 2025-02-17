package com.anhngo.nhaichuttruyen.utils;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError() {
        return "redirect:/đây là trang lỗi thông báo bằng restful api!, chưa có giao diện";
    }
}
