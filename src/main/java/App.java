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

  get("/stylist/:id", (request, response) -> {
    HashMap<String, Object>model= new HashMap<String, Object>();

    Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
    List<Client> clients = stylist.getClients();

    model.put("stylist", stylist);
    model.put("clients", clients);
    model.put("template", "templates/stylist.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/stylist/:id", (request, response) -> {
    HashMap<String, Object>model= new HashMap<String, Object>();
    Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
    String clientName = request.queryParams("clientName");
    Client newClient = new Client (clientName, stylist.getId());
    newClient.save();
    List<Client> clients = stylist.getClients();
    model.put("stylist", stylist);
    model.put("clients", clients);
    model.put("template", "templates/stylist.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/stylist/:id/update", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
    model.put("stylist", stylist);
    model.put("template", "templates/update.vtl");
    return new ModelAndView(model, layout);
   }, new VelocityTemplateEngine());

   get("/stylist/:id/update", (request, response) -> {
     HashMap<String, Object> model = new HashMap<String, Object>();
     Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
     List<Client> clients = stylist.getClients();
     model.put("stylist", stylist);
     model.put("clients", clients);
     model.put("template", "templates/stylist.vtl");
     return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  post("/stylist/:id/update", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
    String stylistName = request.queryParams("stylistName");
    stylist.update(stylistName);
    List<Client> clients = stylist.getClients();
    model.put("stylist", stylist);
    model.put("clients", clients);
    model.put("template", "templates/stylist.vtl");
    return new ModelAndView(model, layout);
   }, new VelocityTemplateEngine());

  post("/delete/:id", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
    stylist.delete();
    model.put("stylists", Stylist.all());
    model.put("template","templates/index.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());


  post("/delete/client/:id", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Client client = Client.find(Integer.parseInt(request.params(":id")));
    int stylistId = client.getStylistId();
    Stylist stylist = Stylist.find(stylistId);
    client.delete();
    response.redirect("/stylist/" + stylistId);
    return null;
  });

  get("/stylist/:id/client-updateName", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Client client = Client.find(Integer.parseInt(request.params(":id")));
    model.put("client", client);
    model.put("template","templates/client-updateName.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/edited/client/:id", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    String newName = request.queryParams("client-update-name");
    Client client = Client.find(Integer.parseInt(request.params(":id")));
    int stylistId = client.getStylistId();
    Stylist stylist = Stylist.find(client.getId());
    client.update(newName);
    response.redirect("/stylist/" + stylistId);
    return null;
  });
  }
}
