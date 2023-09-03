package org.endava.automation.testing.Enums;

public enum TransferFundsAccountTypesEnum {

    CHECKING_ACCOUNT("Checking"),
    CREDIT_CARD_ACCOUNT("Credit");

    private final String text;

    TransferFundsAccountTypesEnum(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
