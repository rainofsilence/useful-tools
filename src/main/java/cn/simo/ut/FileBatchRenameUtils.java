package cn.simo.ut;

import java.io.File;
import java.util.Objects;

/**
 * @author rainofsilecne
 * @version v1.0.0
 * @date 20220903 2:57 周六
 **/
public class FileBatchRenameUtils {

    public static void replace(String dirPath, String oldStr, String nowStr) {
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
