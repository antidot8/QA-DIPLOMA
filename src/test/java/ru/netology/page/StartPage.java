package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$;

public class StartPage {
    private SelenideElement heading = $$(".heading_size_l").find(exactText("Путешествие дня"));
    private SelenideElement paymentByCardButton = $$("[type='button']").find(exactText("Купить"));
    private SelenideElement paymentOnCreditButton = $$("[type='button']").find(exactText("Купить в кредит"));

    public StartPage() {
        heading.shouldBe(visible);
        paymentByCardButton.shouldBe(visible);
        paymentOnCreditButton.shouldBe(visible);
    }

    public PaymentByCardPage selectPaymentByCardPage() {
        paymentByCardButton.click();
        return new PaymentByCardPage();
    }

    public PaymentOnCreditPage selectPaymentOnCreditPage() {
        paymentOnCreditButton.click();
        return new PaymentOnCreditPage();
    }
}