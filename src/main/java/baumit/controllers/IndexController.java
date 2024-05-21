package baumit.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import io.swagger.v3.oas.annotations.*;

@Controller
@RequiredArgsConstructor
public class IndexController  {


    @GetMapping("")
    @Operation(summary = "demo api")
    private String index(Model model){
        return "index";
    }


}
