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
package net.ljcomputing.contacts.pom;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import net.ljcomputing.BasePom;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class ContactsView extends BasePom {
    @FindBy(id = "givenName")
    private WebElement givenName;

    @FindBy(id = "middleName")
    private WebElement middleName;

    @FindBy(id = "surname")
    private WebElement surname;

    @FindBy(id = "suffix")
    private WebElement suffix;

    @FindBy(id = "submitContactDetail")
    private WebElement submitContactDetail;

    @FindBy(id = "pagnationTotalRecords")
    private WebElement pagnationTotalRecords;

    @FindBy(id = "centerLoadingSpinner")
    private WebElement centerLoadingSpinner;

    public ContactsView(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getGivenName() {
        return getWebElementValue(givenName);
    }

    public void setGivenName(String value) {
        setWebElementValue(givenName, value);
    }

    public String getMiddleName() {
        return getWebElementValue(middleName);
    }

    public void setMiddleName(String value) {
        setWebElementValue(middleName, value);
    }

    public String getSurname() {
        return getWebElementValue(surname);
    }

    public void setSurname(String value) {
        setWebElementValue(surname, value);
    }

    public String getSuffix() {
        return getWebElementValue(suffix);
    }

    public void setSuffix(String value) {
        setWebElementValue(suffix, value);
    }

    public void submitContactDetail() {
        submitContactDetail.click();
    }

    public boolean isPagnationTotalRecordsPresent() {
        try {
            FluentWait<WebDriver> wait = new FluentWait<WebDriver>(getDriver());
            wait.withTimeout(Duration.ofSeconds(2L));
            wait.pollingEvery(Duration.ofNanos(500L));
            wait.ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pagnationTotalRecords")));
        } catch (TimeoutException e) {
            return false;
        }

        return true;
    }

    public String getPagnationTotalRecords() {
        if (isPagnationTotalRecordsPresent()) {
            return pagnationTotalRecords.getText();
        } else {
            return "";
        }
    }

    public boolean finishedLoading() {
        try {
            FluentWait<WebDriver> wait = new FluentWait<WebDriver>(getDriver());
            wait.withTimeout(Duration.ofSeconds(2L));
            wait.pollingEvery(Duration.ofNanos(500L));
            wait.ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.invisibilityOf(centerLoadingSpinner));
        } catch (TimeoutException e) {
            return false;
        }

        return true;
    }

    public List<WebElement> getEditIds() {
        return getDriver().findElements(By.xpath("//*[contains(@id, 'edit-')]"));
    }

    public List<WebElement> getDeleteIds() {
        return getDriver().findElements(By.xpath("//*[contains(@id, 'delete-')]"));
    }

    public List<WebElement> getPageLinks() {
        return getDriver().findElements(By.cssSelector(".page-item"));
    }

    public WebElement getNextPage() {
        WebElement value = null;

        for (WebElement el : getPageLinks()) {
            String text = el.getText();
            boolean ret = text.equals("Next");

            if (ret) {
                value = el;
            }
        }

        return value;
    }

    public boolean isNextPageDisabled() {
        return getNextPage().getAttribute("class").contains("disabled");
    }

    public WebElement getPreviousPage() {
        WebElement value = null;

        for (WebElement el : getPageLinks()) {
            String text = el.getText();
            boolean ret = text.equals("Previous");

            if (ret) {
                value = el;
            }
        }

        return value;
    }

    public boolean isPreviousPageDisabled() {
        return getPreviousPage().getAttribute("class").contains("disabled");
    }

    public List<WebElement> getPages() {
        final List<WebElement> elements = new ArrayList<>();

        getPageLinks()
                .forEach(
                        el -> {
                            String text = el.getText();
                            boolean doNotAdd = text.equals("Previous") || text.equals("Next");

                            if (!doNotAdd) {
                                elements.add(el);
                            }
                        });

        return elements;
    }
}
