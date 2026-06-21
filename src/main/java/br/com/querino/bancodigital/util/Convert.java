package br.com.querino.bancodigital.util;

import org.springframework.beans.BeanUtils;

import lombok.NoArgsConstructor;

import java.util.function.Supplier;

@NoArgsConstructor
public final class Convert {
    public static <S, T> T to(S source, Supplier<T> targetSupplier) {
        T target = targetSupplier.get();
        BeanUtils.copyProperties(source, target);
        return target;
    }
}
