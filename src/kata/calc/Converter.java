package kata.calc;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Byzov A.A.
 */
public class Converter {
    Map<Character, Integer> romanNumbersMap = new HashMap<>();

    {
        romanNumbersMap.put('I', 1);
        romanNumbersMap.put('V', 5);
        romanNumbersMap.put('X', 10);
        romanNumbersMap.put('C', 100);
    }

    Map<Integer, String> arabianNumbersMap = new HashMap<>(); {

        arabianNumbersMap.put(10, "X");
        arabianNumbersMap.put(9, "IX");
        arabianNumbersMap.put(5, "V");
        arabianNumbersMap.put(4, "IV");
        arabianNumbersMap.put(1, "I");
    }

    public int romanConverter(String romeNumber) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = map.get(romeNumber.charAt(romeNumber.length()-1));
        for(int i = romeNumber.length()-2; i >= 0; i--) {
            if(map.get(romeNumber.charAt(i)) < map.get(romeNumber.charAt(i+1))){
                result-=map.get(romeNumber.charAt(i));
            }
            else {
                result+=map.get(romeNumber.charAt(i));
            }
        }
        return result;
    }

    public boolean isRoman(String number) {
        return romanNumbersMap.containsKey(number.charAt(0));
    }
}
