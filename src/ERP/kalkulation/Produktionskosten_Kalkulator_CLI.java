package ERP.kalkulation;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Produktionskosten_Kalkulator_CLI {

    private final Scanner scanner = new Scanner(System.in);
    private final Locale locale = Locale.GERMANY;
    private final NumberFormat nf = NumberFormat.getInstance(locale);

    public void start() {

        try {
            System.out.println("==============================================");
            System.out.println(" PRODUKTIONSKOSTEN KALKULATOR ");
            System.out.println("==============================================");
            System.out.println("Eingaben gelten pro Stück (Material & Zeit)\n");

            System.out.print("Personal Nr / Name: ");
            String personal = scanner.nextLine();

            System.out.print("Projekt Name: ");
            String projekt = scanner.nextLine();

            double plaKG = leseDouble("PLA Preis €/kg: ");
            double grammProStk = leseDouble("Gramm pro Stück: ");
            double stunden = leseDouble("Druckzeit Stunden pro Stück: ");
            double minuten = leseDouble("Druckzeit Minuten pro Stück: ");
            double stueckzahl = leseDouble("Stückzahl: ");

            // Minuten normalisieren
            if (minuten < 0) minuten = 0;
            if (minuten >= 60) {
                stunden += Math.floor(minuten / 60.0);
                minuten = minuten % 60.0;
            }

            double dezimalStunden = stunden + (minuten / 60.0);

            // Konstanten
            double strom_kWh = 0.36;
            double strom_drucker_kWh = 0.1;
            double druckVerschleiss = 0.25;
            double drucker_preis = 199.0;
            double drucker_lebensdauer_h = 5000.0;

            // Berechnung
            double materialKosten = (grammProStk / 1000.0) * plaKG;
            double stromKosten = dezimalStunden * strom_drucker_kWh * strom_kWh;
            double druckerKosten = (drucker_preis / drucker_lebensdauer_h) * dezimalStunden;
            double verschleissKosten = dezimalStunden * druckVerschleiss;

            double stueckPreis = materialKosten + stromKosten + druckerKosten + verschleissKosten;
            double gesamt = stueckPreis * stueckzahl;

            // Ausgabe
            LocalDate heute = LocalDate.now();
            String datum = heute.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

            System.out.println("\n==============================================");
            System.out.println("PRODUKTIONSKOSTEN ZUSAMMENFASSUNG");
            System.out.println("==============================================");
            System.out.println("Datum: " + datum);
            System.out.println("Projekt Name: " + projekt);
            System.out.println("\nPersonal Nummer / Name: " + personal + "\n");

            System.out.printf(locale, "Stückzahl: %.0f%n", stueckzahl);
            System.out.printf(locale, "Druckzeit: %.2f h%n%n", dezimalStunden);

            System.out.printf(locale, "Material: %.2f €%n", materialKosten);
            System.out.printf(locale, "Strom: %.2f €%n", stromKosten);
            System.out.printf(locale, "Drucker Abnutzung: %.2f €%n", druckerKosten);
            System.out.printf(locale, "Verschleiß: %.2f €%n", verschleissKosten);

            System.out.println("==============================================");
            System.out.printf(locale, "Kosten pro Stück: %.2f €%n", stueckPreis);
            System.out.println("==============================================");
            System.out.printf(locale, "GESAMT: %.2f €%n", gesamt);
            System.out.println("==============================================");

        } catch (Exception e) {
            System.out.println("\nFEHLER: Bitte Zahlen korrekt eingeben (Komma statt Punkt bei deutschem Format).");
        }
    }

    private double leseDouble(String text) throws Exception {
        System.out.print(text);
        return nf.parse(scanner.nextLine()).doubleValue();
    }

    public static void main(String[] args) {
        new Produktionskosten_Kalkulator_CLI().start();
    }
}
