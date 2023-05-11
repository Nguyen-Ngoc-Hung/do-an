package com.doantotnghiep.nvt.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

@Service 
public class Resource {
    public final Logger log = LogManager.getLogger(Resource.class);

	public void createFile(String targetFolder, String fileName, InputStream isFile, Map<String, Object> map) {
		try {
			log.info("Bat dau creat File");
			XLSTransformer transformer = new XLSTransformer();
			log.info("Transform XLS");
			Workbook wb = transformer.transformXLS(isFile, map);

			FileOutputStream fileOut = null;
			// check exist folder
			File dir = new File(targetFolder);
			if (!dir.exists()) {
				log.info("Chua thay Thu muc > Tao Thu Muc");
				dir.mkdirs();
			}

			log.info("Bat dau output File ra folder");
			log.info("File Output = " + targetFolder + fileName);
			fileOut = new FileOutputStream(targetFolder + fileName);
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
			log.info("Da output File ra folder");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public void createFileToOutputstream(OutputStream outputStream, InputStream isFile, Map<String, Object> map){
		try {
			log.info("Bat dau creat File");
			XLSTransformer transformer = new XLSTransformer();
			log.info("Transform XLS");
			Workbook wb = transformer.transformXLS(isFile, map);
			wb.write(outputStream);

			log.info("Da output File ra folder");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public InputStream createExcelInputStream(InputStream isFile, Map<String, Object> map){
		try {
			XLSTransformer transformer = new XLSTransformer();
			Workbook wb = transformer.transformXLS(isFile, map);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			wb.write(bos);
			byte[] barray = bos.toByteArray();
			InputStream is = new ByteArrayInputStream(barray);
			return is;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
        return null;
	}

	public void downloadFileMau(String fileName, HttpServletResponse response) throws IOException {
		try (InputStream isFileSample = new ClassPathResource(
				"excel-template/" + fileName).getInputStream();) {
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName);
			response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
			org.apache.commons.io.IOUtils.copy(isFileSample, response.getOutputStream());
			response.flushBuffer();
		}
	}

	public void exportExcel(String fileName, Map<String, Object> map, HttpServletResponse response)
			throws IOException, InvalidFormatException, ParsePropertyException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
		try (InputStream isFileSample = new ClassPathResource(
				"excel-template/" + fileName).getInputStream();) {

			XLSTransformer transformer = new XLSTransformer();
			log.info("Transform XLS");
			Workbook wb = transformer.transformXLS(isFileSample, map);
			FileOutputStream fileOut = null;
			fileOut = new FileOutputStream(new ClassPathResource("import-template/" + fileName).getFile());
			wb.write(fileOut);
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName);
			response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
			org.apache.commons.io.IOUtils.copy(isFileSample, response.getOutputStream());
			response.flushBuffer();
		}
	}
}
