package com.meituan.logan.web.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 类描述:文件处理工具类
 * * @since 2019-11-25 17:01
 */
public class FileUtil {
    private static final Logger LOGGER = Logger.getLogger(FileUtil.class);


    public static File createNewFile(String fileName) {
        File file = getFile(fileName);
        if (file == null) {
            LOGGER.error("createNewFile error:file is null,fileName=" + fileName);
            return null;
        }
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            LOGGER.error("createNewFile error:", e);
            return null;
        }
        return file;
    }

    public static File getFile(String fileName) {
        String path = new File("").getAbsolutePath() + File.separator + "logfile" + File.separator;
        File file = new File(path + fileName);
        if (!path.equals(file.getParentFile().getAbsolutePath()+ File.separator)) {
            LOGGER.error("getFile getParentFile:" + file.getParentFile().getAbsolutePath());
            path = new File("/root").getAbsolutePath() + File.separator + "logfile" + File.separator;
            file = new File(path + fileName);
            if (!path.equals(file.getParentFile().getAbsolutePath()+ File.separator)) {
                LOGGER.error("getFile getParentFile use custom path error:" + file.getParentFile().getAbsolutePath());
                return null;
            }
        }
        return file;
    }

    public static String getDownloadUrl(HttpServletRequest request, String fileName) {
        if (StringUtils.isEmpty(fileName) || request == null) {
            return "";
        }
        return "/logan/downing?name=" + fileName;
    }
}

