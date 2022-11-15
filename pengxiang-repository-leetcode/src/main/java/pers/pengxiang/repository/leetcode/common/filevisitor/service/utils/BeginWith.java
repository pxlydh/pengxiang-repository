package pers.pengxiang.repository.leetcode.common.filevisitor.service.utils;

import java.util.function.Predicate;

import lombok.AllArgsConstructor;

/**
 * @author pengxiang
 * Create on 2022-11-15 23:06:03
 */
@AllArgsConstructor(staticName = "of")
public class BeginWith implements Predicate<String> {
    private final String begin;

    @Override
    public boolean test(String s) {
        return s.startsWith(begin);
    }
}
