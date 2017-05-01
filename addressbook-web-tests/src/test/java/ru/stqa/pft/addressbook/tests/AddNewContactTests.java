package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;


public class AddNewContactTests extends TestBase {

  @Test
  public void testAddNewContactCreation() {
    app.getNavigationHelper().goToHomePage();
    List<ContactData> before = app.getContatHelper().getContactList();
    app.getNavigationHelper().goToAddNewContactPage();
    ContactData contact = new ContactData("Viktor", "Zverev", null,
            null, null, null, "test1");
    app.getContatHelper().createContact(contact, true);
    List<ContactData> after = app.getContatHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);


    int max = 0;
    for (ContactData g : after) {
      if (g.getId() > max) {
        max = g.getId();
      }
    }
    contact.setId(max);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
