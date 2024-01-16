package org.telerik.web.beertag.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telerik.web.beertag.exceptions.DuplicateEntityException;
import org.telerik.web.beertag.exceptions.EntityNotFoundException;
import org.telerik.web.beertag.exceptions.UnauthorizedOperationException;
import org.telerik.web.beertag.models.Beer;
import org.telerik.web.beertag.models.FilterOptions;
import org.telerik.web.beertag.models.User;
import org.telerik.web.beertag.repositories.BeerRepository;

import java.util.List;

import static org.telerik.web.beertag.Helpers.*;

@ExtendWith(MockitoExtension.class)
public class BeerServiceTests {
    @Mock
    BeerRepository mockRepository;
    @InjectMocks
    BeerServiceImpl service;

    @Test
    public void get_Should_ReturnListOfBeers_When_Exists() {
        FilterOptions mockFilterOptions = createMockFilterOptions();
        Beer mockBeer1 = createMockBeer();
        mockBeer1.setName("MockBeer1");
        Beer mockBeer2 = createMockBeer();
        mockBeer2.setName("MockBeer2");
        Beer mockBeer3 = createMockBeer();
        mockBeer2.setName("MockBeer3");

        Mockito.when(mockRepository.get(mockFilterOptions)).thenReturn(List.of(mockBeer1, mockBeer2, mockBeer3));
        Assertions.assertEquals(List.of(mockBeer1, mockBeer2, mockBeer3), service.get(mockFilterOptions));
    }

    @Test
    public void getById_Should_ReturnBeer_When_MatchExists() {
        Mockito.when(mockRepository.get(2)).thenReturn(new Beer(2, "MockBeerName", 1.3));

        Beer result = service.getById(2);

        Assertions.assertEquals(2, result.getId());
        Assertions.assertEquals("MockBeerName", result.getName());
        Assertions.assertEquals(1.3, result.getAbv());
    }

    @Test
    public void getByName_Should_ReturnBeer_When_MatchExists() {
        Mockito.when(mockRepository.get("MockBeerName")).thenReturn(new Beer(2, "MockBeerName", 1.3));

        Beer result = service.getByName("MockBeerName");

        Assertions.assertEquals(2, result.getId());
        Assertions.assertEquals("MockBeerName", result.getName());
        Assertions.assertEquals(1.3, result.getAbv());
    }

    @Test
    public void create_Should_Throw_When_BeerWithSameNameExists() {
        Beer mockBeer = createMockBeer();
        User mockUser = createMockUser();
        Mockito.when(mockRepository.get(mockBeer.getName())).thenReturn(mockBeer);

        Assertions.assertThrows(DuplicateEntityException.class, () -> service.create(mockBeer, mockUser));

    }

    @Test
    public void create_Should_CallRepository_WhenBeerWithSameNameExists() {
        Beer mockBeer = createMockBeer();
        User mockUser = createMockUser();
        Mockito.when(mockRepository.get(mockBeer.getName())).thenThrow(new EntityNotFoundException("Beer", "name", mockBeer.getName()));

        service.create(mockBeer, mockUser);

        Mockito.verify(mockRepository, Mockito.times(1)).create(Mockito.any(Beer.class));
    }

    @Test
    public void update_Should_Throw_When_UserIsNotCreatorOrAdmin() {
        Beer mockBeer = createMockBeer();
        User mockUser = createMockUser();
        mockUser.setUsername("MockUser2");
        Mockito.when(mockRepository.get(Mockito.anyInt())).thenReturn(mockBeer);
        Assertions.assertThrows(UnauthorizedOperationException.class, () -> service.update(mockBeer, 1, mockUser));
    }

    @Test
    public void update_Should_Throw_When_BeerNameIsTaken() {
        Beer mockBeer = createMockBeer();
        Beer anotherMockBeer = createMockBeer();
        anotherMockBeer.setId(2);
        Mockito.when(mockRepository.get(Mockito.anyInt())).thenReturn(mockBeer);
        Mockito.when(mockRepository.get(Mockito.anyString())).thenReturn(anotherMockBeer);
        Assertions.assertThrows(DuplicateEntityException.class, () -> service.update(mockBeer, 2, mockBeer.getCreatedBy()));
    }

    @Test
    public void delete_Should_Throw_When_UserIsNotCreatorOrAdmin() {
        Beer mockBeer = createMockBeer();
        User mockUser = createMockUser();
        mockUser.setUsername("MockUser2");
        Mockito.when(mockRepository.get(Mockito.anyInt())).thenReturn(mockBeer);
        Assertions.assertThrows(UnauthorizedOperationException.class, () -> service.delete(1, mockUser));
    }
}
