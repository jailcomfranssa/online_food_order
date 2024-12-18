package github.com.jailcomfranssa.controller;

import github.com.jailcomfranssa.model.Restaurant;
import github.com.jailcomfranssa.model.User;
import github.com.jailcomfranssa.request.CreateRestaurantRequest;
import github.com.jailcomfranssa.response.MessageResponse;
import github.com.jailcomfranssa.service.RestaurantService;
import github.com.jailcomfranssa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {

    private final RestaurantService restaurantService;
    private final UserService userService;

    @Autowired
    public AdminRestaurantController(RestaurantService restaurantService, UserService userService) {
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateRestaurantRequest req,
                                                       @RequestHeader("Authorization")String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.createRestaurant(req, user);

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody CreateRestaurantRequest req,
                                                       @RequestHeader("Authorization")String jwt,
                                                       @PathVariable Long id) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.updateRestaurant(id, req);

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteRestaurant(@RequestBody CreateRestaurantRequest req,
                                                       @RequestHeader("Authorization")String jwt,
                                                       @PathVariable Long id) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        restaurantService.deleteRestaurant(id);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMsg("restaurant deleted successfully");

        return new ResponseEntity<>(messageResponse, HttpStatus.OK);

    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurant> updateRestaurantStatus(@RequestBody CreateRestaurantRequest req,
                                                       @RequestHeader("Authorization")String jwt,
                                                       @PathVariable Long id) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.updateRestaurantStatus(id);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);

    }

    @GetMapping("/user")
    public ResponseEntity<Restaurant> findRestaurantUserId(@RequestBody CreateRestaurantRequest req,
                                                       @RequestHeader("Authorization")String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.getRestaurantByUserId(user.getId());

        return new ResponseEntity<>(restaurant, HttpStatus.OK);

    }
}


//4:21

























