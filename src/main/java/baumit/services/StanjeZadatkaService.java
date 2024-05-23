package baumit.services;

import baumit.dtos.StanjeZadatkaDto;
import baumit.mappers.StanjeZadatkaMapper;
import baumit.models.Stanjezadatka;
import baumit.repos.StanjezadatkaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StanjeZadatkaService {
    private final StanjezadatkaRepository stanjezadatkaRepository;
    private final StanjeZadatkaMapper stanjeZadatkaMapper;


    public List<StanjeZadatkaDto> allStanjaZadataka(){
        List<Stanjezadatka> stanjezadatkas = stanjezadatkaRepository.findAll();
        return stanjezadatkas.stream().map(stanjeZadatkaMapper::stanjeZadatkaToStanjeZadatkaDto).collect(Collectors.toList());
    };

}
