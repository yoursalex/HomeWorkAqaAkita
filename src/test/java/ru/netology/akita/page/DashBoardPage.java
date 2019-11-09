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

}