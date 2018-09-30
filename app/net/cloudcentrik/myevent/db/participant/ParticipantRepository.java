package net.cloudcentrik.myevent.db.participant;

import net.cloudcentrik.myevent.config.MongoConfig;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

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
}
