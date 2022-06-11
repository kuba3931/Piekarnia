package algorytmy.edu;

import java.io.*;
import java.util.*;

public class Main {

    // start methods
    public static void main(String[] args) {
        main_menu();
    }

    public static void main_menu() {

        Decorations.main_decor();
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();

        try {
            switch (choice) {
                case 1 -> person_creator();
                case 2 -> people_sorted_list();
                case 3 -> Add_lesson();
                case 4 -> lesson_print_menu(true);
                case 5 -> Add_Invoice();
                case 6 -> invoice_printer();
                default -> {
                    System.out.println("Niepoprawny wybor");
                    main_menu();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Wprowadz liczba od 1 do 6 aby przejsc dalej");
            main_menu();
        }
    }

    // person methods
    public static void person_creator() {

        Scanner input = new Scanner(System.in);
        Person new_person = new Person();

        new_person.setRandUuid();


        try {
            System.out.println("Podaj imie osoby:");
            new_person.name = input.nextLine();

            System.out.println("Podaj nazwisko osoby:");
            new_person.surname = input.nextLine();

            System.out.println("Podaj email osoby:");
            new_person.email = input.nextLine();

            System.out.println("Podaj numer telefonu osoby:");
            new_person.phone_number = input.nextInt();

            System.out.println("Podaj status osoby:\n1.Uczen 2.Rodzic 3.Korepetytor 4.Administator");
            new_person.status = input.nextInt();

        } catch(InputMismatchException e){
            System.out.println("Wprowadzono zly rodzaj danych sprobuj ponownie\n");
            person_creator();
        }

        switch (new_person.status){
            case 1 -> {
                System.out.println("Podaj klase ucznia: ");
                new_person.klasa = input.nextLine();
                new_person.klasa = input.nextLine();
                System.out.println("Podaj nazwe na discordzie:");
                new_person.discord_name = input.nextLine();
                System.out.println("Podaj stawke godzinowa:");
                new_person.hour_rate = input.nextInt();
            }
            case 3, 4 -> {
                System.out.println("Podaj nazwe na discordzie:");
                new_person.discord_name = input.nextLine();
                new_person.discord_name = input.nextLine();
                System.out.println("Podaj stawke godzinowa:");
                new_person.hour_rate = input.nextInt();
            }
            default -> {
                System.out.println("Niepoprawny typ osoby\n");
                person_creator();
            }
        }

        System.out.println("Oto dane nowej osoby, sprawdz dane przed dodaniem");

        new_person.Print_Person();

        System.out.print("\nCo teraz?\n1. Zapisz osobe do bazy\n2. Utworz jeszcze raz\n3. Powrot do menu\n");
        int choice = input.nextInt();

        switch (choice) {
            case 1 -> save_person(new_person);
            case 2 -> person_creator();
            case 3 -> main_menu();
        }
    }

    public static void save_person(Person new_person) {

        File person_file = new File("person_db.csv");

        try {
            FileWriter person_writer = new FileWriter(person_file, true);
            BufferedWriter person_buffered = new BufferedWriter(person_writer);
            PrintWriter person_printer = new PrintWriter(person_buffered);

            person_printer.println(new_person.uuid + "," + new_person.name + "," + new_person.surname + ","
                    + new_person.email + "," + new_person.phone_number + "," + new_person.status + "," +
                    new_person.klasa + "," + new_person.discord_name + "," + new_person.hour_rate);

            person_printer.flush();
            person_printer.close();

            System.out.println("Osoba " + new_person.name + " zostala dodana do bazy");

        } catch (IOException e) {
            System.out.println("Operacja zakonczona niepowodzeniem!");
        }

        main_menu();
    }

    public static void get_people_from_file(ArrayList<Person> people) {

        File person_file = new File("person_db.csv");
        String line = "";

        try {
            BufferedReader people_BFreader = new BufferedReader(new FileReader(person_file));
            people_BFreader.readLine();

            while ((line = people_BFreader.readLine()) != null) {
                String[] values = line.split(",");

                String StrUuid = values[0];
                String StrName = values[1];
                String StrSurname = values[2];
                String StrEmail = values[3];
                String StrPhone = values[4];
                String StrStatus = values[5];
                String StrKlass = values[6];
                String StrDiscord = values[7];
                String StrRate = values[8];

                UUID ConvUuid = UUID.fromString(StrUuid);
                int ConvPhone = Integer.parseInt(StrPhone);
                int ConvStatus = Integer.parseInt(StrStatus);
                int ConvRate = Integer.parseInt(StrRate);

                Person person_csv = new Person(StrName,StrSurname,StrEmail,ConvPhone,
                        ConvStatus,StrKlass,StrDiscord,ConvRate);
                person_csv.uuid = ConvUuid;

                people.add(person_csv);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void people_sorted_list() {

        System.out.println("Posortowana lista osob:");
        ArrayList<Person> people = new ArrayList<>();
        ArrayList<Person> students = new ArrayList<>();
        ArrayList<Person> parents = new ArrayList<>();
        ArrayList<Person> tutors = new ArrayList<>();
        ArrayList<Person> admins = new ArrayList<>();

        get_people_from_file(people);

        for(Person p : people){
            switch(p.status){
                case 1 -> students.add(p);
                case 2 -> parents.add(p);
                case 3 -> tutors.add(p);
                case 4 -> admins.add(p);
            }
        }

        int i = 1;

        System.out.println("\nLista Uczniow:");

        for(Person p : students){
            System.out.println(i + ". " + p.name + " " + p.surname);
            i++;
        }

        System.out.println("\nLista Rodzicow:");

        i = 1;

        for(Person p : parents){
            System.out.println(i + ". " + p.name + " " + p.surname);
            i++;
        }

        System.out.println("\nLista Korepetytorow:");

        i = 1;

        for(Person p : tutors){
            System.out.println(i + ". " + p.name + " " + p.surname);
            i++;
        }

        System.out.println("\nLista Administratorow:");

        i = 1;

        for(Person p : admins){
            System.out.println(i + ". " + p.name + " " + p.surname);
            i++;
        }

        main_menu();
    }

    // lesson methods
    public static void Add_lesson() {

        Lesson new_lesson = new Lesson();
        new_lesson.RandLesson_id();
        Scanner lesson_input = new Scanner(System.in);

        String rate_choice = null;
        try {
            System.out.println("Podaj date lekcji w formacie (dd.mm.yy)");
            new_lesson.lesson_date = lesson_input.nextLine();
            System.out.println("Podaj godzine rozpoczecia lekcji w formacie (hh:mm)");
            new_lesson.start_time = lesson_input.nextLine();
            System.out.println("Podaj dlugosc lekcji w godz (np. 1.5)");
            new_lesson.duration = lesson_input.nextFloat();
            System.out.println("Standardowa stawka ucznia (tak/nie)?");
            String filler = lesson_input.nextLine();
            rate_choice = lesson_input.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Podano dane w zlym formacie");
            Add_lesson();
        }

        int rate = 0;

        if (!Objects.equals(rate_choice, "tak")) {
            System.out.println("Podaj stawke godzinowa");
            try {
                rate = lesson_input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Podano dane w zlym formacie");
                Add_lesson();
            }
        }

        ArrayList<Person> lesson_people = new ArrayList<>();
        get_people_from_file(lesson_people);

        ArrayList<Person> students = new ArrayList<>();
        ArrayList<Person> tutors = new ArrayList<>();


        for (Person p : lesson_people) {
            switch (p.status) {
                case 1 -> students.add(p);
                case 3, 4 -> tutors.add(p);
            }
        }
        int i = 1;

        System.out.println("\nWybierz numer ucznia:");

        for (Person p : students) {
            System.out.println(i + ". " + p.name + " " + p.surname);
            i++;
        }

        int student_choice = 0;
        try {
            student_choice = lesson_input.nextInt() - 1;
        } catch (InputMismatchException e) {
            System.out.println("Podano dane w zlym formacie");
            Add_lesson();
        }

        Person chosen_student = students.get(student_choice);

        new_lesson.student_uuid = chosen_student.uuid;
        System.out.println("Wybrano ucznia: " + chosen_student.name + " " + chosen_student.surname);

        System.out.println("\nWybierz numer korepetytora:");

        i = 1;

        for (Person p : tutors) {
            System.out.println(i + ". " + p.name + " " + p.surname);
            i++;
        }

        int tutor_choice = 0;
        try {
            tutor_choice = lesson_input.nextInt() - 1;
        } catch (InputMismatchException e) {
            System.out.println("Podano dane w zlym formacie");
            Add_lesson();
        }

        Person chosen_tutor = tutors.get(tutor_choice);

        new_lesson.tutor_uuid = chosen_tutor.uuid;
        System.out.println("Wybrano korepetytora: " + chosen_tutor.name + " " + chosen_tutor.surname);

        if (!Objects.equals(rate_choice, "tak")) {
            new_lesson.cost = new_lesson.duration * rate;
        } else {
            new_lesson.cost = new_lesson.duration * chosen_student.hour_rate;
        }

        new_lesson.Print_Lesson_Full(chosen_student, chosen_tutor);

        lesson_input.reset();
        System.out.print("\nCo teraz?\n1. Zapisz lekcje do bazy\n2. Utworz jeszcze raz\n3. Powrot do menu");

        try {
            int choice = lesson_input.nextInt();

            switch (choice) {
                case 1 -> save_lesson(new_lesson);
                case 2 -> Add_lesson();
                case 3 -> main_menu();
                default -> {
                    System.out.println("Nieprawidlowy wybor");
                    Add_lesson();
                }
            }
        } catch (Exception e) {
            System.out.println("Nieprawidlowy typ danych");
        }
    }

    private static void save_lesson(Lesson new_lesson) {

        File lesson_file = new File("lesson_db.csv");

        try {
            FileWriter lesson_writer = new FileWriter(lesson_file, true);
            BufferedWriter lesson_buffered = new BufferedWriter(lesson_writer);
            PrintWriter lesson_printer = new PrintWriter(lesson_buffered);

            lesson_printer.println(new_lesson.lesson_id + "," + new_lesson.lesson_date + ","
                    + new_lesson.start_time + "," + new_lesson.student_uuid + "," +
                    new_lesson.tutor_uuid + "," + new_lesson.duration + "," + new_lesson.cost);

            lesson_printer.flush();
            lesson_printer.close();

            System.out.println("Lekcja " + new_lesson.lesson_id + " zostala dodana do bazy");

        } catch (IOException e) {
            System.out.println("Operacja zakonczona niepowodzeniem!");
        }

        main_menu();
    }

    private static void get_lessons_from_file(ArrayList<Lesson> semester) {

        File lesson_file = new File("lesson_db.csv");
        String line = "";

        try {
            BufferedReader lesson_BFreader = new BufferedReader(new FileReader(lesson_file));

            lesson_BFreader.readLine();

            while ((line = lesson_BFreader.readLine()) != null) {
                String[] values = line.split(",");

                String StrLessonId = values[0];
                String StrDate = values[1];
                String StrHour = values[2];
                String StrStudentId = values[3];
                String StrTutorId = values[4];
                String StrDuration = values[5];
                String StrCost = values[6];

                UUID ConvLessonId = UUID.fromString(StrLessonId);
                UUID ConvStudentId = UUID.fromString(StrStudentId);
                UUID ConvTutorId = UUID.fromString(StrTutorId);
                float ConvDuration = Float.parseFloat(StrDuration);
                float ConvCost = Float.parseFloat(StrCost);


                Lesson lesson_csv = new Lesson(ConvLessonId,StrDate,StrHour,
                        ConvStudentId,ConvTutorId,ConvDuration,ConvCost);

                semester.add(lesson_csv);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<Lesson, ArrayList<Person>> lesson_print_menu(boolean print){

        ArrayList<Lesson> semester = new ArrayList<>();
        get_lessons_from_file(semester);

        ArrayList<Person> lesson_list_people = new ArrayList<>();
        get_people_from_file(lesson_list_people);

        ArrayList<Person> students = new ArrayList<>();
        ArrayList<Person> tutors = new ArrayList<>();

        for (Person p : lesson_list_people) {
            switch (p.status) {
                case 1 -> students.add(p);
                case 3, 4 -> tutors.add(p);
            }
        }

        HashMap<Lesson, ArrayList<Person>> Lesson_Match_Map = new HashMap<>();

        for(Lesson L : semester){

            ArrayList<Person> Student_Tutor_Match = new ArrayList<>();

            for(Person s : students){
                if(Objects.equals(L.student_uuid, s.uuid)){
                    Student_Tutor_Match.add(s);
                }
            }
            for(Person t : tutors){
                if(Objects.equals(L.tutor_uuid, t.uuid)){
                    Student_Tutor_Match.add(t);
                }
            }

            if(!Student_Tutor_Match.isEmpty()){
                Lesson_Match_Map.put(L,Student_Tutor_Match);
            }
        }

        if(print) {

            Scanner lesson_list_input = new Scanner(System.in);

            System.out.println("""
                    Jak posortowac lekcje?
                    1. Wszystkie
                    2. Wszytskie z danego miesiaca
                    3. Wszystkie lekcje ucznia
                    4. Lekcje ucznia z miesiaca
                    5. Wszystkie lekcje korepetytora
                    6. Lekcje korepetytora z miesiaca""");
            int choice = lesson_list_input.nextInt();

            master_lesson_sorter_printer(Lesson_Match_Map, choice, true);

            main_menu();
        }

        return Lesson_Match_Map;
    }

    public static HashMap<Lesson, ArrayList<Person>> master_lesson_sorter_printer
            (HashMap<Lesson, ArrayList<Person>> Match_Map,int sort_method,boolean print) {

        int chosen_month = 0;
        Person chosen_student = null;
        Person chosen_tutor = null;
        HashMap<Lesson, ArrayList<Person>> Filtered_Map = new HashMap<>();

        switch(sort_method){
            case 1 -> {
                if(print) {
                    System.out.println("Pelna lista wszystkich lekcji");
                }
            }
            case 2 -> {
                chosen_month = month_select();
                String nice_month = Decorations.decor_month(chosen_month);
                if(print) {
                    System.out.println("Wszytskie lekcje w miesiacu " + nice_month);
                }
            }
            case 3 -> {
                chosen_student = student_select();
                if (print) {
                    System.out.println("Wszystkie lekcje ucznia " + chosen_student.name);
                }
            }
            case 4 -> {
                chosen_student = student_select();
                chosen_month = month_select();
                String nice_month = Decorations.decor_month(chosen_month);

                if (print) {
                    System.out.println("Wszytskie lekcje ucznia " + chosen_student.name + " w miesiacu " + nice_month);
                }
            }
            case 5 -> {
                chosen_tutor = tutor_select();
                if (print) {
                    System.out.println("Wszystkie lekcje korepetytora " + chosen_tutor.name);
                }
            }
            case 6 -> {
                chosen_tutor = tutor_select();
                chosen_month = month_select();

                String nice_month = Decorations.decor_month(chosen_month);
                if (print) {
                    System.out.println("Wszytskie lekcje korepetytora " + chosen_tutor.name + " w miesiacu " + nice_month);
                }
            }
        }

        for (HashMap.Entry<Lesson, ArrayList<Person>> entry : Match_Map.entrySet()) {

            try {
                Lesson A = entry.getKey();
                ArrayList<Person> Abba = entry.getValue();
                Person S = Abba.get(0);
                Person T = Abba.get(1);

                switch(sort_method){
                    case 1 -> {
                        Filtered_Map.put(A,Abba);
                        if (print) {
                            A.Print_Lesson_Full(S, T);
                        }
                    }
                    case 2 -> {
                        if(Objects.equals(A.getMonth(A.lesson_date),chosen_month)) {
                            Filtered_Map.put(A, Abba);
                            if (print) {
                                A.Print_Lesson_Full(S, T);
                            }
                        }
                    }
                    case 3 -> {
                        if(Objects.equals(A.student_uuid, chosen_student.uuid)){
                            Filtered_Map.put(A, Abba);
                            if (print) {
                                A.Print_Lesson_Full(S, T);
                            }
                        }
                    }
                    case 4 -> {
                        if(Objects.equals(A.student_uuid, chosen_student.uuid) && Objects.equals(A.getMonth(A.lesson_date),chosen_month)){
                            Filtered_Map.put(A, Abba);
                            if (print) {
                                A.Print_Lesson_Full(S, T);
                            }
                        }
                    }
                    case 5 -> {
                        if (Objects.equals(A.tutor_uuid, chosen_tutor.uuid)) {
                            Filtered_Map.put(A, Abba);
                            if (print) {
                                A.Print_Lesson_Full(S, T);
                            }
                        }
                    }
                    case 6 -> {
                        if(Objects.equals(A.tutor_uuid, chosen_tutor.uuid) && Objects.equals(A.getMonth(A.lesson_date),chosen_month)){
                            Filtered_Map.put(A, Abba);
                            if (print) {
                                A.Print_Lesson_Full(S, T);
                            }
                        }
                    }

                }

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Nie da rady");
            }
        }

        if (print){
            System.out.println("----------------------------------");
        }

        return Filtered_Map;
    }

    // selection/filter methods
    public static Person tutor_select() {
        ArrayList<Person> people = new ArrayList<>();
        ArrayList<Person> tutors = new ArrayList<>();

        get_people_from_file(people);

        for(Person p : people){
            if(p.status == 3 || p.status == 4){
                tutors.add(p);
            }
        }

        int i = 1;

        System.out.println("\nWybierz korepetytora:");

        for(Person t : tutors){
            System.out.println(i + ". " + t.name + " " + t.surname);
            i++;
        }

        Scanner lesson_month_input = new Scanner(System.in);
        int chosen_student_index = lesson_month_input.nextInt() - 1 ;

        return tutors.get(chosen_student_index);
    }

    public static Person student_select() {
        ArrayList<Person> people = new ArrayList<>();
        ArrayList<Person> students = new ArrayList<>();

        get_people_from_file(people);

        for(Person p : people){
            if(p.status == 1){
                students.add(p);
            }
        }

        int i = 1;

        System.out.println("\nWybierz ucznia:");

        for(Person p : students){
            System.out.println(i + ". " + p.name + " " + p.surname);
            i++;
        }

        Scanner lesson_month_input = new Scanner(System.in);
        int chosen_student_index = lesson_month_input.nextInt() - 1 ;

        return students.get(chosen_student_index);
    }

    public static Person parent_select() {
        ArrayList<Person> people = new ArrayList<>();
        ArrayList<Person> parents = new ArrayList<>();

        get_people_from_file(people);

        for(Person p : people){
            if(p.status == 2){
                parents.add(p);
            }
        }

        int i = 1;

        System.out.println("Wybierz rodzica:");

        for(Person p : parents){
            System.out.println(i + ". " + p.name + " " + p.surname);
            i++;
        }

        Scanner choice = new Scanner(System.in);
        int chosen_parent_index = choice.nextInt() - 1 ;

        return parents.get(chosen_parent_index);
    }

    public static Person admin_select() {
        ArrayList<Person> people = new ArrayList<>();
        ArrayList<Person> admins = new ArrayList<>();

        get_people_from_file(people);

        for(Person p : people){
            if(p.status == 4){
                admins.add(p);
            }
        }

        int i = 1;

        System.out.println("Wybierz adminstatora:");

        for(Person p : admins){
            System.out.println(i + ". " + p.name + " " + p.surname);
            i++;
        }

        Scanner lesson_month_input = new Scanner(System.in);
        int chosen_admin_index = lesson_month_input.nextInt() - 1 ;

        return admins.get(chosen_admin_index);
    }

    public static int month_select() {

        Decorations.months();

        Scanner lesson_month_input = new Scanner(System.in);

        return lesson_month_input.nextInt();
    }

    // invoice methods
    public static void Add_Invoice() {

        Invoice new_invoice = new Invoice();
        Scanner invoice_input = new Scanner(System.in);
        int sort_method = 0;
        int a = 0;
        int b = 0;

        Person Payer = null;
        Person Student = null;
        Person Tutor = null;
        Lesson A = null;

        ArrayList<Person> people = new ArrayList<>();
        ArrayList<Person> students = new ArrayList<>();
        ArrayList<Person> parents = new ArrayList<>();
        ArrayList<Person> tutors = new ArrayList<>();
        ArrayList<Person> admins = new ArrayList<>();

        get_people_from_file(people);

        HashMap<Lesson, ArrayList<Person>> Unfiltered_Lessons = lesson_print_menu(false);
        HashMap<Lesson, ArrayList<Person>> Filtered_Lessons = new HashMap<>();

        new_invoice.invoice_id = UUID.randomUUID();

        System.out.println("Podaj date wystawienia faktury w formacie (dd.mm.yy)");
        new_invoice.issue_date = invoice_input.nextLine();

        Person Master = admin_select();

        System.out.println("""
                Wybierz rodzaj faktury:
                1. Faktura dla rodzica
                2. Faktura dla ucznia
                3. Wyplata korepetytora""");

        int type_choice = invoice_input.nextInt();
        new_invoice.invoice_type = type_choice;

        switch (type_choice) {
            case 1 -> {
                Payer = parent_select();
                a = 4;
                b = 3;
            }
            case 2 -> {
                a = 4;
                b = 3;
            }
            case 3 -> {
                a = 6;
                b = 5;
            }
        }

        System.out.println("""
                Wybierz usluge do faktury:
                1. Platnosc za zajecia w danym miesiacu
                2. Platnosc za zajecia ogolna
                3. Usluga dodatkowa""");

        int service_choice = invoice_input.nextInt();
        new_invoice.service_type = service_choice;

        switch (service_choice) {
            case 1 -> {
                Filtered_Lessons = master_lesson_sorter_printer(Unfiltered_Lessons, a, false);

                if(type_choice == 1 || type_choice == 2) {
                    new_invoice.service = "Oplata za korepetycje w miesiacu";
                }
                else{
                    new_invoice.service = "Wyplata dla korepetytora za miesiac";
                }
            }
            case 2 -> {
                Filtered_Lessons = master_lesson_sorter_printer(Unfiltered_Lessons, b, false);
                if(type_choice == 1 || type_choice == 2) {
                    new_invoice.service = "Oplata za korepetycje w zestawieniu ogolnym";
                }
                else{
                    new_invoice.service = "Wyplata dla korepetytora w zestawieniu ogolnym";
                }

            }
            case 3 -> {
                if(type_choice == 1 || type_choice == 2) {
                    Student = student_select();
                    System.out.println("Podaj usluge:");
                    new_invoice.service = invoice_input.nextLine();
                    new_invoice.service = invoice_input.nextLine();
                    System.out.println("Podaj koszt uslugi:");
                }
                else{
                    Tutor = tutor_select();
                    System.out.println("Podaj przyczyne doplaty:");
                    new_invoice.service = invoice_input.nextLine();
                    new_invoice.service = invoice_input.nextLine();
                    System.out.println("Podaj koszt wyplaty:");
                }
                new_invoice.pay_amount = invoice_input.nextFloat();
            }
        }

        new_invoice.int_lesson_count = Filtered_Lessons.size();

        for (HashMap.Entry<Lesson, ArrayList<Person>> entry : Filtered_Lessons.entrySet()) {
            A = entry.getKey();
            ArrayList<Person> Abba = entry.getValue();
            Student = Abba.get(0);
            Tutor = Abba.get(1);
            new_invoice.pay_amount += A.cost;
        }

        if(type_choice == 2){
            Payer = Student;
        }

        if (A != null) {
            new_invoice.int_lesson_month = A.getMonth(A.lesson_date);
        }
        if(service_choice == 2 || service_choice == 3){
            new_invoice.int_lesson_month = 0;
        }

        if (Payer != null) {
            new_invoice.payer_id = Payer.uuid;
        }
        if (Student != null) {
            new_invoice.student_id = Student.uuid;
        }
        if (Tutor != null) {
            new_invoice.tutor_id = Tutor.uuid;
        }

        new_invoice.master_id = Master.uuid;


        try {
            new_invoice.Print_Invoice(Payer, Student, Tutor, Master);
        }
        catch(Exception e){
            Decorations.error();
            main_menu();
        }

        invoice_input.reset();
        System.out.print("\nCo teraz?\n1. Zapisz fakture do bazy\n2. Utworz jeszcze raz\n3. Powrot do menu\n");
        int choice = invoice_input.nextInt();

        switch (choice) {
            case 1 -> save_invoice(new_invoice);
            case 2 -> Add_Invoice();
            case 3 -> main_menu();
        }
    }

    public static void save_invoice(Invoice new_invoice) {

        File lesson_file = new File("invoice_db.csv");

        try {
            FileWriter invoice_writer = new FileWriter(lesson_file, true);
            BufferedWriter invoice_buffered = new BufferedWriter(invoice_writer);
            PrintWriter invoice_printer = new PrintWriter(invoice_buffered);

            invoice_printer.println(new_invoice.invoice_id + "," + new_invoice.issue_date + ","
                    + new_invoice.payer_id + "," + new_invoice.student_id + "," +
                    new_invoice.tutor_id + "," + new_invoice.master_id + "," + new_invoice.pay_amount +
                    "," + new_invoice.invoice_type + "," + new_invoice.service_type +
                    "," + new_invoice.service + "," + new_invoice.int_lesson_month + "," + new_invoice.int_lesson_count);

            invoice_printer.flush();
            invoice_printer.close();

            System.out.println("Faktura " + new_invoice.invoice_id + " zostala dodana do bazy");

        } catch (IOException e) {
            System.out.println("Operacja zakonczona niepowodzeniem!");
        }

        main_menu();

    }

    public static ArrayList<Invoice> get_invoice_from_file(){

        ArrayList<Invoice> payout = new ArrayList<>();
        File lesson_file = new File("invoice_db.csv");
        String line = "";

        try {
            BufferedReader invoice_BFreader = new BufferedReader(new FileReader(lesson_file));

            invoice_BFreader.readLine();

            while ((line = invoice_BFreader.readLine()) != null) {

                Invoice I = new Invoice();

                String[] values = line.split(",");

                String StrInvoiceId = values[0];
                String StrDate = values[1];
                String StrPayerId = values[2];
                String StrStudentId = values[3];
                String StrTutorId = values[4];
                String StrMasterId = values[5];
                String StrPayAmount = values[6];
                String StrInType = values[7];
                String StrServType = values[8];
                String StrService = values[9];
                String StrMonth = values[10];
                String StrCount = values[11];

                I.issue_date = StrDate;
                I.service = StrService;

                I.invoice_id = UUID.fromString(StrInvoiceId);
                I.master_id = UUID.fromString(StrMasterId);

                if (!Objects.equals(StrPayerId, "null")) {
                    I.payer_id = UUID.fromString(StrPayerId);
                }
                if (!Objects.equals(StrStudentId, "null")) {
                    I.student_id = UUID.fromString(StrStudentId);
                }
                if (!Objects.equals(StrTutorId, "null")) {
                    I.tutor_id = UUID.fromString(StrTutorId);
                }

                I.pay_amount = Float.parseFloat(StrPayAmount);
                I.invoice_type = Integer.parseInt(StrInType);
                I.service_type = Integer.parseInt(StrServType);
                I.int_lesson_month = Integer.parseInt(StrMonth);
                I.int_lesson_count = Integer.parseInt(StrCount);
                payout.add(I);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return payout;
    }

    public static void invoice_printer() {

        int a = 0;
        ArrayList<Invoice> inv_print = new ArrayList<>();
        ArrayList<Invoice> inv_all = get_invoice_from_file();
        ArrayList<Person> people = new ArrayList<>();
        get_people_from_file(people);

        Person Payer = new Person();
        Person Student = new Person();
        Person Tutor = new Person();
        Person Master = new Person();

        Scanner lesson_list_input = new Scanner(System.in);

        System.out.println("""
                Jak posortowac faktury?
                1. Wszystkie
                2. Wszytskie z danego miesiaca""");

        int choice = lesson_list_input.nextInt();

        if(choice == 2){
            a = month_select();
        }

        for (Invoice I : inv_all) {

            for (Person P : people) {
                if (Objects.equals(I.payer_id, P.uuid)) {
                    Payer = P;
                }
                if (Objects.equals(I.student_id, P.uuid)) {
                    Student = P;
                }
                if (Objects.equals(I.tutor_id, P.uuid)) {
                    Tutor = P;
                }
                if (Objects.equals(I.master_id, P.uuid)) {
                    Master = P;
                }
            }

            if (choice == 2) {

                if (I.getMonth(I.issue_date) == a) {
                    I.Print_Invoice(Payer, Student, Tutor, Master);
                }
            } else {
                I.Print_Invoice(Payer, Student, Tutor, Master);
            }
        }
    }
}
