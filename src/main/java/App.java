import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";


  get("/", (request, response) -> {
    HashMap<String, Object>model= new HashMap<String, Object>();
    // model.put("clients", Client.all());
    model.put("stylists", Stylist.all());
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/slylists", (request, response) -> {
    HashMap<String, Object>model= new HashMap<String, Object>();
    String stylistName = request.queryParams("stylistName");
    Stylist newStylist = new Stylist (stylistName);
    newStylist.save();
    model.put("stylists", Stylist.all());
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model,layout);
  }, new VelocityTemplateEngine());
  }
}
