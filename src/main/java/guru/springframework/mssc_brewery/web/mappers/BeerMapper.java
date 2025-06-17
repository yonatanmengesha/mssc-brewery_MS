package guru.springframework.mssc_brewery.web.mappers;

import guru.springframework.mssc_brewery.domain.Beer;
import guru.springframework.mssc_brewery.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);
    Beer beerDtoToBeer(BeerDto beerDto);

}
