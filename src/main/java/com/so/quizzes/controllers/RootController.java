package com.so.quizzes.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RootController {
    @Value("${url-swagger-ui}")
    private String swaggerUrl;

    @GetMapping()
    public RedirectView redirectToDocumentation() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(swaggerUrl);
        return redirectView;
    }

}