package cn.sumi.utils.file;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author rainofsilence
 * @date 2022/9/16 周五
 */
class FileUtilsTest {

    private final String testPath = "src/test/resources/file_batch_test";

    @Test
    void replaceNames() {
        FileUtils.mkdirs(testPath);
        for (int i = 0; i < 100; i++) {
            FileUtils.mkFile(testPath + File.separator + "test_" + i + ".txt");
        }

        FileUtils.replaceNames(testPath,"test","new_test");

    }
}