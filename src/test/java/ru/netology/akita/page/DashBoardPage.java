package ru.netology.akita.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.akita.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashBoardPage {
    private SelenideElement heading = $("[data-test-id=dashboard");
    private SelenideElement buttonCardOne = $$("[data-test-id=action-deposit").first();
    private SelenideElement buttonCardTwo = $$("[data-test-id=action-deposit").last();
    private SelenideElement balanceCardOne = $$("li").first();
    private SelenideElement balanceCardTwo = $$("li").last();


    public DashBoardPage() {
        heading.shouldBe(Condition.visible);
    }

    public CreditCardPage cardPageOne() {
        buttonCardOne.click();
        CreditCardPage cardOne = new CreditCardPage();
        return cardOne;
    }

    public CreditCardPage cardPageTwo() {
        buttonCardTwo.click();
        CreditCardPage cardTwo = new CreditCardPage();
        return cardTwo;
    }


    public void transferMoney(DataHelper.CreditCard card1, DataHelper.CreditCard card2, int amount) {
        int amountCard1 = card1.getBalance();
        amountCard1 = amountCard1 + amount; // к первой карте прибавляем сумму
        int amountCard2 = card2.getBalance() - amount;
        amountCard2 = amountCard2 - amount; // списываем сумму со второй карты
    }

    public String getTextBalance(DataHelper.CreditCard card) {
        String balanceAtCard = String.valueOf(card.getBalance());
        return balanceAtCard;
    }

    public String getText() {
        String text = balanceCardOne.innerText();
        return text;
    }


}