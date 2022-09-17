package cn.sumi.utils.file;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * @author rainofsilence
 * @date 2022/9/16 周五
 */
class FileUtilsTest {

    private final String testPath = "src/test/resources/file_test";

    @Test
    void replaceNames() {
        FileUtils.mkdirs(testPath);
        for (int i = 0; i < 100; i++) {
            FileUtils.mkFile(testPath + File.separator + "test_" + i + ".txt");
        }

        FileUtils.replaceNames(testPath, "test", "new_test");

    }

    @Test
    void createMoreFiles() {
        try {
            Files.createDirectories(Paths.get(testPath + "/data/test1/test2/test3/test4/test5/"));
            Files.write(Paths.get(testPath + "/data/test1/test2/test2.log"), "hello".getBytes());
            Files.write(Paths.get(testPath + "/data/test1/test2/test3/test3.log"), "hello".getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void rmDir() {
        createMoreFiles();
        FileUtils.rmDir(testPath + File.separator + "data/test1");
    }

    @Test
    void rmFile() {
        createMoreFiles();
        FileUtils.rmFile(testPath + "/data/test1/test2/test3");
    }
}