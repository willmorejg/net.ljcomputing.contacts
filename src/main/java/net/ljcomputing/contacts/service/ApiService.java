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
package net.ljcomputing.contacts.service;

import java.util.List;
import net.ljcomputing.contacts.model.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** API Service. */
public interface ApiService<T extends Model> {
    /**
     * Persist the given value.
     *
     * @param value
     * @return
     */
    T persist(T value);

    /**
     * Retrieve all value.
     *
     * @return
     */
    List<T> retrieveAll();

    /**
     * Retrieve Page of values.
     *
     * @param pageable
     * @return
     */
    Page<T> retrievePage(Pageable pageable);

    /**
     * Retrieve Page of values.
     *
     * @param pageable
     * @param filterField
     * @param filterValue
     * @return
     */
    Page<T> retrievePage(Pageable pageable, String filterField, String filterValue);

    /**
     * Retrieve value by given id.
     *
     * @param id
     * @return
     */
    T retrieve(String id);

    /**
     * Delete Contact by given id.
     *
     * @param id
     * @return
     */
    void delete(String id);
}
