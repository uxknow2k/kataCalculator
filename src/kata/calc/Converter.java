package kata.calc;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Byzov A.A.
 */
public class Converter {
    Map<Character, Integer> romanMap = new HashMap<>();

    {
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);
    }

    TreeMap<Integer, String> arabNumMap = new TreeMap<>();

    {

        arabNumMap.put(1000, "M");
        arabNumMap.put(900, "CM");
        arabNumMap.put(500, "D");
        arabNumMap.put(400, "CD");
        arabNumMap.put(100, "C");
        arabNumMap.put(90, "XC");
        arabNumMap.put(50, "L");
        arabNumMap.put(40, "XL");
        arabNumMap.put(10, "X");
        arabNumMap.put(9, "IX");
        arabNumMap.put(5, "V");
        arabNumMap.put(4, "IV");
        arabNumMap.put(1, "I");
    }

    public int romanConverter(String romeNumber) {

        int result = romanMap.get(romeNumber.charAt(romeNumber.length() - 1));
        for (int i = romeNumber.length() - 2; i >= 0; i--) {
            if (romanMap.get(romeNumber.charAt(i)) < romanMap.get(romeNumber.charAt(i + 1))) {
                result -= romanMap.get(romeNumber.charAt(i));
            } else {
                result += romanMap.get(romeNumber.charAt(i));
            }
        }
        return result;
    }

    public String arabicConverter(int arabianNumber) {
        StringBuilder roman = new StringBuilder();
        int arabianKey;
        do {
            arabianKey = arabNumMap.floorKey(arabianNumber);
            roman.append(arabNumMap.get(arabianKey));
            arabianNumber -= arabianKey;
        } while (arabianNumber != 0);
        return roman.toString();
    }


    public boolean isRoman(String number) {
        return romanMap.containsKey(number.charAt(0));
    }
}
