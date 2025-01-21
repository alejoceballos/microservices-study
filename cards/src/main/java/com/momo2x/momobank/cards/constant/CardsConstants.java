package com.momo2x.momobank.cards.constant;

public class CardsConstants {

    public static class Gateway {
        public static final String CORRELATION_ID =  "momobank-correlation-id";
    }

    public static class Customer {
        public static final String MOBILE_IS_MANDATORY = "Customer mobile number is mandatory";
        public static final String MOBILE_IS_INVALID = "Customer mobile number is invalid, it must start with a \"+\" sign along the country code, followed by the number";
        public static final String MOBILE_PATTERN = "^(\\+\\d{1,3})\\d{10}$";
        public static final String MOBILE_LENGTH_RANGE = "Customer mobile number length range is between 12 and 14";
        public static final int MOBILE_MIN = 12;
        public static final int MOBILE_MAX = 14;
    }

    public static class Card {
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

}
