package com.juaracoding.week03;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class QuizDragDropTest {
  WebDriver driver;

  @BeforeClass
  @Parameters({ "url" })
  public void init(String url) {
    driver = DriverSingleton.createOrGetDriver();
    driver.get(url);
  }

  @Test(enabled = false)
  public void testStep01() throws InterruptedException {
    JavascriptExecutor jse = (JavascriptExecutor) driver;
    jse.executeScript("window.scrollBy({top: 300, behavior: 'smooth'})");
    Thread.sleep(2000);
    WebElement draggable = driver.findElement(By.xpath("//div[@id='box1']"));
    WebElement drop = driver.findElement(By.id("box101"));
    // WebElement capitals = driver.findElement(By.xpath("//div[@id='capitals']"));
    Actions builder = new Actions(driver);

    Action dragger = builder.clickAndHold(draggable)
        .pause(Duration.ofSeconds(2))
        .moveToElement(drop, 0, 2)
        .pause(Duration.ofSeconds(2))
        .release()
        .pause(Duration.ofSeconds(3))
        .build();
    dragger.perform();

    Thread.sleep(1000);
    String bgColor = draggable.getCssValue("background-color");
    String expected = "rgba(0, 255, 0, 1)";
    // jse.executeScript("alert('uhuyyy')");
    // jse.executeScript("document.querySelector('#box1').style.color = 'white'");
    Thread.sleep(5000);
    Assert.assertEquals(bgColor, expected);
  }

  private void draggableSantui(String idDrag, String idDrop) {
    WebElement draggable = driver.findElement(By.id(idDrag));
    WebElement drop = driver.findElement(By.id(idDrop));
    Actions builder = new Actions(driver);
    builder.dragAndDrop(draggable, drop).perform();
  }

  /*  private void droppableSantui(String idDrag, String idcapitals) {
    WebElement draggable = driver.findElement(By.id(idDrag));
    WebElement capitals = driver.findElement(By.id(idcapitals));
    Actions builder = new Actions(driver);
    builder.dragAndDrop(capitals, draggable).perform();
  }
  */


  @Test (priority = 1)
  public void rightDragdrop() throws InterruptedException {

    String[][] keyElements = {
        { "box1", "box101" }, // (oslo - norway)
        { "box2", "box102" }, // (stockholm - sweden)
        { "box3", "box103" }, 
        { "box4", "box104" }, 
        { "box5", "box105" }, 
        { "box6", "box106" }, 
        { "box7", "box107" }, 
    };

    for (int row = 0; row < keyElements.length; row++) {
      draggableSantui(keyElements[row][0], keyElements[row][1]);
      Thread.sleep(2000);
    }
  }

   @Test (priority = 2)
  public void leftDragdrop() throws InterruptedException {
    String[][] keyElements = {
        { "box1", "capitals" }, 
        { "box2", "capitals" }, 
        { "box3", "capitals" }, 
        { "box4", "capitals" }, 
        { "box5", "capitals" }, 
        { "box6", "capitals" }, 
        { "box7", "capitals" }, 
    };

    for (int row = 0; row < keyElements.length; row++) {
      draggableSantui(keyElements[row][0], keyElements[row][1]);
      Thread.sleep(2000);
    }
  }
}