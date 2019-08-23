//package com.huangjin.util.excel;
//
//import com.huangjin.util.ReflectUtils;
//import com.huangjin.util.bean.ExcelColumnConfigBean;
//import com.monitorjbl.xlsx.StreamingReader;
//import org.apache.commons.io.FileUtils;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.util.IOUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.*;
//
///**
// * Created by huang on 2018-12-13.
// */
//public class ExcelUtils {
//    private static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);
//
//    public static <T> Iterator<T> iterater(InputStream ins, Class<T> clazz, boolean firstRowValue) {
//        if (firstRowValue) {
//            return new ExcelIterater<T>(ins, clazz);
//        } else {
//            return new ExcelIterater<T>(ins, clazz, 1);
//        }
//    }
//
//    public static <T> List<T> getPart(InputStream ins, Class<T> clazz, boolean firstRowValue, int limit, int offset) {
//        List<T> list = new LinkedList<T>();
//        Iterator<T> it = null;
//        if (firstRowValue) {
//            it = new ExcelIterater<T>(ins, clazz, offset, limit);
//        } else {
//            it = new ExcelIterater<T>(ins, clazz, offset + 1, limit);
//        }
//        while (it.hasNext()) {
//            list.add(it.next());
//        }
//
//        return list;
//    }
//
//    public static <T> Iterator<T> getPartIterator(InputStream ins, Class<T> clazz, boolean firstRowValue, int limit, int offset) {
//        Iterator<T> it = null;
//        if (firstRowValue) {
//            it = new ExcelIterater<T>(ins, clazz, offset, limit);
//        } else {
//            it = new ExcelIterater<T>(ins, clazz, offset + 1, limit);
//        }
//
//        return it;
//    }
//
//    private static Map<Class<?>, List<ExcelColumnConfigBean>> clazzColumnConfigsMap = new HashMap<Class<?>, List<ExcelColumnConfigBean>>();
//
//    public static <T> T parse(Row sheetRow, Class<T> clazz) {
//        List<ExcelColumnConfigBean> columnConfigs = clazzColumnConfigsMap.get(clazz);
//        if (columnConfigs == null) {
//            columnConfigs = ReflectUtils.getExcelColumns(clazz);
//            clazzColumnConfigsMap.put(clazz, columnConfigs);
//        }
//
//        Map<String, String> fieldMap = new HashMap<String, String>();
//
//        for (ExcelColumnConfigBean config : columnConfigs) {
//            // cell num is zero based
//            Cell cell = sheetRow.getCell(config.getOrder() -1);
//            if (cell != null) {
//                int cellType = cell.getCellType();
//                if (cellType == Cell.CELL_TYPE_BOOLEAN) {
//                    fieldMap.put(config.getColumn(), String.valueOf(cell.getBooleanCellValue()));
//                } else if (cellType == Cell.CELL_TYPE_STRING) {
//                    fieldMap.put(config.getColumn(), cell.getStringCellValue());
//                } else if (cellType == Cell.CELL_TYPE_NUMERIC) {
//                    fieldMap.put(config.getColumn(), cell.getStringCellValue());
//                } else if (cellType == Cell.CELL_TYPE_BLANK) {
//                    fieldMap.put(config.getColumn(), "");
//                } else {
//                    // error or other异常当前行
//                    logger.error("invalid cell type, cellType: " + cellType + ", rowNum: " + sheetRow.getRowNum() + ", cellIndex: " + config.getOrder());
//                    throw new RuntimeException("不合法的列值   第 " + sheetRow.getRowNum() + "行");
//                }
//            }
//        }
//
//        T t = null;
//        // if(columnConfigs.size() == fieldMap.size()){
//        t = ReflectUtils.getInstance(columnConfigs, clazz, fieldMap);
//        // }
//
//        if (t == null) {
//            throw new RuntimeException("实例化异常 " + clazz);
//        } else {
//            return t;
//        }
//    }
//
//
//    static class ExcelIterater<T> implements Iterator<T> {
//        private File f;
//        private StreamingReader reader;
//        private Iterator<Row> rowIterator = null;
//        private int rowToSkip = 0;
//        private int limit = Integer.MAX_VALUE;
//        private int row;
//        private Class<T> clazz;
//
//        public ExcelIterater(InputStream is, Class<T> clazz) {
//            this(is, clazz, 0, Integer.MAX_VALUE);
//        }
//
//        public ExcelIterater(InputStream is, Class<T> clazz, int rowToSkip) {
//            this(is, clazz, rowToSkip, Integer.MAX_VALUE);
//        }
//
//        public ExcelIterater(InputStream is, Class<T> clazz, int rowToSkip, int limit) {
//            try {
//                this.f = File.createTempFile("tmp-", ".xlsx");
//                IOUtils.copy(is, new FileOutputStream(f));
//            } catch (IOException e) {
//                throw new RuntimeException("unable to write file", e);
//            }
//
//            this.reader = StreamingReader.builder()
//                    .sheetIndex(0)
//                    .read(this.f);
//            this.rowIterator = reader.iterator();
//
//            this.clazz = clazz;
//            this.row = 0;
//            this.rowToSkip = rowToSkip;
//            this.limit = limit;
//
//            // skip rows
//            skipRows();
//        }
//
//        @Override
//        public boolean hasNext() {
//            boolean next =  row < limit && rowIterator.hasNext();
//            if (!next) {
//                reader.close();
//                FileUtils.deleteQuietly(f);
//            }
//
//            return next;
//        }
//
//        private void skipRows() {
//            reader.skipRows(rowToSkip);
//        }
//
//        @Override
//        public T next() {
//            Row sheetRow = this.rowIterator.next();
//            row ++;
//
//            return parse(sheetRow, clazz);
//        }
//
//        @Override
//        public void remove() {
//
//        }
//    }
//
//}
