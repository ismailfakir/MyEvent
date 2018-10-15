package net.cloudcentrik.myevent.db.event;

import net.cloudcentrik.myevent.config.MongoConfig;
import net.cloudcentrik.myevent.db.food.FoodItem;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import java.util.List;

public class EventRepository {

    private Datastore datastore;

    public EventRepository(){
        datastore=MongoConfig.datastore();
    }

    public void saveEvent(Event event){
        datastore.save(event);
    }

    public List<Event> listAllEvent(){
        final Query<Event> query = datastore.createQuery(Event.class);
        final List<Event> eventList = query.asList();
        return eventList;
    }

    public void deleteEventById(ObjectId id){
        final Query<Event> query = datastore.createQuery(Event.class);
        query.field("_id").equal(id);
        datastore.findAndDelete(query);
    }

    public void updateEventById(ObjectId id,String name,String description,String date,String startTime,String endTime,Address address){
        final Query<FoodItem> query = datastore.createQuery(FoodItem.class)
                .field("_id").equal(id);
        final UpdateOperations<FoodItem> updateOperations = datastore.createUpdateOperations(FoodItem.class)
                .set("name", name)
                .set("description", description)
                .set("date", date)
                .set("startTime", startTime)
                .set("endTime", endTime)
                .set("address", address);

        final UpdateResults results = datastore.update(query, updateOperations);
    }

}
