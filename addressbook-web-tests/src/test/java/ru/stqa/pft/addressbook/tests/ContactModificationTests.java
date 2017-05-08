package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;

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
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData()
            .withId(before.get(index).getId()).withFirstname("Viktor").withLastname("Zverev");
    app.contact().modify(index, contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add (contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
