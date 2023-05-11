package com.doantotnghiep.nvt.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.DateUtil;

import org.apache.poi.ss.usermodel.Cell;


public class ReadExcel {
    public static Object getValue(Cell cell) {
		if(cell == null) return "";
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				return cell.getStringCellValue();
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					SimpleDateFormat DtFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date date = cell.getDateCellValue();
					return DtFormat.format(date).toString();
				}
				cell.setCellType(Cell.CELL_TYPE_STRING);
				return cell.getStringCellValue();
			case Cell.CELL_TYPE_BOOLEAN:
				return cell.getBooleanCellValue();
			case Cell.CELL_TYPE_ERROR:
				return cell.getErrorCellValue();
			case Cell.CELL_TYPE_FORMULA:
				switch (cell.getCachedFormulaResultType()) {
					case Cell.CELL_TYPE_NUMERIC:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						return cell.getStringCellValue();
					case Cell.CELL_TYPE_STRING:
						return cell.getRichStringCellValue();
				}
				return cell.getCellFormula();
			case Cell.CELL_TYPE_BLANK:
				return "";
			default:
				return "";
		}
	}
}
