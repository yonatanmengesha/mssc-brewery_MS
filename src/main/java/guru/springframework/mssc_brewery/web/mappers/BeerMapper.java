package guru.springframework.mssc_brewery.web.mappers;

import guru.springframework.mssc_brewery.domain.Beer;
import guru.springframework.mssc_brewery.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDto beerDto);
    BeerDto beerToBeerDto(Beer beer);
}
