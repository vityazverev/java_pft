package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Виктор on 5/16/2017.
 */
public class EmailTests extends TestBase {

  public class ContactPhoneTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
      if (app.contact().all().size() == 0) {
        app.contact().create();
        app.contact().create(new ContactData().withFirstname("Viktor").withLastname("Zverev").withAddress("Odessa")
                .withMobilePhone("0938478961").withWorkPhone("0932222222").withEmail("vityazverev@gmail.com").withGroup("test1"), true);
      }
    }


    @Test
    public void testEmails() {
      app.goTo().homePage();
      ContactData contact = app.contact().all().iterator().next();
      ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

      assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    private String mergeEmails(ContactData contact) {
      return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
              .stream().filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
    }
  }
}
