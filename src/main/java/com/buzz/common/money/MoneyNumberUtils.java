package com.buzz.common.money;

public class MoneyNumberUtils {
    private static String[] NUMBER1 = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    private static String[] NUMBER2 = {"零", "拾", "佰", "仟", "万", "亿"};

    /**
     * 将数字转化为大写
     *
     * @param num
     * @return
     */
    public static String getNumberStr(int num) {
        if (num < 0) {
            return "";
        }
        if (num == 0) {
            return NUMBER1[0];
        }
        int split = 10000;
        int y = num / (split * split);
        int w = (num / split) % split;
        int g = num % split;
        StringBuffer sb = new StringBuffer();
        //亿
        if (y > 0) {
            sb.append(getNumberStr1000(y));
            sb.append(NUMBER2[5]);
        }
        //万
        if (w > 999) {
            sb.append(getNumberStr1000(w));
            sb.append(NUMBER2[4]);
        } else {
            if (w > 0) {
                if (y != 0) {
                    sb.append(NUMBER2[0]);
                }
                sb.append(getNumberStr1000(w));
                sb.append(NUMBER2[4]);
            }
        }
        //万以下
        if (g > 0) {
            if (w != 0) {
                if (g > 999) {
                    sb.append(getNumberStr1000(g));
                } else {
                    sb.append(NUMBER2[0]);
                    sb.append(getNumberStr1000(g));
                }

            } else {
                if (y != 0) {
                    sb.append(NUMBER2[0]);
                }
                sb.append(getNumberStr1000(g));
            }
        }
        return sb.toString();
    }

    /**
     * 对万以下的数字进行大小写转化
     *
     * @param num
     * @return
     */
    private static String getNumberStr1000(int num) {
        if (num > 9999 || num < 0) {
            return "";
        }
        int q = num / 1000;
        int b = (num / 100) % 10;
        int s = (num / 10) % 10;
        int g = num % 10;
        StringBuffer sb = new StringBuffer();
        //千
        if (q > 0) {
            sb.append(NUMBER1[q]);
            sb.append(NUMBER2[3]);
        }
        //百
        if (b > 0) {
            sb.append(NUMBER1[b]);
            sb.append(NUMBER2[2]);
        } else {
            if (q != 0) {
                sb.append(NUMBER2[0]);
            }
        }
        //十
        if (s > 0) {
            sb.append(NUMBER1[s]);
            sb.append(NUMBER2[1]);
        } else {
            if (b != 0) {
                sb.append(NUMBER2[0]);
            }
        }
        //个
        if (g > 0) {
            sb.append(NUMBER1[g]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int i = 4;
        String DESCRIPTOR = "------>>";
        System.out.println(i + DESCRIPTOR + MoneyNumberUtils.getNumberStr(i));
        i = 2205;
        System.out.println(i + DESCRIPTOR + MoneyNumberUtils.getNumberStr(i));
        i = 230106;
        System.out.println(i + DESCRIPTOR + MoneyNumberUtils.getNumberStr(i));
        i = 10300709;
        System.out.println(i + DESCRIPTOR + MoneyNumberUtils.getNumberStr(i));
        i = 1234567890;
        System.out.println(i + DESCRIPTOR + MoneyNumberUtils.getNumberStr(i));
    }
}
