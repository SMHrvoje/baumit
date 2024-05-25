package baumit.controllers;

import baumit.dtos.ZadatakDto;
import baumit.dtos.ZadatakRequestDto;
import baumit.services.ZadatakService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/zadatak")
public class ZadatakController {
    private final ZadatakService zadatakService;


    @PostMapping("")
    public ResponseEntity<Map<String,Object>> addTask(@Valid @ModelAttribute ZadatakRequestDto zadatakDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            //bindingResult.getAllErrors().forEach(System.out::println);
            Map<String, Object> response = new HashMap<>();
            response.put("error", bindingResult.getFieldError().getDefaultMessage());
            return ResponseEntity.badRequest().body(response);
        }
        zadatakService.saveNewZadatak(zadatakDto);
        return ResponseEntity.ok().build();
    };

    @PostMapping("/{id}")
    public ResponseEntity<Map<String,Object>>  modifyTask(@PathVariable int id,@Valid @ModelAttribute ZadatakDto zadatakDto,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            Map<String, Object> response = new HashMap<>();
            response.put("error", bindingResult.getFieldError().getDefaultMessage());
            return ResponseEntity.badRequest().body(response);
        }
        zadatakService.updateZadatak(zadatakDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("")
    public ResponseEntity<Boolean> deleteTask(@RequestParam("id") int id) {
        // Code to delete the task with the provided id
        zadatakService.deleteZadatak(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
