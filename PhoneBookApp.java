import java.util.Scanner;

public class PhoneBookApp {

    // Declare the phonebook and total contacts variable
    private static String[] phoneBook = new String[100];
    private static int totalContacts = 0;
    
    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("1. Insert Contact");
            System.out.println("2. Search Contact");
            System.out.println("3. Display All Contacts");
            System.out.println("4. Delete Contact");
            System.out.println("5. Update Contact");
            System.out.println("6. Sort Contacts");
            System.out.println("7. Analyze Search Efficiency");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // clear the buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    insertContact(name, phoneNumber);
                    break;
                case 2:
                    System.out.print("Enter name to search: ");
                    name = scanner.nextLine();
                    int index = searchContact(name);
                    if (index == -1) {
                        System.out.println("Contact not found.");
                    } else {
                        System.out.println("Contact found: " + phoneBook[index]);
                    }
                    break;
                case 3:
                    displayContacts();
                    break;
                case 4:
                    System.out.print("Enter name to delete: ");
                    name = scanner.nextLine();
                    deleteContact(name);
                    break;
                case 5:
                    System.out.print("Enter name to update: ");
                    name = scanner.nextLine();
                    System.out.print("Enter new phone number: ");
                    phoneNumber = scanner.nextLine();
                    updateContact(name, phoneNumber);
                    break;
                case 6:
                    sortContacts();
                    break;
                case 7:
                    System.out.print("Enter name to analyze search: ");
                    name = scanner.nextLine();
                    analyzeSearchEfficiency(name);
                    break;
                case 8:
                    System.out.println("Exiting application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);
        scanner.close();
    }

    // Insert a new contact into the phonebook
    public static void insertContact(String name, String phoneNumber) {
        if (totalContacts < phoneBook.length) {
            phoneBook[totalContacts] = name + "," + phoneNumber;
            totalContacts++;
            System.out.println("Contact inserted successfully.");
        } else {
            System.out.println("Phonebook is full. Cannot insert more contacts.");
        }
    }

    // Search for a contact by name
    public static int searchContact(String name) {
        for (int i = 0; i < totalContacts; i++) {
            String contact = phoneBook[i];
            String contactName = contact.split(",")[0]; // Extract name from "name,phoneNumber"
            if (contactName.equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1; // Contact not found
    }

    // Display all contacts
    public static void displayContacts() {
        if (totalContacts == 0) {
            System.out.println("Phonebook is empty.");
        } else {
            for (int i = 0; i < totalContacts; i++) {
                System.out.println(phoneBook[i]);
            }
        }
    }

    // Delete a contact by name
    public static void deleteContact(String name) {
        int index = searchContact(name);
        if (index == -1) {
            System.out.println("Contact not found.");
        } else {
            // Shift all contacts after the deleted contact
            for (int i = index; i < totalContacts - 1; i++) {
                phoneBook[i] = phoneBook[i + 1];
            }
            totalContacts--; // Reduce totalContacts count
            System.out.println("Contact deleted successfully.");
        }
    }

    // Update a contact's phone number
    public static void updateContact(String name, String newPhoneNumber) {
        int index = searchContact(name);
        if (index == -1) {
            System.out.println("Contact not found.");
        } else {
            phoneBook[index] = name + "," + newPhoneNumber; // Update the contact
            System.out.println("Contact updated successfully.");
        }
    }

    // Sort contacts alphabetically by name using bubble sort
    public static void sortContacts() {
        for (int i = 0; i < totalContacts - 1; i++) {
            for (int j = i + 1; j < totalContacts; j++) {
                String[] contact1 = phoneBook[i].split(",");
                String[] contact2 = phoneBook[j].split(",");
                if (contact1[0].compareToIgnoreCase(contact2[0]) > 0) {
                    // Swap contacts
                    String temp = phoneBook[i];
                    phoneBook[i] = phoneBook[j];
                    phoneBook[j] = temp;
                }
            }
        }
        System.out.println("Contacts sorted successfully.");
    }

    // Analyze search efficiency by counting the number of steps
    public static void analyzeSearchEfficiency(String name) {
        int steps = 0;
        for (int i = 0; i < totalContacts; i++) {
            steps++;
            String contactName = phoneBook[i].split(",")[0]; // Extract name from "name,phoneNumber"
            if (contactName.equalsIgnoreCase(name)) {
                System.out.println("Contact found in " + steps + " steps.");
                return;
            }
        }
        System.out.println("Contact not found. Search took " + steps + " steps.");
    }
}
