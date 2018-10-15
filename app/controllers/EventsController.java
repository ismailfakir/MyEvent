package controllers;

import net.cloudcentrik.myevent.db.event.Address;
import net.cloudcentrik.myevent.db.event.Event;
import net.cloudcentrik.myevent.db.event.EventRepository;
import org.bson.types.ObjectId;
import play.Logger;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.events;

import javax.inject.Inject;
import java.util.List;

public class EventsController extends Controller {
    private static final Logger.ALogger LOG = Logger.of(UsersController.class);
    private static final EventRepository eventRepository = new EventRepository();

    @Inject
    FormFactory fromFactory;

    public Result index() {

        /*eventRepository.saveEvent(new Event("Eid 2018","Eid celebration for all bangladeshi people",
                "2018-01-11","19:00","21:00",new Address("chalmers gata 28","gothenburg","41281","Sweden")));
        */

        String user = session("user");
        if (user == null) {
            return redirect(routes.HomeController.index());
        } else {
            return renderEventList();
        }
    }

    //edit Event
    public Result submitEditEvent() {

        String user = session("user");
        if (user == null) {
            return redirect(routes.HomeController.index());
        } else {

            DynamicForm dynamicForm = fromFactory.form().bindFromRequest();

            final String id = dynamicForm.get("id").trim();
            final String name = dynamicForm.get("name").trim();
            final String description = dynamicForm.get("description").trim();
            final String date = dynamicForm.get("date").trim();
            final String startTime = dynamicForm.get("startTime").trim();
            final String endTime = dynamicForm.get("endTime").trim();

            final String street = dynamicForm.get("street").trim();
            final String city = dynamicForm.get("city").trim();
            final String zipCode = dynamicForm.get("zipCode").trim();
            final String country = dynamicForm.get("country").trim();

            final Address address = new Address(street, city, zipCode, country);

            eventRepository.updateEventById(new ObjectId(id),
                    name, description, date, startTime, endTime, address);

            flash("info", "Food Item updated!");

            return renderEventList();
        }
    }

    //delete FoodItem
    public Result deleteEvent(String id) {
        String user = session("user");
        if (user == null) {
            return redirect(routes.HomeController.index());
        } else {
            eventRepository.deleteEventById(new ObjectId(id));
            flash("info", "Food Item deleted!");
            return renderEventList();
        }
    }

    //add FoodItem
    public Result submitAddEvent() {

        String user = session("user");
        if (user == null) {
            return redirect(routes.HomeController.index());
        } else {

            DynamicForm dynamicForm = fromFactory.form().bindFromRequest();

            final String name = dynamicForm.get("name").trim();
            final String description = dynamicForm.get("description").trim();
            final String date = dynamicForm.get("date").trim();
            final String startTime = dynamicForm.get("startTime").trim();
            final String endTime = dynamicForm.get("endTime").trim();

            final String street = dynamicForm.get("street").trim();
            final String city = dynamicForm.get("city").trim();
            final String zipCode = dynamicForm.get("zipCode").trim();
            final String country = dynamicForm.get("country").trim();

            final Address address = new Address(street, city, zipCode, country);

            eventRepository.saveEvent(new Event(name, description, date, startTime, endTime, address));
            flash("info", "New Event added!");

            return renderEventList();
        }
    }

    //render Event list
    private Result renderEventList() {
        List<Event> eventList = eventRepository.listAllEvent();
        return ok(events.render("Event", Boolean.TRUE, eventList));
    }
}
