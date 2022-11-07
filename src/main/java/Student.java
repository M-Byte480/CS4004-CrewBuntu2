public class Student {
    private String borrowedBook;
    private String name;
    private String borrowDate;
    private String dueDate;

    public Student(String borrowedBook, String name, String borrowDate, String dueDate) {
        this.borrowedBook = borrowedBook;
        this.name = name;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    public String getBorrowedBook() {
        return borrowedBook;
    }

    public String getName() {
        return name;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    
    public String toString(){
        return getName() + " " + getBorrowDate() + " " + getDueDate();
    }
}
