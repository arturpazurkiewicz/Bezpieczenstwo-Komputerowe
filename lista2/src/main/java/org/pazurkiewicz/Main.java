package org.pazurkiewicz;

import org.pazurkiewicz.language.Polish;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    private static Namespace createArgumentParser(String[] args) {
        ArgumentParser parser = ArgumentParsers.newFor("decrypter-1.0.jar").build()
                .description("Decrypter of stream ciphers");
        parser.addArgument("-f", "--file").help("File with cryptograms").type(String.class).required(true);
        parser.addArgument("-c", "--ciphers").help("Max number of cryptograms")
                .type(Integer.class).required(false);
        try {
            return parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            parser.handleError(e);
        }
        System.exit(0);
        return null;
    }

    private static ArrayList<Cryptogram> getCryptogramsFromFile(String filename, int ciphers){
        Scanner scanner = null;
        ArrayList<Cryptogram> cryptograms = new ArrayList<>();
        try {
            scanner = new Scanner(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.out.println("Invalid directory "+filename);
            System.out.println("Working Directory = " + System.getProperty("user.dir"));
            System.exit(-1);
        }
        while (scanner.hasNextLine()) {
            if (ciphers == 0)
                break;
            ciphers--;
            cryptograms.add(new Cryptogram(scanner.nextLine()));
        }
        return cryptograms;
    }

    public static void main(String[] args) {
        Namespace cli = createArgumentParser(args);
        int ciphers;
        if (cli.getInt("ciphers") != null)
            ciphers = cli.getInt("ciphers");
        else
            ciphers = Integer.MAX_VALUE;
        Decryptor decryptor = new Decryptor(new Polish(), getCryptogramsFromFile(cli.get("file"),ciphers));
        Scanner in = new Scanner(System.in);

        while (true) {
            try {
                ArrayList<String> decrypted = decryptor.decrypt();
                for (int i = 0; i < decrypted.size(); i++) {
                    String message = decryptor.decrypt().get(i);
                    System.out.println(i + ":\t" + message);
                }
                System.out.println("To improve the key, type number of the message you want correct. " +
                        "To exit, type anything else");
                System.out.print("Message id: ");
                int number = in.nextInt();
                in.nextLine();
                System.out.println("Please rewrite message (might be partially)");
                System.out.println(decrypted.get(number));
                String fixedMessage = in.nextLine();
                decryptor.fixKey(number, fixedMessage);
                in.reset();
            } catch (Exception e) {
                break;
            }
        }
        StringBuilder key = new StringBuilder();
        for (int k : decryptor.getKey()) {
            String a = Integer.toBinaryString(k);
            String formatted = ("00000000" + a).substring(a.length());
            key.append(formatted).append(" ");
        }
        System.out.println("Key:\n" + key);
    }
}

