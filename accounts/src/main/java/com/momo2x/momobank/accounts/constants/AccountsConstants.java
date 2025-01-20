package com.momo2x.momobank.accounts.constants;

public class AccountsConstants {

    public static class Customer {
        public static final String NAME_IS_MANDATORY = "Customer name is mandatory";
        public static final String NAME_LENGTH_RANGE = "Customer name length range is between 1 and 100";
        public static final int NAME_MIN = 1;
        public static final int NAME_MAX = 100;

        public static final String EMAIL_IS_MANDATORY = "Customer email is mandatory";
        public static final String EMAIL_IS_INVALID = "Customer email is invalid";
        public static final String EMAIL_LENGTH_RANGE = "Customer email length range is between 5 and 100";
        public static final int EMAIL_MIN = 5;
        public static final int EMAIL_MAX = 100;

        public static final String MOBILE_IS_MANDATORY = "Customer mobile number is mandatory";
        public static final String MOBILE_IS_INVALID = "Customer mobile number is invalid, it must start with a \"+\" sign along the country code, followed by the number";
        public static final String MOBILE_PATTERN = "^(\\+\\d{1,3})\\d{10}$";
        public static final String MOBILE_LENGTH_RANGE = "Customer mobile number length range is between 12 and 14";
        public static final int MOBILE_MIN = 12;
        public static final int MOBILE_MAX = 14;
    }

    public static class Account {
        public static final String NUMBER_IS_MANDATORY = "Account number is mandatory";
        public static final String NUMBER_RANGE = "Account number range is between 1 million and 1.9 millions";
        public static final long NUMBER_MIN = 1_000_000_000;
        public static final long NUMBER_MAX = 1_900_000_000;

        public static final String TYPE_IS_MANDATORY = "Account type is mandatory";
        public static final String TYPE_LENGTH_RANGE = "Account type length range is between 1 and 100";
        public static final int TYPE_MIN = 1;
        public static final int TYPE_MAX = 100;

        public static final String BRANCH_ADDR_IS_MANDATORY = "Branch address is mandatory";
        public static final String BRANCH_ADDR_LENGTH_RANGE = "Branch address length range is between 1 and 200";
        public static final int BRANCH_ADDR_MIN = 1;
        public static final int BRANCH_ADDR_MAX = 200;
    }

    public static class Card {
        public static final String SERVICE_NAME = "cards";
        public static final String CARD_NUMBER_IS_MANDATORY = "Card number is mandatory";
        public static final String CARD_NUMBER_IS_INVALID = "Card number is invalid, must be numeric with 12 digits";
        public static final String CARD_NUMBER_PATTERN = "(^$|[0-9]{12})";
        public static final String CARD_NUMBER_LENGTH_RANGE = "Customer mobile number length must be 12";
        public static final int CARD_NUMBER_MIN = 12;
        public static final int CARD_NUMBER_MAX = 12;

        public static final String CARD_TYPE_IS_MANDATORY = "Card type is mandatory";
        public static final String CARD_TYPE_LENGTH_RANGE = "Card type length must be between 1 and 100";
        public static final int CARD_TYPE_MIN = 1;
        public static final int CARD_TYPE_MAX = 100;

        public static final String CARD_LIMIT_RANGE = "Card limit should be greater than zero";
        public static final String CARD_AMOUNT_USED_RANGE = "Card amount used should be equal or greater than zero";
        public static final String CARD_AVAILABLE_AMOUNT_RANGE = "Card available amount should be equal or greater than zero";
    }

    public static class Loan {
        public static final String SERVICE_NAME = "loans";
        public static final String LOAN_NUMBER_IS_MANDATORY = "Card number is mandatory";
        public static final String LOAN_NUMBER_IS_INVALID = "Card number is invalid, must be numeric with 12 digits";
        public static final String LOAN_NUMBER_PATTERN = "(^$|[0-9]{12})";
        public static final String LOAN_NUMBER_LENGTH_RANGE = "Customer mobile number length must be 12";
        public static final int LOAN_NUMBER_MIN = 12;
        public static final int LOAN_NUMBER_MAX = 12;

        public static final String LOAN_TYPE_IS_MANDATORY = "Card type is mandatory";
        public static final String LOAN_TYPE_LENGTH_RANGE = "Card type length must be between 1 and 100";
        public static final int LOAN_TYPE_MIN = 1;
        public static final int LOAN_TYPE_MAX = 100;

        public static final String LOAN_LIMIT_RANGE = "Loan limit should be greater than zero";
        public static final String LOAN_AMOUNT_USED_RANGE = "Loan amount used should be equal or greater than zero";
        public static final String LOAN_AVAILABLE_AMOUNT_RANGE = "Loan available amount should be equal or greater than zero";
    }

}
