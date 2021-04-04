import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException{
        for(Restaurant restaurant: restaurants){
            if(restaurant.getName().equalsIgnoreCase(restaurantName)){
                return restaurant;
            }
        }
        throw new restaurantNotFoundException(restaurantName);
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

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public int getOrderValue(List<String> itemNames,String restaurantName) throws itemNotFoundException, restaurantNotFoundException {
        Restaurant restaurant = findRestaurantByName(restaurantName);
        int cost = 0;
        for(String item: itemNames){
            if(restaurant.findItemByName(item) == null){
                throw new itemNotFoundException(item);
            }else{
                cost = cost + restaurant.findItemByName(item).getPrice();
            }
        }
        return cost;
    }
}
