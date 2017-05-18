package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[]{new ContactData().withFirstname("name1").withLastname("lastname1").withHomePhone("12345")
            .withMobilePhone("2345").withWorkPhone("345").withEmail("email@mail.com1").withAddress("address1").withGroup("test1")});
    list.add(new Object[]{new ContactData().withFirstname("name2").withLastname("lastname2").withHomePhone("12345")
            .withMobilePhone("2345").withWorkPhone("345").withEmail("email@mail.com2").withAddress("address2").withGroup("test1")});
    list.add(new Object[]{new ContactData().withFirstname("name3").withLastname("lastname3").withHomePhone("12345")
            .withMobilePhone("2345").withWorkPhone("345").withEmail("email@mail.com3").withAddress("address3").withGroup("test1")});
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

  @Test
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

