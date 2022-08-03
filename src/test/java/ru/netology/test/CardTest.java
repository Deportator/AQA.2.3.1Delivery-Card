package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.conditions.Text;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardTest {
    @Test
    void shouldOrderDeliveryCard() {

        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Санкт-Петербург");
        $("[type='tel']").sendKeys(Keys.CONTROL, "a");
        $("[type='tel']").sendKeys(Keys.DELETE);
        $("[type='tel']").setValue("15.08.2022");
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("+79213523223");
        $("[class='checkbox__box']").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(withText("Успешно!")).waitUntil(Condition.visible, 20000);
    }
}


