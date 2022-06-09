package algorytmy.edu;

import java.util.UUID;

public class Invoice {

    public UUID invoice_id;
    public UUID payer_id;
    public UUID student_id;
    public UUID tutor_id;
    public UUID master_id;
    public String issue_date;
    public float pay_amount;
    public int invoice_type;
    public int service_type;
    public int int_month;

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
        return int_month;
    }

    public void setService(int service) {
        this.int_month = service;
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

    public void Print_Invoice(Person Parent, Person Student, Person Tutor, Person Master, int lesson_count){

        String month = Decorations.decor_month(int_month);

        System.out.println("----- Start Fw: " + invoice_id + " ---------------");
        System.out.println("Faktura wystawiona dnia: " + issue_date);
        switch(invoice_type){
            case 1 -> {
                System.out.println("Dane platnika:");
                Parent.Person_Printer_Basic();

                System.out.println("\nDane odbiorcy:");
                Master.Person_Printer_Basic();
            }
            case 2 -> {
                System.out.println("Dane platnika:");
                Student.Person_Printer_Basic();

                System.out.println("\nDane odbiorcy:");
                Master.Person_Printer_Basic();
            }
            case 3 -> {
                System.out.println("Wyplata dla:");
                Tutor.Person_Printer_Basic();

                System.out.println("\nOplacona przez:");
                Master.Person_Printer_Basic();
            }
        }

        switch(service_type){
            case 1 -> {
                System.out.println("\nPrzedmiotem platnosci sa zajecia ucznia " + Student.name + " " + Student.surname);
                System.out.println("W miesiacu " + month + " odbylo sie lacznie " + lesson_count + " zajec");
            }
            case 2 -> {
                System.out.println("\nPrzedmiotem platnosci sa zajecia ucznia " + Student.name + " " + Student.surname);
            }
            case 3 -> System.out.println("\nPrzedmiotem platnosci jest usluga wykonana dla " + Student.name + " " + Student.surname);
        }

        System.out.println("Faktura jest wystawiona na kwote " + pay_amount);










    }
}
