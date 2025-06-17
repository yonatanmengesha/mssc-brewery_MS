package guru.springframework.mssc_brewery.web.controller.v2;

import guru.springframework.mssc_brewery.services.v2.BeerServiceV2;
import guru.springframework.mssc_brewery.web.model.v2.BeerDtoV2;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v2/beer")
public class BeerControllerV2 {

    private final BeerServiceV2 beerServiceV2;




    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDtoV2> getBeer(@PathVariable UUID beerId){

        return new ResponseEntity<>(beerServiceV2.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity handlePost(@Valid @RequestBody BeerDtoV2 beerDtoV2){

        val savedDtoV2 = beerServiceV2.saveNewBeer(beerDtoV2);
        val headers = new HttpHeaders();
        headers.add("Location","/api/v2/beer/"+savedDtoV2.getId().toString());
        return new ResponseEntity(headers,HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleUpdate(@PathVariable UUID beerId,@Valid @RequestBody BeerDtoV2 beerDtoV2){
        beerServiceV2.updateBeer(beerId,beerDtoV2);

    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleDelete(@PathVariable UUID beerId){
        beerServiceV2.deleteById(beerId);
    }




}
