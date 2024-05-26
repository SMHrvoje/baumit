package baumit.controllers;

import baumit.dtos.*;
import baumit.services.GradilisteService;
import baumit.services.KorisnikService;
import baumit.services.StanjeZadatkaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class GradilisteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private GradilisteService gradilisteService;

    @Mock
    private KorisnikService korisnikService;

    @Mock
    private StanjeZadatkaService stanjezadatkaService;

    @InjectMocks
    private GradilisteController gradilisteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(gradilisteController).build();
    }

    @Test
    void testJednoGradiliste() throws Exception {
        int gradilisteId = 1;
        GradilisteWithTasksDto gradiliste = new GradilisteWithTasksDto(1,1,"Test",
                "Test naziv", List.of(new ZadatakDto(1, gradilisteId, "Test zad", "Opis",
                1)));
        List<StanjeZadatkaDto> stanjaZadataka = List.of(new StanjeZadatkaDto(1, "Test"));
        List<KorisnikDto> voditelji = List.of(new KorisnikDto("Tester", 1, 1));

        when(gradilisteService.constructionWithTasksDtos(gradilisteId)).thenReturn(gradiliste);
        when(stanjezadatkaService.allStanjaZadataka()).thenReturn(stanjaZadataka);
        when(korisnikService.voditelji()).thenReturn(voditelji);

        mockMvc.perform(get("/gradiliste/{id}", gradilisteId))
                .andExpect(status().isOk())
                .andExpect(view().name("gradiliste"))
                .andExpect(model().attribute("construction", is(gradiliste)))
                .andExpect(model().attribute("stanjaZadataka", is(stanjaZadataka)))
                .andExpect(model().attribute("voditelji", is(voditelji)));

        verify(gradilisteService, times(1)).constructionWithTasksDtos(gradilisteId);
        verify(stanjezadatkaService, times(1)).allStanjaZadataka();
        verify(korisnikService, times(1)).voditelji();
    }

    @Test
    void testEditGradiliste() throws Exception {
        GradilisteDto gradilisteDto = new GradilisteDto(1, 1, "Test Adresa 22, 42240 Varazdin, Hrvatska", "Test Naziv");

        mockMvc.perform(post("/gradiliste/edit")
                        .flashAttr("gradilisteDto", gradilisteDto))
                .andExpect(status().is2xxSuccessful());

        verify(gradilisteService, times(1)).updateGradiliste(gradilisteDto);
    }
}
