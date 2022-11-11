import java.util.ArrayList;

public class Shelf {
    private String genre;
    private ArrayList<Book> books;

    public Shelf(String Genre, ArrayList<Book> books) {
        this.genre = Genre;
        this.books = books;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void addBookToShelf(Book b){
        books.add(b);
    }
}
