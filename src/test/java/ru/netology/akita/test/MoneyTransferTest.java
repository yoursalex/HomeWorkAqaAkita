package ru.netology.akita.test;

import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.netology.akita.data.*;
import ru.netology.akita.page.*;

import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Selenide.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MoneyTransferTest {

        private CreditCard cardOne = new CreditCard();
        private CreditCard cardTwo = new CreditCard();
        private CreditCard illegal = new CreditCard();

    @BeforeAll
    public void setUp() {
        cardOne.setNumber("5559 0000 0000 0001");
        cardOne.setBalance(10000);
        cardTwo.setNumber("5559 0000 0000 0002");
        cardTwo.setBalance(10000);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/amount.cvs", numLinesToSkip = 1)
    void shouldTransferMoneyFromCardTwoToCardOne(int amount, String message, String messageTwo) {
        open("http://localhost:9999/");
        val cardPage = login().cardOnePage();
        val dashBoardPage2 = cardPage.transferFromInfo(cardTwo, amount);
        cardPage.transferMoney(cardOne, cardTwo, amount);
        assertEquals(dashBoardPage2.getBalanceFromPageCardOne(), cardOne.getBalance(), message);
        assertEquals(dashBoardPage2.getBalanceFromPageCardTwo(), cardTwo.getBalance(), messageTwo);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/amount.cvs", numLinesToSkip = 1)
    void shouldTransferMoneyFromCardOneToCardTwo(int amount) {
        open("http://localhost:9999/");
        val cardPage = login().cardTwoPage();
        val dashBoardPage2 = cardPage.transferFromInfo(cardOne, amount);
        cardPage.transferMoney(cardTwo, cardOne, amount);
        assertEquals(dashBoardPage2.getBalanceFromPageCardOne(), cardOne.getBalance());
        assertEquals(dashBoardPage2.getBalanceFromPageCardTwo(), cardTwo.getBalance());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/illegalAmount.cvs", numLinesToSkip = 1)
    void shouldNotTransferMoneyFromCardTwoToCardOneIfNegativeBalance(int amount, String message) {
        open("http://localhost:9999/");
        val cardPage = login().cardOnePage();
        val dashBoardPage2 = cardPage.transferFromInfo(cardTwo, amount);
        cardPage.transferMoneyCheckingBalance(cardOne, cardTwo, cardTwo.getBalance()+amount); //даст возможность всегда пытатся перевести больше, чем баланс карты с которой переводим
        assertEquals(dashBoardPage2.getBalanceFromPageCardOne(), cardOne.getBalance());
        assertEquals(dashBoardPage2.getBalanceFromPageCardTwo(), cardTwo.getBalance(),message);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/illegalAmount.cvs", numLinesToSkip = 1)
    void shouldNotTransferMoneyFromCardOneToCardTwoIfNegativeBalance(int amount, String message) {
        open("http://localhost:9999/");
        val cardPage = login().cardTwoPage();
        val dashBoardPage2 = cardPage.transferFromInfo(cardOne, amount);
        cardPage.transferMoneyCheckingBalance(cardOne, cardTwo, cardOne.getBalance()+amount); //даст возможность всегда пытатся перевести больше, чем баланс карты с которой переводим
        assertEquals(dashBoardPage2.getBalanceFromPageCardOne(), cardOne.getBalance(), message);
        assertEquals(dashBoardPage2.getBalanceFromPageCardTwo(), cardTwo.getBalance());
    }


    @Test
    @DisplayName("После нажатия Отмена должен возвращать на страницу карт без изменения баланса. Карта 1")
    void shouldCancelRequestCardPageOneAndKeepSameBalance() {
        open("http://localhost:9999/");
        val cardPage = login().cardOnePage();
        val dashBoardPage2 = cardPage.cancelRequest(cardTwo, 1000);
        assertEquals(dashBoardPage2.getBalanceFromPageCardOne(), cardOne.getBalance());
        assertEquals(dashBoardPage2.getBalanceFromPageCardTwo(), cardTwo.getBalance());
    }

    @Test
    @DisplayName("После нажатия Отмена должен возвращать на страницу карт без изменения баланса. Карта 2")
    void shouldCancelRequestCardPageTwoAndKeepSameBalance() {
        open("http://localhost:9999/");
        val cardPage = login().cardTwoPage();
        val dashBoardPage2 = cardPage.cancelRequest(cardOne, 1000);
        assertEquals(dashBoardPage2.getBalanceFromPageCardOne(), cardOne.getBalance());
        assertEquals(dashBoardPage2.getBalanceFromPageCardTwo(), cardTwo.getBalance());
    }

    @Test
    @DisplayName("После нажатия Отмена должен очищать поля страницы перевода. Карта 1.")
    void shouldCleanFieldsIfReqestIsCandelledCardOnePage() {
        open("http://localhost:9999/");
        val cardPage = login().cardOnePage();
        val dashBoardPage2 = cardPage.cancelRequest(cardTwo, 1000);
        val cardPage2 = dashBoardPage2.cardOnePage();
        boolean isEmpty = true;
        assertEquals(isEmpty, cardPage2.isEmpty());
    }

    @Test
    @DisplayName("После нажатия Отмена должен очищать поля страницы перевода. Карта 2.")
    void shouldCleanFieldsIfReqestIsCandelledCardTwoPage() {
        open("http://localhost:9999/");
        val cardPage = login().cardTwoPage();
        val dashBoardPage2 = cardPage.cancelRequest(cardTwo, 1000);
        val cardPage2 = dashBoardPage2.cardTwoPage();
        boolean isEmpty = true;
        assertEquals(isEmpty, cardPage2.isEmpty());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/illegalCardOne.cvs", numLinesToSkip = 1)
    void shouldNotTransferMoneyCardOneFromIllegalCard(String card, String message) {
        open("http://localhost:9999/");
        illegal.setNumber(card);
        val cardPage = login().cardOnePage();
        val dashBoardPage2 = cardPage.transferFromInfo(illegal, 1000);
        boolean isVisible = true;
        assertEquals(dashBoardPage2.errorIsVisible(), isVisible, message);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/illegalCardTwo.cvs", numLinesToSkip = 1)
    void shouldNotTransferMoneyCardTwoFromIllegalCard(String card, String message) {
        open("http://localhost:9999/");
        illegal.setNumber(card);
        val cardPage = login().cardTwoPage();
        val dashBoardPage2 = cardPage.transferFromInfo(illegal, 1000);
        boolean isVisible = true;
        assertEquals(dashBoardPage2.errorIsVisible(), isVisible, message);
    }

    public DashBoardPage login() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashBoardPage = verificationPage.validVerify(verificationCode);
        cardOne.setBalance(dashBoardPage.getBalanceFromPageCardOne()); //дает возможность прогонять тесты не перегружая SUT
        cardTwo.setBalance(dashBoardPage.getBalanceFromPageCardTwo());
        return dashBoardPage;

    }


}
