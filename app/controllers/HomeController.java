package controllers;

import com.google.inject.Inject;
import net.cloudcentrik.myevent.db.participant.Participant;
import net.cloudcentrik.myevent.db.participant.ParticipantRepository;
import net.cloudcentrik.myevent.db.user.User;
import net.cloudcentrik.myevent.db.user.UserRepository;
import net.cloudcentrik.myevent.util.MyEventUtils;
import play.Logger;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.util.List;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private static final Logger.ALogger LOG = Logger.of(HomeController.class);

    @Inject
    FormFactory fromFactory;
    public Result index() {

        String user = session("user");
        if(user==null){
            return ok(login.render("Login","user credientials incorrect"));
        }else {
            List<User> usersList=new UserRepository().listAllUser();
            return ok(dashboard.render("home",Boolean.TRUE,usersList));
        }

    }

    //login page
    public Result login() {
        return ok(login.render("Login",""));
    }

    //logout page
    public Result logout() {
        session().clear();
        return ok(login.render("Login",""));
    }

    //login page
    public Result loginSubmit() {

        DynamicForm dynamicForm=fromFactory.form().bindFromRequest();

        final String userName=dynamicForm.get("username").trim();
        final String userPassword=dynamicForm.get("password").trim();

        LOG.info("user :"+userName);
        LOG.info("password :"+userPassword);

        final User user=new UserRepository().findUserByEmail(userName,MyEventUtils.MD5(userPassword));

        if(user!=null){
            LOG.info("user :"+user.toString());
            session("user",user.getEmail());
        }else {
            session().remove("user");
            LOG.info("user not found");
        }

        return index();
    }

    //participant page
    public Result participant() {

        String user = session("user");
        if(user==null){
            return ok(login.render("Login",""));
        }else {
            List<Participant> participantList=new ParticipantRepository().listAllParticipant();
            return ok(participants.render("participant",Boolean.TRUE,participantList));
        }
    }

    //delete participant
    public Result deleteParticipant(String email) {

        LOG.info("Email : "+email);

        new ParticipantRepository().deletePerticipantByEmail(email);

        String user = session("user");
        if(user==null){
            return ok(login.render("Login",""));
        }else {
            List<Participant> participantList=new ParticipantRepository().listAllParticipant();
            return ok(participants.render("participant",Boolean.TRUE,participantList));
        }
    }

    //register
    public Result registerSubmit() {

        DynamicForm dynamicForm=fromFactory.form().bindFromRequest();

        final String name=dynamicForm.get("name").trim();
        final String phone=dynamicForm.get("phone").trim();
        final String email=dynamicForm.get("email").trim();
        final String password=dynamicForm.get("password").trim();


        final User user=new User(name,MyEventUtils.MD5(password),email,phone);

        new UserRepository().saveUser(user);


        return login();
    }

    //register new user
    public Result register() {
        return ok(register.render("Register"));
    }

    //forgotpassword
    public Result forgotpassword() {
        return ok(forgotpassword.render("Forgot password"));
    }

}
