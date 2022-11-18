import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.*;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.regex.Pattern;

//import org.mockito.Mockito.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
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
        Journal christianity = new Journal("Christianity Monthly", true);
        ul.subscribe(l1, christianity);
        Library l2 = new Library();
        ul.newLibrary(l2);
        assertFalse(ul.subscribe(l2, christianity));
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
    @DisplayName("Check for journal duplicates in other Universities")
    public void testOtherUniJournal(){
        University ul = new University();
        University uwon = new University();
        uwon.joinUni(ul);
        Library l1 = new Library();
        ul.newLibrary(l1);
        Journal mock = new Journal("Mockito Monthly", true);
        l1.subscribe(mock);
        Library l2 = new Library();
        uwon.newLibrary(l2);
        assertFalse(uwon.subscribe(l2, mock));
    }

    @Test
    @DisplayName("Display previous owners")
    public void testShow() {
        Library l1 = new Library();
        l1.getNewBook("The Bible");
        l1.borrow("The Bible", "Jacob Beck", 15);
        assertNotEquals("", l1.getBorrowers("The Bible"));
    }

    // ===================================== Breny =========================================== //
    //Complaint 4
    //Unavailability of journal issues while they are being bound into yearly volumes
    @Test
    @DisplayName("Check Person For Admin Permissions")
    public void sortJournalsIntoVolumes () {
         int[] subs = new int[12];
        Journal j = new Journal("J",false, 1);
        Journal f = new Journal("F", false, 2);
        Journal m = new Journal("M", false, 3);
        Journal a = new Journal("A", false, 4);
        Journal J = new Journal("J", false, 6);






    }

    //Complaint 4
    //Unavailability of journal issues while they are being bound into yearly volumes
    @Test
    @DisplayName("Check Person For Admin Permissions")
    public void sortJournalsIntoVolumes() {
        int[] subs = new int[12];
        Journal j = new Journal("J", false, 1);
        Journal f = new Journal("F", false, 2);
        Journal m = new Journal("M", false, 3);
        Journal a = new Journal("A", false, 4);
        Journal J = new Journal("J", false, 6);


    }
    //---------------------------------------Breny----------------------------------------
    //Admin Checker Complaint
    //Check wheter a member of a university has admin permissions
    @Test
    @DisplayName("Check Person For Admin Permissions")
    public void checkPrivelidges () {
        Person andy = new Person("andy" , true);
        Person tom = new Person("Tom", false);
        University ul = new University();
        ul.setAdmins(andy);
        assertTrue(ul.checkAdmins(andy));
    }
    //Complaint 5
    //Subscription to journals of marginal interest to the university, which could be
    //accessed in other universities with which UWON has an agreement.
    @Test
    @DisplayName("Check to see if subscription is available in UWON")
    public void subscriptionsAcrossUWON() {
        University u1 = new University();
        University u2 = new University();
        Library l1 = new Library();
        Library l2 = new Library();
        u2.newLibrary(l1);
        u2.newLibrary(l2);
        u1.joinUni(u2);
        ArrayList<Book> DW = new ArrayList<>();
        ArrayList<Book> D = new ArrayList<>();
        Subscription davidWalliams = new Subscription("Gangster Granny", DW);


        l1.addASubscription(davidWalliams);
        assertTrue(u2.getSubscriptionForLib(davidWalliams));
    }

    //Complaint 6
    //Acquisition of books or proceedings of marginal interest to the university, which
    //could be borrowed from other universities with which UWON has an agreement.
    @Test
    @DisplayName("Check to see if subscription is available in UWON")
    public void sendingCompSciBooksFromLSADToUL() {
        University UL = new University();
        University LSAD = new University();

        Library bugLibrary = new Library();
        Library artStudentBodyOdour = new Library();

        UL.newLibrary(bugLibrary);
        LSAD.newLibrary(artStudentBodyOdour);
        ArrayList<Book> bugBook = new ArrayList<>();
        Shelf bugShelf = new Shelf("Bugs", bugBook);
        Book Worms = new Book();
        bugShelf.addBookToShelf(Worms);

        LSAD.joinUni(UL);


        Worms = new Book("Bugs", "Breny");
        bugLibrary.addBookTOLibrary(Worms);

        boolean temp = LSAD.getBookForLib(Worms);

        assertTrue(temp);
    }
    //Complaint 7
    //Inaccuracy of card indexes, e.g. a book is stated as being available whereas it is not
    //found at the appropriate place on the shelves.
    @Test
    @DisplayName("Problem 7 : Innacuracy of book being on specified shelf")
    public void bookOnShelFinder() {
        University BrenysWRLD = new University();
        Library BookRoom = new Library();
        BrenysWRLD.newLibrary(BookRoom);

        ArrayList<Book> AnimeArraylist = new ArrayList<>();
        Book Yugioh = new Book("Yu-gi-oh", "Kazuki Takahashi");
        AnimeArraylist.add(Yugioh);
        Shelf Anime = new Shelf("Amine", AnimeArraylist);
        BookRoom.newShelf(Anime);

        ArrayList<Book> OverwatchArraylist = new ArrayList<>();
        Book Monke = new Book("Monke", "Winston");
        OverwatchArraylist.add(Monke);
        Shelf Overwatch = new Shelf("Overwatch", OverwatchArraylist);
        BookRoom.newShelf(Overwatch);

        BookRoom.addNewBooksToSystem(Monke, Overwatch);

        Book bobo = new Book("Monke", "Winston");
        BookRoom.addNewBooksToSystem(bobo, Overwatch);
        BookRoom.newShelf(Anime);
        BookRoom.newShelf(Overwatch);
        BrenysWRLD.newLibrary(BookRoom);

        assertTrue(BrenysWRLD.checkUniversityForBook(bobo));
    }
    // ===================================== End of Breny =========================================== //


    // ===================================== Milan =========================================== //
    // Problem 9:
    // ●    Bibliographical search restricted to library opening hours. Slow, tedious
    //      bibliographical search due to manipulation of card indexes
    //
    // Approach:
    // We are going
    // two separate naming and sorting convention hosted.
    // To simplify this we will standardize it and will do it by ascending alphabetical order.


    @Mock
    Book spyAnimal = new Book();

    @DisplayName("9.1.1: Test if Linear finds books")
    @ParameterizedTest
    @ValueSource(strings = {"apple", "bee", "zebra", "donkey", "giraffe", "lion", "orangutan", "monkey", "mongoose", "butterfly"})
    public void isSearchResultFoundByLinearSearch(String animal) {
        when(spyAnimal.getBibliography()).thenReturn(animal);

        Library uwon = new Library();

        assertTrue(uwon.linearSearch(spyAnimal.getBibliography()));  // Gets first bibliography of the book
    }

    @DisplayName("9.1.2: Test if Binary finds books")
    @ParameterizedTest
    @ValueSource(strings = {"apple", "bee", "zebra", "donkey", "giraffe", "lion", "orangutan", "monkey", "mongoose", "butterfly"})
    public void isSearchFoundByBinarySearch(String animal) {
        when(spyAnimal.getBibliography()).thenReturn(animal);

        Library uwon = new Library();

        assertTrue(uwon.binarySearch(spyAnimal.getBibliography()));
    }

    @DisplayName("9.2.1: Test if Linear Fails to find books")
    @ParameterizedTest
    @ValueSource(strings = {"a1f1v", "aw1vbne", "eftg4h", "asfg", "awwwrrr", "wwwewr", "ttetbrhrh", "lokode", "[#]", "3r2"})
    public void isFailFindLinear(String animal) {
        when(spyAnimal.getBibliography()).thenReturn(animal);

        Library uwon = new Library();

        assertFalse(uwon.linearSearch(spyAnimal.getBibliography()));
    }

    @DisplayName("9.2.2: Test if Binary Fails to find books")
    @ParameterizedTest
    @ValueSource(strings = {"a1f1v", "aw1vbne", "eftg4h", "asfg", "awwwrrr", "wwwewr", "ttetbrhrh", "lokode", "[#]", "3r2"})
    public void isFailFindBinary(String animal) {
        when(spyAnimal.getBibliography()).thenReturn(animal);

        Library uwon = new Library();

        assertFalse(uwon.binarySearch(spyAnimal.getBibliography()));
    }

    @DisplayName("9.3.1: Search Speed Test for books found")
    @ParameterizedTest
    @ValueSource(strings = {"apple", "bee", "zebra", "donkey", "giraffe", "lion", "orangutan", "monkey", "mongoose", "butterfly"})
    public void testSpeedOfTwoSearches(String animal) {
        Book animals = new Book();
        animals.addBibliography(animal);
        Library uwon = new Library();

        // Speed of Binary
        Instant start = Instant.now();
        uwon.binarySearch(animals.getBibliographies().get(0));
        Instant end = Instant.now();

        Duration binarySearchTime = Duration.between(start, end);

        // Speed of Linear Search
        start = Instant.now();
        uwon.linearSearch(animals.getBibliographies().get(0));
        end = Instant.now();

        Duration linearSearchTime = Duration.between(start, end);

        /* Here is a neat Trick: you can comment out this block of code by removing //
        System.out.println("Binary search took: " + binarySearchTime.toNanos() + " nanoseconds");
        System.out.println("Linear search took: " + linearSearchTime.toNanos() + " nanoseconds");
        //*/

        assertTrue(binarySearchTime.toNanos() <= linearSearchTime.toNanos());
    }

    @DisplayName("9.3.2: Search Speed Test for books not found")
    @ParameterizedTest
    @ValueSource(strings = {"a1f1v", "aw1vbne", "eftg4h", "asfg", "awwwrrr", "wwwewr", "ttetbrhrh", "lokode", "[#]", "3r2"})
    public void testSpeedOfTwoFailedSearches(String animal) {
        Book animals = new Book();
        animals.addBibliography(animal);
        Library uwon = new Library();

        // Speed of Binary
        Instant start = Instant.now();
        uwon.binarySearch(animals.getBibliographies().get(0));
        Instant end = Instant.now();

        // Print speed of Binary and calculate it
        Duration binarySearchTime = Duration.between(start, end);

        // Speed of Linear Search
        start = Instant.now();
        uwon.linearSearch(animals.getBibliographies().get(0));
        end = Instant.now();

        // Print speed of
        Duration linearSearchTime = Duration.between(start, end);


        /* Here is a neat Trick: you can comment out this block of code by removing //
        System.out.println("Binary search took: " + binarySearchTime.toNanos() + " nanoseconds");
        System.out.println("Linear search took: " + linearSearchTime.toNanos() + " nanoseconds");
        //*/

        assertTrue(binarySearchTime.toNanos() <= linearSearchTime.toNanos());
    }


    // Problem 10:
    // ●    Inaccurate search results, due to poor classification of books, journals or
    //      proceedings within departments.
    //
    // Approach:
    //      So this sounds like there is a mix of books and journals in a set of collection.
    //      A possible solution is to split up the collection into two groups: Books and Journals.
    //      When you are searching, you will have to select which type you want and it will automatically look at that data set.

    @DisplayName("10.0.0: Splitting books into two sets")
    @ParameterizedTest
    @CsvSource(value = {
            "book,The Milk:Boris",
            "book,Oregon:Hans",
            "journal,What is popping:Kevin",
            "journal,Hello World! Every Programmers favourite program:Milan Kovacs",
            "book,Maker of all:Adam",
            "journal,Brny's Wrld! Top 50:Breny",
            "book,The meaning of life:Italo",
            "journal,Ich Liebe Dich:Perto"
    })
    public void splitTheBooks(String input, String output) {
        String[] data = output.split(":");

        String title = data[0];
        String author = data[1];

        Object textBook = null;
        if (input.equals("book")) {
            textBook = new Book(title, author);
        } else if (input.equals("journal")) {
            textBook = new Journal(title, author);
        }

        String type = null;
        if (textBook instanceof Book) {
            type = "Book";
        } else if (textBook instanceof Journal) {
            type = "Journal";
        }

        assertEquals(type.toLowerCase(), input.toLowerCase());
    }

    @DisplayName("Valid Emails")
    @ParameterizedTest
    @ValueSource(strings = {"bepis@uwon.com", "milan@lib.ie", "josh@josh.josh", "k@gp.tv"})
    public void validEmail(String e){

        Email email = new Email(e);
        assertNotEquals(null, email.getEmail());

        assertAll(
                () -> assertAll("Main",
                        () -> assertTrue(email.containsSingleStrudel()),
//                        () -> assertTrue(email.containsNoSpace()),
                        () -> assertTrue(email.startsWithLetter()),
                        () -> assertTrue(email.endsWithLetter())
//                        () -> assertTrue(email.containsAllowedCharacters())
                ),

                () -> {
                    assertEquals(2, email.emailParts().length);

                    assertAll("Name",
                            () -> assertTrue(email.nameContainsAlphanumericCharacters())
                            );

                    assertAll("Domain",
                            () -> assertTrue(email.domainContainAlphabeticalCharacters()),
                            () -> assertTrue(email.domainContainsSingleDot())
                            );
                    }
                );
    }


    @ParameterizedTest
    @DisplayName("Invalid Emails")
    @ValueSource(strings = {"hello@gmail.com"})
    public void invalidEmails(String email){
        String[] emailSplit = email.split("@");

        assertAll(
                () -> assertNotEquals(2, emailSplit.length),
                () -> assertNotEquals(2, 3)
        );
    }

    // ===================================== End of Milan =========================================== //

    /*
    ===================================== Aaron ===========================================
     -    Unavailability of requested books, for a variety of reasons such as
          department budget restrictions, excessive borrowing by the same user,
          lack of enforcement of rules limiting loan periods, loss or stealing
          of book copies and so on.

     (Excessive Borrowing) Let's say if a book has been borrowed twice within the
     past 3 months by the same user, we don't allow them to borrow it again.

     (Loan Periods) If a loan is overdue we will add the book name and borrower
     name to an arraylist of books that need to be returned. This arraylist can
     then be accessed by moderators to see what books need to be returned, so they
     can reach out to the borrower.
     */

    @Test
    @DisplayName("Test for excessive borrowing.")
    public void testForExcessiveBorrowing() {
        Library uwon = new Library();
        Student student = new Student("", "Timmy", 14);
        Student student2 = new Student("", "John", 14);
        // Adds two instances of the student borrowing "The Bible".
        uwon.addBorrower("The Bible", student);
        uwon.addBorrower("The Bible", student);
        // Returns false and doesn't borrow the book because there is already 2 instances of the student borrowing "The Bible" in the past 3 months.
        assertFalse(uwon.addBorrower("The Bible", student));
        // Returns true and borrows the book because the student has not borrowed this book before.
        assertTrue(uwon.addBorrower("How To Steal Identities", student));
        // Returns true and borrows the book because a different student has not borrowed this same book before.
        assertTrue(uwon.addBorrower("The Bible", student2));
    }

    @Test
    @DisplayName("Test for overdue loans.")
    public void testForOverdueLoans() {
        Student student = new Student("", "Timmy", 14);
        // Returns false because the book is not due.
        assertFalse(student.checkDue());
        // Increases the loan time by 14 days.
        student.increaseBorrowTime(14);
        // Returns true because the book is overdue (it has been loaned for over 14 days).
        assertTrue(student.checkDue());
    }

    /*
     -    Incomplete or ineffective search results, due to relevant books, journals
     or proceedings being indexed in other UWON department libraries, or unavailable
     at UWON.

     (Books etc. being indexed in other libraries) Let's say that there are a set amount
     of UWON department libraries, e.g. 3. When searching for books etc., we will search
     each library for results and display a list of matching searches.
go
     (Unavailability of books) A book may be unavailable at UWON but available in partner
     libraries, so we will also check those in our search.

     */


    @Test
    @DisplayName("Test for comprehensive search results.")
    public void testForComprehensiveSearchResults() throws Exception {
        // Initializing libraries and books available at UWON.
        University uwon = new University();
        Library l1 = new Library();
        Library l2 = new Library();
        Library l3 = new Library();
        l1.populateBook(new String[]{
                "The Bible",
                "Of Mice And Men",
                "Winnie The Pooh"
        });
        l2.populateBook(new String[]{
                "The Bible",
                "Alice in Wonderland",
        });
        l3.populateBook(new String[]{
                "Computer Science Vol. 3"
        });
        uwon.newLibrary(l1);
        uwon.newLibrary(l2);
        uwon.newLibrary(l3);
        // Initializing partner library.
        University partner = new University();
        Library l4 = new Library();
        l4.populateBook(new String[]{
                "Bible Of Mice Vol. 3"
        });
        partner.newLibrary(l4);
        uwon.newPartner(partner);

        /*
        Now we have a university "uwon" with 3 libraries, and a partner
        university "partner" with a library of its own. When searching,
        all libraries in the university and all of its partners will be
        searched.
        */

        // If "The Bible" is searched we can expect to find "The Bible".
        ArrayList<String> test = new ArrayList<String>(List.of("The Bible"));
        assertEquals(test, uwon.findBook("The Bible"));
        // If "Alice In" is searched we can expect to find "Alice in Wonderland".
        test = new ArrayList<String>(Arrays.asList("Alice in Wonderland"));
        assertEquals(test, uwon.findBook("Alice In"));
        // If "bibl" is searched we can expect to find "The Bible" and "Bible Of Mice Vol. 3".
        test = new ArrayList<String>(Arrays.asList("The Bible", "Bible Of Mice Vol. 3"));
        assertEquals(test, uwon.findBook("bibl"));
        // If "3" is searched we can expect to find "Computer Science Vol. 3" and "Bible Of Mice Vol. 3".
        test = new ArrayList<String>(Arrays.asList("Computer Science Vol. 3", "Bible Of Mice Vol. 3"));
        assertEquals(test, uwon.findBook("3"));
        // If there are no matching results, an exception will be thrown.
        assertThrows(Exception.class, () -> {
            uwon.findBook("Harry Potter");
        });
    }
    // ===================================== End of Aaron =========================================== //
}
