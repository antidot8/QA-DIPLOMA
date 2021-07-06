package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.page.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.SQLHelper.*;

public class PaymentByCardTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUpEach() {
        open(System.getProperty("sut.url"));
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @AfterEach
    void clearDataBase() {
        clearDB();
    }

//1.
    @Test
    void validCardPayment() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(validCardData());
        paymentByCardPage.successMsgWait();
        paymentByCardPage.successMsgClose();
        assertEquals("APPROVED", getPaymentByCardStatus());
    }

//2.
    @Test
    void allFieldsEmptyPayment () {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(emptyFieldsCardData());
        paymentByCardPage.cardNumberBadFormatErrorWait();
        paymentByCardPage.monthBadFormatErrorWait();
        paymentByCardPage.yearBadFormatErrorWait();
        paymentByCardPage.cardholderRequiredErrorWait();
        paymentByCardPage.cvvBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

//3.
    @Test
    void nonexistentCardNumberPayment() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(nonexistentCardNumberCardData());
        paymentByCardPage.cardNumberBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

//4.
    @Test
    void shortCardNumberPaymentByCardTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(shortCardNumberCardData());
        paymentByCardPage.cardNumberBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }


//5.
    @Test
    void nonexistentMonthPayment() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(nonexistentMonthCardData());
        paymentByCardPage.monthInvalidExpirationDateErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

//6.
    @Test
    void expiredCardPayment() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(expiredCardData());
        paymentByCardPage.yearCardExpiredErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

//7.
    @Test
    void specialCharactersInCardholderNamePayment() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(specialCharactersInCardholderData());
        paymentByCardPage.cardNumberBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

//8.
    @Test
    void numbersInCardholderNamePayment() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(numbersInCardholderData());
        paymentByCardPage.cardNumberBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

//9.
    @Test
    void oneNumberCVVPayment() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(oneNumberCVVCardData());
        paymentByCardPage.cvvBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

//10.
    @Test
    void twoNumbersCVVPayment() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(twoNumbersCVVCardData());
        paymentByCardPage.cvvBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }
}