package pers.pengxiang.repository.utils.collection;

import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import java.util.function.Predicate;

/**
 * @author pengxiang
 * Create on 2022-11-15 23:01:27
 */
public class ListUtils {
    private ListUtils() {
    }

    /**
     * 获取 '指定列表' 中满足 '指定条件' 的第一个元素所处的位置
     *
     * @param list      指定列表
     * @param predicate 指定条件
     * @return list 中满足 predicate 条件的第一个元素所处的位置，如果不存在满足条件的元素，则返回 -1
     */
    public static <T> int indexOf(List<T> list, Predicate<T> predicate) {
        return indexOf(list, 0, predicate);
    }

    /**
     * 获取 '指定列表' 中 '指定位置' 之后满足 '指定条件' 的第一个元素所处的位置
     *
     * @param list      指定列表
     * @param begin     指定位置
     * @param predicate 指定条件
     * @return list 中 begin 位置之后满足 predicate 条件的第一个元素所处的位置，如果不存在满足条件的元素，则返回 -1
     */
    public static <T> int indexOf(List<T> list, int begin, Predicate<T> predicate) {
        if (list instanceof RandomAccess) {
            for (int i = begin; i < list.size(); i++) {
                T t = list.get(i);
                if (predicate.test(t)) {
                    return i;
                }
            }
        } else {
            ListIterator<T> listIterator = list.listIterator(begin);
            while (listIterator.hasNext()) {
                T t = listIterator.next();
                if (predicate.test(t)) {
                    return listIterator.previousIndex();
                }
            }
        }
        return -1;
    }

    /**
     * 获取 '指定列表' 中满足 '指定条件' 的最后一个元素所处的位置
     *
     * @param list      指定列表
     * @param predicate 指定条件
     * @return list 中满足 predicate 条件的最后一个元素所处的位置，如果不存在满足条件的元素，则返回 -1
     */
    public static <T> int lastIndexOf(List<T> list, Predicate<T> predicate) {
        return lastIndexOf(list, list.size() - 1, predicate);
    }

    /**
     * 获取 '指定列表' 中 '指定位置' 之前满足 '指定条件' 的最后一个元素所处的位置
     *
     * @param list      指定列表
     * @param end       指定位置
     * @param predicate 指定条件
     * @return list 中 end 位置之前满足 predicate 条件的最后一个元素所处的位置，如果不存在满足条件的元素，则返回 -1
     */
    public static <T> int lastIndexOf(List<T> list, int end, Predicate<T> predicate) {
        if (list instanceof RandomAccess) {
            for (int i = end; i >= 0; i--) {
                T t = list.get(i);
                if (predicate.test(t)) {
                    return i;
                }
            }
            return -1;
        } else {
            ListIterator<T> listIterator = list.listIterator();
            int index = -1;
            while (listIterator.hasNext()) {
                T t = listIterator.next();
                if (predicate.test(t)) {
                    index = listIterator.previousIndex();
                }
            }
            return index <= end ? index : -1;
        }
    }
}