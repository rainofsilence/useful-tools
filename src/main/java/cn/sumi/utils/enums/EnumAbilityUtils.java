package cn.sumi.utils.enums;

import java.util.EnumSet;

/**
 * @author rainofsilence
 * @date 2022/7/26 周二
 */
public class EnumAbilityUtils {

    @SuppressWarnings("unchecked")
    public static <E extends Enum<E>, T> EnumAbility<T> getEnumByCode(Class<E> enumClass, T code) {
        if (null == code) {
            return null;
        }

        EnumSet<E> es = EnumSet.allOf(enumClass);

        for (E anEnum : es) {
            if (anEnum instanceof EnumAbility) {
                if (((EnumAbility<T>) anEnum).codeEquals(code)) {
                    return (EnumAbility<T>) anEnum;
                }
            }
        }
        return null;
    }

}
