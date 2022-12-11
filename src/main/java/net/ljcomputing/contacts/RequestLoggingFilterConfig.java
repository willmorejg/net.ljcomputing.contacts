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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * Request logging filter configuration.
 *
 * @author James G. Willmore
 */
@Configuration
public class RequestLoggingFilterConfig {

    /**
     * Log filter.
     *
     * @return the commons request logging filter
     */
    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        final CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setIncludeClientInfo(true);
        filter.setIncludeHeaders(true);
        filter.setMaxPayloadLength(1000000);
        filter.setAfterMessagePrefix("REQUEST DATA : \n");
        filter.setAfterMessageSuffix("\n=========================\n");
        filter.setBeforeMessagePrefix("BEFORE : \n");
        filter.setBeforeMessageSuffix("\n*************************\n");
        return filter;
    }
}
