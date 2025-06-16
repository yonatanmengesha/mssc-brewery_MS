package guru.springframwork.mssc_brewery.web.controller;

import guru.springframwork.mssc_brewery.services.BeerService;
import guru.springframwork.mssc_brewery.web.model.BeerDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Deprecated
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {


    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable UUID beerId){

        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity  handlePost(@Valid @RequestBody BeerDto beerDto){

        BeerDto savedBeer =  beerService.saveNewBeer(beerDto);

        HttpHeaders  headers = new HttpHeaders();
        //todo add hostname to the url
        headers.add("Location","/api/v1/beer/" + savedBeer.getId().toString());

        return new ResponseEntity(headers,HttpStatus.CREATED);

    }


    @PutMapping("/{beerId}")
    public ResponseEntity handleUpdate(@PathVariable("beerId") UUID beerId,@Valid @RequestBody BeerDto beerDto){

        beerService.updateBeer(beerId,beerDto);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable UUID beerId){

        beerService.deleteById(beerId);
    }


}
