package ru.ratnikov.lessons.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mikhail Ratnikov
 */
@Controller
@RequestMapping("")
public class AppController {
    @GetMapping()
    public String index (Model model) {
        return "index";
    }
}
