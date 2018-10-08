package net.cloudcentrik.myevent.db.user;


import net.cloudcentrik.myevent.config.MongoConfig;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

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

    public void deleteUserByEmail(String email){
        final Query<User> query = datastore.createQuery(User.class);
        query.field("email").equal(email);
        datastore.findAndDelete(query);
    }

    public void updateUserByEmail(String email,String name,String password,String phone){
        final Query<User> query = datastore.createQuery(User.class)
                .field("email").equal(email);
        final UpdateOperations<User> updateOperations = datastore.createUpdateOperations(User.class)
                .set("name", name)
                .set("password", password)
                .set("phone", phone);

        final UpdateResults results = datastore.update(query, updateOperations);
    }

}
