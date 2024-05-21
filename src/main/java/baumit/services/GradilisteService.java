package baumit.services;

import baumit.dtos.GradilisteWithTasksDto;
import baumit.mappers.GradilisteMapper;
import baumit.models.Gradiliste;
import baumit.repos.GradilisteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GradilisteService {
    private final GradilisteRepository gradilisteRepository;
    private final GradilisteMapper gradilisteMapper;

    public List<GradilisteWithTasksDto> allConstructionsWithTasksDtos(){
         List<Gradiliste> gradilista = gradilisteRepository.findAll();
         return gradilista.stream().map(gradilisteMapper::gradilisteToGradilisteWithTasksDto).collect(Collectors.toList());

    }
}
