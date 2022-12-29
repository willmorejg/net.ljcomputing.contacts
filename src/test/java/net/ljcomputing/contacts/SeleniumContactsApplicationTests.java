/*
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.

James G Willmore - LJ Computing - (C) 2022
*/
package net.ljcomputing.contacts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import net.ljcomputing.contacts.model.Contact;
import net.ljcomputing.contacts.pom.ContactsView;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class SeleniumContactsApplicationTests {
    @LocalServerPort private int serverPort;

    private static final int totalRecords = 12;

    private static final int itemsPerPage = 5;

    @Test
    void testSimple() throws Exception {
        String wd = System.getProperty("user.dir");
        System.setProperty(
                "webdriver.chrome.driver",
                wd + "/src/main/resources/static/node_modules/chromedriver/bin/chromedriver");

        WebDriver driver = new ChromeDriver();
        String url = "http://localhost:" + serverPort + "/#/view/contacts";
        driver.manage().window().maximize();
        driver.navigate().to(url);

        try {
            ContactsView contactsView = new ContactsView(driver);

            for (Contact current : data()) {
                if (contactsView.finishedLoading()) {
                    contactsView.setGivenName(current.getGivenName());
                    contactsView.setMiddleName(current.getMiddleName());
                    contactsView.setSurname(current.getSurname());
                    contactsView.setSuffix(current.getSuffix());
                    contactsView.submitContactDetail();
                }
            }

            contactsView.takeScreenshot();
            assertEquals(Integer.toString(data().size()), contactsView.getPagnationTotalRecords());
            assertEquals(itemsPerPage, contactsView.getEditIds().size());
            assertEquals(itemsPerPage, contactsView.getDeleteIds().size());
            assertEquals(totalPages(), contactsView.getPages().size());
            assertTrue(contactsView.isPreviousPageDisabled());
            assertFalse(contactsView.isNextPageDisabled());

            String deleteId =
                    contactsView
                            .getDeleteIds()
                            .get(0)
                            .getAttribute("id")
                            .replace(ContactsView.Action.DELETE.idPrefix(), "");
            WebElement deleteLink = contactsView.getIdAction(ContactsView.Action.DELETE, deleteId);

            if (deleteLink != null) {
                deleteLink.click();

                if (contactsView.finishedLoading()) {
                    contactsView.takeScreenshot();
                    assertEquals(
                            Integer.toString(data().size() - 1),
                            contactsView.getPagnationTotalRecords());
                }
            } else {
                contactsView.takeScreenshot();
                fail("delete link is null");
            }

            String editId =
                    contactsView
                            .getEditIds()
                            .get(0)
                            .getAttribute("id")
                            .replace(ContactsView.Action.EDIT.idPrefix(), "");
            WebElement editLink = contactsView.getIdAction(ContactsView.Action.EDIT, editId);

            if (editLink != null) {
                editLink.click();

                if (contactsView.finishedLoading()) {
                    contactsView.setGivenName("x");
                    contactsView.setMiddleName("x");
                    contactsView.setSurname("x");
                    contactsView.setSuffix("x");
                    contactsView.submitContactDetail();
                }

                if (contactsView.finishedLoading()) {
                    editLink.click();

                    if (contactsView.finishedLoading()) {
                        contactsView.takeScreenshot();
                        assertEquals("x", contactsView.getGivenName());
                        assertEquals("x", contactsView.getMiddleName());
                        assertEquals("x", contactsView.getSurname());
                        assertEquals("x", contactsView.getSuffix());
                    }

                    contactsView.takeScreenshot();
                    assertEquals(
                            Integer.toString(data().size() - 1),
                            contactsView.getPagnationTotalRecords());
                }
            } else {
                contactsView.takeScreenshot();
                fail("edit link is null");
            }
        } finally {
            driver.quit();
        }
    }

    private static List<Contact> data() {
        final List<Contact> contacts = new ArrayList<>();
        Contact current = null;

        for (int i = 0; i < totalRecords; i++) {
            current = new Contact();
            current.setGivenName(i + "GivenName");
            current.setMiddleName(i + "MiddleName");
            current.setSurname(i + "Surname");
            current.setSuffix(i + "Suffix");
            contacts.add(current);
        }

        return contacts;
    }

    private static int totalPages() {
        double l = totalRecords;
        double i = itemsPerPage;
        int value = 1;

        if (l != 0) {
            value = (int) Math.ceil(l / i);

            if (value == 0) {
                value = 1;
            }
        }

        return value;
    }
}
