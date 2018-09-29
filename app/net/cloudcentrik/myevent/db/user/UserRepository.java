package net.cloudcentrik.myevent.db.user;


import net.cloudcentrik.myevent.config.MongoConfig;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import java.util.List;

public class UserRepository {

    private Datastore datastore;

    public UserRepository(){
        datastore=MongoConfig.datastore();
    }

    public void saveUser(User user){
        datastore.save(user);
    }

    public User findUserByEmail(String email,String password){
        Query<User> query = datastore.createQuery(User.class)
        .field("email").equal(email)
        .field("password").equal(password);
        return query.get();
    }

    public List<User> listAllUser(){
        final Query<User> query = datastore.createQuery(User.class);
        final List<User> userList = query.asList();
        return userList;
    }

}
