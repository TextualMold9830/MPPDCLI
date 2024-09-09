package textualmold9830.cli.util;

public class StringToInt {
    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            System.out.println(s +" is not an int");
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
