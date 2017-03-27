package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().goToHomePage();
    app.getContatHelper().selectContact();
    app.getContatHelper().deleteSelectedContact();
    app.getContatHelper().acceptAlertPopUp();
    app.getNavigationHelper().goToHomePage();
  }
}
