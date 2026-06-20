package br.com.querino.bancodigital.util;

public class DataMaskUtil {
    public static String maskCpf(String cpf) {
        if (cpf == null || cpf.length() != 11) return "***********";
        return "***." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-**";
    }

    public static String maskEmail(String email) {
        if (email == null || !email.contains("@")) return "******";
        int atIndex = email.indexOf("@");
        return email.charAt(0) + "******" + email.substring(atIndex - 1);
    }
} 