package controllers;

import com.google.inject.Inject;
import net.cloudcentrik.myevent.db.food.FoodItem;
import net.cloudcentrik.myevent.db.food.FoodItemRepository;
import net.cloudcentrik.myevent.util.MyEventUtils;
import play.Logger;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.fooditems;

import java.util.List;

public class FoodItemsController extends Controller {
    private static final Logger.ALogger LOG = Logger.of(FoodItemsController.class);
    private static final FoodItemRepository foodItemRepository = new FoodItemRepository();

    @Inject
    FormFactory fromFactory;

    public Result index() {

        String user = session("user");
        if (user == null) {
            return redirect(routes.HomeController.index());
        } else {
            return renderFoodItemsList();
        }
    }

    //edit FoodItem
    public Result submitEditFoodItem() {

        String user = session("user");
        if (user == null) {
            return redirect(routes.HomeController.index());
        } else {

            DynamicForm dynamicForm = fromFactory.form().bindFromRequest();

            final String email = dynamicForm.get("email").trim();
            final String name = dynamicForm.get("name").trim();
            final String type = dynamicForm.get("type").trim();
            final String amount = dynamicForm.get("amount").trim();

            foodItemRepository.updateFoodItemByEmail(
                    email, name,type, amount);

            return renderFoodItemsList();
        }
    }

    //delete FoodItem
    public Result deleteFoodItem(String email) {
        String user = session("user");
        if (user == null) {
            return redirect(routes.HomeController.index());
        } else {
            foodItemRepository.deleteFoodItemByEmail(email);
            return renderFoodItemsList();
        }
    }

    //add FoodItem
    public Result submitAddFoodItem() {

        String user = session("user");
        if (user == null) {
            return redirect(routes.HomeController.index());
        } else {

            DynamicForm dynamicForm = fromFactory.form().bindFromRequest();

            final String email = dynamicForm.get("email").trim();
            final String name = dynamicForm.get("name").trim();
            final String type = dynamicForm.get("type").trim();
            final String amount = dynamicForm.get("amount").trim();

            foodItemRepository.saveFoodItem(new FoodItem(type, name, amount, email));

            return renderFoodItemsList();
        }
    }

    //render FoodItem list
    private Result renderFoodItemsList() {
        List<FoodItem> foodItemsList = foodItemRepository.listAllFoodItem();
        return ok(fooditems.render("Food Item", Boolean.TRUE, foodItemsList));
    }
}
