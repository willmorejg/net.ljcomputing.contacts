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
package net.ljcomputing.contacts.service.impl;

import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import net.ljcomputing.contacts.model.Contact;
import net.ljcomputing.contacts.repository.ContactRepository;
import net.ljcomputing.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired private ContactRepository contactRepository;

    @Override
    public Contact persist(@Valid Contact contact) {
        contactRepository.save(contact);
        return contact;
    }

    @Override
    public List<Contact> retrieveAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact retrieve(String id) {
        return contactRepository.findById(UUID.fromString(id)).get();
    }

    @Override
    public void delete(String id) {
        contactRepository.deleteById(UUID.fromString(id));
    }
}
