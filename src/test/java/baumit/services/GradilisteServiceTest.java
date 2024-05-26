package baumit.services;

import baumit.dtos.GradilisteDto;
import baumit.dtos.GradilisteRequestDto;
import baumit.dtos.GradilisteWithTasksDto;
import baumit.mappers.GradilisteMapper;
import baumit.models.Gradiliste;
import baumit.models.Zadatak;
import baumit.repos.GradilisteRepository;
import baumit.repos.ZadatakRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class GradilisteServiceTest {

    @Mock
    private GradilisteRepository gradilisteRepository;

    @Mock
    private GradilisteMapper gradilisteMapper;

    @InjectMocks
    private GradilisteService gradilisteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAllConstructionsDtos() {
        List<Gradiliste> gradilista = new ArrayList<>();
        Gradiliste gradiliste = new Gradiliste();
        gradiliste.setIdgradilista(1);
        gradiliste.setIdkorisnika(1);
        gradiliste.setAdresa("Test Address");
        gradiliste.setNaziv("Test Name");
        gradilista.add(gradiliste);


        when(gradilisteRepository.findAll()).thenReturn(gradilista);
        when(gradilisteMapper.gradilisteToGradilisteDto(gradiliste)).thenReturn(new GradilisteDto(1, 1, "Test Address", "Test Name"));

        List<GradilisteDto> result = gradilisteService.allConstructionsDtos();

        assertThat(result.size(), is(1));
        verify(gradilisteRepository, times(1)).findAll();
        verify(gradilisteMapper, times(1)).gradilisteToGradilisteDto(any(Gradiliste.class));
    }

    @Test
    void testConstructionWithTasksDtos() {
        Gradiliste gradiliste = new Gradiliste();
        gradiliste.setIdgradilista(1);

        GradilisteWithTasksDto dto = new GradilisteWithTasksDto(1, 1, "Test Address", "Test Name", new ArrayList<>());

        when(gradilisteRepository.findById(1)).thenReturn(Optional.of(gradiliste));
        when(gradilisteMapper.gradilisteToGradilisteWithTasksDto(any(Gradiliste.class))).thenReturn(dto);

        GradilisteWithTasksDto result = gradilisteService.constructionWithTasksDtos(1);

        assertThat(result, is(dto));
        verify(gradilisteRepository, times(1)).findById(1);
        verify(gradilisteMapper, times(1)).gradilisteToGradilisteWithTasksDto(any(Gradiliste.class));
    }

    @Test
    void testSaveGradiliste() {
        GradilisteRequestDto requestDto = new GradilisteRequestDto(1, "Test Address", "Test Name");
        Gradiliste gradiliste = new Gradiliste();
        gradiliste.setNaziv("Test Name");
        gradiliste.setAdresa("Test Address");
        gradiliste.setIdkorisnika(1);

        when(gradilisteRepository.save(any(Gradiliste.class))).thenReturn(gradiliste);

        Gradiliste result = gradilisteService.saveGradiliste(requestDto);

        assertThat(result.getNaziv(), is("Test Name"));
        assertThat(result.getAdresa(), is("Test Address"));
        assertThat(result.getIdkorisnika(), is(1));
        verify(gradilisteRepository, times(1)).save(any(Gradiliste.class));
    }

    @Test
    void testUpdateGradiliste() {
        GradilisteDto gradilisteDto = new GradilisteDto(1, 1, "Updated Address", "Updated Name");
        Gradiliste gradiliste = new Gradiliste();
        gradiliste.setIdgradilista(1);

        when(gradilisteRepository.findById(1)).thenReturn(Optional.of(gradiliste));

        gradilisteService.updateGradiliste(gradilisteDto);

        ArgumentCaptor<Gradiliste> gradilisteArgumentCaptor = ArgumentCaptor.forClass(Gradiliste.class);
        verify(gradilisteRepository).save(gradilisteArgumentCaptor.capture());

        Gradiliste updatedGradiliste = gradilisteArgumentCaptor.getValue();
        assertThat(updatedGradiliste.getNaziv(), is("Updated Name"));
        assertThat(updatedGradiliste.getAdresa(), is("Updated Address"));
        assertThat(updatedGradiliste.getIdkorisnika(), is(1));
    }

    @Test
    void testUpdateGradiliste_NotFound() {
        GradilisteDto gradilisteDto = new GradilisteDto(1, 1, "Updated Address", "Updated Name");

        when(gradilisteRepository.findById(1)).thenReturn(Optional.empty());

        try {
            gradilisteService.updateGradiliste(gradilisteDto);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Invalid construction Id:1"));
        }

        verify(gradilisteRepository, never()).save(any(Gradiliste.class));
    }
}
