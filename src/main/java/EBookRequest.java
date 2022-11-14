import java.time.LocalDateTime;

public class EBookRequest {
    private LocalDateTime timeOfRequest;
    private Book book;
    private boolean isCompleted;

    public EBookRequest(Book book){
        this.timeOfRequest = LocalDateTime.now();
        this.book = book;
        this.isCompleted = false;
    }
}
