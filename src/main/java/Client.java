import java.util.*;
import org.sql2o.*;

public class Client {
  private int id;
  private String name;
  private int stylist_id;


  public int getId() {
    return id;
  }

  public String getName () {
    return name;
  }

  public int getStylistId() {
    return stylist_id;
  }

  public Client (String name, int stylist_id) {
    this.name = name;
    this.stylist_id = stylist_id;
  }

  public static List<Client> all() {
    String sql = "SELECT * FROM clients";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  @Override
  public boolean equals(Object otherClient) {
    if(!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName()) &
      this.getId() == newClient.getId() && this.getStylistId() == newClient.getStylistId();
    }
  }

  public void save() {
    String sql = "INSERT INTO clients (name, stylist_id) VALUES (:name, :stylist_id)";
    try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true).addParameter("name", name).addParameter("stylist_id", stylist_id).executeUpdate().getKey();
    }
  }

  public static Client find (int id) {
    String sql = "SELECT * FROM clients WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Client.class);
    }
  }

  public void update(String newName) {
    this.name = newName;
    try(Connection con= DB.sql2o.open()) {
      String sql = "UPDATE clients SET name = :name WHERE stylist_id = :stylist_id";
      con.createQuery(sql).addParameter("name", newName).addParameter("stylist_id", stylist_id).executeUpdate();
    }
  }
}
