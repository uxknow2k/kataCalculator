package kata.calc;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Byzov A.A.
 */
public class Converter {
    Map<Character, Integer> romanNumMap = new HashMap<>();

    {
        romanNumMap.put('I', 1);
        romanNumMap.put('V', 5);
        romanNumMap.put('X', 10);
        romanNumMap.put('L', 50);
        romanNumMap.put('C', 100);
        romanNumMap.put('D', 500);
        romanNumMap.put('M', 1000);
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

        int result = romanNumMap.get(romeNumber.charAt(romeNumber.length() - 1));
        for (int i = romeNumber.length() - 2; i >= 0; i--) {
            if (romanNumMap.get(romeNumber.charAt(i)) < romanNumMap.get(romeNumber.charAt(i + 1))) {
                result -= romanNumMap.get(romeNumber.charAt(i));
            } else {
                result += romanNumMap.get(romeNumber.charAt(i));
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
        return romanNumMap.containsKey(number.charAt(0));
    }
}
