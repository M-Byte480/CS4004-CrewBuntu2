import java.time.LocalDate;

public class Loan {
    private Student loanee;
    private Book book;
    private LocalDate startDate;
    private LocalDate dueDate;
    private static int loanLength = 7;

    public Loan(Student loanee, Book book, LocalDate startDate){
        this.loanee = loanee;
        this.book = book;
        this.startDate = startDate;
        this.dueDate = startDate.plusDays(loanLength);
        book.setIsAvailable(false);
    }
}