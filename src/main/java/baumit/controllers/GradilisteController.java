package baumit.controllers;

import baumit.dtos.GradilisteWithTasksDto;
import baumit.dtos.StanjeZadatkaDto;
import baumit.models.Stanjezadatka;
import baumit.services.GradilisteService;
import baumit.services.StanjeZadatkaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Tag(name = "Construction Controller", description = "APIs related to managing specific construction")
@RequiredArgsConstructor
@RequestMapping("/gradiliste")
public class GradilisteController {
    private final GradilisteService gradilisteService;
    private final StanjeZadatkaService stanjezadatkaService;


    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved construction"),
            @ApiResponse(responseCode = "404", description = "Construction not found")
    })
    @Operation(summary = "api for getting one specific construction depending on the id passed in the url")
    public String jednoGradiliste(Model model,@Parameter(description = "id of the construction that will be displayed",example = "1") @PathVariable int id){
        GradilisteWithTasksDto gradiliste = gradilisteService.constructionWithTasksDtos(id);
        model.addAttribute("construction", gradiliste);
        List<StanjeZadatkaDto> stanjaZadatakasDto = stanjezadatkaService.allStanjaZadataka();
        model.addAttribute("stanjaZadataka", stanjaZadatakasDto);
        return "gradiliste";
    };

}
