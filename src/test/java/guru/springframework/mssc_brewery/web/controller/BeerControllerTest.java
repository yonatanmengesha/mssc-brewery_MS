package guru.springframework.mssc_brewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.mssc_brewery.services.BeerService;
import guru.springframework.mssc_brewery.web.model.BeerDto;
import guru.springframework.mssc_brewery.web.model.v2.BeerStyleEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.core.Is.is;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BeerController.class)
public class BeerControllerTest {

    @MockBean
    BeerService beerService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    BeerDto validBeer;

    @Before
    public void setUp() throws Exception {


        validBeer = BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("Beer1")
                .beerStyle("PALE_ALE")
                .upc(123456789012L).build();
    }

    @Test
    public void getBeer() throws Exception {

     given(beerService.getBeerById(any(UUID.class))).willReturn(validBeer);
     mockMvc.perform(get("/api/v1/beer/" +
             validBeer.getId().toString()).accept(MediaType.APPLICATION_JSON))
             .andExpect(status().isOk())
             .andExpect(content().contentType(MediaType.APPLICATION_JSON))
             .andExpect(jsonPath("$.id",is(validBeer.getId().toString())))
             .andExpect(jsonPath("$.beerName",is("Beer1")));

    }

    @Test
    public void handlePost() throws Exception {

        BeerDto beerDto = validBeer;
        beerDto.setId(null);
        BeerDto savedDto = BeerDto.builder().id(UUID.randomUUID()).beerName("New Beer").build();

        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        given(beerService.saveNewBeer(any())).willReturn(savedDto);

        mockMvc.perform(post("/api/v1/beer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());


    }

    @Test
    public void handleUpdate() throws Exception {

        //given

        BeerDto beerDto = validBeer;
        beerDto.setId(null);
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        //when

        mockMvc.perform(put("/api/v1/beer/"+ UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson)
        ).andExpect(status().isNoContent());

        //then

        then(beerService).should().updateBeer(any(),any());
    }
}