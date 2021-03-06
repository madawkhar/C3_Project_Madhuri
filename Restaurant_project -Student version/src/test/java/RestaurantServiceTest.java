import org.junit.jupiter.api.*;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE


    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");

        service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        service.addRestaurant("coffeeCafe", "Nagar",openingTime,closingTime);
        Restaurant restaurant = service.findRestaurantByName("Amelie's cafe");
        Assertions.assertEquals("Amelie's cafe",restaurant.getName());
       // Assertions.assertEquals("coffeeCafe",restaurant.getName());
        Assertions.assertEquals( LocalTime.parse("22:00:00"),restaurant.closingTime);
        Assertions.assertEquals( LocalTime.parse("10:30:00"),restaurant.openingTime);
    }

    //You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {

        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        service.addRestaurant("Amelie's cafe","chennai",openingTime,closingTime);
        assertThrows(restaurantNotFoundException.class,()->service.findRestaurantByName("Pantry d'or"));

        //WRITE UNIT TEST CASE HERE
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>




    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1()
            throws restaurantNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        initRestaurantTestDatawithManu();
        assertThrows(restaurantNotFoundException.class,
                ()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void calculate_order_value_with_empty_manu_list_will_zero(){
        assertEquals(0,service.calculateOrderValue(null));
    }

    @Test
    public void calculate_order_value_with_null_menu_list_will_zero(){
        assertEquals(0,service.calculateOrderValue(new ArrayList<>()));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        initRestaurantTestDatawithManu();
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }

    private void initRestaurantTestDatawithManu(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}