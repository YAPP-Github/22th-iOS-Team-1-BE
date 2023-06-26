package kr.co.yapp._22nd.coffice.domain.place;

import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class PlaceSearchResponseVo {
    Long placeId;
    String name;
    Coordinates coordinates;
    Address address;
    List<OpeningHour> openingHours;
    PhoneNumber phoneNumber;
    ElectricOutletLevel electricOutletLevel;
    Boolean hasCommunalTable;
    CapacityLevel capacityLevel;
    List<String> imageUrls;
    List<Crowdedness> crowdednessList;
    Distance distance;
}
