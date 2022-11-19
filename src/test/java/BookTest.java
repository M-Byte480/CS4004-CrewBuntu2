import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test

    public void testForLastStudentToBorrow(){
        Book book = new Book("Book", "Author");
        Student student = new Student("Student1");
        Student student2 = new Student("Student2");

        Loan loan1 = new Loan(student, book, LocalDate.now().minusDays(1));
        Loan loan2 = new Loan(student2, book, LocalDate.now());

        assertTrue(book.lastStudentBeforeDamage() == student2);

    }
}