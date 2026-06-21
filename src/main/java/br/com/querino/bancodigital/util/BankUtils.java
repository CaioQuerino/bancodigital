package br.com.querino.bancodigital.util;

import java.security.SecureRandom;

public final class BankUtils {

    private static final SecureRandom RANDOM = new SecureRandom();

    private BankUtils() {
    }

    public static String generateAgency() {
        int agency = 1000 + RANDOM.nextInt(9000);
        return String.valueOf(agency);
    }

    public static String generateAccountNumber() {
        int number = 10000000 + RANDOM.nextInt(90000000);
        int digit = RANDOM.nextInt(10);

        return number + "-" + digit;
    }
}