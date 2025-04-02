package com.anhngo.nhaichuttruyen.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeRest {

    @GetMapping("/")
    public String index() {
        return "\"Hello World!\"";
    }

}
