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
package net.ljcomputing.contacts.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "given_name", length = 100)
    @Size(max = 100)
    @NotNull
    @NotEmpty(message = "Given name is required")
    private String givenName;

    @Column(name = "middle_name", length = 100)
    @Size(max = 100)
    private String middleName;

    @Column(name = "surname", length = 100)
    @Size(max = 100)
    @NotNull
    @NotEmpty(message = "Surname is required")
    private String surname;

    @Column(name = "suffix", length = 100)
    @Size(max = 100)
    private String suffix;

    public void trimValues() {
        this.givenName = this.givenName.trim();
        this.middleName = this.middleName.trim();
        this.surname = this.surname.trim();
        this.suffix = this.suffix.trim();
    }
}
