package com.huangjin.util;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;

/**
 * @Author 清磊
 * @Date 2022/3/9 13:57
 * @Description 根据ascii排序，保证所有层级的有序性
 * 若只有单层，可用其他方式排序
 **/
public class AsciiSortUtil {

    /**
     * 保证JSON所有层级上Key的有序性
     * 保证JSON的所有数值不带多余的小数点
     *
     * @param map
     * @return
     */
    public static String marshal(Map map) {
        String raw = CustomGson.toJson(map);
        // 执行反序列化，把所有JSON对象转换成LinkedTreeMap
        Map<?, ?> m = CustomGson.fromJson(raw, LinkedTreeMap.class);
        // 重新序列化，保证JSON所有层级上Key的有序性
        return CustomGson.toJson(m);
    }

    private static final Gson CustomGson = new GsonBuilder()
            // 定制LinkedTreeMap序列化，确保所有key按字典序排序
            .registerTypeAdapter(LinkedTreeMap.class, newMapSerializer())
            // 定制数值类型的序列化，确保整数输出不带小数点
            .registerTypeAdapter(Integer.class, newNumberSerializer())
            .registerTypeAdapter(Long.class, newNumberSerializer())
            .registerTypeAdapter(Double.class, newNumberSerializer())
            // 禁用Html Escape，确保符号不转义：&<>='
            .disableHtmlEscaping()
            .create();

    /**
     * 为LinkedTreeMap定制的序列化器
     *
     * @return
     */
    public static JsonSerializer<LinkedTreeMap<?, ?>> newMapSerializer() {
        return new JsonSerializer<LinkedTreeMap<?, ?>>() {
            @Override
            public JsonElement serialize(LinkedTreeMap<?, ?> src, Type typeOfSrc, JsonSerializationContext context) {
                List<String> keys = src.keySet().stream().map(Object::toString).sorted().collect(Collectors.toList());
                JsonObject o = new JsonObject();
                for (String k : keys) {
                    o.add(k, context.serialize(src.get(k)));
                }
                return o;
            }
        };
    }

    /**
     * 为Number定制化的序列化器
     * @param <T>
     * @return
     */
    private static <T extends Number> JsonSerializer<T> newNumberSerializer() {
        return new JsonSerializer<T>() {
            @Override
            public JsonElement serialize(T number, Type type, JsonSerializationContext context) {
                if (number instanceof Integer) {
                    return new JsonPrimitive(number.intValue());
                }
                if (number instanceof Long) {
                    return new JsonPrimitive(number.longValue());
                }
                if (number instanceof Double) {
                    long longValue = number.longValue();
                    double doubleValue = number.doubleValue();
                    if (longValue == doubleValue) {
                        return new JsonPrimitive(longValue);
                    }
                }
                return new JsonPrimitive(number);
            }
        };
    }
}
