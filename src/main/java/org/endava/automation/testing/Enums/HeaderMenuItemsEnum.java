package org.endava.automation.testing.Enums;

public enum HeaderMenuItemsEnum {

    TRANSFER_FUNDS("transfer_funds_tab"),
    PAY_BILLS("pay_bills_tab"),
    ACCOUNT_ACTIVITY("account_activity_tab"),
    MY_MONEY_MAP("my_money_map_tab");

    private final String id;

    HeaderMenuItemsEnum(final String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }

}
