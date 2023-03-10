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

James G Willmore - LJ Computing - (C) 2023
*/
package net.ljcomputing.contacts.controller;

import net.ljcomputing.contacts.model.Model;
import org.springframework.data.domain.Page;

/** REST API Controller interface. */
public interface ApiController<T extends Model> {
    /**
     * Get a Page of data.
     *
     * @param page
     * @param pageSize
     * @param direction
     * @param field
     * @param filterField
     * @param filterValue
     * @return
     */
    Page<T> getPage(
            Integer page,
            Integer pageSize,
            String direction,
            String field,
            String filterField,
            String filterValue);

    /**
     * Post model.
     *
     * @param value
     * @return
     */
    T post(T value);

    /**
     * Get model by id.
     *
     * @param id
     * @return
     */
    T get(String id);

    /**
     * Delete model by id.
     *
     * @param id
     */
    void delete(String id);
}
