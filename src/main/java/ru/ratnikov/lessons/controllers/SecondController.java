package ru.ratnikov.lessons.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mikhail Ratnikov
 */
@Controller
@RequestMapping("/second")
public class SecondController {

    @GetMapping("/exit")
    public String exit() {
        return "second/exit";
    }
}
