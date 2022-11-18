import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class Book {
    private String name;
    private String author;
    private Date published;
    private ArrayList<String> genres;
    private ArrayList<String> departments;
    private ArrayList<String> bibliography;
    private boolean isAvailable;
    private ArrayList<Student> loanees;
    private boolean isBeingBound = false;
    private boolean isDamaged;

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
        this.genres = new ArrayList<>();
        this.published = new Date();
        this.isAvailable = true;
        this.bibliography = new ArrayList<>();
        this.departments = new ArrayList<>();
        this.loanees = new ArrayList<Student>();
    }


    public Book(){
        this.name = new String();
        this.author = new String();
        this.genres = new ArrayList<>();
        this.published = new Date();
        this.isAvailable = true;
        this.bibliography = new ArrayList<>();
        this.departments = new ArrayList<>();
        this.loanees = new ArrayList<Student>();
    }

    public ArrayList<String> getBibliographies() {
        return bibliography;
    }


    public void addBibliography(String word){
        bibliography.add(word);
    }

    public void setIsAvailable(boolean availability){
        this.isAvailable = availability;
    }

    public void beingBound(){
        this.isBeingBound = true;
        this.requestEBookVersion();
    }

    public void requestEBookVersion(){
        EBookRequest request = new EBookRequest(this);
        Library.getRequestsForEBooks().add(request);
    }

    public ArrayList<Student> getLoanees() {
        return loanees;
    }

    public Student lastStudentBeforeDamage(){
        return loanees.get(loanees.size() - 1);
    }

    public Library getShelfWhereStored() {
        return new Library();
    }

    public void setShelfWhereStored(Shelf s) {
    }

    public String getBibliography() {
        return null;
    }
}

