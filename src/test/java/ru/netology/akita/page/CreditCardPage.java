package ru.netology.akita.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.akita.data.CreditCard;
import ru.netology.akita.data.DataHelper;
import sun.awt.SunHints;

import static com.codeborne.selenide.Selenide.$;

public class CreditCardPage {
    private SelenideElement heading = $("[data-test-id=dashboard");
    private SelenideElement amountField = $("[data-test-id=amount] input.input__control");
    private SelenideElement cardNumber = $("[data-test-id=from] input.input__control");
    private SelenideElement confirmButton = $("[data-test-id=action-transfer]");
    private SelenideElement cancellation = $("[data-test-id=action-cancel]");


    public CreditCardPage() {
        heading.shouldBe(Condition.visible);
    }

    public DashBoardPage2 putCardInfo(CreditCard card1, CreditCard card2, int amount) {
        amountField.setValue(String.valueOf(amount)); //ввод суммы к переводу в формате строки
        cardNumber.setValue(card2.getNumber());
        transferMoney(card1, card2, amount);
        confirmButton.click();
        return new DashBoardPage2();
    }

    public void transferMoney(CreditCard card1, CreditCard card2, int amount) {
        int amountCard1 = card1.getBalance() + amount;
        card1.setBalance(amountCard1);
        int amountCard2 = card2.getBalance() - amount;
        card2.setBalance(amountCard2);
    }


}
