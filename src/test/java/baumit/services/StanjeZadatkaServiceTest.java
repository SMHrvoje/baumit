package baumit.services;

import baumit.dtos.StanjeZadatkaDto;
import baumit.mappers.StanjeZadatkaMapper;
import baumit.models.Stanjezadatka;
import baumit.repos.StanjezadatkaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StanjeZadatkaServiceTest {

    @Mock
    private StanjezadatkaRepository stanjezadatkaRepository;

    @Mock
    private StanjeZadatkaMapper stanjeZadatkaMapper;

    @InjectMocks
    private StanjeZadatkaService stanjeZadatkaService;

    private Stanjezadatka stanjezadatka1;
    private Stanjezadatka stanjezadatka2;
    private StanjeZadatkaDto stanjeZadatkaDto1;
    private StanjeZadatkaDto stanjeZadatkaDto2;

    @BeforeEach
    public void setUp() {
        stanjezadatka1 = new Stanjezadatka();
        stanjezadatka1.setIdstanjazadatka(1);
        stanjezadatka1.setNaziv("State 1");

        stanjezadatka2 = new Stanjezadatka();
        stanjezadatka2.setIdstanjazadatka(2);
        stanjezadatka2.setNaziv("State 2");

        stanjeZadatkaDto1 = new StanjeZadatkaDto(1, "State 1");


        stanjeZadatkaDto2 = new StanjeZadatkaDto(2, "State 2");

    }

    @Test
    public void testAllStanjaZadataka() {
        when(stanjezadatkaRepository.findAll()).thenReturn(Arrays.asList(stanjezadatka1, stanjezadatka2));
        when(stanjeZadatkaMapper.stanjeZadatkaToStanjeZadatkaDto(stanjezadatka1)).thenReturn(stanjeZadatkaDto1);
        when(stanjeZadatkaMapper.stanjeZadatkaToStanjeZadatkaDto(stanjezadatka2)).thenReturn(stanjeZadatkaDto2);

        List<StanjeZadatkaDto> allStanjaZadataka = stanjeZadatkaService.allStanjaZadataka();

        assertEquals(2, allStanjaZadataka.size());
        assertTrue(allStanjaZadataka.contains(stanjeZadatkaDto1));
        assertTrue(allStanjaZadataka.contains(stanjeZadatkaDto2));

        verify(stanjezadatkaRepository, times(1)).findAll();
    }
}
