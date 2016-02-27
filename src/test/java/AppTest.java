import org.fluentlenium.adapter.FluentTest;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.*;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Hair Salon");
  }

  @Test
  public void stylistIsCreated() {
    goTo ("http://localhost:4567/");
    fill("#stylistName").with("Jennie Thompson");
    submit(".btn btn-success");
    assertThat(pageSource()).contains("Jennie Thompson");
  }

  @Test
  public void displaysAllClientsOnceAdded() {
    Stylist newStylist = new Stylist("Jennie Thompson");
    newStylist.save();
    Client firstClient = new Client ("Linda", newStylist.getId());
    firstClient.save();
    String categoryPath = String.format("http://localhost:4567/stylist/%d", newStylist.getId());
    goTo(categoryPath);
    assertThat(pageSource()).contains("Linda");
  }

  @Test
  public void update_updateStylistNameInDatabase() {
    Stylist newStylist = new Stylist("Jennie Thompson");
    newStylist.save();
    newStylist.update("Polina Nenchev");
    String categoryPath = String.format("http://localhost:4567/stylist/%d", newStylist.getId());
    goTo(categoryPath);
    assertThat(pageSource()).contains("Polina Nenchev");
  }

  @Test
  public void delete_deletesStylistsNameFromDatabase() {
    Stylist newStylist = new Stylist("Jennie Thompson");
    newStylist.save();
    newStylist.delete();
    String categoryPath = String.format("http://localhost:4567/", newStylist.getId());
    goTo(categoryPath);
    assertThat(!(pageSource()).contains("Jennie Thompson"));
  }

  // @Test
  // public void addsClientsToStylistPage() {
  //   Stylist newStylist = new Stylist("Jennie Thompson");
  //   newStylist.save();
  //
  // }
  //
  // @Test
  // public void stylistIsUpdate() {
  //   Stylist stylist = new Stylist("Jennie Thompson");
  //   stylist.save();
  //   stylist.update("Polina Nenchev");
  //   String categoryPath = String.format("http://localhost://4567", stylist.getId());
  //   goTo(categoryPath);
  //   assertThat(pageSource()).contains("Polina Nenchev");
  // }


}
