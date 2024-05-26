package baumit.controllers;

import baumit.dtos.GradilisteDto;
import baumit.dtos.GradilisteRequestDto;
import baumit.dtos.KorisnikDto;
import baumit.services.GradilisteService;
import baumit.services.KorisnikService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/gradilista")
@RequiredArgsConstructor
public class GradilistaController {
    private final GradilisteService gradilisteService;
    private final KorisnikService korisnikService;

    @GetMapping
    public String getGradilista(Model model) {
        List<GradilisteDto> constructions = gradilisteService.allConstructionsDtos();
        Map<Integer, String> korisnikNamesById = new HashMap<>();

        for (GradilisteDto construction : constructions) {
            int korisnikId = construction.idKorisnika();
            String korisnikName = korisnikService.getKorisnikById(korisnikId);
            korisnikNamesById.put(construction.idGradilista(), korisnikName);
        }

        model.addAttribute("constructions", constructions);
        model.addAttribute("korisnikNamesById", korisnikNamesById);

        List<KorisnikDto> voditelji = korisnikService.voditelji();
        model.addAttribute("voditelji", voditelji);

        return "gradilistaView";
    }

    @PostMapping
    public ResponseEntity<Map<String,Object>> addGradiliste(@Valid @ModelAttribute GradilisteRequestDto gradilisteRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            Map<String, Object> response = new HashMap<>();
            response.put("error", bindingResult.getFieldError().getDefaultMessage());
            return ResponseEntity.badRequest().body(response);
        }
        gradilisteService.saveGradiliste(gradilisteRequestDto);
        return ResponseEntity.ok().build();
    }
}
