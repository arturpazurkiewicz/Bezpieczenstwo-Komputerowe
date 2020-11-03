package org.pazurkiewicz;

import java.util.*;

public class Decryptor {
    private final Language language;
    ArrayList<Cryptogram> cryptograms;
    private ArrayList<Integer> key;

    public Decryptor(Language language, ArrayList<Cryptogram> cryptograms) {
        this.language = language;
        this.cryptograms = cryptograms;
        int len = 0;
        for (Cryptogram cryptogram : cryptograms) {
            len = Math.max(len,cryptogram.bytes.size());
        }
        generateKey(len);
    }

    private void generateKey(int len) {
        key = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            HashMap<Integer,Float> possibleKeys = new HashMap<>();
            for (Cryptogram cryptogram: cryptograms) {
                if (cryptogram.bytes.size() > i){
                    for (Map.Entry<Character,Float> letter : language.getFrequency().entrySet()){
                        int pom =(cryptogram.bytes.get(i) ^ letter.getKey());
                        if (! possibleKeys.containsKey(pom)){
                            possibleKeys.put(pom,letter.getValue());
                        } else {
                            possibleKeys.put(pom,possibleKeys.get(pom)+letter.getValue());
                        }
                    }
                }
            }
            possibleKeys = sortByValue(possibleKeys);
            int best = (int) (' ');
            int bestCounter = 0;
            for (int possible : possibleKeys.keySet()){
                int counter = 0;
                for (Cryptogram cryptogram: cryptograms) {
                    if (cryptogram.bytes.size() > i) {
                        if (language.getFrequency().containsKey((char)(cryptogram.bytes.get(i) ^ possible)))
                            counter++;
                    }
                    if (counter > bestCounter){
                        bestCounter = counter;
                        best = possible;
                    }
                }
            }
            key.add(best);
        }
    }

    public static HashMap<Integer, Float> sortByValue(HashMap<Integer, Float> hashMap){
        List<Map.Entry<Integer, Float>> list = new LinkedList<>(hashMap.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        HashMap<Integer,Float> result = new LinkedHashMap<>();
        for (Map.Entry<Integer,Float> p : list){
            result.put(p.getKey(),p.getValue());
        }
        return result;
    }

    public ArrayList<String> decrypt() {
        ArrayList<String> result = new ArrayList<>();
        for (Cryptogram cryptogram: cryptograms){
            StringBuilder r= new StringBuilder();
            for (int i = 0, bytesSize = cryptogram.bytes.size(); i < bytesSize; i++) {
                r.append((char) (cryptogram.bytes.get(i) ^ key.get(i)));
            }
            result.add(r.toString());
        }
        return result;
    }

    public ArrayList<Integer> getKey() {
        return key;
    }

    public void fixKey(int messageNumber, String fixedMessage){
        char[] charArray = fixedMessage.toCharArray();
        ArrayList<Integer> cryptogram = cryptograms.get(messageNumber).bytes;
        for (int i = 0; i < charArray.length; i++) {
            key.set(i,(int) charArray[i] ^ cryptogram.get(i));
        }
    }
}
