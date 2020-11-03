package org.pazurkiewicz.language;

import org.pazurkiewicz.Language;

import java.util.HashMap;

public class Polish extends Language {
    @Override
    public HashMap<Character, Float> getFrequency() {
        HashMap<Character,Float> pl = new HashMap<>();
        pl.put('i',8.83f);
        pl.put('e',8.68f);
        pl.put('a',8.37f);
        pl.put('o',7.53f);
        pl.put('n',5.69f);
        pl.put('z',5.33f);
        pl.put('r',4.15f);
        pl.put('s',4.13f);
        pl.put('w',4.11f);
        pl.put('y',4.03f);
        pl.put('c',3.89f);
        pl.put('t',3.85f);
        pl.put('d',3.35f);
        pl.put('k',3.01f);
        pl.put('p',2.87f);
        pl.put('m',2.81f);
        pl.put('ł',2.38f);
        pl.put('j',2.28f);
        pl.put('l',2.24f);
        pl.put('u',2.06f);
        pl.put('b',1.93f);
        pl.put('g',1.46f);
        pl.put('h',1.25f);
        pl.put('ę',1.13f);
        pl.put('ż',0.93f);
        pl.put('ą',0.79f);
        pl.put('ó',0.79f);
        pl.put('ś',0.72f);
        pl.put('ć',0.60f);
        pl.put('f',0.26f);
        pl.put('ń',0.16f);
        pl.put('ź',0.08f);
        pl.put(' ',10f);
        pl.put(',',1.6f);
        pl.put('.',1.0f);
        pl.put('-',1.0f);
        pl.put('"',1.0f);
        pl.put('!',1.0f);
        pl.put('?',1.0f);
        pl.put(':',1.0f);
        pl.put(';',1.0f);
        pl.put('(',1.0f);
        pl.put(')',1.0f);
        pl.put('I',0.0883f);
        pl.put('E',0.0868f);
        pl.put('A',0.0837f);
        pl.put('O',0.0753f);
        pl.put('N',0.0569f);
        pl.put('Z',0.0533f);
        pl.put('S',0.0415f);
        pl.put('R',0.0413f);
        pl.put('W',0.0411f);
        pl.put('Y',0.0403f);
        pl.put('C',0.0389f);
        pl.put('T',0.0385f);
        pl.put('D',0.0335f);
        pl.put('K',0.0301f);
        pl.put('P',0.0287f);
        pl.put('M',0.0281f);
        pl.put('J',0.0228f);
        pl.put('L',0.0224f);
        pl.put('U',0.0206f);
        pl.put('B',0.0193f);
        pl.put('G',0.0146f);
        pl.put('H',0.0125f);
        pl.put('F',0.0026f);
        return pl;
    }
}
