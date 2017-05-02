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
    if (! app.getContatHelper().isThereAContact() ) {
      app.getContatHelper().goToAddNewContactPage();
      app.getContatHelper().createContact(new ContactData("Viktor", "Zverev", "Odessa",
              "0938478961", "0932222222", "vityazverev@gmail.com", "test1"), true);
    }
  }

  @Test
  public void testContactModification(){
    app.getNavigationHelper().goToHomePage();
    List<ContactData> before = app.getContatHelper().getContactList();
    int index = before.size() - 1;
    ContactData contact = new ContactData(before.get(index).getId(), "Viktor", "Zverev", null,
            null, null, null, null);
    app.getContatHelper().modifyContact(index, contact);
    List<ContactData> after = app.getContatHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add (contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
