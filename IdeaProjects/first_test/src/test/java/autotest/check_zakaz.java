package autotest;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class check_zakaz {
    private static WebDriver driver;
    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\time_to_cooking\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://lknew.qa.cdek.ru");
    }
    @Test
    public void userLog_in1() {
        WebElement login = driver.findElement(By.id("loginform-login"));
        login.sendKeys("ma.shishkin");
        WebElement Passwd = driver.findElement(By.id("loginform-password"));
        Passwd.sendKeys("1234");
        WebElement loginButton = driver.findElement(By.name("login-button"));
        loginButton.click();
        String errorText = driver.findElement(By.cssSelector("#login-form > div.form-group.field-loginform-password.required.has-error > p")).getText();
        System.out.println(errorText);
        Assert.assertEquals("Неверное сочетание логина и пароля.",errorText);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://lknew.qa.cdek.ru");
    }
    @Test
    public void userLog_in2() {
        WebElement login = driver.findElement(By.id("loginform-login"));
        login.sendKeys("ma.shishkin");
        WebElement Passwd = driver.findElement(By.id("loginform-password"));
        Passwd.sendKeys("192168113");
        WebElement loginButton = driver.findElement(By.name("login-button"));
        loginButton.click();
        WebElement ordersButton = driver.findElement(By.cssSelector("body > div.wrap > div > div > div.col-lg-3 > div.panel.panel-default > div > ul > li:nth-child(3) > a"));
        ordersButton.click();
    }
    @Test
    public void lk_submenu3() {
        WebElement neworderButton = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[1]/div/ul/li[1]/a"));
        neworderButton.click();
        WebElement length = driver.findElement(By.id("l1"));
        length.sendKeys("11");
        WebElement width = driver.findElement(By.id("w1"));
        width.sendKeys("11");
        WebElement height = driver.findElement(By.id("h1"));
        height.sendKeys("11");
        WebElement description = driver.findElement(By.id("d1"));
        description.sendKeys("Посылочка");
        WebElement payer = driver.findElement(By.id("payer-type-sender"));
        payer.click();
        WebElement cityFrom = driver.findElement(By.id("dFromCity"));
        cityFrom.click();
        WebElement cityFromEnter = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div[2]/div/form/div[2]/div[2]/input"));
        cityFromEnter.sendKeys("Новосиб");
        WebElement selectFrom = (new WebDriverWait(driver, 100)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ul[1]/li[contains(text(), 'Новосибирск, Новосибирская обл., Россия')]")));
        selectFrom.click();

        WebElement ToCity = driver.findElement(By.id("dToCity"));
        ToCity.sendKeys("Иркут");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        List<WebElement> tc = driver.findElements(By.xpath("//ul[2]/li"));
        for (WebElement tcchaild : tc) {
            System.out.println(tcchaild.getText());
            if (tcchaild.getText().equals("Иркутск, Иркутская обл., Россия")) {
                tcchaild.click();
                break;
            }
        }

        WebElement tarifCalc = driver.findElement(By.id("tarif-calc"));
        tarifCalc.click();
    }
    @Test
    public void lk_submenu4() {
        WebElement streetFrom = (new WebDriverWait(driver, 100)).until(ExpectedConditions.visibilityOfElementLocated(By.id("dSenderStreet")));
        streetFrom.click();
        WebElement streetFromEnter = driver.findElement(By.xpath("//*[@id=\"dSenderStreet\"]"));
        streetFromEnter.sendKeys("Киро");
        WebElement selectStreetFrom = (new WebDriverWait(driver, 100)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ul[5]/li[contains(text(), 'КИРОВА УЛ')]")));
        selectStreetFrom.click();
        WebElement senderPhone = driver.findElement(By.xpath("//*[@id=\"sender-block\"]/div[3]/div/input[1]"));
        senderPhone.sendKeys("1288");
        WebElement senderHouse = driver.findElement(By.id("dSenderHouse"));
        senderHouse.sendKeys("104");
        WebElement senderRoom = driver.findElement(By.id("dSenderRoom"));
        senderRoom.sendKeys("98");
        WebElement receiverName = driver.findElement(By.id("dReceiverName"));
        receiverName.sendKeys("Ромашев Виктор Алексеевич");
        WebElement streetTo = driver.findElement(By.id("dReceiverStreet"));
        streetTo.click();
        WebElement streetToEnter = driver.findElement(By.xpath("//*[@id=\"dReceiverStreet\"]"));
        streetToEnter.sendKeys("Киро");
        WebElement selectStreetTo = (new WebDriverWait(driver, 100)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ul[6]/li[contains(text(), 'КИРОВА УЛ')]")));
        selectStreetTo.click();
        WebElement receiverPhone = driver.findElement(By.xpath("//*[@id=\"receiver-block\"]/div[3]/div/input[1]"));
        receiverPhone.sendKeys("89138961288");
        WebElement receiverHouse = driver.findElement(By.id("dReceiverHouse"));
        receiverHouse.sendKeys("98");
        WebElement receiverRoom = driver.findElement(By.id("dReceiverRoom"));
        receiverRoom.sendKeys("50");
        WebElement previewButton = driver.findElement(By.xpath("//*[@id=\"delivery-confirm\"]"));
        previewButton.click();
        String textCheck = driver.findElement(By.cssSelector("#confirmation-table > tbody > tr:nth-child(1) > th")).getText();
        Assert.assertEquals("Проверьте правильность введенных данных", textCheck);
        WebElement orderConfirm = (new WebDriverWait(driver, 500)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#delivery-send")));
       (new WebDriverWait(driver, 500)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#delivery-send")));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", orderConfirm);
        String orderNumber = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div[2]/div/div/h4/span[1]")).getText();
        String order = getNumberFromString(orderNumber);
        System.out.println (order);
        driver.get("http://lknew.qa.cdek.ru/page/orderhistory");

        WebElement historyButton = driver.findElement(By.cssSelector("#tabOrderHistory > a"));
        historyButton.click();
        WebElement orderNumberField = driver.findElement(By.xpath("//*[@id=\"order_number\"]"));
        orderNumberField.click();
        orderNumberField.sendKeys(order);

        WebElement filtrButton = driver.findElement(By.cssSelector("#filter_form > table > tbody > tr:nth-child(6) > td:nth-child(1) > button"));
        filtrButton.click();

    }

    @Test
    public void lk_submenu7() {
        WebElement neworderButton = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[1]/div/ul/li[1]/a"));
        neworderButton.click();
        WebElement length = driver.findElement(By.id("l1"));
        length.sendKeys("11");
        WebElement width = driver.findElement(By.id("w1"));
        width.sendKeys("11");
        WebElement height = driver.findElement(By.id("h1"));
        height.sendKeys("11");
        WebElement description = driver.findElement(By.id("d1"));
        description.sendKeys("Посылочка");
        WebElement payer = driver.findElement(By.id("payer-type-sender"));
        payer.click();
        WebElement cityFrom = driver.findElement(By.id("dFromCity"));
        cityFrom.click();
        WebElement cityFromEnter = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div[2]/div/form/div[2]/div[2]/input"));
        cityFromEnter.sendKeys("Новосиб");

        WebElement selectFrom = (new WebDriverWait(driver, 100)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ul[1]/li[contains(text(), 'Новосибирск, Новосибирская обл., Россия')]")));
        selectFrom.click();
        WebElement cityTo = driver.findElement(By.id("dToCity"));
        cityTo.click();

        WebElement ToCity = driver.findElement(By.id("dToCity"));
        ToCity.sendKeys("Иркут");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        List<WebElement> tc = driver.findElements(By.xpath("//ul[2]/li"));
        for (WebElement tcchaild : tc) {
            System.out.println(tcchaild.getText());
            if (tcchaild.getText().equals("Иркутск, Иркутская обл., Россия")) {
                tcchaild.click();
                break;
            }
        }

        WebElement tarifCalc = driver.findElement(By.id("tarif-calc"));
        tarifCalc.click();
    }
    @Test
    public void lk_submenu8() {
        WebElement streetFrom = (new WebDriverWait(driver, 100)).until(ExpectedConditions.visibilityOfElementLocated(By.id("dSenderStreet")));
        streetFrom.click();
        WebElement streetFromEnter = driver.findElement(By.xpath("//*[@id=\"dSenderStreet\"]"));
        streetFromEnter.sendKeys("Киро");
        WebElement selectStreetFrom = (new WebDriverWait(driver, 100)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ul[5]/li[contains(text(), 'КИРОВА УЛ')]")));
        selectStreetFrom.click();
        WebElement senderPhone = driver.findElement(By.xpath("//*[@id=\"sender-block\"]/div[3]/div/input[1]"));
        senderPhone.sendKeys("91389644444");
        WebElement senderHouse = driver.findElement(By.id("dSenderHouse"));
        senderHouse.sendKeys("98");
        WebElement senderRoom = driver.findElement(By.id("dSenderRoom"));
        senderRoom.sendKeys("5");
        WebElement receiverName = driver.findElement(By.id("dReceiverName"));
        receiverName.sendKeys("Ромашев Виктор Алексеевич");
        WebElement streetTo = driver.findElement(By.id("dReceiverStreet"));
        streetTo.click();
        WebElement streetToEnter = driver.findElement(By.xpath("//*[@id=\"dReceiverStreet\"]"));
        streetToEnter.sendKeys("Киро");
        WebElement selectStreetTo = (new WebDriverWait(driver, 100)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ul[6]/li[contains(text(), 'КИРОВА УЛ')]")));
        selectStreetTo.click();
        WebElement receiverPhone = driver.findElement(By.xpath("//*[@id=\"receiver-block\"]/div[3]/div/input[1]"));
        receiverPhone.sendKeys("89138961288");
        WebElement receiverHouse = driver.findElement(By.id("dReceiverHouse"));
        receiverHouse.sendKeys("");
        WebElement receiverRoom = driver.findElement(By.id("dReceiverRoom"));
        receiverRoom.sendKeys("");
        WebElement previewButton = driver.findElement(By.xpath("//*[@id=\"delivery-confirm\"]"));
        previewButton.click();
        String errorText2 = driver.findElement(By.cssSelector("#delivery-error")).getText();
        Assert.assertEquals("Пожалуйста, заполните все необходимые поля", errorText2);
        System.out.println(errorText2);
    }
    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
    public  String getNumberFromString(String str) {
        Pattern numberPattern = Pattern.compile("-?\\d+");
        Matcher matcher = numberPattern.matcher(str);
        return matcher.find() ? matcher.group() : "";
    }
}

