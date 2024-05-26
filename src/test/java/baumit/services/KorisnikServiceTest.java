package baumit.services;

import baumit.dtos.KorisnikDto;
import baumit.mappers.KorisnikMapper;
import baumit.models.Korisnik;
import baumit.repos.KorisnikRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class KorisnikServiceTest {

    @Mock
    private KorisnikRepository korisnikRepository;

    @Mock
    private KorisnikMapper korisnikMapper;

    @InjectMocks
    private KorisnikService korisnikService;

    private Korisnik korisnik1;
    private Korisnik korisnik2;
    private KorisnikDto korisnikDto1;
    private KorisnikDto korisnikDto2;

    @BeforeEach
    public void setUp() {
        korisnik1 = new Korisnik();
        korisnik1.setIdkorisnika(1);
        korisnik1.setKorisnickoime("user1");
        korisnik1.setIduloge(1);

        korisnik2 = new Korisnik();
        korisnik2.setIdkorisnika(2);
        korisnik2.setKorisnickoime("user2");
        korisnik2.setIduloge(2);

        korisnikDto1 = new KorisnikDto("user1", 1, 1);

        korisnikDto2 = new KorisnikDto("user2", 1,2);
    }

    @Test
    public void testVoditelji() {
        when(korisnikRepository.findAll()).thenReturn(Arrays.asList(korisnik1, korisnik2));
        when(korisnikMapper.korisniciDto(korisnik1)).thenReturn(korisnikDto1);
        when(korisnikMapper.korisniciDto(korisnik2)).thenReturn(korisnikDto2);

        List<KorisnikDto> voditelji = korisnikService.voditelji();

        assertEquals(1, voditelji.size());
        assertTrue(voditelji.contains(korisnikDto1));
        assertTrue(!voditelji.contains(korisnikDto2));

        verify(korisnikRepository, times(1)).findAll();
    }

    @Test
    public void testGetKorisnikById_Found() {
        when(korisnikRepository.findById(1)).thenReturn(Optional.of(korisnik1));

        String korisnickoime = korisnikService.getKorisnikById(1);

        assertEquals("user1", korisnickoime);

        verify(korisnikRepository, times(1)).findById(1);
    }

    @Test
    public void testGetKorisnikById_NotFound() {
        when(korisnikRepository.findById(1)).thenReturn(Optional.empty());

        String korisnickoime = korisnikService.getKorisnikById(1);

        assertEquals("Unknown", korisnickoime);

        verify(korisnikRepository, times(1)).findById(1);
    }
}
