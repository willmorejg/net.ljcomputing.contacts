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
package net.ljcomputing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;
import javax.imageio.ImageIO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public abstract class BasePom {
    private String baseScreenshotDirectory =
            Path.of(System.getProperty("user.dir"), "screenshots").toString();
    private WebDriver driver;

    public BasePom(final WebDriver driver) {
        this.driver = driver;
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected void clearWebElementValue(final WebElement element) {
        new Actions(driver).moveToElement(element).perform();
        element.clear();
    }

    protected String getWebElementValue(final WebElement element) {
        new Actions(driver).moveToElement(element).perform();
        return element.getAttribute("value");
    }

    protected void setWebElementValue(final WebElement element, final String value) {
        new Actions(driver).moveToElement(element).perform();
        element.sendKeys(value);
    }

    public void takeScreenshot() {
        String filename = new Date().getTime() + ".jpg";
        String filepath = baseScreenshotDirectory + File.separator + filename;
        System.out.println("filepath = " + filepath);
        Screenshot screenshot =
                new AShot()
                        .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                        .takeScreenshot(driver);
        try {
            ImageIO.write(screenshot.getImage(), "jpg", new File(filepath));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
