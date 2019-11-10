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
    private SelenideElement cancellationButton = $("[data-test-id=action-cancel]");


    public CreditCardPage() {
        heading.shouldBe(Condition.visible);
    }

    public DashBoardPage2 transferFromInfo(CreditCard card, int amount) {
        amountField.setValue(String.valueOf(amount));
        cardNumber.setValue(card.getNumber());
        confirmButton.click();
        return new DashBoardPage2();
    }

    public DashBoardPage2 cancelRequest(CreditCard card, int amount) {
        amountField.setValue(String.valueOf(amount));
        cardNumber.setValue(card.getNumber());
        cancellationButton.click();
        return new DashBoardPage2();

    }

    public void transferMoney(CreditCard card1, CreditCard card2, int amount) {
        int amountCard1 = card1.getBalance() + amount;
        card1.setBalance(amountCard1);
        int amountCard2 = card2.getBalance() - amount;
        card2.setBalance(amountCard2);
    }

    public void transferMoneyCheckingBalance (CreditCard card1, CreditCard card2, int amount) {
        int amountCard1 = card1.getBalance();
        int amountCard2 = card2.getBalance();
        if (amountCard2 >= amount) {
            amountCard1 = amountCard1 + amount;
            amountCard2 = amountCard2 - amount;
            card1.setBalance(amountCard1);
            card2.setBalance(amountCard2);
        } else {
            card1.setBalance(amountCard1);
            card2.setBalance(amountCard2);
        }

    }


}
