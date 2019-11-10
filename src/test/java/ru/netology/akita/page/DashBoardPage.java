package ru.netology.akita.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.akita.data.CreditCard;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashBoardPage {
    private SelenideElement heading = $("[data-test-id=dashboard");
    private SelenideElement buttonCardOne = $$("[data-test-id=action-deposit").first();
    private SelenideElement buttonCardTwo = $$("[data-test-id=action-deposit").last();
    private SelenideElement balanceCardOne = $$("li").first().$("div");
    private SelenideElement balanceCardTwo = $$("li").last().$("div");


    public DashBoardPage() {
        heading.shouldBe(Condition.visible);
    }

    public CreditCardPage cardOnePage() {
        buttonCardOne.click();
        CreditCardPage cardOne = new CreditCardPage();
        return cardOne;
    }

    public CreditCardPage cardTwoPage() {
        buttonCardTwo.click();
        CreditCardPage cardTwo = new CreditCardPage();
        return cardTwo;
    }

    public String getTextCardOne() {
        String [] text = balanceCardOne.innerText().substring(3).split(" ");
        String balance = text[5];
        return balance;
    }

    public int getBalanceFromPageCardOne() {
        int balanceAmount = Integer.parseInt(getTextCardOne());
        return balanceAmount;
    }

    public String getTextCardTwo() {
        String [] text = balanceCardTwo.innerText().substring(3).split(" ");
        String balance = text[5];
        return balance;
    }

    public int getBalanceFromPageCardTwo() {
        int balanceAmount = Integer.parseInt(getTextCardTwo());
        return balanceAmount;
    }

}