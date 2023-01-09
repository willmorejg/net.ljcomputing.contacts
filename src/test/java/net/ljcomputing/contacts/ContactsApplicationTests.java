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

James G Willmore - LJ Computing - (C) 2022-2023
*/
package net.ljcomputing.contacts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import net.ljcomputing.contacts.model.Contact;
import net.ljcomputing.contacts.model.EmailAddress;
import net.ljcomputing.contacts.service.ApiService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

/** Contacts Application Unit Tests. */
@SpringBootTest
class ContactsApplicationTests {
    /** Logging instance. */
    private static final Logger log = LoggerFactory.getLogger(ContactsApplicationTests.class);

    /** Contact Service. */
    @Autowired private ApiService<Contact> contactService;

    /** Email Address Service. */
    @Autowired private ApiService<EmailAddress> emailAddressService;

    /** Services Tests. */
    @Test
    void servicesTests() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Ab", "ab", "4Bb", "bb"));
        contacts.add(new Contact("Ac", "ac", "2Bc", "bc"));
        contacts.add(new Contact("Ad", "ad", "1Bd", "bd"));
        contacts.add(new Contact("A", "a", "3B", "b"));

        for (Contact contact : contacts) {
            contactService.persist(contact);
            assertNotNull(contact.getId());
        }

        contacts = contactService.retrieveAll();
        assertEquals(4, contacts.size());

        Pageable pageable = PageRequest.of(0, 4, Direction.valueOf("DESC"), "surname");
        assertEquals(4, contactService.retrievePage(pageable).toList().size());

        String localPart =
                String.format(
                        "%s.%s", contacts.get(0).getGivenName(), contacts.get(0).getSurname());

        EmailAddress emailAddress = new EmailAddress(localPart, "dev.net", contacts.get(0));
        emailAddress = emailAddressService.persist(emailAddress);
        log.debug("emailAddress: {}", emailAddress.getContact());

        Contact contactWithAssociated = contactService.retrieve(contacts.get(0).getId().toString());
        log.debug("contactWithAssociated: {}", contactWithAssociated);
        assertEquals(1, contactWithAssociated.getEmailAddresses().size());
    }
}
