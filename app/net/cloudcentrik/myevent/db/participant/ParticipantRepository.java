package net.cloudcentrik.myevent.db.participant;

import net.cloudcentrik.myevent.config.MongoConfig;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import java.util.List;

import static net.cloudcentrik.myevent.util.RandomUtils.generateRandomEmail;
import static net.cloudcentrik.myevent.util.RandomUtils.getRandomIntBetweenRange;

public class ParticipantRepository {
    private Datastore datastore;

    public ParticipantRepository(){
        datastore=MongoConfig.datastore();
    }

    public List<Participant> listAllParticipant(){
        /*Participant participant=new Participant();
        participant.setType("family");
        participant.setEmail(generateRandomEmail(20));
        participant.setName("ismail");
        participant.setNoOfChild(getRandomIntBetweenRange(1,4));
        participant.setNoOfAdult(getRandomIntBetweenRange(1,2));
        participant.setTotal(getRandomIntBetweenRange(1,4));
        participant.setConfirm(Boolean.FALSE);
        datastore.save(participant);*/

        final Query<Participant> query = datastore.createQuery(Participant.class);
        final List<Participant> userList = query.asList();
        return userList;
    }

    public void deletePerticipantByEmail(String email){
        final Query<Participant> query = datastore.createQuery(Participant.class);
        query.field("email").equal(email);
        datastore.findAndDelete(query);
    }

    public void updatePerticipantByEmail(String email,int noOfChild,int noOfAdult,boolean confirm){
        final Query<Participant> query = datastore.createQuery(Participant.class)
                .field("email").equal(email);
        final UpdateOperations<Participant> updateOperations = datastore.createUpdateOperations(Participant.class)
                .set("noOfChild", noOfChild)
                .set("noOfAdult", noOfAdult)
                .set("total", noOfChild+noOfAdult)
                .set("confirm", confirm);

        final UpdateResults results = datastore.update(query, updateOperations);
    }
}
