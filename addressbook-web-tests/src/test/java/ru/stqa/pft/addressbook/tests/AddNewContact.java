package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;


public class AddNewContact extends TestBase {

  @Test
  public void testAddNewContactCreation() {
    app.getNavigationHelper().goToAddNewContactPage();
    app.getContatHelper().fillContactForm(new ContactData("Viktor", "Zverev", "Odessa", "0938478961", "0932222222", "vityazverev@gmail.com"));

  }



public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupGreation() {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage();
  }

}