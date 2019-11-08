package ru.netology.akita.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.netology.akita.data.*;
import ru.netology.akita.page.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$;


class MoneyTransferTest {
    DataHelper.CreditCard cardOne;
    DataHelper.CreditCard cardTwo;
    private SelenideElement balanceCardOne = $$("li").first();
    private SelenideElement balanceCardTwo = $$("li").last();

    @BeforeEach
    void setup() {
        cardOne = new DataHelper.CreditCard("5559 0000 0000 0001", 10000);
        cardTwo = new DataHelper.CreditCard("5559 0000 0000 0002", 10000);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/amount.cvs", numLinesToSkip = 1)
    @DisplayName("Должен переводить деньги с карты 1 на карту 2")
    void shouldTransferMoneyFromCardTwoToCardOne(int amount) {
        open("http://localhost:9999/");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashBoardPage = verificationPage.validVerify(verificationCode);
        val cardPage = dashBoardPage.cardPageOne(); // перешли на страницу карты 1
        cardPage.putCardInfo(cardTwo, amount); // метод вернет страницу с 2мя картами
        dashBoardPage.transferMoney(cardOne, cardTwo, amount);
        $(balanceCardOne.getText()).shouldHave(Condition.text(dashBoardPage.getTextBalance(cardOne))); //не рабочий метод
    }

}
