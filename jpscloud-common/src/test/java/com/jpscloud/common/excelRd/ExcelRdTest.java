package com.jpscloud.common.excelRd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jpscloud.common.utils.excelRd.ExcelRd;
import com.jpscloud.common.utils.excelRd.ExcelRdException;
import com.jpscloud.common.utils.excelRd.ExcelRdRow;
import com.jpscloud.common.utils.excelRd.ExcelRdTypeEnum;

public class ExcelRdTest {

	public static void main(String[] args) throws IOException, ExcelRdException {
		excelRd();
	}

	private static void excelRd() throws IOException, ExcelRdException {

		String path = "D:/workspacessm/jpscloud/jpscloud-common/src/main/resources/xlsx/test.xlsx";
		ExcelRd excelRd = new ExcelRd(path);
		excelRd.setStartRow(1);	// 指定起始行，从0开始
		excelRd.setStartCol(0);	// 指定起始列，从0开始
        ExcelRdTypeEnum[] types = {
			ExcelRdTypeEnum.STRING,
			ExcelRdTypeEnum.DATE,
			ExcelRdTypeEnum.DATETIME,
            ExcelRdTypeEnum.DOUBLE,
            ExcelRdTypeEnum.STRING,
            ExcelRdTypeEnum.STRING,
            ExcelRdTypeEnum.STRING, 
            ExcelRdTypeEnum.STRING
		};
		excelRd.setTypes(types);	// 指定每列的类型
		
		List<ExcelRdRow> rows = excelRd.analysisXlsx();
		List<Map<String, Object>> plans = new ArrayList<Map<String,Object>>(rows.size());
		int size = rows.size();
		for (int i = 0; i < size; i++) {
			
			ExcelRdRow excelRdRow = rows.get(i);
			List<Object> row = excelRdRow.getRow();
			HashMap<String, Object> plan = new HashMap<String, Object>();

            for (Object t : row) {
                System.out.print(t + " | ");
            }
            System.out.println("\n");
			
            plans.add(plan);
		}
	}

}
