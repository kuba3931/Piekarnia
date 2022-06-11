package algorytmy.edu;

import java.util.UUID;

public class Invoice {

    public UUID invoice_id;
    public String issue_date;

    public UUID payer_id;
    public UUID student_id;
    public UUID tutor_id;
    public UUID master_id;

    public float pay_amount;
    public int invoice_type;
    public int service_type;
    public String service;
    public int int_lesson_month;
    public int int_lesson_count;

    public int getInt_lesson_count() {
        return int_lesson_count;
    }

    public void setInt_lesson_count(int int_lesson_count) {
        this.int_lesson_count = int_lesson_count;
    }

    public int getInt_lesson_month() {
        return int_lesson_month;
    }

    public void setInt_lesson_month(int int_lesson_month) {
        this.int_lesson_month = int_lesson_month;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getService_type() {
        return service_type;
    }

    public void setService_type(int service_type) {
        this.service_type = service_type;
    }

    public int getInvoice_type() {
        return invoice_type;
    }

    public void setInvoice_type(int invoice_type) {
        this.invoice_type = invoice_type;
    }

    public int getService() {
        return int_lesson_month;
    }

    public void setService(int service) {
        this.int_lesson_month = service;
    }

    public UUID getMaster_id() {
        return master_id;
    }

    public void setMaster_id(UUID master_id) {
        this.master_id = master_id;
    }

    public float getPay_amount() {
        return pay_amount;
    }

    public void setPay_amount(float pay_amount) {
        this.pay_amount = pay_amount;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public UUID getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(UUID invoice_id) {
        this.invoice_id = invoice_id;
    }

    public void RandInvoice_id(UUID invoice_id) {
        invoice_id = UUID.randomUUID();
    }

    public UUID getTutor_id() {
        return tutor_id;
    }

    public void setTutor_id(UUID tutor_id) {
        this.tutor_id = tutor_id;
    }

    public UUID getStudent_id() {
        return student_id;
    }

    public void setStudent_id(UUID student_id) {
        this.student_id = student_id;
    }

    public UUID getPayer_id() {
        return payer_id;
    }

    public void setPayer_id(UUID payer_id) {
        this.payer_id = payer_id;
    }

    public int getMonth(String date) {
        String[] date_str_list = date.split("\\.");
        return Integer.parseInt(date_str_list[1]);
    }

    public void Print_Invoice(Person Parent, Person Student, Person Tutor, Person Master) {

        String month = Decorations.decor_month(int_lesson_month);

        System.out.println("----- Start Fw: " + invoice_id + " -----");
        System.out.println("Faktura wystawiona dnia: " + issue_date);

        switch (invoice_type) {
            case 1 -> {
                System.out.println("\nDane platnika:");
                Parent.Person_Printer_Basic();

                System.out.println("\nDane odbiorcy:");
                System.out.println("Korepetycje Symbol Sukcesu");
                Master.Person_Printer_Basic();
            }
            case 2 -> {

                System.out.println("\nDane platnika:");
                Student.Person_Printer_Basic();

                System.out.println("\nDane odbiorcy:");
                System.out.println("Korepetycje Symbol Sukcesu");
                Master.Person_Printer_Basic();
            }
            case 3 -> {
                System.out.println("\nWyplata dla:");
                Tutor.Person_Printer_Basic();

                System.out.println("\nOplacona przez:");
                System.out.println("Korepetycje Symbol Sukcesu");
                Master.Person_Printer_Basic();
            }
        }

        switch (service_type) {
            case 1 -> {
                if (invoice_type == 1 || invoice_type == 2) {
                    System.out.println("\nPrzedmiotem platnosci sa zajecia ucznia " + Student.name + " " + Student.surname);
                    System.out.println("W miesiacu " + month + " odbylo sie lacznie " + int_lesson_count + " zajec");
                } else {
                    System.out.println("\nPrzedmiotem wyplaty sa lekcje korepetytora " + Tutor.name + " " + Tutor.surname);
                    System.out.println("W miesiacu " + month + " przeprowadzil lacznie " + int_lesson_count + " zajec");
                }
            }
            case 2 -> {
                if (invoice_type == 1 || invoice_type == 2) {
                    System.out.println("\nPrzedmiotem platnosci sa zajecia ucznia " + Student.name + " " + Student.surname);
                    System.out.println("Na moment wystawienie dokumentu odbylo sie lacznie " + int_lesson_count + " zajec");
                } else {
                    System.out.println("\nPrzedmiotem wyplaty sa lekcje korepetytora " + Tutor.name + " " + Tutor.surname);
                    System.out.println("Na moment wystawienie dokumentu przeprowadzil lacznie " + int_lesson_count + " zajec");
                }
            }
            case 3 -> {
                if (invoice_type == 1 || invoice_type == 2) {
                    System.out.println("\nPrzedmiotem platnosci jest " + service + " wykonane dla " + Student.name + " " + Student.surname);
                } else {
                    System.out.println("\nPrzedmiotem wyplaty jest " + service + " dla " + Tutor.name + " " + Tutor.surname);
                }
            }
        }

        System.out.println("Faktura jest wystawiona na kwote " + pay_amount + "\n");
        System.out.println("----- Koniec Fw. " + invoice_id + " -----\n");
    }

    public void Invoice_check_basic() {
        System.out.println("-------------------------");
        System.out.println(invoice_id);
        System.out.println(payer_id);
        System.out.println(student_id);
        System.out.println(tutor_id);
        System.out.println(master_id);
        System.out.println(issue_date);
        System.out.println(pay_amount);
        System.out.println(invoice_type);
        System.out.println(service_type);
        System.out.println(service);
        System.out.println(int_lesson_month);
        System.out.println(int_lesson_count);
    }
}