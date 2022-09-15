package cn.sumi.utils.date;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author rainofsilence
 * @date 2022/9/15 周四
 */
class DateUtilsTest {

    @Test
    void getMonthBetween() {
        DateUtils.getMonthBetween("2021-01-01", "2022-07-08").forEach(System.out::println);
    }
}