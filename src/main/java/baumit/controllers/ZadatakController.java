package baumit.controllers;

import baumit.dtos.ZadatakDto;
import baumit.dtos.ZadatakRequestDto;
import baumit.services.ZadatakService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/zadatak")
public class ZadatakController {
    private final ZadatakService zadatakService;


    @PostMapping("")
    public String addTask(@ModelAttribute ZadatakRequestDto zadatakDto){
        zadatakService.saveNewZadatak(zadatakDto);
        return "redirect:/gradiliste/" + zadatakDto.idGradilista();
    };

    @PostMapping("/{id}")
    public String modifyTask(@PathVariable int id,@ModelAttribute ZadatakDto zadatakDto){
        zadatakService.updateZadatak(zadatakDto);
        return "redirect:/gradiliste/" + zadatakDto.idGradilista();
    }

    @DeleteMapping("")
    public ResponseEntity<Boolean> deleteTask(@RequestParam("id") int id) {
        // Code to delete the task with the provided id
        zadatakService.deleteZadatak(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
