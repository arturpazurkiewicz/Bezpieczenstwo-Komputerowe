package org.pazurkiewicz;

import java.util.*;

public class KeySegment {
    public int value;
    public final Language language;
    public final ArrayList<Integer> messages;

    public KeySegment(Language language, ArrayList<Integer> messages) {
        this.language = language;
        this.messages = messages;
    }

    public void generateKey() {
        HashMap<Integer, Float> possibleKeys = new HashMap<>();
        for (int m : messages) {
            for (Map.Entry<Character, Float> letter : language.getFrequency().entrySet()) {
                int pom = (m ^ letter.getKey());
                if (!possibleKeys.containsKey(pom)) {
                    possibleKeys.put(pom, letter.getValue());
                } else {
                    possibleKeys.put(pom, possibleKeys.get(pom) + letter.getValue());
                }
            }
        }
        possibleKeys = sortByValue(possibleKeys);
        int best = ' ';
        int bestCounter = 0;
        for (int possible : possibleKeys.keySet()) {
            int counter = 0;
            for (int m : messages) {
                if (language.getFrequency().containsKey((char) (m ^ possible)))
                    counter++;
            }
            if (counter > bestCounter) {
                bestCounter = counter;
                best = possible;
            }

        }
        this.value = best;
    }

    public static HashMap<Integer, Float> sortByValue(HashMap<Integer, Float> hashMap) {
        List<Map.Entry<Integer, Float>> list = new LinkedList<>(hashMap.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        HashMap<Integer, Float> result = new LinkedHashMap<>();
        for (Map.Entry<Integer, Float> p : list) {
            result.put(p.getKey(), p.getValue());
        }
        return result;
    }
}
