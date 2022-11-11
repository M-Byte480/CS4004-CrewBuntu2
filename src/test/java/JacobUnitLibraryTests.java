import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

//import org.mockito.Mockito.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;
//import org.junit.Assert.*;

/**
 * @author 21308128
 */
@ExtendWith(MockitoExtension.class)
public class JacobUnitLibraryTests {

    public JacobUnitLibraryTests() {
    }

    @Test
    @DisplayName("Check for duplicates")
    public void testDupes() {
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
    public void testSubscribe() {
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
    public void testOtherUni() {
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
    public void testShow() {
        Library l1 = new Library();
        l1.getNewBook("The Bible");
        l1.borrow("The Bible", "Jacob Beck", "23-03-23", "30-04-23");
        assertNotEquals("", l1.getBorrowers("The Bible"));
    }

    // ===================================== Breny =========================================== //
    //Complaint 5
    //Subscription to journals of marginal interest to the university, which could be
    //accessed in other universities with which UWON has an agreement.
    @Test
    @DisplayName("Check to see if subscription is available in UWON")
    public void subscriptionsAcrossUWON() {
        University u1 = new University();
        University u2 = new University();
        u1.joinUni(u2);
        ArrayList<Book> DW = new ArrayList<>();
        Subscription davidWalliams = new Subscription("Gangster Granny", DW);
        Library l1 = new Library();
        Library l2 = new Library();
        u2.newLibrary(l1);
        u2.newLibrary(l2);
        l1.addASubscription(davidWalliams);
        assertTrue(u2.getSubscriptionForLib(l2, davidWalliams));
    }
    //Complaint 6
    //Acquisition of books or proceedings of marginal interest to the university, which
    //could be borrowed from other universities with which UWON has an agreement.
    @Test
    @DisplayName("Check to see if subscription is available in UWON")
    public void sendingCompSciBooksFromLSADToUL () {
        University UL = new University();
        University LSAD = new University();
        LSAD.joinUni(UL);



    }
    //Complaint 7
    //Inaccuracy of card indexes, e.g. a book is stated as being available whereas it is not
    //found at the appropriate place on the shelves.
    @Test
    @DisplayName("Problem 7 : Innacuracy of book being on specified shelf")
    public void bookOnShelFinder(){
        University BrenysWRLD = new University();
        Library BookRoom = new Library();
        BrenysWRLD.newLibrary(BookRoom);

        ArrayList<Book> AnimeArraylist = new ArrayList<>() ;
        Book Yugioh = new Book("Yu-gi-oh","Kazuki Takahashi");
        AnimeArraylist.add(Yugioh);
        Shelf Anime = new Shelf("Amine", AnimeArraylist);
        BookRoom.newShelf(Anime);

        ArrayList<Book> OverwatchArraylist = new ArrayList<>() ;
        Book Monke = new Book("Monke","Winston");
        OverwatchArraylist.add(Monke);
        Shelf Overwatch = new Shelf("Overwatch", OverwatchArraylist);
        BookRoom.newShelf(Overwatch);

        BookRoom.addNewBooksToSystem(Monke,Overwatch);

        Book bobo = new Book("Monke","Winston");
        BookRoom.addNewBooksToSystem(bobo,Overwatch);
        BookRoom.newShelf(Anime);
        BookRoom.newShelf(Overwatch);
        BrenysWRLD.newLibrary(BookRoom);

        assertTrue(BrenysWRLD.checkUniversityForBook(bobo));
    }
    // ===================================== End of Breny =========================================== //


    // ===================================== Milan =========================================== //
    // Problem 8:
    // ●    Inaccuracy of card indexes, e.g. a book is stated as being available whereas it is not
    //      found at the appropriate place on the shelves.

    // Approach:
    // Since the book is not found in the correct place on the shelf, suggests to me that there are
    // two separate naming and sorting convention hosted.
    // To simplify this we will standardize it and will do it by ascending alphabetical order.
    @DisplayName("Problem 8: \n Sorting Algorithm")
    @Test
    public void test() {

    }

    // Problem 9:
    // ●    Bibliographical search restricted to library opening hours. Slow, tedious
    //      bibliographical search due to manipulation of card indexes
    //
    //
    @DisplayName("Problem 9: \n Search Speed Test")
    @ParameterizedTest
    @ValueSource(strings = {"apple", "bee", "zebra", "donkey", "giraffe", "lion", "orangutan", "monkey", "mongoose", "butterfly"})
    @Timeout(1)
    public void testSpeedOfTwoSearches(String animal) throws FileNotFoundException {
        Book animals = new Book();
        animals.addBibliography(animal);
        Library uwon = new Library();

        // Speed of Binary
        Instant start = Instant.now();
        uwon.binarySearch(animals.getBibliography().get(0));
        Instant end = Instant.now();

        // Print speed of Binary and calculate it
        Duration binarySearchTime = Duration.between(start, end);
        System.out.println("Binary search took: " + binarySearchTime.toNanos() + " nanoseconds");

        // Speed of Linear Search
        start = Instant.now();
        uwon.linearSearch(animals.getBibliography().get(0));
        end = Instant.now();

        // Print speed of
        Duration linearSearchTime = Duration.between(start, end);
        System.out.println("Linear search took: " + linearSearchTime.toNanos() + " nanoseconds");

        assertTrue(binarySearchTime.toNanos() <= linearSearchTime.toNanos());
    }
    // ===================================== End of Milan =========================================== //
}
