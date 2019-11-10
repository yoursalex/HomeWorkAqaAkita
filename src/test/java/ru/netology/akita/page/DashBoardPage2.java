package ru.netology.akita.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.akita.data.CreditCard;

import javax.smartcardio.Card;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashBoardPage2 {
    private SelenideElement heading = $("[data-test-id=dashboard");
    private SelenideElement buttonCardOne = $$("[data-test-id=action-deposit").first();
    private SelenideElement buttonCardTwo = $$("[data-test-id=action-deposit").last();
    private SelenideElement balanceCardOne = $$("li").first().$("div");
    private SelenideElement balanceCardTwo = $$("li").last().$("div");
    private SelenideElement error = $("[data-test-id=error-notification");


    public DashBoardPage2() {
        heading.shouldBe(Condition.visible);
    }

    public CreditCardPage2 cardOnePage() {
        buttonCardOne.click();
        CreditCardPage2 cardOne = new CreditCardPage2();
        return cardOne;
    }

    public CreditCardPage2 cardTwoPage() {
        buttonCardTwo.click();
        CreditCardPage2 cardTwo = new CreditCardPage2();
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

    public boolean errorIsVisible() {
        return error.is(Condition.visible);
    }

}


