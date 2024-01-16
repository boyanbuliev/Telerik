package org.telerik.web.beertag.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.server.ResponseStatusException;
import org.telerik.web.beertag.exceptions.AuthorizationException;
import org.telerik.web.beertag.exceptions.DuplicateEntityException;
import org.telerik.web.beertag.exceptions.EntityNotFoundException;
import org.telerik.web.beertag.helpers.AuthenticationHelper;
import org.telerik.web.beertag.helpers.BeerMapper;
import org.telerik.web.beertag.models.Beer;
import org.telerik.web.beertag.models.User;
import org.telerik.web.beertag.models.dtos.BeerDto;
import org.telerik.web.beertag.services.BeerService;

import static org.telerik.web.beertag.Helpers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BeerControllerTests {
//
//    @MockBean
//    BeerService mockService;
//    @MockBean
//    BeerMapper mockMapper;
//    @MockBean
//    AuthenticationHelper mockHelper;
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Test
//    public void getById_Should_return_StatusOK_When_BeerExists() throws Exception {
//        Beer mockBeer = createMockBeer();
//
//        Mockito.when(mockService.getById(mockBeer.getId())).thenReturn(mockBeer);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/beers/{id}", mockBeer.getId())).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.name").value(mockBeer.getName()));
//    }
//
//    @Test
//    public void getById_Should_return_StatusNotFound_When_BeerDoesNotExist() throws Exception {
//        Mockito.when(mockService.getById(Mockito.anyInt())).thenThrow(new EntityNotFoundException("Beer", 1));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/beers/{id}", Mockito.anyInt())).andExpect(MockMvcResultMatchers.status().isNotFound());
//    }
//
//    @Test
//    public void getByName_Should_return_StatusOK_When_BeerExists() throws Exception {
//        Beer mockBeer = createMockBeer();
//
//        Mockito.when(mockService.getByName(mockBeer.getName())).thenReturn(mockBeer);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/beers/search").param("name", mockBeer.getName()))
//                .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.name").value(mockBeer.getName()));
//    }
//
//    @Test
//    public void getByName_Should_return_StatusNotFound_When_BeerDoesNotExist() throws Exception {
//        Mockito.when(mockService.getByName(Mockito.anyString())).thenThrow(new EntityNotFoundException("Beer", 1));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/beers/search").param("name", Mockito.anyString()))
//                .andExpect(MockMvcResultMatchers.status().isNotFound());
//    }
//
//    @Test
//    public void create_Should_return_StatusOK_When_BeerCreated() throws Exception {
//        Beer mockBeer = createMockBeer();
//        User mockUser = createMockUser();
//        String dto = toJson(createBeerDto());
//
//        Mockito.when(mockHelper.tryGetUser(Mockito.any(HttpHeaders.class)))
//                .thenReturn(mockUser);
//
//        Mockito.when(mockMapper.fromDto(Mockito.any(BeerDto.class)))
//                .thenReturn(mockBeer);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/beers").contentType(MediaType.APPLICATION_JSON).content(dto))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    public void create_Should_return_StatusConflict_When_DuplicateBeer() throws Exception {
//        Beer mockBeer = createMockBeer();
//        User mockUser = createMockUser();
//        String dto = toJson(createBeerDto());
//
//        Mockito.when(mockHelper.tryGetUser(Mockito.any(HttpHeaders.class)))
//                .thenReturn(mockUser);
//
//        Mockito.when(mockMapper.fromDto(Mockito.any(BeerDto.class)))
//                .thenReturn(mockBeer);
//
//        Mockito.doThrow(DuplicateEntityException.class).when(mockService)
//                .create(Mockito.any(Beer.class), Mockito.any(User.class));
//
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/beers").contentType(MediaType.APPLICATION_JSON)
//                .content(dto)).andExpect(MockMvcResultMatchers.status().isConflict());
//    }
//
//    @Test
//    public void create_Should_return_StatusUnauthorized_When_UserUnauthorized() throws Exception {
//        Mockito.when(mockHelper.tryGetUser(Mockito.any(HttpHeaders.class))).thenThrow(AuthorizationException.class);
//        String dto = toJson(createBeerDto());
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/beers").contentType(MediaType.APPLICATION_JSON).content(dto))
//                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
//    }
//
//    @Test
//    public void update_Should_return_StatusConflict_When_DuplicateBeer() throws Exception {
//        Beer mockBeer = createMockBeer();
//        User mockUser = createMockUser();
//        String dto = toJson(createBeerDto());
//
//        Mockito.when(mockHelper.tryGetUser(Mockito.any(HttpHeaders.class)))
//                .thenReturn(mockUser);
//
//        Mockito.when(mockMapper.fromDto(Mockito.any(BeerDto.class), Mockito.anyInt()))
//                .thenReturn(mockBeer);
//
//        Mockito.doThrow(DuplicateEntityException.class).when(mockService)
//                .update(Mockito.any(Beer.class), Mockito.anyInt(), Mockito.any(User.class));
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/beers/{id}", Mockito.anyInt()).contentType(MediaType.APPLICATION_JSON)
//                .content(dto)).andExpect(MockMvcResultMatchers.status().isConflict());
//    }
//
//    @Test
//    public void update_Should_return_StatusUnauthorized_When_UserUnauthorized() throws Exception {
//
//    }
//
//    @Test
//    public void update_Should_return_StatusNotFound_When_BeerDoesNotExist() throws Exception {
//
//
//    }
//}

    @MockBean
    BeerService mockService;

    @MockBean
    BeerMapper mockBeerMapper;

    @MockBean
    AuthenticationHelper mockAuthenticationHelper;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getById_Should_ReturnStatusOk_When_BeerExists() throws Exception {

        Beer mockBeer = createMockBeer();

        Mockito.when(mockService.getById(mockBeer.getId())).thenReturn(mockBeer);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/beers/{id}", mockBeer.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.name")
                        .value(mockBeer.getName()));
    }

    @Test
    public void getById_Should_ReturnStatusNotFound_When_BeerDoesntExist() throws Exception {
        // Arrange
        Mockito.when(mockService.getById(Mockito.anyInt()))
                .thenThrow(EntityNotFoundException.class);

        // Act, Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/beers/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void create_Should_ReturnStatusOk_When_CorrectRequest() throws Exception {
        // Arrange
        User mockUser = createMockUser();

        Mockito.when(mockAuthenticationHelper.tryGetUser(Mockito.any()))
                .thenReturn(mockUser);

        Beer mockBeer = createMockBeer();

        Mockito.when(mockBeerMapper.fromDto(Mockito.any()))
                .thenReturn(mockBeer);

        // Act, Assert
        String body = toJson(createBeerDto());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/beers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void create_Should_ReturnStatusUnauthorized_When_AuthorizationIsMissing() throws Exception {
        // Arrange
        Mockito.when(mockAuthenticationHelper.tryGetUser(Mockito.any()))
                .thenThrow(new ResponseStatusException(HttpStatus.UNAUTHORIZED, null));

        // Act, Assert
        String body = toJson(createBeerDto());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/beers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    public void create_Should_ReturnStatusBadRequest_When_BodyIsInvalid() throws Exception {
        // Arrange
        BeerDto dto = createBeerDto();
        dto.setName(null);

        // Act, Assert
        String body = toJson(dto);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/beers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void create_Should_ReturnStatusNotFound_WhenStyleDoesntExist() throws Exception {
        // Arrange
        User mockUser = createMockUser();

        Mockito.when(mockAuthenticationHelper.tryGetUser(Mockito.any()))
                .thenReturn(mockUser);

        Mockito.when(mockBeerMapper.fromDto(Mockito.any()))
                .thenThrow(EntityNotFoundException.class);

        // Act, Assert
        String body = toJson(createBeerDto());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/beers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void create_Should_ReturnStatusConflict_WhenBeerWithSameNameExists() throws Exception {
        // Arrange
        User mockUser = createMockUser();
        Beer mockBeer = createMockBeer();

        Mockito.when(mockAuthenticationHelper.tryGetUser(Mockito.any()))
                .thenReturn(mockUser);

        Mockito.when(mockBeerMapper.fromDto(Mockito.any()))
                .thenReturn(mockBeer);

        Mockito.doThrow(DuplicateEntityException.class)
                .when(mockService)
                .create(mockBeer, mockUser);

        // Act, Assert
        String body = toJson(createBeerDto());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/beers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }

    @Test
    public void update_Should_ReturnStatusOk_When_CorrectRequest() throws Exception {
        // Arrange
        User mockUser = createMockUser();

        Mockito.when(mockAuthenticationHelper.tryGetUser(Mockito.any()))
                .thenReturn(mockUser);

        Beer mockBeer = createMockBeer();

        Mockito.when(mockBeerMapper.fromDto(Mockito.any(), Mockito.anyInt()))
                .thenReturn(mockBeer);

        // Act, Assert
        String body = toJson(createBeerDto());
        mockMvc.perform(MockMvcRequestBuilders.put("/api/beers/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void update_Should_ReturnStatusUnauthorized_When_AuthorizationIsMissing() throws Exception {
        // Arrange
        Mockito.when(mockAuthenticationHelper.tryGetUser(Mockito.any()))
                .thenThrow(new ResponseStatusException(HttpStatus.UNAUTHORIZED, null));

        // Act, Assert
        String body = toJson(createBeerDto());
        mockMvc.perform(MockMvcRequestBuilders.put("/api/beers/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    public void update_Should_ReturnStatusBadRequest_When_BodyIsInvalid() throws Exception {
        // Arrange
        BeerDto dto = createBeerDto();
        dto.setName(null);

        // Act, Assert
        String body = toJson(dto);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/beers/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void update_Should_ReturnStatusNotFound_When_BeerOrStyleDoesntExist() throws Exception {
        // Arrange
        User mockUser = createMockUser();

        Mockito.when(mockAuthenticationHelper.tryGetUser(Mockito.any()))
                .thenReturn(mockUser);

        Mockito.when(mockBeerMapper.fromDto(Mockito.any(), Mockito.anyInt()))
                .thenThrow(EntityNotFoundException.class);

        // Act, Assert
        String body = toJson(createBeerDto());
        mockMvc.perform(MockMvcRequestBuilders.put("/api/beers/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void update_Should_ReturnStatusConflict_When_BeerWithSameNameExists() throws Exception {
        // Arrange
        User mockUser = createMockUser();

        Mockito.when(mockAuthenticationHelper.tryGetUser(Mockito.any()))
                .thenReturn(mockUser);

        Beer mockBeer = createMockBeer();

        Mockito.when(mockBeerMapper.fromDto(Mockito.any(), Mockito.anyInt()))
                .thenReturn(mockBeer);

        Mockito.doThrow(DuplicateEntityException.class)
                .when(mockService)
                .update(Mockito.any(Beer.class), Mockito.anyInt(), Mockito.any(User.class));

        // Act, Assert
        String body = toJson(createBeerDto());
        mockMvc.perform(MockMvcRequestBuilders.put("/api/beers/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }

    @Test
    public void update_Should_ReturnStatusUnauthorized_When_UserIsNotAuthorizedToEdit() throws Exception {
        // Arrange
        User mockUser = createMockUser();

        Mockito.when(mockAuthenticationHelper.tryGetUser(Mockito.any()))
                .thenReturn(mockUser);

        Beer mockBeer = createMockBeer();

        Mockito.when(mockBeerMapper.fromDto(Mockito.any(), Mockito.anyInt()))
                .thenReturn(mockBeer);

        Mockito.doThrow(AuthorizationException.class)
                .when(mockService)
                .update(mockBeer, 1, mockUser);

        // Act, Assert
        String body = toJson(createBeerDto());
        mockMvc.perform(MockMvcRequestBuilders.put("/api/beers/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    public void delete_Should_ReturnStatusOk_When_CorrectRequest() throws Exception {
        // Arrange
        User mockUser = createMockUser();

        Mockito.when(mockAuthenticationHelper.tryGetUser(Mockito.any()))
                .thenReturn(mockUser);

        // Act, Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/beers/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void delete_Should_ReturnStatusUnauthorized_When_AuthorizationIsMissing() throws Exception {
        // Arrange
        Mockito.when(mockAuthenticationHelper.tryGetUser(Mockito.any()))
                .thenThrow(new ResponseStatusException(HttpStatus.UNAUTHORIZED, null));

        // Act, Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/beers/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    public void delete_Should_ReturnStatusNotFound_When_BeerDoesNotExist() throws Exception {
        // Arrange
        User mockUser = createMockUser();

        Mockito.when(mockAuthenticationHelper.tryGetUser(Mockito.any()))
                .thenReturn(mockUser);

        Mockito.doThrow(EntityNotFoundException.class)
                .when(mockService)
                .delete(1, mockUser);

        // Act, Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/beers/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void delete_Should_ReturnStatusUnauthorized_When_UserIsNotAuthorizedToEdit() throws Exception {
        // Arrange
        User mockUser = createMockUser();

        Mockito.when(mockAuthenticationHelper.tryGetUser(Mockito.any()))
                .thenReturn(mockUser);

        Mockito.doThrow(AuthorizationException.class)
                .when(mockService)
                .delete(1, mockUser);

        // Act, Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/beers/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

}