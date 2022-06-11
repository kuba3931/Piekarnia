package algorytmy.edu;

public class Decorations {


    int decor_int;

    public int getDecor_int() {
        return decor_int;
    }

    public void setDecor_int(int decor_int) {
        this.decor_int = decor_int;
    }

    public static void error(){
        System.out.println("""
                     ____ ____ ____ ____ ____\s
                    ||E |||r |||r |||o |||r ||
                    ||__|||__|||__|||__|||__||
                    |/__\\|/__\\|/__\\|/__\\|/__\\|
                    """);
    }

    public static void main_decor(){
        System.out.print("""
                
                 _ __   __ _ (_) _ _    _ __   ___  _ _  _  _\s
                | '  \\ / _` || || ' \\  | '  \\ / -_)| ' \\| || |
                |_|_|_|\\__,_||_||_||_| |_|_|_|\\___||_||_|\\_,_|
                                                             \s
                1. Dodaj nowa osobe
                2. Lista osob
                3. Dodaj Lekcje
                4. Lista lekcji
                5. Dodaj Fakture
                6. Wyswietl Faktury
                """);
    }

    public static void months(){
        System.out.println("Wybierz miesiac:");
        System.out.println("""
                01. Styczen - 02. Luty - 03. Marzec
                04. Kwiecien - 05. Maj - 06. Czerwiec
                07. Lipiec - 08. Sierpien - 09. Wrzesien
                10. Pazdziernik - 11. Listopad - 12. Grudzien""");
    }

    public static String decor_month(int decor_int) {

        String Conv_str_month;

        int decor_int_Val = Integer.valueOf(decor_int);

        switch(decor_int_Val){
            case 1 -> Conv_str_month = "Stycznen";
            case 2 -> Conv_str_month = "Luty";
            case 3 -> Conv_str_month = "Marzec";
            case 4 -> Conv_str_month = "Kwietnien";
            case 5 -> Conv_str_month = "Maj";
            case 6 -> Conv_str_month = "Czerwiec";
            case 7 -> Conv_str_month = "Lipiec";
            case 8 -> Conv_str_month = "Siepien";
            case 9 -> Conv_str_month = "Wrzesien";
            case 10 -> Conv_str_month = "Pazdziernik";
            case 11 -> Conv_str_month = "Listopad";
            case 12 -> Conv_str_month = "Grudzien";
            default -> Conv_str_month = "Brak";
        }

        return Conv_str_month;
    }

}
