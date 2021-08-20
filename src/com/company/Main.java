package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner myObj = new Scanner(System.in);
        System.out.println("If your want to find the key insert '1' " +
                "else insert '2' to proceed to encrypting or decrypting a message");
        String method = myObj.nextLine();

        switch (method) {
            case "1":
                game1();
                break;
            case "2":
                game2();
                break;
            default:
                System.out.println("Enter a valid option");
                break;
        }
    }

    public static void game1(){
        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter the plaintext");
        String plaintext = myObj.nextLine();

        System.out.println("Enter the ciphertext");
        String ciphertext = myObj.nextLine();

        if(ciphertext.charAt(0) > plaintext.charAt(0)){
            Integer range = ciphertext.charAt(0) - plaintext.charAt(0);
            String message = decrypt(range.toString(), ciphertext);

            if (plaintext.matches(message)){
                System.out.println("The key is: ");
                System.out.println(range);
            } else{
                System.out.println("The key is not found");
                System.out.println("The possible options with each key are: ");
                for (Integer i=0; i<26; i++){
                    System.out.println(i);
                    System.out.println(decrypt(i.toString(), ciphertext));
                }
            }
        }else {
            Integer range = 26-(plaintext.charAt(0) - ciphertext.charAt(0));
            String message = decrypt(range.toString(), ciphertext);

            if (plaintext.matches(message)){
                System.out.println("The key is: ");
                System.out.println(range);
            } else{
                System.out.println("The key is not found");
                System.out.println("The possible options with each key are: ");
                for (Integer i=0; i<26; i++){
                    System.out.println(i);
                    System.out.println(decrypt(i.toString(), ciphertext));
                }
            }
        }
    }

    public static void game2(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter the key");
        String key = myObj.nextLine();  //Save value into the key object.
        int ikey =Integer.parseInt(key);
        //Verify key is between the values of 0 and 25
        if(ikey < 0 || ikey > 25){
            System.out.println("Invalid Key");
            return;
        }

        System.out.println("Enter the plaintext/ciphertext");
        String message = myObj.nextLine();

        System.out.println("Encrypt or Decrypt");
        String type = myObj.nextLine();
        switch (type.toLowerCase()) {
            case "encrypt":
                String encryptedMessage = encrypt(key, message);
                System.out.println("The encrypted message is: ");
                System.out.println(encryptedMessage);
                break;
            case "decrypt":
                String decryptedMessage = decrypt(key, message);
                System.out.println("The decrypted message is: ");
                System.out.println(decryptedMessage);
                break;
            default:
                System.out.println("Enter a valid option");
                break;
        }
    }

    //ENCRYPTION FOR CAPS IS MISSING
    public static String encrypt(String key, String message){
        int ikey =Integer.parseInt(key);
        char[] encryptedMessage = new char[message.length()];
        for (int i=0; i<message.length(); i++){
            char letter = message.charAt(i);
            if(letter == 122 || (letter+ikey) >= 123){
                char let = letter-=26;
                encryptedMessage[i] = let+=ikey;
            } else {
                encryptedMessage[i] = letter+=ikey;
            }
        }
        String string = new String(encryptedMessage);
        return string;
    }

    public static String decrypt(String key, String message){
        int ikey =Integer.parseInt(key);
        char[] encryptedMessage = new char[message.length()];
        for (int i=0; i<message.length(); i++){
            char letter = message.charAt(i);
            if(letter == 122 ){
                encryptedMessage[i] = 'a';
            } else if (letter-ikey >= 97){
                encryptedMessage[i] = letter-=ikey;
            } else if ((letter-ikey) < 97 || (letter+ikey) > 122){
                int index = ((int) letter);
                int let = index-ikey+26;
                char letterC = ((char) let);
                encryptedMessage[i] = letterC;
            }
            else  {
                encryptedMessage[i] = letter-=ikey;
            }
        }
        String string = new String(encryptedMessage);
        return string;
    }
}
