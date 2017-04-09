package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class AddNewContactTests extends TestBase {

  @Test
  public void testAddNewContactCreation() {
    app.getNavigationHelper().goToAddNewContactPage();
    app.getContatHelper().createContact(new ContactData("Viktor", "Zverev", "Odessa",
            "0938478961", "0932222222", "vityazverev@gmail.com", "test1"), true);
  }
}
