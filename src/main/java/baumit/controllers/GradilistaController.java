package baumit.controllers;

import baumit.dtos.GradilisteDto;
import baumit.dtos.GradilisteWithTasksDto;
import baumit.models.Gradiliste;
import baumit.services.GradilisteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/gradilista")
@RequiredArgsConstructor
public class GradilistaController {
    private final GradilisteService gradilisteService;

    @GetMapping("")
    public String showGradilista(Model model){
        List<GradilisteDto> gradilista = gradilisteService.allConstructionsDtos();
        model.addAttribute("constructions",gradilista);
        return "gradilista";
    }
}
