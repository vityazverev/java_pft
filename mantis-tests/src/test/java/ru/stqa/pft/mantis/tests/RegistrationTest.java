package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by Виктор on 5/19/2017.
 */
public class RegistrationTest extends TestBase {

  @Test
  public void testRegistration() {
    app.registration().start("user", "user1@localhost.localdomain");

  }
}
