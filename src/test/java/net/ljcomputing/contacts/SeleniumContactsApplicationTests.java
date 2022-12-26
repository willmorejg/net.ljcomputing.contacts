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

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@Slf4j
class SeleniumContactsApplicationTests {
    @LocalServerPort private int serverPort;

    @Test
    void testSimple() throws Exception {
        String wd = System.getProperty("user.dir");
        System.setProperty(
                "webdriver.chrome.driver",
                wd + "/src/main/resources/static/node_modules/chromedriver/bin/chromedriver");

        WebDriver driver = new ChromeDriver();
        String url = "http://localhost:" + serverPort + "/#/view/contacts";
        log.debug("url: {}", url);
        driver.manage().window().maximize();
        driver.navigate().to(url);
        Thread.sleep(6000);
        driver.quit();
    }
}
