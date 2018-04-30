package com.buzz.common.string;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PinyinUtils {

    private static Logger logger = LoggerFactory.getLogger(PinyinUtils.class);

    public static String toPingYin(String aText) {
        char[] chars = aText.toCharArray();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        String py = "";

        for (char ch : chars) {
            if (RegexUtils.isChinese(String.valueOf(ch))) {
                try {
                    py += PinyinHelper.toHanyuPinyinStringArray(ch, format)[0];
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    logger.error("BadHanyuPinyinOutputFormatCombination ERROR:" + badHanyuPinyinOutputFormatCombination);
                }
            } else {
                py += String.valueOf(ch);
            }
        }
        return py;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(toPingYin("航天飞机"));
        System.out.println(System.currentTimeMillis() - start);
    }


}
