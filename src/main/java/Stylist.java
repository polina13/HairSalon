import java.util.List;
import org.sql2o.*;

public class Stylist {
  private int id;
  private String name;


  public int getId() {
    return id;
  }

  public String getName () {
    return name;
  }

  public Stylist (String name) {
    this.name = name;
  }

  public static List<Stylist> all() {
    String sql = "SELECT * FROM stylists ORDER BY name ASC";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }


  @Override
  public boolean equals(Object otherStylist) {
    if(!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return newStylist.getName().equals(name);
    }
  }

  public void save() {
    String sql = "INSERT INTO stylists (name) VALUES (:name)";
    try (Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true).addParameter("name", name).executeUpdate().getKey();
    }
  }

  public static Stylist find(int id ) {
    String sql = "SELECT * FROM stylists WHERE id = :id";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Stylist.class);
    }
  }

  public void update(String newName) {
    this.name = newName;
    try(Connection con= DB.sql2o.open()) {
      String sql = "UPDATE stylists SET name = :newName WHERE id = :id";
      con.createQuery(sql).addParameter("newName", newName).addParameter("id", id).executeUpdate();
    }
  }

  public void delete () {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists WHERE id = :id";
      con.createQuery(sql).addParameter("id", id).executeUpdate();
    }
  }

  public List <Client> getClients() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE stylist_id = :id";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetch(Client.class);
    }
  }
}
