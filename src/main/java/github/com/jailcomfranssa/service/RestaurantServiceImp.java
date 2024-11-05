package github.com.jailcomfranssa.service;

import github.com.jailcomfranssa.dto.RestaurantDto;
import github.com.jailcomfranssa.model.Address;
import github.com.jailcomfranssa.model.Restaurant;
import github.com.jailcomfranssa.model.User;
import github.com.jailcomfranssa.repository.AddressRepository;
import github.com.jailcomfranssa.repository.RestaurantRepository;
import github.com.jailcomfranssa.repository.UserRepository;
import github.com.jailcomfranssa.request.CreateRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImp implements RestaurantService{

    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;



    @Autowired
    public RestaurantServiceImp(RestaurantRepository restaurantRepository, AddressRepository addressRepository, UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {

        Address address = addressRepository.save(req.getAddress());

        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRestaurant) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        if (restaurant.getCuisineType() != null){
            restaurant.setCuisineType(updateRestaurant.getCuisineType());
        }

        if(restaurant.getDescription() != null){
            restaurant.setDescription(updateRestaurant.getDescription());
        }

        if (restaurant.getName() != null){
            restaurant.setName(updateRestaurant.getName());
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception{

        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurantRepository.delete(restaurant);

    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long restaurantId) throws Exception {
        Optional<Restaurant> optional = restaurantRepository.findById(restaurantId);
        if (optional.isEmpty()){
            throw new Exception("restaurant not found whit id" + restaurantId);
        }

        return optional.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
        if (restaurant == null){
            throw new Exception("restaurant not found with owner id " + userId);
        }

        return restaurant;
    }

    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        RestaurantDto dto = new RestaurantDto();
        dto.setDescription(restaurant.getDescription());
        dto.setImages(restaurant.getImages());
        dto.setTitle(restaurant.getName());
        dto.setId(restaurantId);

        if (user.getFavorito().contains(dto)){
            user.getFavorito().remove(dto);
        }else {
            user.getFavorito().add(dto);
        }
        userRepository.save(user);
        return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {

        Restaurant restaurant = findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}

