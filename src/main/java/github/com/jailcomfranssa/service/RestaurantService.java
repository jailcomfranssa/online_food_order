package github.com.jailcomfranssa.service;

import github.com.jailcomfranssa.dto.RestaurantDto;
import github.com.jailcomfranssa.model.Restaurant;
import github.com.jailcomfranssa.model.User;
import github.com.jailcomfranssa.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {

    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRestaurant)throws Exception;
    public void deleteRestaurant(Long restaurantId)throws Exception;
    public List<Restaurant> getAllRestaurant();
    public List<Restaurant> searchRestaurant(String keyword);
    public Restaurant findRestaurantById(Long restaurantId) throws Exception;
    public Restaurant getRestaurantByUserId(Long userId)throws Exception;
    public RestaurantDto addToFavorites(Long restaurantId, User user)throws Exception;
    public Restaurant updateRestaurantStatus(Long id)throws Exception;
}
