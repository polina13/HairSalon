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
}
