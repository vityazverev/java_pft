package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create();
      app.contact().create(new ContactData()
              .withFirstname("Viktor").withLastname("Zverev").withAddress("Odessa").withMobilephone("0938478961")
              .withMobilephone("0932222222").withEmail("vityazverev@gmail.com").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactDeletion() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    //int index = before.size() - 1;
    ContactData deletedContact = before.iterator().next();
    app.contact().deleteContact(deletedContact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() - 1);

    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
