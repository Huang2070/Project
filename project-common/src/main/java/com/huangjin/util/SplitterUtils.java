package com.huangjin.util;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class SplitterUtils {

    public static final Splitter COMMON_SPLITTER = Splitter.on(Pattern.compile(",|;|:|，|；|：|、|\t")).trimResults();

    public static final Splitter NEW_LINE_SPLITTER = Splitter.on("\r\n").trimResults().omitEmptyStrings();


    /**
     * sequence转为list
     *
     * @param sequence
     * @return
     */
    public static List<String> splitToList(String sequence) {
        if (StringUtils.isBlank(sequence)) {
            return Lists.newArrayList();
        }
        return COMMON_SPLITTER.splitToList(sequence);
    }

    /**
     * sequence转为long list
     *
     * @param sequence
     * @return
     */
    public static List<Long> splitToLongList(String sequence) {
        if (StringUtils.isBlank(sequence)) {
            return Lists.newArrayList();
        }
        return COMMON_SPLITTER.splitToList(sequence).stream()
            .filter(key -> NumberUtils.isCreatable(key))
            .map(Long::parseLong)
            .collect(Collectors.toList());
    }

    /**
     * sequence转为long set
     *
     * @param sequence
     * @return
     */
    public static Set<Long> splitToLongSet(String sequence) {
        if (StringUtils.isBlank(sequence)) {
            return Sets.newHashSet();
        }
        return COMMON_SPLITTER.splitToList(sequence).stream()
            .filter(key -> NumberUtils.isCreatable(key))
            .map(Long::parseLong)
            .collect(Collectors.toSet());
    }

    /**
     * sequence转为Integer list
     *
     * @param sequence
     * @return
     */
    public static List<Integer> splitToIntList(String sequence) {
        if (StringUtils.isBlank(sequence)) {
            return Lists.newArrayList();
        }
        return COMMON_SPLITTER.splitToList(sequence).stream()
            .filter(key -> NumberUtils.isCreatable(key))
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }

    /**
     * sequence转为Integer set
     *
     * @param sequence
     * @return
     */
    public static Set<Integer> splitToIntSet(String sequence) {
        if (StringUtils.isBlank(sequence)) {
            return Sets.newHashSet();
        }
        return COMMON_SPLITTER.splitToList(sequence).stream()
            .filter(key -> NumberUtils.isCreatable(key))
            .map(Integer::parseInt)
            .collect(Collectors.toSet());
    }
}
