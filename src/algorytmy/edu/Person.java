package algorytmy.edu;


import java.util.Arrays;
import java.util.UUID;

public class Person {

    public UUID uuid;
    public String name;
    public String surname;
    public String email;
    public int phone_number;
    public int status;
    public String klasa;
    public String discord_name;
    public int hour_rate;

    public String getKlasa() {
        return klasa;
    }

    public void setKlasa(String klasa) {
        this.klasa = klasa;
    }

    public int getHour_rate() {
        return hour_rate;
    }

    public void setHour_rate(int hour_rate) {
        this.hour_rate = hour_rate;
    }

    public String getDiscord_name() {
        return discord_name;
    }

    public void setDiscord_name(String discord_name) {
        this.discord_name = discord_name;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setRandUuid(){
        uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public Person() {

    }

    public Person(String name, String surname, String email,
                  int phone_number, int status) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone_number = phone_number;
        this.status = status;
    }

    public Person(String name, String surname, String email,
                  int phone_number, int status, String klasa,
                  String discord_name, int hour_rate) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone_number = phone_number;
        this.status = status;
        this.klasa = klasa;
        this.discord_name = discord_name;
        this.hour_rate = hour_rate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void Print_Person() {
        System.out.println("----------------------------------");
        System.out.println(uuid);
        System.out.println("Imie: " + name);
        System.out.println("Naziwsko: " + surname);
        System.out.println("Email: " + email);
        System.out.println("Numer telefonu: " + phone_number);
        switch (status) {
            case 1  -> {
                System.out.println("Ta osoba jest uczniem");
                System.out.println("Klasa ucznia: "+ klasa);
                System.out.println("Discord: " + discord_name);
                System.out.println("Stawka godzinowa: " + hour_rate);
            }
            case 2 -> System.out.println("Ta osoba jest rodzicem");
            case 3 -> {
                System.out.println("Ta osoba jest korepetytorem");
                System.out.println("Discord: " + discord_name);
                System.out.println("Stawka godzinowa: " + hour_rate);
            }
            case 4 -> {
                System.out.println("Ta osoba jest administratorem");
                System.out.println("Discord: " + discord_name);
                System.out.println("Stawka godzinowa: " + hour_rate);
            }
            default -> System.out.println("Ta osoba nie ma przypisanego statusu");

        }

    }

    public void Person_Printer_Basic(){
        System.out.println("Imie: " + name);
        System.out.println("Naziwsko: " + surname);
        System.out.println("Email: " + email);
        System.out.println("Numer telefonu: " + phone_number);
    }
}
