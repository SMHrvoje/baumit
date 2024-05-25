package baumit.controllers;

import baumit.dtos.*;
import baumit.models.Korisnik;
import baumit.models.Stanjezadatka;
import baumit.services.GradilisteService;
import baumit.services.KorisnikService;
import baumit.services.StanjeZadatkaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Tag(name = "Construction Controller", description = "APIs related to managing specific construction")
@RequiredArgsConstructor
@RequestMapping("/gradiliste")
public class GradilisteController {
    private final GradilisteService gradilisteService;
    private final KorisnikService korisnikService;
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
        List<KorisnikDto> voditelji = korisnikService.voditelji();
        model.addAttribute("voditelji", voditelji);
        return "gradiliste";
    };

    @PostMapping("/edit")
    public ResponseEntity<Map<String,Object>> editGradiliste(@Valid @ModelAttribute GradilisteDto gradilisteDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            Map<String, Object> response = new HashMap<>();
            response.put("error", bindingResult.getFieldError().getDefaultMessage());
            return ResponseEntity.badRequest().body(response);
        }
        gradilisteService.updateGradiliste(gradilisteDto);
        return ResponseEntity.ok().build();

    }

}
