package baumit.integration;

import baumit.dtos.GradilisteDto;
import baumit.dtos.GradilisteRequestDto;
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
public class GradilistaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GradilisteRepository gradilisteRepository;

    @Test
    void testCreateGradiliste() throws Exception {
        GradilisteRequestDto gradilisteDto = new GradilisteRequestDto(1, "Test Address 123, 44444 Osijek, Hrvatska", "Test Name");
        long size = gradilisteRepository.count();

        mockMvc.perform(post("/gradilista")
                        .flashAttr("gradilisteRequestDto", gradilisteDto))
                .andExpect(status().is2xxSuccessful());

        long newSize = gradilisteRepository.count();

        assertThat(newSize, is(size + 1));
    }
}
