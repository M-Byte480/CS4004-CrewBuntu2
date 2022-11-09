public class Reminder {
    private int reminderNumber = 1;
    private Student loanee;
    private Book book;
    private boolean hasBeenReturned;
    private int reminderInterval = 1;
    private int overdueDays;

    public Reminder(Student loanee, Book book){
        this.loanee = loanee;
        this.book = book;
        this.hasBeenReturned = false;
        this.overdueDays = 1;
    }

    public void bookReturned(){
        this.hasBeenReturned = true;
    }



    public void periodicReminder(Loan loan){
        if (!hasBeenReturned){
            Library.sendReminder(loan.getLoanee());
            overdueDays++;
        }
    }
}
