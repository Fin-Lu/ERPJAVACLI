package ERP.menu;

import java.util.Scanner;

public class ERP_Hauptmenue_CLI {

    private final Scanner scanner = new Scanner(System.in);
    private boolean running = true;

    public void start() {

        while (running) {
            printHauptmenue();
            int auswahl = leseInt();

            switch (auswahl) {
                case 1 -> kalkulationMenue();
                case 2 -> produktionMenue();
                case 3 -> auftragMenue();
                case 4 -> kundenMenue();
                case 5 -> systemMenue();
                case 0 -> beenden();
                default -> System.out.println("Ungültige Auswahl!");
            }
        }
    }

    // ===============================
    // HAUPTMENÜ
    // ===============================
    private void printHauptmenue() {
        System.out.println("\n===== ERP HAUPTMENÜ =====");
        System.out.println("1 - Kalkulation");
        System.out.println("2 - Produktion");
        System.out.println("3 - Aufträge");
        System.out.println("4 - Kunden");
        System.out.println("5 - System");
        System.out.println("0 - Beenden");
        System.out.print("Auswahl: ");
    }

    // ===============================
    // KALKULATION
    // ===============================
    private void kalkulationMenue() {
        System.out.println("\n--- Kalkulation ---");
        System.out.println("1 - Produktkosten Kalkulator");
        System.out.println("2 - Etsy Kalkulator UVP");
        System.out.println("3 - Offline Kalkulator UVP");
        System.out.println("4 - B2B Kalkulator");
        System.out.println("0 - Zurück");

        int auswahl = leseInt();

        switch (auswahl) {
            case 1 -> System.out.println("Produktkosten startet...");
            case 2 -> System.out.println("Etsy startet...");
            case 3 -> System.out.println("Offline startet...");
            case 4 -> System.out.println("B2B startet...");
            default -> System.out.println("Ungültige Auswahl!");
        }
    }

    private void produktionMenue() {
        System.out.println("\n--- Produktion ---");
        System.out.println("Kommt später...");
        leseInt();
    }

    private void auftragMenue() {
        System.out.println("\n--- Aufträge ---");
        System.out.println("Kommt später...");
        leseInt();
    }

    private void kundenMenue() {
        System.out.println("\n--- Kunden ---");
        System.out.println("Kommt später...");
        leseInt();
    }

    private void systemMenue() {
        System.out.println("\n--- System ---");
        System.out.println("1 - Dashboard");
        System.out.println("0 - Zurück");

        int auswahl = leseInt();

        if (auswahl == 1) {
            dashboard();
        }
    }

    private void dashboard() {
        System.out.println("\n===== DASHBOARD =====");
        System.out.println("ERP läuft im CLI Modus.");
    }

    private void beenden() {
        System.out.println("Programm wird beendet...");
        running = false;
    }

    private int leseInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Bitte Zahl eingeben!");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        new ERP_Hauptmenue_CLI().start();
    }
}