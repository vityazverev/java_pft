package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

public class ContactDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().create();
      app.contact().create(new ContactData()
              .withFirstname("Viktor").withLastname("Zverev").withAddress("Odessa").withMobilephone("0938478961")
              .withMobilephone("0932222222").withEmail("vityazverev@gmail.com").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactDeletion() {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    //int index = before.size() - 1;
    ContactData deletedContact = before.iterator().next();
    app.contact().deleteContact(deletedContact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }
}
