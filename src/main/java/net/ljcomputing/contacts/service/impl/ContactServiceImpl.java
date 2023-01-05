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

import java.util.UUID;
import net.ljcomputing.contacts.model.Contact;
import net.ljcomputing.contacts.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

/** Contact Service Implementation. */
@Service
public class ContactServiceImpl extends ApiServiceImpl<Contact> {

    public ContactServiceImpl(@Autowired PagingAndSortingRepository<Contact, UUID> repository) {
        super.repository = repository;
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
        return ((ContactRepository) repository)
                .findBySurnameContainsIgnoreCaseOrGivenNameContainsIgnoreCase(
                        pageable, filterValue, filterValue);
    }
}
