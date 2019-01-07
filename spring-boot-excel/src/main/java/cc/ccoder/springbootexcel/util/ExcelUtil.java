package cc.ccoder.springbootexcel.util;


import cc.ccoder.springbootexcel.annotion.CellWidth;
import cc.ccoder.springbootexcel.annotion.DateField;
import cc.ccoder.springbootexcel.annotion.EnumFiled;
import cc.ccoder.springbootexcel.annotion.EnumMap;
import cc.ccoder.springbootexcel.annotion.ExcelFiled;
import cc.ccoder.springbootexcel.annotion.NumberFiled;
import cc.ccoder.springbootexcel.annotion.SensitiveData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ccoder.cc
 * @since 2019-1-7 14:48:46
 */
@Slf4j
public class ExcelUtil {


    /**
     * 生成excel文件
     *
     * @param objectList 数据
     * @param clazz      将要注入的类型
     * @return 返回excel文件
     * @throws Exception exception
     */
    public static Workbook generateXLSWithFooter(List objectList, Class clazz) throws Exception {
        log.info("开始生成excel,excel格式:{},数据行数:{}", clazz.getName(), objectList.size());
        //创建一个Excel文件
        SXSSFWorkbook workbook = new SXSSFWorkbook(5000);
        //创建一个Excel的sheet
        Sheet sheet = workbook.createSheet();
        workbook.setSheetName(0, "Sheet 1");
        //创建第一行
        Row row0 = sheet.createRow(0);
        //获取列表对象类型
        Field[] fields = clazz.getDeclaredFields();
        //这是列序号
        int colCount = 0;
        for (Field field : fields) {
            int widthvalue = 5000;
            for (Annotation annotation : field.getAnnotations()) {
                if (annotation instanceof CellWidth) {
                    widthvalue = ((CellWidth) annotation).value();
                }
            }
            sheet.setColumnWidth(colCount, widthvalue);
            for (Annotation annotation : field.getAnnotations()) {
                //普通的屌丝文字
                if (annotation instanceof ExcelFiled) {
                    //创建列
                    Cell cell = row0.createCell(colCount);
                    cell.setCellValue(new HSSFRichTextString(((ExcelFiled) annotation).name()));
                    cell.setCellStyle(createTitleCellStyle(workbook));
                    colCount++;
                } else if (annotation instanceof NumberFiled) {
                    //创建列
                    Cell cell = row0.createCell(colCount);
                    cell.setCellValue(new HSSFRichTextString(((NumberFiled) annotation).name()));
                    cell.setCellStyle(createTitleCellStyle(workbook));
                    colCount++;
                } else if (annotation instanceof DateField) {
                    //创建列
                    Cell cell = row0.createCell(colCount);
                    cell.setCellValue(new HSSFRichTextString(((DateField) annotation).name()));
                    cell.setCellStyle(createTitleCellStyle(workbook));
                    //sheet.autoSizeColumn(colCount);
                    colCount++;
                } else if (annotation instanceof EnumFiled) {
                    //创建列
                    Cell cell = row0.createCell(colCount);
                    cell.setCellValue(new HSSFRichTextString(((EnumFiled) annotation).name()));
                    cell.setCellStyle(createTitleCellStyle(workbook));
                    //sheet.autoSizeColumn(colCount);
                    colCount++;
                }
            }
        }
        //生成表头完成 开始生产数据
        int row = 1;
        for (Object rowObj : objectList) {
            Row rowx = sheet.createRow(row);
            int column = 0;
            for (Field field : fields) {
                for (Annotation annotation : field.getAnnotations()) {
                    if (!(annotation instanceof ExcelFiled) &&
                            !(annotation instanceof NumberFiled) &&
                            !(annotation instanceof DateField) &&
                            !(annotation instanceof EnumFiled)) {
                        continue;
                    }

                    //找到这个格子
                    Cell cell = rowx.createCell(column);
                    PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                    //获得get方法
                    Method getMethod = pd.getReadMethod();
                    Object obj = getMethod.invoke(rowObj);
                    if (obj == null) {
                        log.info("{}的get方法为空,自动设置为空", pd.getName());
                        cell.setCellValue(new HSSFRichTextString(""));
                    } else {
                        //如果是文字
                        if (annotation instanceof ExcelFiled) {
                            if (field.getName().toLowerCase().contains("amt") || field.getName().toLowerCase().contains("amount") || field.getName().toLowerCase().contains("rate")) {
                                if (StringUtils.isBlank(obj.toString())) {
                                    cell.setCellValue(new HSSFRichTextString(obj.toString()));
                                } else {
                                    cell.setCellValue(getFormatString(obj.toString()));
                                }
                            } else {
                                if (field.getDeclaredAnnotation(SensitiveData.class) != null) {
                                    cell.setCellValue(new HSSFRichTextString(DataUtil.getEncData(obj.toString())));
                                } else {
                                    cell.setCellValue(new HSSFRichTextString(obj.toString()));
                                }
                            }

                        } else if (annotation instanceof NumberFiled) {
                            CellStyle cellStyle = workbook.createCellStyle();
                            cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
                            cell.setCellStyle(cellStyle);
                            if (field.getName().toLowerCase().contains("amt") || field.getName().toLowerCase().contains("amount") || field.getName().toLowerCase().contains("rate")) {
                                if (StringUtils.isBlank(obj.toString())) {
                                    cell.setCellValue(Double.parseDouble(obj.toString()));
                                } else {
                                    cell.setCellValue(Double.parseDouble(obj.toString()));
                                }
                            } else {
                                cell.setCellValue(Double.parseDouble(obj.toString()));
                            }

                        } else if (annotation instanceof DateField) {
                            cell.setCellValue(new HSSFRichTextString(DateUtil.date2String((Date) obj, ((DateField) annotation).datePattern())));
                        } else {
                            String strKey = obj.toString();
                            String strValue = "";
                            EnumMap[] maps = ((EnumFiled) annotation).keyValueMap();
                            for (EnumMap map : maps) {
                                if (map.k().equals(strKey)) {
                                    strValue = map.v();
                                }
                            }
                            cell.setCellValue(new HSSFRichTextString(strValue));
                        }
                    }
                    column++;

                }
            }
            row++;
        }
        //写Footer
        CellStyle style = workbook.createCellStyle();
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);

//        Row rowx = sheet.createRow(row + 1);
//        Cell cell1 = rowx.createCell(0);
//        cell1.setCellValue(new HSSFRichTextString("制表人："));
//        cell1.setCellStyle(style);
//        Cell cell2 = rowx.createCell(1);
//        cell2.setCellValue(new HSSFRichTextString(userName));
//        cell2.setCellStyle(style);
//        Cell cell3 = rowx.createCell(2);
//        cell3.setCellValue(new HSSFRichTextString("制表人岗位："));
//        cell3.setCellStyle(style);
//        Cell cell4 = rowx.createCell(3);
//        cell4.setCellValue(new HSSFRichTextString(roles));
//        cell4.setCellStyle(style);
//        Cell cell5 = rowx.createCell(4);
//        cell5.setCellValue(new HSSFRichTextString("复核人："));
//        cell5.setCellStyle(style);
//        Cell cell6 = rowx.createCell(5);
//        cell6.setCellStyle(style);
//        Cell cell7 = rowx.createCell(6);
//        cell7.setCellValue(new HSSFRichTextString("审批人："));
//        cell7.setCellStyle(style);
//        Cell cell8 = rowx.createCell(5);
//        cell8.setCellStyle(style);
        //这下Workbook就写完了，返回之
        return workbook;

    }

    /**
     * 将金额格式转化成固定格式0.00
     *
     * @param decimal 金额
     * @return 返回固定格式字符串
     */
    private static String getFormatString(String decimal) {
        try {
            BigDecimal amount = new BigDecimal(decimal.toString());
            DecimalFormat df = new DecimalFormat("#0.00");
            return df.format(amount.doubleValue());
        } catch (Exception e) {
            return decimal;
        }
    }

    private static CellStyle createTitleCellStyle(Workbook wb) {
        Font font = wb.createFont();
        font.setFontName("Verdana");
        font.setBoldweight((short) 320);
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setFont(font);
        // 设置边框
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        return style;
    }


}
