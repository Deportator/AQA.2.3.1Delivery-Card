package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.BACK_SPACE;

public class CardTest {

    @BeforeEach
    void open() {
        Selenide.open("http://localhost:9999/");
    }

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldOrderDeliveryCard() {

        $("[placeholder='Город']").setValue("Санкт-Петербург");
        $("[data-test-id=date] .input__control").click();
        $("[data-test-id=date] .input__control").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=date] .input__control").sendKeys(BACK_SPACE);
        $("[data-test-id=date] .input__control").setValue(generateDate(7));
        $("[name='name']").setValue("Иванов-Петров Иван");
        $("[name='phone']").setValue("+79213523223");
        $("[class='checkbox__box']").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(text("Встреча успешно забронирована на " + generateDate(7)), Duration.ofSeconds(15))
                .shouldBe(visible);
    }
}


