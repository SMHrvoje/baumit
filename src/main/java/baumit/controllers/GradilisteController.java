package baumit.controllers;

import baumit.dtos.GradilisteWithTasksDto;
import baumit.services.GradilisteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/gradiliste")
public class GradilisteController {
    private final GradilisteService gradilisteService;


    @GetMapping("/{id}")
    public String jednoGradiliste(Model model, @PathVariable int id){
        GradilisteWithTasksDto gradiliste = gradilisteService.constructionWithTasksDtos(id);
        System.out.println(gradiliste.zadaci());
        model.addAttribute("construction", gradiliste);
        return "gradiliste";
    };

}
