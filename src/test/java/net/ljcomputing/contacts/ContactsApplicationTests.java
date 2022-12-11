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
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import net.ljcomputing.contacts.model.Contact;
import net.ljcomputing.contacts.service.ContactService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/** Contacts Application Unit Tests. */
@SpringBootTest
class ContactsApplicationTests {
    /** Contact Service. */
    @Autowired private ContactService contactService;

    /** Repository (Service) Test. */
    @Test
    void repositoryTest() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact(null, "Ab", "ab", "Bb", "bb"));
        contacts.add(new Contact(null, "Ac", "ac", "Bc", "bc"));
        contacts.add(new Contact(null, "Ad", "ad", "Bd", "bd"));
        contacts.add(new Contact(null, "A", "a", "B", "b"));

        for (Contact contact : contacts) {
            contactService.persist(contact);
            assertNotNull(contact.getId());
        }

        contacts = contactService.retrieveAll();

        assertEquals(4, contacts.size());
    }
}
