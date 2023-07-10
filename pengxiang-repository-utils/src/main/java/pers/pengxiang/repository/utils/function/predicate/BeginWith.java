package pers.pengxiang.repository.utils.function.predicate;

import java.util.function.Predicate;

import lombok.AllArgsConstructor;

/**
 * @author pengxiang
 * Create on 2023-06-30 00:41:58
 */
@AllArgsConstructor
public class BeginWith implements Predicate<String> {
    private String pattern;


    @Override
    public boolean test(String s) {
        return s.startsWith(pattern);
    }
}