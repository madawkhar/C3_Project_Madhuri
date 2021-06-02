import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException{
        for(Restaurant restaurant:restaurants ) {
            if (restaurant.getName().equals(restaurantName)) {
                return restaurant;
            }
        }
       throw new restaurantNotFoundException("Restaurant not found");
        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public int calculateOrderValue(List<Item> selectedMenuItems) {
        int orderValue = 0;
        if (selectedMenuItems != null && selectedMenuItems.size() > 0) {
            for (Item item : selectedMenuItems) {
                {
                    orderValue = orderValue + item.getPrice();
                }
            }
        }
        return orderValue;
    }
    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
}
