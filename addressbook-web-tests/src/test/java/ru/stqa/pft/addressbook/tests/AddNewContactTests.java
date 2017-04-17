package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;


public class AddNewContactTests extends TestBase {

  @Test
  public void testAddNewContactCreation() {
    app.getNavigationHelper().goToAddNewContactPage();
    List<ContactData> before = app.getContatHelper().getContactList();
    app.getContatHelper().createContact(new ContactData("Viktor", "Zverev", "Odessa",
            "0938478961", "0932222222", "vityazverev@gmail.com", "test1"), true);
    List<ContactData> after = app.getContatHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }
}
