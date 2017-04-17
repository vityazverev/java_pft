package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification(){

    app.getNavigationHelper().goToHomePage();
    if (! app.getContatHelper().isThereAContact() ) {
      app.getContatHelper().createContact(new ContactData("Viktor", "Zverev", "Odessa",
              "0938478961", "0932222222", "vityazverev@gmail.com", "test1"), true);
    }
    List<ContactData> before = app.getContatHelper().getContactList();
    app.getContatHelper().goToEditContact()/*(before - 1)*/;
    app.getContatHelper().fillContactForm(new ContactData("Viktor", "Zverev", "Odessa",
            "0938478961", "0932222222", "vityazverev@gmail.com", "null"), false);
    app.getContatHelper().submitContactModification();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContatHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
  }
}
