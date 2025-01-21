package com.momo2x.momobank.gatewayserver.constant;

public class GatewayConstants {

    public static final String BASE_URI = "momobank";
    public static final String CORRELATION_ID = "momobank-correlation-id";

    public static class Service {
        public static final String ACCOUNTS = "accounts";
        public static final String CARDS = "cards";
        public static final String LOANS = "loans";
    }

    public static class CircuitBreaker {
        public static final String SUFFIX = "CircuitBreaker";
        public static String name(final String prefix) {
            return prefix + SUFFIX;
        }

        public static class Fallback {
            public static final String URL = "/contactsupport";
        }
    }

}
