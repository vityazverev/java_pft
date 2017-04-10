package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification(){
    app.getNavigationHelper().goToHomePage();
/*
    int before = app.getGroupHelper().getGroupCount();
*/
    if (! app.getContatHelper().isThereAContact() ) {
      app.getContatHelper().createContact(new ContactData("Viktor", "Zverev", "Odessa",
              "0938478961", "0932222222", "vityazverev@gmail.com", "test1"), true);
    }
    app.getContatHelper().goToEditContact();
    app.getContatHelper().fillContactForm(new ContactData("Viktor", "Zverev", "Odessa",
            "0938478961", "0932222222", "vityazverev@gmail.com", "null"), false);
    app.getContatHelper().submitContactModification();
    app.getNavigationHelper().goToHomePage();
    /*int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before);*/
  }
}
