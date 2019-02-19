package com.github.tpucal.spring5cglib.web;

import com.github.tpucal.spring5cglib.core.SeriousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// http://localhost:8080/a/greeting
@Controller
public class GreetingController {

    @Autowired
    SeriousService seriousService;

    @GetMapping("/greeting")
    public @ResponseBody
    String greeting(@RequestParam(defaultValue = "n/a") String param) {
        return "greeting " + seriousService.getResult(param);
    }

}