import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Library {
    private ArrayList<String> books;
    private ArrayList<String> journalSubs;
    private ArrayList<Student> borrowers;
    private static ArrayList<String> bibliography;
    private ArrayList<Reminder> reminders;
    private ArrayList<Loan> loans;

    static {
        try {
            bibliography = populate();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Library(){
        books = new ArrayList<String>();
        journalSubs = new ArrayList<String>();
        borrowers = new ArrayList<Student>();
    }
    
    public void getNewBook(String book){
        books.add(book);
    }

    public void subscribe(String journal){
        journalSubs.add(journal);
    }
    
    public ArrayList<String> getJournalSubs() {
        return journalSubs;
    }
    
    public boolean inOtherLibs(String book, Library l){
        if(l.getBooks().contains(book)){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean inOtherLibsJournal(String journal, Library l){
        if(l.getJournalSubs().contains(journal)){
            return true;
        }else{
            return false;
        }
    }
    
    public ArrayList<String> getBooks(){
        return books;
    }
    
    public void borrow(String book, String studentName, String borrowDate, String dueDate){
        Student borrower = new Student(book, studentName, borrowDate, dueDate);
        borrowers.add(borrower);
    }
    
    public String getBorrowers(String book){
        String returnMe = "";
        for(int i = 0; i < borrowers.size(); i++){
            if(borrowers.get(i).getBorrowedBook().equals(book)){
                returnMe += borrowers.get(i).toString();
            }             
        }
        return returnMe;
    }

    public boolean linearSearch(String name){
        for (String type :
                bibliography) {
            if (name.equals(type)){
                return true;
            }
        }
        return false;
    }

    public boolean binarySearch(String name) throws FileNotFoundException {
        return -1 < Collections.binarySearch(bibliography, name);
    }

    private static ArrayList<String> populate() throws FileNotFoundException {
        File file = new File("src/main/java/wordlist.10000");
        Scanner writeBibliography = new Scanner(file);
        ArrayList<String> words = new ArrayList<>();
        while(writeBibliography.hasNextLine()){
            words.add(writeBibliography.nextLine());
        }
        return words;
    }



    public ArrayList<String> getBibliography(){
        return bibliography;
    }

    public void addLoan(Loan loan){
        loans.add(new Loan(loan.getLoanee(), loan.getBook(), loan.getStartDate()));
    }


    public void isBookDue(Loan loan){
        if ((loan.getDueDate().compareTo(LocalDate.now()) > 0)){
            reminders.add(new Reminder(loan.getLoanee(),loan.getBook()));
        }
    }

    public static void sendReminder(Student loanee){
        // Need to send a Reminder object that will periodically be sent to the student's email

    }


}
