public class Student {
    private String borrowedBook;
    private String name;
    private long borrowDate;
    private long dueDate;

    public Student(String borrowedBook, String name, int daysUntilDue) {
        this.borrowedBook = borrowedBook;
        this.name = name;
        borrowDate = System.currentTimeMillis();
        dueDate = borrowDate + ((long) daysUntilDue * 24 * 60 * 60 * 1000);
    }

    public String getBorrowedBook() {
        return borrowedBook;
    }

    public String getName() {
        return name;
    }

    public long getBorrowDate() {
        return borrowDate;
    }

    public long getDueDate() {
        return dueDate;
    }

    public void increaseBorrowTime(int days) {
        borrowDate += ((long) days * 24 * 60 * 60 * 1000 + 1);
    }

    public boolean checkDue() {
        if ( borrowDate > dueDate ) {
            return true;
        }
        return false;
    }
    
    public String toString(){
        return getName() + ", " + getBorrowDate() + ", " + getDueDate();
    }
}
