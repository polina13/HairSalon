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

  post("/", (request, response) -> {
    HashMap<String, Object>model= new HashMap<String, Object>();
    String stylistName = request.queryParams("stylistName");
    Stylist newStylist = new Stylist (stylistName);
    newStylist.save();
    model.put("stylists", Stylist.all());
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model,layout);
  }, new VelocityTemplateEngine());

  get("/stylists", (request, response ) -> {
    HashMap<String, Object>model = new HashMap<String, Object>();
    model.put("stylists", Stylist.all());
    model.put("template", "templates/stylists.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/stylists" , (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    String stylistName = request.queryParams("stylistName");
    Stylist newStylist = new Stylist(stylistName);
    newStylist.save();
    model.put("stylists", Stylist.all());
    model.put("template", "templates/restaurants.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/stylist/:id", (request, response) -> {
    HashMap<String, Object>model= new HashMap<String, Object>();
    int id =Integer.parseInt(request.params(":id"));
    Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
    List<Client> clients = stylist.getClients();
    model.put("id", id);
    model.put("stylist", stylist);
    model.put("clients", clients);
    model.put("template", "templates/stylist.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/stylist/:id", (request, response) -> {
    HashMap<String, Object>model= new HashMap<String, Object>();
    int id =Integer.parseInt(request.params(":id"));
    Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
    String clientName = request.queryParams("clientName");
    List<Client> clients = stylist.getClients();
    model.put("id", id);
    model.put("stylist", stylist);
    model.put("clients", clients);
    model.put("template", "templates/stylist.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());
  }
}
