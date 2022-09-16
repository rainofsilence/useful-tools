package cn.sumi.utils.file;

import java.io.File;
import java.util.Objects;

/**
 * @author rainofsilence
 * @date 2022/8/6 周六
 */
public class FileUtils {

    /**
     * 创建不存在的文件夹
     *
     * @param path
     */
    public static void mkdirs(String path) {
        if (!path.endsWith("/")) {
            path = path + "/";
        }
        File f;
        try {
            f = new File(path);
            if (!f.exists()) {
                boolean b = f.mkdirs();
                if (b) {
                    System.out.println("目录: {" + path + "} 创建成功");
                } else {
                    System.out.println("目录: {" + path + "} 创建成功");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建文件
     *
     * @param path
     */
    public static void mkFile(String path) {
        File f;
        try {
            f = new File(path);
            if (!f.exists()) {
                boolean b = f.createNewFile();
                if (b) {
                    System.out.println("文件: {" + path + "} 创建成功");
                } else {
                    System.out.println("文件: {" + path + "} 创建成功");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量修改文件名称
     * @param dirPath
     * @param oldStr
     * @param nowStr
     */
    public static void replaceNames(String dirPath, String oldStr, String nowStr) {
        File file = new File(dirPath);
        File[] files = file.listFiles();
        if (Objects.nonNull(files) && files.length > 0) {
            for (File f : files) {
                if (f.isDirectory()) {
                    continue;
                }
                String fileName = f.getName();
                File parentPath = f.getParentFile();
                String newFileName = f.getName().replace(oldStr, nowStr);
                File newDir = new File(parentPath + File.separator + newFileName);
                f.renameTo(newDir);
            }
        }
    }
}
