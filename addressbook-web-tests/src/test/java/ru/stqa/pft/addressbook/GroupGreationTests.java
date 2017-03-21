package ru.stqa.pft.addressbook;


import org.testng.annotations.Test;

public class GroupGreationTests extends TestBase {

  @Test
  public void testGroupGreation() {
    goToGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test1", "test2", "test3"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
