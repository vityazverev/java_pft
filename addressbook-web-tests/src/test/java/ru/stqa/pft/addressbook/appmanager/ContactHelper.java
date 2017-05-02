package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;


public class ContactHelper extends HelperBase{
  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobilephone());
    type(By.name("work"), contactData.getWorkphone());
    type(By.name("email"),contactData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }


  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void selectContact(int index) {
    wd.findElements(By.xpath(".//td/input")).get(index).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void acceptAlertPopUp() {
    wd.switchTo().alert().accept();
  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@alt = 'Edit']")).get(index).click();
   /* click(By.xpath("//table[@id='maintable']/tbody/tr/td[8]/a/img"));*/
  }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void createContact(ContactData contact, boolean b) {
    //*goToAddNewContactPage();
    fillContactForm(contact, b);
    submitContactCreation();
    goToHomePage();
  }

  public void modifyContact(int index, ContactData contact) {
  initContactModification(index);
  fillContactForm(contact, false);
  submitContactModification();
  goToHomePage();
  }

  public void goToHomePage() {
    click(By.linkText("home"));
  }

  public void goToAddNewContactPage() {
    click(By.linkText("add new"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
  }

  public int getContactCount() {
    return wd.findElements(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input")).size();
  }

  public List<ContactData> getContactList() {
  List<ContactData> contacts = new ArrayList<ContactData>();
  List<WebElement> elements = wd.findElements(By.xpath("//tr[@name = 'entry']"));
  for (WebElement element : elements){
   String firstname = element.findElement(By.xpath(".//td[2]")).getText();
   String lastname = element.findElement(By.xpath(".//td[3]")).getText();
   int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
   ContactData contact = new ContactData(id, lastname, firstname, null, null,
           null, null, null);
   contacts.add(contact);
  }
  return contacts;
  }
}
