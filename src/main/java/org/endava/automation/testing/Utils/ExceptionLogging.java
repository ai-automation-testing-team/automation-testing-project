package org.endava.automation.testing.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExceptionLogging {

    static void elementNotFoundFromParent(WebElement root, By byLocator) {
        String innerHTML = root.getAttribute("innerHTML");

        StringBuilder sb = new StringBuilder();

        sb.append("Element by locator: '")
            .append(byLocator)
            .append("' wasn't found in root element: ")
            .append(root)
            .append("\n")
            .append("Please verify if the locator '")
            .append(byLocator)
            .append("' being used is correct when compared with the one from the provided Root element HTML below.")
            .append("\n")
            .append("Root element innerHTML: ")
            .append(innerHTML);

        Log.errorLogger("Exception was thrown because of this reason:\n" + sb.toString());
    }

    static void elementNotFoundPerLocator(WebDriver driver, By byLocator) {
        WebElement bodyElement = driver.findElement(By.tagName("body"));
        String innerHTML = bodyElement.getAttribute("innerHTML");

        StringBuilder sb = new StringBuilder();

        sb.append("Element by locator: ")
            .append(byLocator)
            .append(" wasn't found from root")
            .append("\n")
            .append("HTML: ")
            .append("\n")
            .append(innerHTML);

        Log.errorLogger("Exception was thrown because of this reason:\n" + sb.toString());
    }

    static void invalidElementState(WebElement element) {
        String parentInnerHTML = element.findElement(By.xpath("./..")).getAttribute("innerHTML");

        StringBuilder sb = new StringBuilder();

        sb.append("Element: ")
            .append(element)
            .append(" wasn't interactable. ")
            .append("It is located inside his parent element with HTML: ")
            .append("\n")
            .append(parentInnerHTML);

        Log.errorLogger("Exception was thrown because of this reason:\n" + sb.toString());
    }

    static void staleElementNotFound(WebElement element) {
        StringBuilder sb = new StringBuilder();

        sb.append("Stale element: ")
            .append(element)
            .append(" is no longer present in the DOM, or has been modified after it was initially located");

        Log.errorLogger("Exception was thrown because of this reason:\n" + sb.toString());
    }
}
