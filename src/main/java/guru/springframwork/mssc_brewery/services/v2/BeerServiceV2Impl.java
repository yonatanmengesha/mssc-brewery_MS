package guru.springframwork.mssc_brewery.services.v2;

import guru.springframwork.mssc_brewery.web.model.v2.BeerDtoV2;
import guru.springframwork.mssc_brewery.web.model.v2.BeerStyleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class BeerServiceV2Impl implements BeerServiceV2 {
    @Override
    public BeerDtoV2 getBeerById(UUID beerId) {
        return BeerDtoV2.builder()
                .id(UUID.randomUUID())
                .beerName("Galaxy Cat")
                .beerStyleEnum(BeerStyleEnum.PALE_ALE)
                .build();
    }

    @Override
    public BeerDtoV2 saveNewBeer(BeerDtoV2 beerDtoV2) {
        return BeerDtoV2.builder().id(UUID.randomUUID()).build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDtoV2 beerDtoV2) {
        log.debug("Updating ...");
    }

    @Override
    public void deleteById(UUID beerId) {
        log.debug("Deleting ...");
    }
}
