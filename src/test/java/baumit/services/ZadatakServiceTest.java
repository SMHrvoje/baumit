package baumit.services;

import baumit.dtos.ZadatakDto;
import baumit.dtos.ZadatakRequestDto;
import baumit.models.Zadatak;
import baumit.repos.ZadatakRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ZadatakServiceTest {

    @Mock
    private ZadatakRepository zadatakRepository;

    @InjectMocks
    private ZadatakService zadatakService;

    private Zadatak zadatak;
    private ZadatakDto zadatakDto;
    private ZadatakRequestDto zadatakRequestDto;

    @BeforeEach
    public void setUp() {

        zadatak = new Zadatak();
        zadatak.setIdzadatka(1);
        zadatak.setNaziv("Task 1");
        zadatak.setOpis("Opis 1");
        zadatak.setIdgradilista(1);
        zadatak.setIdstanjazadatka(1);

        zadatakDto = new ZadatakDto(1, 1, "Task 1", "Opis 1", 1);


        zadatakRequestDto = new ZadatakRequestDto(1, "Task 1", "Opis 1", 1);

    }

    @Test
    public void testSaveNewZadatak() {
        when(zadatakRepository.save(any(Zadatak.class))).thenReturn(zadatak);

        boolean result = zadatakService.saveNewZadatak(zadatakRequestDto);

        assertTrue(result);
        verify(zadatakRepository, times(1)).save(any(Zadatak.class));
    }

    @Test
    public void testUpdateZadatak() {
        when(zadatakRepository.save(any(Zadatak.class))).thenReturn(zadatak);

        boolean result = zadatakService.updateZadatak(zadatakDto);

        assertTrue(result);
        verify(zadatakRepository, times(1)).save(any(Zadatak.class));
    }

    @Test
    public void testDeleteZadatak_Found() {
        when(zadatakRepository.findById(1)).thenReturn(Optional.of(zadatak));

        boolean result = zadatakService.deleteZadatak(1);

        assertTrue(result);
        verify(zadatakRepository, times(1)).findById(1);
        verify(zadatakRepository, times(1)).delete(zadatak);
    }

    @Test
    public void testDeleteZadatak_NotFound() {
        when(zadatakRepository.findById(1)).thenReturn(Optional.empty());

        boolean result = zadatakService.deleteZadatak(1);

        assertFalse(result);
        verify(zadatakRepository, times(1)).findById(1);
        verify(zadatakRepository, times(0)).delete(any(Zadatak.class));
    }
}
