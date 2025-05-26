/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poe;
import java.util.*;

/**
 *
 * @author RC_Student_lab
 */
public class Message {
   
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static int messageCount = 0;



 
    static class MessageData {
        long messageID;
        int messageNumber;
        String recipient;
        String content;
        String messageHash;

        MessageData(String recipient, String content) {
            this.messageID = generateMessageID();
            this.messageNumber = ++messageCount;
            this.recipient = recipient;
            this.content = content;
            this.messageHash = generateMessageHash();
        }

        private long generateMessageID() {
            return 1000000000L + (long)(random.nextDouble() * 8999999999L);
        }

        private String generateMessageHash() {
            String[] words = content.trim().split("\\s+");
            String firstWord = words.length > 0 ? words[0].toUpperCase() : "";
            String lastWord = words.length > 1 ? words[words.length - 1].toUpperCase() : firstWord;
            return String.format("%d:%d:%s:%s", messageID / 100000000, messageNumber, firstWord, lastWord);
        }

        public String toString() {
            return String.format("ID: %d\nTo: %s\nMessage #%d: %s\nHash: %s",
                    messageID, recipient, messageNumber, content, messageHash);
        }
    }

    public static void Part2() {
        while (true) {
            System.out.println("Welcome to QuickChat"
                    + "\nChoose an option:"
                    + "\n1. Send Messages"
                    + "\n2. Coming Soon..."
                    + "\n3. Quit");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    handleSendMessages();
                    break;
                case "2":
                    System.out.println("Coming Soon...");
                    break;
                case "3":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter a valid input.");
            }
        }
    }


    private static void handleSendMessages() {
        System.out.print("How many messages would you like to send? ");
        int numMessages = Integer.parseInt(scanner.nextLine());

        List<MessageData> storedMessages = new ArrayList<>();

        for (int i = 0; i < numMessages; i++) {
            System.out.println("\n--- Message " + (i + 1) + " ---");

            String recipient;
            while (true) {
                System.out.print("Enter recipient phone number (must start with international code +27 and be followed by 9 digits): ");
                recipient = scanner.nextLine();
                if (recipient.matches("\\+27\\d{1,9}")) {
                    break;
                } else {
                    System.out.println("Invalid number. Format must be +27 followed by up to 9 digits.");
                }
            }

            String content;
            while (true) {
                System.out.print("Enter message (max 250 characters): ");
                content = scanner.nextLine();
                if (content.length() <= 250) {
                    break;
                } else {
                    System.out.println("Message is too long.");
                }
            }

            MessageData message = new MessageData(recipient, content);
            System.out.println("\n" + message);

            System.out.println("Choose an option:"
                    + "\n1. Send Message"
                    + "\n2. Disregard Message"
                    + "\n3. Store Message to send later");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Message sent!");
                    break;
                case "2":
                    System.out.println("Message disregarded.");
                    break;
                case "3":
                    storedMessages.add(message);
                    System.out.println("Message stored.");
                    break;
                default:
                    System.out.println("Invalid choice. Message disregarded by default.");
            }
        }

        if (!storedMessages.isEmpty()) {
            System.out.println("\nStored Messages:");
            for (MessageData msg : storedMessages) {
                System.out.println(msg + "\n");
            }
        }
    }
}
    

