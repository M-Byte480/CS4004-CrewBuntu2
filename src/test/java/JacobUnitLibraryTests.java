import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
//import org.mockito.Mockito.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;
//import org.junit.Assert.*;

/**
 *
 * @author 21304149
 */
@ExtendWith(MockitoExtension.class)
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
        assertNotEquals("", l1.getBorrowers("The Bible"));
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
    @ParameterizedTest
    @ValueSource(strings = {"apple", "bee", "zebra", "donkey", "giraffe", "lion", "orangutan", "monkey", "mongoose", "butterfly"})
    @Timeout(1)
    @DisplayName("Checking for Linear Search")
    public void testSpeedOfTwoSearches(String animal) throws FileNotFoundException {
        Book animals = new Book();
        animals.addBibliography(animal);
        Library uwon = new Library();

        // Speed of Binary
        Instant start = Instant.now();
        uwon.binarySearch(animals.getBibliography().get(0));
        Instant end = Instant.now();

        // Print speed of Binary and calculate it
        Duration binarySearchTime  = Duration.between(start, end);
        System.out.println("Binary search took: " + binarySearchTime.toMillis() + " milliseconds");

        // Speed of Linear Search
        start = Instant.now();
        uwon.linearSearch(animals.getBibliography().get(0));
        end = Instant.now();

        // Print speed of
        Duration linearSearchTime  = Duration.between(start, end);
        System.out.println("Linear search took: " + linearSearchTime.toMillis() + " milliseconds");

        assertTrue(Library.faster(binarySearchTime, linearSearchTime));
    }
    // ===================================== End of Milan =========================================== //
}
