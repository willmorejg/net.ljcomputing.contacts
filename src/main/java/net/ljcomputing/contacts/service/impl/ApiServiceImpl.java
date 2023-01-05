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
import net.ljcomputing.contacts.model.Model;
import net.ljcomputing.contacts.service.ApiService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/** Api Service Implementation. */
public abstract class ApiServiceImpl<T extends Model> implements ApiService<T> {
    /** Value repository. */
    protected PagingAndSortingRepository<T, UUID> repository;

    /**
     * Persist the given value.
     *
     * @param contact
     * @return
     */
    @Override
    public T persist(@Valid T value) {
        repository.save(value);
        return value;
    }

    /**
     * Retrieve all values.
     *
     * @return
     */
    @Override
    public List<T> retrieveAll() {
        return (List<T>) repository.findAll();
    }

    /**
     * Retrieve Page of values.
     *
     * @return
     */
    @Override
    public Page<T> retrievePage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    /**
     * Retrieve Page of values.
     *
     * @param pageable
     * @param filterField
     * @param filterValue
     * @return
     */
    public abstract Page<T> retrievePage(Pageable pageable, String filterField, String filterValue);

    /**
     * Retrieve value by given id.
     *
     * @param id
     * @return
     */
    @Override
    public T retrieve(String id) {
        return repository.findById(UUID.fromString(id)).get();
    }

    /**
     * Delete value by given id.
     *
     * @param id
     * @return
     */
    @Override
    public void delete(String id) {
        repository.deleteById(UUID.fromString(id));
    }
}
