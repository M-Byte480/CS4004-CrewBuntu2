import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.FileNotFoundException;
//import org.mockito.Mockito.*;
//import org.junit.Assert.*;

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
    // ===================================== Milan =========================================== //
    // ●    Inaccuracy of card indexes, e.g. a book is stated as being available whereas it is not
    //      found at the appropriate place on the shelves.
    @Test
    @DisplayName("")
    public void test(){

    }
    // ●    Bibliographical search restricted to library opening hours. Slow, tedious
    //      bibliographical search due to manipulation of card indexes

    @Test
    @Timeout(10)
    @DisplayName("Checking for Linear Search")
    public void testSpeedOfLinearSearch(){
        Book zealand = new Book();
        zealand.addBibliography("zealand");
        Library uwon = new Library();
        assertTrue(uwon.linearSearch(zealand.getBibliography().get(0)));
    }
    @Test
    @Timeout(2)
    @DisplayName("Checking for Binary Search")
    public void testSpeedOfBinarySearch() throws FileNotFoundException {
        Book zealand = new Book();
        zealand.addBibliography("zealand");
        Library uwon = new Library();
        assertTrue(uwon.binarySearch(zealand.getBibliography().get(0)));
    }
    // ===================================== End =========================================== //
}
