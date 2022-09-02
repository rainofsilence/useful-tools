package cn.simo.ut;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author rainofsilecne
 * @version v1.0.0
 * @date 20220903 3:05 周六
 **/
class FileBatchRenameUtilsTest {

    @Test
    void replace() {
        FileBatchRenameUtils.replace("C:\\test","hello","");
    }
}