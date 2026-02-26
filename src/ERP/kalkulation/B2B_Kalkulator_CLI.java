package ERP.kalkulation;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class B2B_Kalkulator_CLI {


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
                if (minuten >= 60) {
                    stunden += Math.floor(minuten / 60);
                    minuten = minuten % 60;
                }

                double dezimalStunden = stunden + (minuten / 60.0);

                // Gewinn Auswahl
                System.out.println("\nGewinn wählen:");
                System.out.println("1 - 30%");
                System.out.println("2 - 40%");
                System.out.println("3 - 50%");
                System.out.println("4 - 60%");
                System.out.println("5 - 70%");
                System.out.println("6 - 80%");
                System.out.println("7 - 90%");
                System.out.println("8 - 800%");

                int auswahl = Integer.parseInt(scanner.nextLine());

                if (auswahl < 1 || auswahl > 8) {
                    System.out.println("Ungültige Auswahl!");
                    return;
                }

                double[] gewinnOptionen = {30, 40, 50, 60, 70, 80, 90, 800};
                double gewinnProzent = gewinnOptionen[auswahl - 1];

                // Konstanten
                final double stromPreis_kWh = 0.36;
                final double stromDrucker_kWh = 0.1;
                final double verschleissProStunde = 0.25;
                final double druckerPreis = 199.0;
                final double druckerLebensdauer = 5000.0;
                final double umsatzsteuer = 1.19;

                // Berechnungen
                double materialKosten = (grammProStk / 1000.0) * plaKG;
                double stromKosten = dezimalStunden * stromDrucker_kWh * stromPreis_kWh;
                double druckerKosten = (druckerPreis / druckerLebensdauer) * dezimalStunden;
                double verschleissKosten = dezimalStunden * verschleissProStunde;

                double kostenProStueck = materialKosten + stromKosten + druckerKosten + verschleissKosten;
                double gesamtKosten = kostenProStueck * stueckzahl;

                double verkaufNetto = gesamtKosten * (1 + gewinnProzent / 100.0);
                double gewinnBetrag = verkaufNetto - gesamtKosten;
                double verkaufBrutto = verkaufNetto * umsatzsteuer;

                // Ausgabe
                LocalDate heute = LocalDate.now();
                String datum = heute.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

                System.out.println("\n==============================================");
                System.out.println(" PRODUKTIONSKOSTEN ZUSAMMENFASSUNG ");
                System.out.println("==============================================");
                System.out.println("Datum: " + datum);
                System.out.println("Projekt: " + projekt);
                System.out.println("Personal: " + personal);
                System.out.println("----------------------------------------------");

                System.out.printf(locale, "Stückzahl: %.0f%n", stueckzahl);
                System.out.printf(locale, "Druckzeit: %.2f h%n%n", dezimalStunden);

                System.out.printf(locale, "Materialkosten: %.2f €%n", materialKosten);
                System.out.printf(locale, "Stromkosten: %.2f €%n", stromKosten);
                System.out.printf(locale, "Drucker Abnutzung: %.2f €%n", druckerKosten);
                System.out.printf(locale, "Verschleißkosten: %.2f €%n", verschleissKosten);

                System.out.println("----------------------------------------------");
                System.out.printf(locale, "Kosten pro Stück: %.2f €%n", kostenProStueck);
                System.out.printf(locale, "Gesamtkosten: %.2f €%n", gesamtKosten);
                System.out.println("----------------------------------------------");

                System.out.printf(locale, "Gewinnbetrag: %.2f € (%.0f%%)%n", gewinnBetrag, gewinnProzent);
                System.out.printf(locale, "Netto Verkaufspreis: %.2f €%n", verkaufNetto);
                System.out.printf(locale, "Brutto Verkaufspreis: %.2f €%n", verkaufBrutto);
                System.out.println("==============================================");

            } catch (Exception e) {
                System.out.println("\nFEHLER: Bitte Zahlen korrekt eingeben (z.B. 1,5 statt 1.5).");
            }
        }

        private double leseDouble(String text) throws Exception {
            System.out.print(text);
            return nf.parse(scanner.nextLine()).doubleValue();
        }

        public static void main(String[] args) {
            new ERP.kalkulation.Offline_Kalkulator_CLI().start();
        }
    }
