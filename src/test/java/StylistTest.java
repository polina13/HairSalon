import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;

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
    Stylist newStylist = new Stylist("Jennie Thompson");
    newStylist.save();
    assertTrue(Stylist.all().get(0).equals(newStylist));
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

  // @Test
  // public void viewClients_returnsListOfClients() {
  //   Stylist myStylist= new Stylist("Jennie Thompson");
  //   myStylist.save();
  //   Client newClient = new Client("Sonya"); myStylist.getId());
  //   Client newClient2 = new Client("Rosie"); myStylist.getId());
  //   newClient.save();
  //   newClient2.save();
  //   Client[] clients = new Client[] { newClient, newClient2 };
  //   assertTrue(myStylist.viewClients().containsAll(Arrays.asList(clients)));
  // }
}
