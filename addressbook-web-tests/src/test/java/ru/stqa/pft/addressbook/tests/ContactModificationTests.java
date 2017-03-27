package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification(){
    app.getNavigationHelper().goToHomePage();
    app.getContatHelper().goToEditContact();
    app.getContatHelper().fillContactForm(new ContactData("Viktor", "Zverev", "Odessa", "0938478961", "0932222222", "vityazverev@gmail.com"));
    app.getContatHelper().submitContactModification();
    app.getNavigationHelper().goToHomePage();

  }
}
