package controllers;

import com.google.inject.Inject;
import net.cloudcentrik.myevent.db.participant.Participant;
import net.cloudcentrik.myevent.db.participant.ParticipantRepository;
import play.Logger;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;
import views.html.participants;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ParticipantController extends Controller {
    private static final Logger.ALogger LOG = Logger.of(ParticipantController.class);

    @Inject
    FormFactory fromFactory;

    //edit participant
    public Result submitEditParticipant() {

        DynamicForm dynamicForm=fromFactory.form().bindFromRequest();

        final String email=dynamicForm.get("email").trim();
        final String name=dynamicForm.get("name").trim();
        final String noOfChild=dynamicForm.get("child").trim();
        final String noOfAdult=dynamicForm.get("adult").trim();
        final String confirm=dynamicForm.get("confirm").trim();


        LOG.info("Email : "+email);
        LOG.info("Name : "+name);
        LOG.info("Child : "+noOfChild);
        LOG.info("adult : "+noOfAdult);
        LOG.info("confirm : "+confirm);

        new ParticipantRepository().updatePerticipantByEmail(
                email,Integer.parseInt(noOfChild),Integer.parseInt(noOfAdult),Boolean.parseBoolean(confirm));

        String user = session("user");
        if(user==null){
            return ok(login.render("Login",""));
        }else {
            List<Participant> participantList=new ParticipantRepository().listAllParticipant();
            return ok(participants.render("participant",Boolean.TRUE,participantList));
        }
    }
}
