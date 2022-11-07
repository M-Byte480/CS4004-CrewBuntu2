import java.util.ArrayList;
public class Library {
    private ArrayList<String> books;
    private ArrayList<String> journalSubs;
    private ArrayList<Student> borrowers;
    
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


    //if a library has a book
    public boolean inOtherLibs(String book, Library l){
        if(l.getBooks().contains(book)){
            return true;
        }else{
            return false;
        }
    }

    //if a library has a subscription to journal
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


    //Adding a Borrower of book
    public void borrow(String book, String studentName, String borrowDate, String dueDate){
        Student borrower = new Student(book, studentName, borrowDate, dueDate);
        borrowers.add(borrower);
    }


    //returning a list of borrowers who borrowed a specific book
    public String getBorrowers(String book){
        String returnMe = "";
        for(int i = 0; i < borrowers.size(); i++){
            if(borrowers.get(i).getBorrowedBook().equals(book)){
                returnMe += borrowers.get(i).toString();
            }             
        }
        return returnMe;
    }
}
