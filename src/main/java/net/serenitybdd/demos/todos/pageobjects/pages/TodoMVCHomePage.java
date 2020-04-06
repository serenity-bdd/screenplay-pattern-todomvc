package net.serenitybdd.demos.todos.pageobjects.pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.demos.todos.pageobjects.model.TodoStatus;
import net.serenitybdd.demos.todos.pageobjects.model.TodoStatusFilter;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@DefaultUrl("http://todomvc.com/")
public class TodoMVCHomePage extends PageObject {

}
