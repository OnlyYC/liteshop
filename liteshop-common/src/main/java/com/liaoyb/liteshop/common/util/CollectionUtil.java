package com.liaoyb.liteshop.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 集合工具类
 *
 * @author liaoyb
 */
public final class CollectionUtil {
    /**
     * 字符串集合去重
     *
     * @param list     源字符串集合
     * @param keepSort 是否保持排序
     */
    public static <T> List<T> distinct(List<T> list, boolean keepSort) {
        // 不需要保持原有排序，直接使用hashSet实现
        if (!keepSort) {
            return new ArrayList<>(new HashSet<>(list));
        } else {
            List<T> listTmp = new ArrayList<>();
            for (T str : list) {
                if (!listTmp.contains(str)) {
                    listTmp.add(str);
                }
            }
            return listTmp;
        }
    }

    /**
     * int数组列表转换为int二维数组
     *
     * @param list int数组列表
     * @return int二维数组
     */
    public static int[][] toArray(List<int[]> list) {
        int[][] multipleArr = new int[list.size()][];
        for (int i = 0; i < multipleArr.length; i++) {
            multipleArr[i] = list.get(i);
        }
        return multipleArr;
    }

    /**
     * 把collection集合转换成list集合
     *
     * @param collection collection集合
     * @param <T>        泛型参数
     * @return list集合
     */
    public static <T> List<T> list(Collection<T> collection) {
        if (collection.isEmpty()) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>(collection.size());
        list.addAll(collection);
        return list;
    }

    /**
     * 剔除空元素
     *
     * @param list 原始列表
     * @param <T>  类型
     * @return 非空的列表
     */
    public static <T> List<T> notNull(List<T> list) {
        if (list != null && !list.isEmpty()) {
            list = list.stream()
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
        return list;
    }

    /**
     * 剔除空元素
     *
     * @param arr 原始数组
     * @param <T> 类型
     * @return 非空的数组
     */
    @SafeVarargs
    public static <T> List<T> notNull(T... arr) {
        if (arr != null && arr.length > 0) {
            return Arrays.stream(arr)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    /**
     * 列表按照指定长度分割
     *
     * @param list 列表
     * @param len  子列表长度
     * @return 子列表列表
     * @throws java.util.ConcurrentModificationException 浅拷贝,执行任何修改会抛出此异常
     */
    public static <T> List<List<T>> splitList(List<T> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }
        List<List<T>> result = new ArrayList<>();
        int size = list.size();
        int count = (size + len - 1) / len;
        for (int i = 0; i < count; i++) {
            List<T> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }

    /**
     * 列表按照指定长度分割(执行深拷贝,不会抛出java.util.ConcurrentModificationException异常)
     *
     * @param list 列表
     * @param len  子列表长度
     * @return 子列表列表
     */
    public static <T> List<List<T>> deepSplitList(List<T> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }
        List<List<T>> result = new ArrayList<>();
        int size = list.size();
        int count = (size + len - 1) / len;
        for (int i = 0; i < count; i++) {
            List<T> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(new ArrayList<>(subList));
        }
        return result;
    }

    /**
     * 列表分割为指定数量的子列表
     *
     * @param source 列表
     * @param n      指定数量
     * @return 子列表列表
     */
    public static <T> List<List<T>> averageAssign(List<T> source, int n) {
        if (source == null || source.size() == 0 || n < 1) {
            return null;
        }
        List<List<T>> result = new ArrayList<>();
        int remaider = source.size() % n;  //(先计算出余数)
        int number = source.size() / n;  //然后是商
        int offset = 0;//偏移量
        for (int i = 0; i < n; i++) {
            List<T> value;
            if (remaider > 0) {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remaider--;
                offset++;
            } else {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }

    public static boolean isEmpty(Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }
}
