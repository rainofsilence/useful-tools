package cn.sumi.utils.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
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
     * 递归删除文件夹
     *
     * @param dirPath
     */
    public static void rmDir(String dirPath) {
        Path path = Paths.get(dirPath);

        try {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    // System.out.printf("文件被删除: %s%n", file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    // System.out.printf("文件夹被删除: %s%n", dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            // throw new RuntimeException(e);
            System.out.println(e.toString());
        }
    }

    /**
     * 删除文件
     *
     * @param path
     */
    public static void rmFile(String path) {
        File file = new File(path);

        try {
            if (file.exists() && file.isFile()) {
                boolean result = file.delete();
                if (result) {
                    // System.out.println("文件删除成功");
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * 批量修改文件名称
     *
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
                File parentPath = f.getParentFile();
                String newFileName = f.getName().replace(oldStr, nowStr);
                File newDir = new File(parentPath + File.separator + newFileName);
                f.renameTo(newDir);
            }
        }
    }
}
