package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contact.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(";");
      list.add(new Object[]{new ContactData()
              .withFirstname(split[0]).withLastname(split[1]).withAddress(split[2])
              .withEmail(split[3]).withHomePhone(split[4]).withGroup(split[5])});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
    app.goTo().homePage();
  }

  @Test(dataProvider = "validContacts")
  public void ContactCreatingTests(ContactData contact) {
    Contacts before = app.contact().all();
    app.goTo().create();
    File photo = new File("src/test/resources/l6m1.png");
    app.contact().create(contact, true);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();

    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

//  @Test
//  public void testContactCreation() {
//    app.goTo().homePage();
//    File photo = new File ("src/test/resources/l6m1.png");
//    Contacts before = app.contact().all();
//    app.goTo().create();
//    ContactData contact = new ContactData().withFirstname("Viktor").withLastname("Zverev").withGroup("test1").withPhoto(photo);
//    app.contact().create(contact, true);
//    Contacts after = app.contact().all();
//    assertThat(after.size(), equalTo(before.size() + 1));
//    assertThat(after, equalTo(
//            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
//  }

  @Test (enabled = false)
  public void testBadContactCreation() throws FileNotFoundException {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.goTo().create();
    ContactData contact = new ContactData().withFirstname("Viktor'").withLastname("Zverev").withGroup("test1");
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }
}


/*
    int max = 0;
    for (ContactData g : after) {
      if (g.getId() > max) {
        max = g.getId();
      }
    }
    contact.setId(max);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));*/

