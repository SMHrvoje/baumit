package baumit.controllers;

import baumit.dtos.ZadatakDto;
import baumit.dtos.ZadatakRequestDto;
import baumit.services.ZadatakService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class ZadatakControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ZadatakService zadatakService;

    @InjectMocks
    private ZadatakController zadatakController;

    public ZadatakControllerTest() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(zadatakController).build();
    }

    @Test
    public void testAddTask_Success() throws Exception {
        ZadatakRequestDto zadatakDto = new ZadatakRequestDto(1, "Naziv", "Opis", 1);

        mockMvc.perform(post("/zadatak")
                        .flashAttr("zadatakRequestDto", zadatakDto))
                .andExpect(status().isOk());

        verify(zadatakService, times(1)).saveNewZadatak(zadatakDto);
    }

    @Test
    public void testAddTask_ValidationFailure() throws Exception {
        ZadatakRequestDto zadatakDto = new ZadatakRequestDto(1, "", "Opis", 1);

        mockMvc.perform(post("/zadatak")
                        .flashAttr("zadatakRequestDto", zadatakDto))
                .andExpect(status().isBadRequest());

        verify(zadatakService, times(0)).saveNewZadatak(zadatakDto);
    }

    @Test
    public void testModifyTask_Success() throws Exception {
        int taskId = 1;
        ZadatakDto zadatakDto = new ZadatakDto(1, 1,"Naziv", "Opis", 1);

        mockMvc.perform(post("/zadatak/{id}", taskId)
                        .flashAttr("zadatakDto", zadatakDto))
                .andExpect(status().isOk());

        verify(zadatakService, times(1)).updateZadatak(zadatakDto);
    }

    @Test
    public void testModifyTask_ValidationFailure() throws Exception {
        int taskId = 1;
        ZadatakDto zadatakDto = new ZadatakDto(1, 1,"", "Opis", 1);

        mockMvc.perform(post("/zadatak/{id}", taskId)
                        .flashAttr("zadatakDto", zadatakDto))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }
}
