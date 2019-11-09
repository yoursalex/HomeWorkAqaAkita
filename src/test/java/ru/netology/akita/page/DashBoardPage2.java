package ru.netology.akita.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.akita.data.CreditCard;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashBoardPage2 {
    private SelenideElement heading = $("[data-test-id=dashboard");
    private SelenideElement balanceCardOne = $$("li").first().$("div");
    private SelenideElement balanceCardTwo = $$("li").last().$("div");


    public DashBoardPage2() {
        heading.shouldBe(Condition.visible);
    }

    public String getTextBalance(CreditCard card) {
        String balanceAtCard = String.valueOf(card.getBalance());
        return balanceAtCard;
    }

    public String getTextCardOne() {
        String text = balanceCardOne.innerText();
        return text;
    }

    public String getTextCardTwo() {
        String text = balanceCardTwo.innerText();
        return text;
    }

}


