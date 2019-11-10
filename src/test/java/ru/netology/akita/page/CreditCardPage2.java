package ru.netology.akita.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selenide.$;

public class CreditCardPage2 {
    private SelenideElement heading = $("[data-test-id=dashboard");
    private SelenideElement amountField = $("[data-test-id=amount] input.input__control");
    private SelenideElement cardNumber = $("[data-test-id=from] input.input__control");
    private SelenideElement confirmButton = $("[data-test-id=action-transfer]");
    private SelenideElement cancellationButton = $("[data-test-id=action-cancel]");


    public CreditCardPage2() {
        heading.shouldBe(Condition.visible);
    }

    public boolean isEmpty() {
        return amountField.is(empty) && cardNumber.is(empty);
    }
}
