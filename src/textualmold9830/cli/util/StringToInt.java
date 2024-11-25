package textualmold9830.cli.util;

public class StringToInt {
    public static boolean isInt(String s) {
        try {
            if (s == null){
                return false;
            }
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static int optionalInt(String s, int defValue){
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return defValue;
        }
    }
}
