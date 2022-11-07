import java.util.ArrayList;

public class Subscription{
    private String name;
    private ArrayList<Book> bookList;

    public Subscription(String name, ArrayList<Book> bookList){
        this.name = name;
        this.bookList = bookList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }
}
