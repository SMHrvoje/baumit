package baumit.integration;

import baumit.dtos.GradilisteDto;
import baumit.models.Gradiliste;
import baumit.repos.GradilisteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class GradilisteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GradilisteRepository gradilisteRepository;

    @BeforeEach
    void setUp() {
        Gradiliste gradiliste = new Gradiliste();
        gradiliste.setIdgradilista(1);
        gradiliste.setIdkorisnika(1);
        gradiliste.setAdresa("Initial Address");
        gradiliste.setNaziv("Initial Name");
        gradilisteRepository.save(gradiliste);
    }

    @Test
    void testEditGradiliste() throws Exception {
        GradilisteDto gradilisteDto = new GradilisteDto(1, 1, "Updated Address", "Updated Name");

        mockMvc.perform(post("/gradiliste/edit")
                        .flashAttr("gradilisteDto", gradilisteDto))
                .andExpect(status().is2xxSuccessful());

        Gradiliste updatedGradiliste = gradilisteRepository.findById(gradilisteDto.idGradilista()).orElse(null);

        assert updatedGradiliste != null;
        assertThat(updatedGradiliste.getAdresa(), is(gradilisteDto.adresa()));
        assertThat(updatedGradiliste.getNaziv(), is(gradilisteDto.naziv()));
    }
}
