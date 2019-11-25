package com.lollipop.spider.util;

import com.github.houbb.paradise.common.util.CollectionUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
 * @author lzh
 * @date 2019/11/25 18:48
 **/
public class ExcelUtils {
    public static void buildSheet(Workbook workbook, String sheetName, List<List<String>> dataSet) {
        if (CollectionUtil.isEmpty(dataSet)) {
            return;
        }
        Sheet sheet = workbook.createSheet(sheetName);

        int rowCount = 0;
        for (List<String> rowString : dataSet) {
            Row row = sheet.createRow(rowCount++);
            for (int j = 0; j < rowString.size(); ++j) {
                Cell cell = row.createCell(j);
                cell.setCellValue(rowString.get(j));
            }
        }
    }
}
