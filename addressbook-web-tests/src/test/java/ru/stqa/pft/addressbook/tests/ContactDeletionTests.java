package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if (! app.getContatHelper().isThereAContact() ) {
      app.getContatHelper().goToAddNewContactPage();
      app.getContatHelper().createContact(new ContactData("Viktor", "Zverev", "Odessa",
              "0938478961", "0932222222", "vityazverev@gmail.com", "test1"), true);
    }
  }

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().goToHomePage();
    List<ContactData> before = app.getContatHelper().getContactList();
    app.getContatHelper().selectContact(before.size() - 1);
    app.getContatHelper().deleteSelectedContact();
    app.getContatHelper().acceptAlertPopUp();
    app.getContatHelper().goToHomePage();
    List<ContactData> after = app.getContatHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}
