
package poe1;

import java.util.*;

public class Message {

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static int messageCount = 0;

    static List<MessageData> sentMessages = new ArrayList<>();
    static List<MessageData> disregardedMessages = new ArrayList<>();
    static List<MessageData> storedMessages = new ArrayList<>();
    static List<String> messageHashes = new ArrayList<>();
    static List<Long> messageIDs = new ArrayList<>();

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
                    + "\n2. Message Operations Menu"
                    + "\n3. Quit");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    handleSendMessages();
                    break;
                case "2":
                    messageReportsMenu();
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

        for (int i = 0; i < numMessages; i++) {
            System.out.println("\n--- Message " + (i + 1) + " ---");

            String recipient;
            while (true) {
                System.out.print("Enter recipient phone number (must start with +27 and be followed by 9 digits): ");
                recipient = scanner.nextLine();
                if (recipient.matches("\\+27\\d{9}")) {
                    break;
                } else {
                    System.out.println("Invalid number. Format must be +27 followed by 9 digits.");
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
                    sentMessages.add(message);
                    messageHashes.add(message.messageHash);
                    messageIDs.add(message.messageID);
                    System.out.println("Message sent!");
                    break;
                case "2":
                    disregardedMessages.add(message);
                    System.out.println("Message disregarded.");
                    break;
                case "3":
                    storedMessages.add(message);
                    System.out.println("Message stored.");
                    break;
                default:
                    disregardedMessages.add(message);
                    System.out.println("Invalid choice. Message disregarded by default.");
            }
        }

        // Optionally show stored messages
        System.out.println("\nStored Messages:");
        for (MessageData msg : storedMessages) {
            System.out.println(msg + "\n");
        }
    }

    private static void messageReportsMenu() {
        while (true) {
            System.out.println("\nMessage Reports:"
                + "\na. Display sender and recipient of all sent messages"
                + "\nb. Display longest sent message"
                + "\nc. Search by Message ID"
                + "\nd. Search by Recipient"
                + "\ne. Delete message by hash"
                + "\nf. Display report of all sent messages"
                + "\ng. Back to main menu");

            String choice = scanner.nextLine().toLowerCase();

            switch (choice) {
                case "a": displaySendersAndRecipients(); break;
                case "b": displayLongestMessage(); break;
                case "c": searchByMessageID(); break;
                case "d": searchByRecipient(); break;
                case "e": deleteByHash(); break;
                case "f": displayFullReport(); break;
                case "g": return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static void displaySendersAndRecipients() {
        if (sentMessages.isEmpty()) {
            System.out.println("No sent messages.");
            return;
        }
        for (MessageData m : sentMessages) {
            System.out.println("To: " + m.recipient + " | Hash: " + m.messageHash);
        }
    }

    private static void displayLongestMessage() {
        if (sentMessages.isEmpty()) {
            System.out.println("No messages to check.");
            return;
        }
        MessageData longest = sentMessages.get(0);
        for (MessageData m : sentMessages) {
            if (m.content.length() > longest.content.length()) {
                longest = m;
            }
        }
        System.out.println("Longest message:\n" + longest);
    }

    private static void searchByMessageID() {
        System.out.print("Enter message ID: ");
        long id = Long.parseLong(scanner.nextLine());
        for (MessageData m : sentMessages) {
            if (m.messageID == id) {
                System.out.println(m);
                return;
            }
        }
        System.out.println("Message not found.");
    }

    private static void searchByRecipient() {
        System.out.print("Enter recipient number: ");
        String recipient = scanner.nextLine();
        for (MessageData m : sentMessages) {
            if (m.recipient.equals(recipient)) {
                System.out.println(m);
            }
        }
    }

    private static void deleteByHash() {
        System.out.print("Enter message hash to delete: ");
        String hash = scanner.nextLine();
        boolean removed = sentMessages.removeIf(m -> m.messageHash.equals(hash));
        if (removed) {
            System.out.println("Message deleted.");
        } else {
            System.out.println("Message with that hash not found.");
        }
    }

    private static void displayFullReport() {
        if (sentMessages.isEmpty()) {
            System.out.println("No messages to report.");
            return;
        }
        for (MessageData m : sentMessages) {
            System.out.println(m + "\n");
        }
    }
}
