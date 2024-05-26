package baumit.controllers;

import baumit.dtos.GradilisteDto;
import baumit.dtos.GradilisteRequestDto;
import baumit.dtos.KorisnikDto;
import baumit.services.GradilisteService;
import baumit.services.KorisnikService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class GradilistaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private GradilisteService gradilisteService;

    @Mock
    private KorisnikService korisnikService;

    @InjectMocks
    private GradilistaController gradilistaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(gradilistaController).build();
    }

    @Test
    void testGetGradilista() throws Exception {
        GradilisteDto gradilisteDto1 = new GradilisteDto(1, 1, "", "Gradiliste 1");
        GradilisteDto gradilisteDto2 = new GradilisteDto(2, 2,"","Gradiliste 2");
        List<GradilisteDto> constructions = Arrays.asList(gradilisteDto1, gradilisteDto2);
        when(gradilisteService.allConstructionsDtos()).thenReturn(constructions);
        when(korisnikService.getKorisnikById(1)).thenReturn("Korisnik 1");
        when(korisnikService.getKorisnikById(2)).thenReturn("Korisnik 2");

        KorisnikDto voditelj1 = new KorisnikDto( "Voditelj 1",1,1);
        KorisnikDto voditelj2 = new KorisnikDto( "Voditelj 2", 2, 1);
        List<KorisnikDto> voditelji = Arrays.asList(voditelj1, voditelj2);
        when(korisnikService.voditelji()).thenReturn(voditelji);

        mockMvc.perform(get("/gradilista"))
                .andExpect(status().isOk())
                .andExpect(view().name("gradilistaView"))
                .andExpect(model().attributeExists("constructions"))
                .andExpect(model().attributeExists("korisnikNamesById"))
                .andExpect(model().attributeExists("voditelji"));

        verify(gradilisteService, times(1)).allConstructionsDtos();
        verify(korisnikService, times(2)).getKorisnikById(anyInt());
        verify(korisnikService, times(1)).voditelji();
    }

    @Test
    void testAddGradilisteValid() throws Exception {
        GradilisteRequestDto gradilisteDto1 = new GradilisteRequestDto(1, "Adresa 123, 42222 Split, Hrvatska", "Naziv");

        mockMvc.perform(post("/gradilista")
                        .flashAttr("gradilisteRequestDto", gradilisteDto1))
                .andExpect(status().isOk());

        verify(gradilisteService, times(1)).saveGradiliste(gradilisteDto1);
    }

    @Test
    void testAddGradilisteInvalid() throws Exception {
        GradilisteRequestDto gradilisteDto1 = new GradilisteRequestDto(1, "Adresa", "");

        mockMvc.perform(post("/gradilista")
                        .flashAttr("gradilisteRequestDto", gradilisteDto1))
                .andExpect(status().isBadRequest());

        verify(gradilisteService, times(0)).saveGradiliste(gradilisteDto1);
    }
}

