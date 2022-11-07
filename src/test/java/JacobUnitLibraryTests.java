import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author 21304149
 */
public class JacobUnitLibraryTests {
    
    public JacobUnitLibraryTests() {
    }
    
    @Test
    @DisplayName("Check for duplicates")
    public void testDupes(){
        University ul = new University();
        Library l1 = new Library();
        ul.newLibrary(l1);
        l1.getNewBook("The Bible");
        Library l2 = new Library();
        ul.newLibrary(l2);
        assertFalse(ul.getBookForLib(l2, "The Bible"));
    }
    
    @Test
    @DisplayName("Check for journal duplicates")
    public void testSubscribe(){
        University ul = new University();
        Library l1 = new Library();
        ul.newLibrary(l1);
        ul.subscribe(l1, "Christianity Monthly");
        Library l2 = new Library();
        ul.newLibrary(l2);
        assertFalse(ul.subscribe(l2, "Christianity Monthly"));
    }
    
    @Test
    @DisplayName("Check if a book's in another University")
    public void testOtherUni(){
        University ul = new University();
        University uwon = new University();
        uwon.joinUni(ul);
        Library l1 = new Library();
        ul.newLibrary(l1);
        l1.getNewBook("Computer Science for Dummies");
        Library l2 = new Library();
        uwon.newLibrary(l2);
        assertFalse(uwon.getBookForLib(l2, "Computer Science for Dummies"));
    }
    
    @Test
    @DisplayName("Display previous owners")
    public void testShow(){
        Library l1 = new Library();
        l1.getNewBook("The Bible");
        l1.borrow("The Bible", "Jacob Beck", "23-03-23", "30-04-23");
        assertFalse(l1.getBorrowers("The Bible").equals(""));
    }

    @Test
    void testBook(){
        assertEquals(213, 233);
    }
}
