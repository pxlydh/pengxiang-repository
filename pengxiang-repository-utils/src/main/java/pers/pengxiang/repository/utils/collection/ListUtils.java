package pers.pengxiang.repository.utils.collection;

import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import java.util.function.Predicate;

/**
 * java.util.List 工具类
 *
 * @author pengxiang
 * Create on 2023-06-30 00:31:44
 */
public class ListUtils {
    private ListUtils() {
    }

    /**
     * 获取 '列表' 中满足 '条件' 的第一个元素所处的位置
     *
     * @param list    列表
     * @param matcher 条件
     * @return list 中满足 matcher 条件的第一个元素所处的位置，如果不存在满足条件的元素，则返回 -1
     */
    public static <T> int indexOf(List<T> list, Predicate<T> matcher) {
        return indexOf(list, 0, matcher);
    }

    /**
     * 获取 '列表' 中 '指定位置' 之后满足 '条件' 的第一个元素所处的位置
     *
     * @param list    列表
     * @param begin   指定位置
     * @param matcher 条件
     * @return list 中 begin 位置之后满足 matcher 条件的第一个元素所处的位置，如果不存在满足条件的元素，则返回 -1
     */
    public static <T> int indexOf(List<T> list, int begin, Predicate<T> matcher) {
        if (list instanceof RandomAccess) {
            for (int i = begin; i < list.size(); i++) {
                T t = list.get(i);
                if (matcher.test(t)) {
                    return i;
                }
            }
        } else {
            ListIterator<T> listIterator = list.listIterator(begin);
            while (listIterator.hasNext()) {
                T t = listIterator.next();
                if (matcher.test(t)) {
                    return listIterator.previousIndex();
                }
            }
        }
        return -1;
    }

    /**
     * 获取 '列表' 中满足 '条件' 的最后一个元素所处的位置
     *
     * @param list    列表
     * @param matcher 条件
     * @return list 中满足 matcher 条件的最后一个元素所处的位置，如果不存在满足条件的元素，则返回 -1
     */
    public static <T> int lastIndexOf(List<T> list, Predicate<T> matcher) {
        return lastIndexOf(list, list.size() - 1, matcher);
    }

    /**
     * 获取 '列表' 中 '指定位置' 之前满足 '条件' 的最后一个元素所处的位置
     *
     * @param list    列表
     * @param end     指定位置
     * @param matcher 条件
     * @return list 中 end 位置之前满足 matcher 条件的最后一个元素所处的位置，如果不存在满足条件的元素，则返回 -1
     */
    public static <T> int lastIndexOf(List<T> list, int end, Predicate<T> matcher) {
        if (list instanceof RandomAccess) {
            for (int i = end; i >= 0; i--) {
                T t = list.get(i);
                if (matcher.test(t)) {
                    return i;
                }
            }
            return -1;
        } else {
            ListIterator<T> listIterator = list.listIterator();
            int index = -1;
            while (listIterator.hasNext()) {
                T t = listIterator.next();
                if (matcher.test(t)) {
                    index = listIterator.previousIndex();
                }
            }
            return index <= end ? index : -1;
        }
    }
}