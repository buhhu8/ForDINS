package org.DINS.findMethod;

import java.util.HashMap;

public class FindByName {

    //Упрощенный алгоритм Бойера-Мура
    public int getNameFirstEntry(String source, String template) {
        int sourceLen = source.length();
        int templateLen = template.length();
        if (templateLen > sourceLen) {
            return -1;
        }
        HashMap<Character, Integer> offsetTable = new HashMap<Character, Integer>();
        for (int i = 65; i <= 122; i++) {
            offsetTable.put((char) i, templateLen);
        }
        for (int i = 1040; i <= 1103; i++) {
            offsetTable.put((char) i, templateLen);
        }
        for (int i = 0; i < templateLen - 1; i++) {
            offsetTable.put(template.charAt(i), templateLen - i - 1);
        }
        int i = templateLen - 1;
        int j = i;
        int k = i;
        while (j >= 0 && i <= sourceLen - 1) {
            j = templateLen - 1;
            k = i;
            while (j >= 0 && source.charAt(k) == template.charAt(j)) {
                k--;
                j--;
            }
            i += offsetTable.get(source.charAt(i));
        }
        if (k >= sourceLen - templateLen) {
            return -1;
        } else {
            return k + 1;
        }
    }

}
