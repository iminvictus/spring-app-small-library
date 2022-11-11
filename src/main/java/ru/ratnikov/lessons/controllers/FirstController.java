package ru.ratnikov.lessons.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mikhail Ratnikov
 */
@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String surname,
                            Model model) {

        // System.out.println("Hi, " + name + " " + surname + "!");
        model.addAttribute("message", "Hi, " + name + " " + surname + "!");
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodbyePage() {
        return "first/goodbye";
    }

    @GetMapping("/calculator")
    public String doCalc(@RequestParam(value = "a", required = false) int a,
                         @RequestParam(value = "b", required = false) int b,
                         @RequestParam(value = "action", required = false) String action,
                         Model model) {
        String result;

        switch (action) {
            case "multiply":
                result = Double.toString(a * (double) b);
                break;
            case "add":
                result = Integer.toString(a + b);
                break;
            case "subtract":
                result = Integer.toString(a - b);
                break;
            case "divide":
                result = Double.toString(a / (double) b);
                break;
            default:
                result = "Unsupported operation";
                break;
        }

        model.addAttribute("calcResult", result);
        return "first/calculator";
    }
}
