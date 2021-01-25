package org.pazurkiewicz;

import java.util.*;

public class Decryptor {
    ArrayList<Cryptogram> cryptograms;
    private final ArrayList<KeySegment> key;

    public Decryptor(Language language, ArrayList<Cryptogram> cryptograms) {
        this.cryptograms = cryptograms;
        int len = 0;
        for (Cryptogram cryptogram : cryptograms) {
            len = Math.max(len,cryptogram.bytes.size());
        }
        key = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            ArrayList<Integer> m = new ArrayList<>();
            for (Cryptogram cryptogram : cryptograms){
                if (cryptogram.bytes.size() > i)
                    m.add(cryptogram.bytes.get(i));
            }
            key.add(new KeySegment(language, m));
        }
        generateKey();
    }

    private void generateKey() {
        key.parallelStream().forEach(KeySegment::generateKey);
    }


    public ArrayList<String> decrypt() {
        ArrayList<String> result = new ArrayList<>();
        for (Cryptogram cryptogram: cryptograms){
            StringBuilder r= new StringBuilder();
            for (int i = 0, bytesSize = cryptogram.bytes.size(); i < bytesSize; i++) {
                r.append((char) (cryptogram.bytes.get(i) ^ key.get(i).value));
            }
            result.add(r.toString());
        }
        return result;
    }

    public ArrayList<Integer> getKey() {
        ArrayList<Integer> result = new ArrayList<>();
        for (KeySegment segment : key){
            result.add(segment.value);
        }
        return result;
    }

    public void fixKey(int messageNumber, String fixedMessage){
        char[] charArray = fixedMessage.toCharArray();
        ArrayList<Integer> cryptogram = cryptograms.get(messageNumber).bytes;
        for (int i = 0; i < charArray.length; i++) {
            key.get(i).value = (int) charArray[i] ^ cryptogram.get(i);
        }
    }
}
