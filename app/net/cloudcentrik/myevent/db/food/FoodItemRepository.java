package net.cloudcentrik.myevent.db.food;

import net.cloudcentrik.myevent.config.MongoConfig;
import net.cloudcentrik.myevent.db.user.User;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import java.util.List;

public class FoodItemRepository {
    private Datastore datastore;

    public FoodItemRepository(){
        datastore=MongoConfig.datastore();
    }

    public void saveFoodItem(FoodItem foodItem){
        datastore.save(foodItem);
    }

    public FoodItem findFoodItemByEmail(String email){
        Query<FoodItem> query = datastore.createQuery(FoodItem.class)
                .field("email").equal(email);
        return query.get();
    }

    public List<FoodItem> listAllFoodItem(){
        final Query<FoodItem> query = datastore.createQuery(FoodItem.class);
        final List<FoodItem> foodItemList = query.asList();
        return foodItemList;
    }

    public void deleteFoodItemByEmail(String email){
        final Query<FoodItem> query = datastore.createQuery(FoodItem.class);
        query.field("email").equal(email);
        datastore.findAndDelete(query);
    }

    public void updateFoodItemByEmail(String email,String name,String type,String amount){
        final Query<FoodItem> query = datastore.createQuery(FoodItem.class)
                .field("email").equal(email);
        final UpdateOperations<FoodItem> updateOperations = datastore.createUpdateOperations(FoodItem.class)
                .set("name", name)
                .set("type", type)
                .set("amount", amount);

        final UpdateResults results = datastore.update(query, updateOperations);
    }
}
