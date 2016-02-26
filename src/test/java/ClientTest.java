import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void checkIfReturnsEmptyArray() {
    assertEquals(Client.all().size(), 0);
  }

  // @Test
  // public void equals_returnsTrueIfClientsNamesAreTheSame() {
  //   Client firstClient = new Client("Sonya", 1);
  //   Client secondClient = new Client("Sonya", 1);
  //   assertTrue(firstClient.equals(secondClient));
  // }
  //
  // @Test
  // public void save_savesClientToDatabase() {
  //   Client newClient = new Client("Sonya", 1);
  //   newClient.save();
  //   assertTrue(Client.all().get(0).equals(newClient));
  // }
  //
  // @Test
  // public void find_findsClientInDatabase_true() {
  //   Client myClient = new Client ("Sonya", 1);
  //   myClient.save();
  //   Client savedClient = Client.find(myClient.getId());
  //   assertTrue(myClient.equals(savedClient));
  // }
  //
  // @Test
  // public void update_updateClientNameInDatabase() {
  //   Client myClient = new Client ("Sonya", 1);
  //   myClient.save();
  //   myClient.update("Rosie");
  //   assertEquals("Rosie", myClient.getName());
  // }
  //
  // @Test
  // public void delete_deletesClientsNameFromDatabase() {
  //   Client myClient = new Client ("Sonya", 1);
  //   myClient.save();
  //   myClient.delete();
  //   assertEquals(Client.all().size(), 0);
  // }

  // @Test
  // public void viewStylists_returnsStylistForClients() {
  //   Client myClient= new Client("Sonya");
  //   myClient.save();
  //   Stylist newStylist = new Stylist("Sonya"); myClient.getId());
  //   Stylist newStylist2 = new Stylist("Rosie"); myClient.getId());
  //   newStylist.save();
  //   newStylist2.save();
  //   Stylist[] stylists = new Stylist[] { newStylist, newStylist2 };
  //   assertTrue(myClient.viewStylists().containsAll(Arrays.asList(stylists)));
  // }
}
