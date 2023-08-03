package kr.co.yapp._22nd.coffice.domain.place;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaceCommandServiceImpl implements PlaceCommandService {
    private final PlaceRepository placeRepository;

    @Override
    @Transactional
    public Place create(PlaceCreateVo placeCreateVo) {
        Place place = Place.from(placeCreateVo);
        return placeRepository.save(place);
    }

    @Override
    @Transactional
    public Place update(Long placeId, PlaceUpdateVo placeUpdateVo) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new PlaceNotFoundException(placeId));
        place.update(placeUpdateVo);
        return place;
    }

    @Override
    @Transactional
    public void addImage(Long placeId, String url) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new PlaceNotFoundException(placeId));
        place.getImageUrls().add(url);
        placeRepository.save(place);
    }

    @Override
    @Transactional
    public void removeImages(Long placeId) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new PlaceNotFoundException(placeId));
        place.getImageUrls().clear();
        placeRepository.save(place);
    }
}
