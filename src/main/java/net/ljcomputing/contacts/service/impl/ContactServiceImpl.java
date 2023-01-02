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
package net.ljcomputing.contacts.service.impl;

import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import net.ljcomputing.contacts.model.Contact;
import net.ljcomputing.contacts.repository.ContactRepository;
import net.ljcomputing.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/** Contact Service Implementation. */
@Service
public class ContactServiceImpl implements ContactService {
    /** Contact data repository. */
    @Autowired private ContactRepository contactRepository;

    /**
     * Persist the given Contact.
     *
     * @param contact
     * @return
     */
    @Override
    public Contact persist(@Valid Contact contact) {
        contactRepository.save(contact);
        return contact;
    }

    /**
     * Retrieve all Contacts.
     *
     * @return
     */
    @Override
    public List<Contact> retrieveAll() {
        return (List<Contact>) contactRepository.findAll();
    }

    /**
     * Retrieve Page of Contacts.
     *
     * @return
     */
    @Override
    public Page<Contact> retrievePage(Pageable pageable) {
        return contactRepository.findAll(pageable);
    }

    /**
     * Retrieve Page of Contacts.
     *
     * @param pageable
     * @param filterField
     * @param filterValue
     * @return
     */
    public Page<Contact> retrievePage(Pageable pageable, String filterField, String filterValue) {
        return contactRepository.findBySurnameContainsIgnoreCaseOrGivenNameContainsIgnoreCase(
                pageable, filterValue, filterValue);
    }

    /**
     * Retrieve Contact by given id.
     *
     * @param id
     * @return
     */
    @Override
    public Contact retrieve(String id) {
        return contactRepository.findById(UUID.fromString(id)).get();
    }

    /**
     * Delete Contact by given id.
     *
     * @param id
     * @return
     */
    @Override
    public void delete(String id) {
        contactRepository.deleteById(UUID.fromString(id));
    }
}
