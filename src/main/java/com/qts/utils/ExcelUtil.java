package com.qts.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: poi解析excel工具类
 * @author: duanyashu
 * @time: 2020-07-13 16:40
 */
public class ExcelUtil {

    /**
     * 根据fileType不同读取excel文件
     *
     * @param path
     * @param path
     * @throws IOException
     */
    public static List<List<String>> readExcel(String path) {
        String fileType = path.substring(path.lastIndexOf(".") + 1);
        // return a list contains many list
        List<List<String>> lists = new ArrayList<List<String>>();
        //读取excel文件
        InputStream is = null;
        try {
            is = new FileInputStream(path);
            //获取工作薄
            Workbook wb = null;
            if (fileType.equals("xls")) {
                wb = new HSSFWorkbook(is);
            } else if (fileType.equals("xlsx")) {
                wb = new XSSFWorkbook(is);
            } else {
                return null;
            }

            //读取第一个工作页sheet
            Sheet sheet = wb.getSheetAt(0);

            int cellNum = sheet.getRow(0).getLastCellNum();
            //第一行为标题
            for (Row row : sheet) {
                ArrayList<String> list = new ArrayList<String>();
                for (int i = 0; i < cellNum; i++) {
                    Cell cell = row.getCell(i);
                    if (cell==null){
                        list.add("");
                    }else {
                    //根据不同类型转化成字符串
                    cell.setCellType(CellType.STRING);
                    list.add(cell.getStringCellValue());
                    }
                }
                lists.add(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                {is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lists;
    }

    /**
     * 创建Excel.xls
     * @param lists 需要写入xls的数据
     * @param titles 列标题
     * @param name  文件名
     * @return
     * @throws IOException
     */
    public static Workbook creatExcel(List<List<String>> lists, String[] titles, String name) throws IOException {
        System.out.println(lists);
        //创建新的工作薄
        Workbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet(name);
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for(int i=0;i<titles.length;i++){
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }

        // 创建第一行
        Row row = sheet.createRow((short) 0);

        // 创建两种单元格格式
        CellStyle titleCellStyle = wb.createCellStyle();
        // 创建两种字体
        Font titleFont = wb.createFont();
        // 创建第一种字体样式（用于列名）
        titleFont.setFontHeightInPoints((short) 10);
        titleFont.setColor(IndexedColors.BLACK.getIndex());
        titleFont.setBold(true);
        // 设置第一种单元格的样式（用于列名）
        titleCellStyle.setFont(titleFont);
        titleCellStyle.setBorderBottom(BorderStyle.THIN);
        titleCellStyle.setBorderLeft(BorderStyle.THIN);
        titleCellStyle.setBorderRight(BorderStyle.THIN);
        titleCellStyle.setBorderTop(BorderStyle.THIN);
        //设置列名
        for(int i=0;i<titles.length;i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(titleCellStyle);
        }

        CellStyle contentCellStyle = wb.createCellStyle();
        Font contentFont = wb.createFont();
        // 创建第二种字体样式（用于值）
        contentFont.setFontHeightInPoints((short) 10);
        contentFont.setColor(IndexedColors.BLACK.getIndex());
        // 设置第二种单元格的样式（用于值）
        contentCellStyle.setFont(contentFont);
        contentCellStyle.setBorderBottom(BorderStyle.THIN);
        contentCellStyle.setBorderLeft(BorderStyle.THIN);
        contentCellStyle.setBorderRight(BorderStyle.THIN);
        contentCellStyle.setBorderTop(BorderStyle.THIN);
        contentCellStyle.setAlignment(HorizontalAlignment.CENTER);
        if(lists == null || lists.size() == 0){
            return wb;
        }
        //设置每行每列的值
        for (short i = 1; i <= lists.size(); i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            Row row1 = sheet.createRow(i);
            for(short j=0;j<titles.length;j++){
                // 在row行上创建一个方格
                Cell cell = row1.createCell(j);
                cell.setCellValue(lists.get(i-1).get(j));
                cell.setCellStyle(contentCellStyle);
            }
        }
        return wb;
    }


    public static void main(String[] args) {
        List<List<String>> lists = readExcel("d://user.xlsx");
        for (List<String> list : lists) {
            System.out.println(list.toString());
        }
        List<String> titleList  = lists.get(0);
        List<List<String>> contentList = lists;
        contentList.remove(0);
        try {
            Workbook workbook = creatExcel(contentList, titleList.toArray(new String[titleList.size()]), "用户表");
            OutputStream outputStream = new FileOutputStream("d://user1.xls");
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
