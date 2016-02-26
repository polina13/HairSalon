import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import java.util.Arrays;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void checkIfReturnsEmptyArray() {
    assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfStylistsNamesAreTheSame() {
    Stylist firstStylist = new Stylist("Jennie Thompson");
    Stylist secondStylist = new Stylist("Jennie Thompson");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_savesStylistToDatabase() {
    Stylist myStylist = new Stylist("Jennie Thompson");
    myStylist.save();
    assertTrue(Stylist.all().get(0).equals(myStylist));
  }

  @Test
  public void find_findsStylistInDatabase_true() {
    Stylist myStylist = new Stylist ("Jennie Thompson");
    myStylist.save();
    Stylist savedStylist = Stylist.find(myStylist.getId());
    assertTrue(myStylist.equals(savedStylist));
  }

  @Test
  public void update_updateStylistNameInDatabase() {
    Stylist myStylist = new Stylist ("Jennie Thompson");
    myStylist.save();
    myStylist.update("Polina Nenchev");
    assertEquals("Polina Nenchev", myStylist.getName());
  }

  @Test
  public void delete_deletesStylistsNameFromDatabase() {
    Stylist myStylist = new Stylist ("Jennie Thompson");
    myStylist.save();
    myStylist.delete();
    assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void getClients_retrievesClientsFromDatabase_clients() {
    Stylist myStylist = new Stylist ("Jennie Thompson");
    myStylist.save();
    Client firstClient = new Client ("Linda", myStylist.getId());
    firstClient.save();
    Client secondClient = new Client ("Sonya", myStylist.getId());
    secondClient.save();
    Client[] clients = new Client[] { firstClient, secondClient };
    assertTrue(myStylist.getClients().containsAll(Arrays.asList(clients)));
  }
}
