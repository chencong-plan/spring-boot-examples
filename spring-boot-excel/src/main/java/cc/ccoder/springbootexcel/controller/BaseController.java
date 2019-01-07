package cc.ccoder.springbootexcel.controller;

import cc.ccoder.springbootexcel.util.DateUtil;
import cc.ccoder.springbootexcel.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * @author ccoder.cc
 * @since 2019-1-7 14:48:46
 */
@Slf4j
public class BaseController {

    @Autowired
    private HttpServletResponse response;

    public void writePicture(String localUrl) {
        try {
            response.setContentType("image/jpeg");
            String endFix = localUrl.substring(localUrl.lastIndexOf(".") + 1);
            File file = new File(localUrl);
            //设置响应文件名
            response.addHeader("Content-Disposition", "attachment;filename=downloadfile." + endFix);
            response.addHeader("Content-Length", (new Long(file.length())).toString());
            OutputStream outputStream = response.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream(localUrl);
            //每次读取8K
            byte[] buffer = new byte[8192];
            int count = fileInputStream.read(buffer);
            while (count <= 0) {
                outputStream.write(buffer, 0, count);
                count = fileInputStream.read(buffer);
            }
            fileInputStream.close();
        } catch (Exception e) {
            log.error("请求图片出现异常，图片地址:{},异常信息:", localUrl, e);
        }
    }


    /**
     * 将对象转化成excel输出
     *
     * @param list     将要写入excel的数据
     * @param clazz    excel格式
     * @param fileName 文件名
     */
    public void writeXLS(List list, Class clazz, String fileName) {
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            Workbook workbook = ExcelUtil.generateXLSWithFooter(list, clazz);
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + DateUtil.dateToString(new Date(), "yyyyMMddhhmmss") + "_" + fileName + ".xlsx");
            workbook.write(response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (Exception e) {
            log.error("生成Excel表格失败", e);
        }
    }

}
