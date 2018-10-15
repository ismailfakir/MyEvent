package controllers;

import com.google.inject.Inject;
import net.cloudcentrik.myevent.db.user.User;
import net.cloudcentrik.myevent.db.user.UserRepository;
import net.cloudcentrik.myevent.util.MyEventUtils;
import play.Logger;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.users;

import java.util.List;

public class UsersController extends Controller {
    private static final Logger.ALogger LOG = Logger.of(UsersController.class);
    private static final UserRepository userRepository=new UserRepository();

    @Inject
    FormFactory fromFactory;

    public Result index() {

        String user = session("user");
        if(user==null){
            return redirect(routes.HomeController.index());
        }else {
            List<User> usersList=new UserRepository().listAllUser();
            return ok(users.render("User",Boolean.TRUE,usersList));
        }

    }

    //edit user
    public Result submitEditUser() {

        String user = session("user");
        if(user==null){
            return redirect(routes.HomeController.index());
        }else {

            DynamicForm dynamicForm=fromFactory.form().bindFromRequest();

            final String email=dynamicForm.get("email").trim();
            final String name=dynamicForm.get("name").trim();
            final String password=dynamicForm.get("password").trim();
            final String phone=dynamicForm.get("phone").trim();

            userRepository.updateUserByEmail(
                    email,name,MyEventUtils.MD5(password),phone);

            List<User> userList=userRepository.listAllUser();
            return ok(users.render("user",Boolean.TRUE,userList));
        }
    }

    //delete User
    public Result deleteUser(String email) {
        String user = session("user");
        if(user==null){
            return redirect(routes.HomeController.index());
        }else {
            userRepository.deleteUserByEmail(email);
            List<User> userList=userRepository.listAllUser();
            return ok(users.render("participant",Boolean.TRUE,userList));
        }
    }

    //add user
    public Result submitAddUser() {

        String user = session("user");
        if(user==null){
            return redirect(routes.HomeController.index());
        }else {

            DynamicForm dynamicForm=fromFactory.form().bindFromRequest();

            final String email=dynamicForm.get("email").trim();
            final String name=dynamicForm.get("name").trim();
            final String password=dynamicForm.get("password").trim();
            final String phone=dynamicForm.get("phone").trim();

            userRepository.saveUser(new User(name,MyEventUtils.MD5(password),email,phone));

            flash("info", "User added!");

            List<User> userList=userRepository.listAllUser();
            return ok(users.render("user",Boolean.TRUE,userList));
        }
    }

}
