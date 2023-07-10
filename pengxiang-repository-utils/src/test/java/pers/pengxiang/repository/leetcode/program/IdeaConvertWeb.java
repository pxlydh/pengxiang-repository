package pers.pengxiang.repository.leetcode.program;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author pengxiang
 * Create on 2023-06-30 01:13:12
 */
public class IdeaConvertWeb {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = reader.readLine();
            if ("over".equals(line)) {
                break;
            }
            System.out.println(line.
                    replace("解答成功:", "执行结果：通过").
                    replace("执行结果", "<p>执行结果").
                    replace("通过", "通过</p>").
                    replace("执行耗时", "执行用时").
                    replace("执行用时", "<p>执行用时").
                    replace(" 的Java用户", "的用户").
                    replace("的用户", "的用户</p>").
                    replace(":", "：").
                    replace("内存消耗", "<p>内存消耗").
                    replace(",击败了", ", 在所有 Java 提交中击败了"));
        }
    }
}