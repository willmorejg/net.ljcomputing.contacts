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
package net.ljcomputing.contacts.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import net.ljcomputing.contacts.model.Contact;
import net.ljcomputing.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/contacts")
@Slf4j
public class ContactController {
    @Autowired private ContactService contactService;

    @GetMapping(produces = "application/json")
    public List<Contact> getContacts() {
        return contactService.retrieveAll();
    }

    @PostMapping(produces = "application/json")
    public Contact postContact(@Valid @RequestBody Contact contact) {
        log.debug("contact: {}", contact);
        return contactService.persist(contact);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Contact getContact(@PathVariable String id) {
        return contactService.retrieve(id);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public void deleteContact(@PathVariable String id) {
        contactService.delete(id);
    }
}
