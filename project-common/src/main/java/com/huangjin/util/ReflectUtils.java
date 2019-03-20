package com.huangjin.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.huangjin.util.annotation.ExcelColumn;
import com.huangjin.util.bean.ExcelColumnConfigBean;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;


/**
 * Created by huang on 2019-2-12.
 */
public class ReflectUtils {

    public static void setProperties(Object obj,Properties prop){
        List<Field> fields = getAllDeclearedFields(obj.getClass());
        for(Field f:fields){
            String v = prop.getProperty(f.getName());
            if(v!=null){
                try {
                    v = v.trim();
                    f.setAccessible(true);
                    f.set(obj, convertValue(f.getType(), v));
                } catch (IllegalArgumentException e) {
                    System.out.println("IllegalArgumentException field "+f.getName()+" "+obj.getClass()+" "+e.getMessage());
                } catch (IllegalAccessException e) {
                    System.out.println("IllegalAccessException field "+f.getName()+" "+obj.getClass()+" "+e.getMessage());
                }
            }
        }
    }

    public static List<ExcelColumnConfigBean> getExcelColumns(Class<?> clazz){
        ArrayList<ExcelColumnConfigBean> list = new ArrayList<ExcelColumnConfigBean>();
        Field[] fields = clazz.getDeclaredFields();
        for(Field f:fields){
            ExcelColumn column = f.getAnnotation(ExcelColumn.class);
            if(column!=null){
                ExcelColumnConfigBean bean = new ExcelColumnConfigBean();
                bean.setOrder(column.order());
                bean.setFieldName(f.getName());
                bean.setType(f.getType());
                if(StringUtils.isNoneBlank(column.column())){
                    bean.setColumn(column.column());
                }else{
                    bean.setColumn(f.getName());
                }
                list.add(bean);
            }
        }
        Collections.sort(list);
        return list;
    }

    public static List<Field> getAllDeclearedFields(Class<?> clazz){
        List<Field> list = new LinkedList<Field>();
        if(clazz!=Object.class){
            list.addAll(Arrays.asList(clazz.getDeclaredFields()));
            list.addAll(getAllDeclearedFields(clazz.getSuperclass()));
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getInstance(List<ExcelColumnConfigBean> configs,Class<T> clazz,Map<String,String> valueMap){
        try {
            Object obj = clazz.newInstance();
            for(ExcelColumnConfigBean config:configs){
                String vv = valueMap.get(config.getColumn());
                if (vv != null) {
                    BeanUtils.setProperty(obj, config.getFieldName(), convertValue(config.getType(),vv));
                }
            }
            return (T)obj;
        } catch (InstantiationException e) {
            System.out.println("get instance of "+clazz+" error InstantiationException");
        } catch (IllegalAccessException e) {
            System.out.println("get instance of "+clazz+" error InstantiationException");
        } catch (InvocationTargetException e) {
            System.out.println("set property of "+clazz+" error");
        }catch(Exception e){
            System.out.println(clazz+" error "+e.getMessage());
        }
        return null;
    }

    public static Object convertValue(Class<?> need, String value) {
        if (need == byte.class || need == Byte.class) {
            if (value == null) {
                return (byte) 0;
            }
            return Byte.parseByte(value);
        } else if (need == short.class || need == Short.class) {
            if (value == null) {
                return (short) 0;
            }
            BigDecimal decimal = new BigDecimal(Double.parseDouble(value)).setScale(2, BigDecimal.ROUND_HALF_UP);
            return decimal.shortValue();
        } else if (need == int.class || need == Integer.class) {
            if (value == null) {
                return (int) 0;
            }
            BigDecimal decimal = new BigDecimal(Double.parseDouble(value)).setScale(2, BigDecimal.ROUND_HALF_UP);
            return decimal.intValue();
        } else if (need == long.class || need == Long.class) {
            if (value == null) {
                return (long) 0;
            }
            BigDecimal decimal = new BigDecimal(Double.parseDouble(value)).setScale(2, BigDecimal.ROUND_HALF_UP);
            return decimal.longValue();
        } else if (need == boolean.class || need == Boolean.class) {
            if (value == null) {
                return false;
            }
            return Boolean.parseBoolean(value);
        } else if (need == float.class || need == Float.class) {
            if (value == null) {
                return (float) 0.0;
            }
            BigDecimal decimal = new BigDecimal(Double.parseDouble(value)).setScale(3, BigDecimal.ROUND_HALF_UP);
            return decimal.floatValue();
        } else if (need == double.class || need == Double.class) {
            if (value == null) {
                return (double) 0.0;
            }
            BigDecimal decimal = new BigDecimal(Double.parseDouble(value)).setScale(2, BigDecimal.ROUND_HALF_UP);
            return decimal.doubleValue();
        } else if (need == char.class || need == Character.class) {
            if (value == null) {
                return ' ';
            }
            return value.trim().charAt(0);
        } else if (need == BigDecimal.class) {
            if (value == null) {
                return BigDecimal.ZERO;
            }
            return new BigDecimal(Double.parseDouble(value)).setScale(2, BigDecimal.ROUND_HALF_UP);
        } else {
            if (value == null) {
                return "";
            }
            return value;
        }
    }
}
