package ru.netology.akita.test;

import com.codeborne.selenide.Condition;
import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.netology.akita.data.*;
import ru.netology.akita.page.*;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MoneyTransferTest {

        CreditCard cardOne = new CreditCard();
        CreditCard cardTwo = new CreditCard();

    @BeforeAll
    public void setUp() {
        cardOne.setNumber("5559 0000 0000 0001");
        cardOne.setBalance(10000);
        cardTwo.setNumber("5559 0000 0000 0002");
        cardTwo.setBalance(10000);
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
        val dashBoardPage2 = cardPage.putCardInfo(cardOne, cardTwo, amount); // метод вернет страницу с 2мя картами
        val cardBalanceText = dashBoardPage2.getTextBalance(cardOne);
        $(dashBoardPage2.getTextCardOne()).shouldBe(Condition.exactText(cardBalanceText));
    }


}
