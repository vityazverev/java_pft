package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContatHelper().isThereAContact() ) {
      app.getContatHelper().createContact(new ContactData("Viktor", "Zverev", "Odessa",
              "0938478961", "0932222222", "vityazverev@gmail.com", "test1"), true);
    }
    int before = app.getContatHelper().getContactCount();
    app.getContatHelper().selectContact();
    app.getContatHelper().deleteSelectedContact();
    app.getContatHelper().acceptAlertPopUp();
    app.getContatHelper().goToHomePage();
    int after = app.getContatHelper().getContactCount();
    Assert.assertEquals(after, before - 1);
  }
}
