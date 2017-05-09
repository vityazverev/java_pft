package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.contact().list().size() == 0) {
      app.contact().create();
      app.contact().create(new ContactData().withFirstname("Viktor").withLastname("Zverev").withAddress("Odessa")
              .withMobilephone("0938478961").withWorkphone("0932222222").withEmail("vityazverev@gmail.com").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactModification(){
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("Viktor").withLastname("Zverev");
    app.contact().modify(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add (contact);
    Assert.assertEquals(before, after);
  }


}
