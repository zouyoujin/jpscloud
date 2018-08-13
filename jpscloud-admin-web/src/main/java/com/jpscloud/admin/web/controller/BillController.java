package com.jpscloud.admin.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @ClassName: BillController
 * @Description: 票据业务控制器
 * @author: Kitty
 * @date: 2018年8月13日 下午11:39:08
 *
 */
@Controller
@RequestMapping("/bill")
public class BillController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * 导出查询报表
	 */
	@RequestMapping("/doExport")
	public void doExport(HttpServletResponse response) {
		XSSFWorkbook wb = null;
		String fileName = "票据报表";
		try {
			response.setHeader("Content-type", "application/vnd.ms-excel");
			// 解决导出文件名中文乱码
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + ".xlsx");
			// 1.读取Excel模板
			File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "static/excel/template.xlsx");
			wb = new XSSFWorkbook(new FileInputStream(file));
			// 2.将Excel写入到输出流里面
			wb.write(response.getOutputStream());
		} catch (IOException e) {
			log.error("doExport fail.", e);
		} finally {
			if (null != wb) {
				try {
					wb.close();
				} catch (IOException e) {
					log.error("doExport wb close fail.", e);
				}
			}
		}
	}
}
