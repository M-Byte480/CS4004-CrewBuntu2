import java.time.LocalDateTime;

public class EBookRequest {
    private LocalDateTime timeOfRequest;
    private Book book;
    private Journal journal;
    private boolean isCompleted;

    public EBookRequest(Book book){
        this.timeOfRequest = LocalDateTime.now();
        this.book = book;
        this.isCompleted = false;
    }

    public EBookRequest(Journal journal){
        this.timeOfRequest = LocalDateTime.now();
        this.journal = journal;
        this.isCompleted = false;
    }
}