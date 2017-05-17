package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactPhoneInfoTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.contact().all().size() == 0) {
      app.contact().create();
      app.contact().create(new ContactData().withFirstname("Viktor").withLastname("Zverev").withAddress("Odessa")
              .withMobilePhone("0938478961").withWorkPhone("0932222222").withEmail("vityazverev@gmail.com").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactInfo() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData infoContactDetails = app.contact().infoFromDetailsForm(contact);
    ContactData infoFromEditFormForDetails = app.contact().infoFromEditFormForDetails(contact);

    assertThat(details(infoContactDetails), equalTo(mergePhones(infoFromEditFormForDetails)));
  }


  private String details (ContactData contact) {
    String result =  Arrays.asList(contact.getAllDetails())
            .stream().filter(s -> !(s == null || s.equals("")))
            .map(ContactPhoneInfoTests::cleanedForDetail)
            .collect(Collectors.joining(" "));
    System.out.println("Детали: " + result);
    return result;
  }
  private String mergePhones(ContactData contact) {
    String result =  Arrays.asList(
            contact.getFirstname(),
            contact.getLastname(),
            contact.getAddress(),
            contact.getHomePhone(),
            contact.getMobilePhone(),
            contact.getWorkPhone(),
            contact.getEmail(),
            contact.getEmail2(),
            contact.getEmail3())
            .stream().filter(s -> !(s == null || s.equals("")))
            .map(ContactPhoneInfoTests::cleaned)
            .collect(Collectors.joining(" "));
    System.out.println("Редактирование: " + result);
    return result;
  }

  public static String cleanedForDetail(String phone) {
    return phone.replaceAll("\n", " ").replaceAll("  ", " ").replaceAll(": ", ":");
  }

  public static String cleaned(String phone) {
    return phone./*replaceAll("\\s", "").*/replaceAll("\n", "");
  }
}

