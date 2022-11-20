import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.util.*;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author 21308128
 */
@ExtendWith(MockitoExtension.class)
public class JacobUnitLibraryTests {

    public JacobUnitLibraryTests() {
    }

    // ==================================== Jacob's Test ==================================== //
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
    @DisplayName("Display previous owners")
    public void testShow() {
        Library l1 = new Library();
        l1.getNewBook("The Bible");
        l1.borrow("The Bible", "Jacob Beck", 15);
        assertNotEquals("", l1.getBorrowers("The Bible"));
    }

    @Test
    @DisplayName("Check the subscription method")
    public void subscribe(){
        Journal j = new Journal("Wonderland News", false);
        Library l = new Library();
        l.subscribe(j);
        assertTrue(l.getJournals().contains(j));
    }

    @Test
    @DisplayName("Check the e-journal subscription method")
    public void eSubscribe(){
        Journal j = new Journal("Wonderland News Online", true);
        Library l = new Library();
        l.subscribe(j);
        assertFalse(l.getJournals().contains(j));
        assertTrue(l.geteJournals().contains(j));
    }

    @Test
    @DisplayName("Journal Simple Method testing")
    public void varietyTests(){
        Journal j = new Journal("Mockito 101", false);
        assertFalse(j.iseJournal());
        j.journalDelivery();
        assertEquals(11, j.getSubscriptionMonths());
        j.renew(6);
        assertEquals(17, j.getSubscriptionMonths());
    }

    @Test
    @DisplayName("Avoid multi-subscribing")
    public void otherLib(){
        Library l1 = new Library();
        Library l2 = new Library();
        University uwon = new University();
        uwon.newLibrary(l1);
        uwon.newLibrary(l2);
        Journal j = new Journal("Mockito Monthly ONLINE", true);
        uwon.subscribe(l2, j);
        assertFalse(uwon.subscribe(l1, j));
    }

    @Test
    @DisplayName("Trying to subscribe with the same journal in another library")
    public void otherUni(){
        Library l1 = new Library();
        Library l2 = new Library();
        University uwon = new University();
        University ul = new University();
        uwon.newLibrary(l1);
        ul.newLibrary(l2);
        ul.joinUni(uwon);
        Journal j = new Journal("Mockito Monthly OFFLINE", false);
        ul.subscribe(l2, j);
        assertFalse(uwon.subscribe(l1, j));
    }

    @Test
    @DisplayName("Getting journal info")
    public void testToString(){
        Journal j = new Journal("The World's Best Test Suites of the Month", false);
        assertTrue(j.toString().equals("Title:The World's Best Test Suites of the Month, Physical Journal with 12 subscription months left"));
    }

    // ================================ End of Jacob ========================================= //

    // ===================================== Breny =========================================== //
    @Test
    @DisplayName("Check Person For Admin Permissions")
    public void checkPrivelidges() {
        //-------------HAPPY PATH-------------------
        //add a new person
        Person andy = new Person("andy", true);
        //Create a new college
        University ul = new University();
        //checking admin stauts
        ul.setAdmins(andy);
        //-------------------------------------------
        //add a new person
        Person tom = new Person("Tom", false);
        //checking admin stauts
        ul.setAdmins(tom);

        assertAll(
                () -> assertTrue(ul.checkAdmins(andy)),
                () -> assertFalse(ul.checkAdmins(tom))
        );
    }

    //Complaint 5
    //Subscription to journals of marginal interest to the university, which could be
    //accessed in other universities with which UWON has an agreement.
    @Test
    @DisplayName("Check to see if subscription is available in UWON")
    public void subscriptionsAcrossUWON() {
        //-------------HAPPY PATH-------------------
        //add colleges
        University u1 = new University();
        University u2 = new University();
        University u3 = new University();
        //add librarys
        Library l1 = new Library();
        Library l2 = new Library();
        Library l3= new Library();
        //set librarys
        u2.newLibrary(l1);
        u1.newLibrary(l2);
        u1.joinUni(u2);
        //setting arraylist for subscriptions
        ArrayList<Book> DW = new ArrayList<>();
        //creating new subscriptions
        Subscription davidWalliams = new Subscription("Gangster Granny", DW);
        //add subscription to library
        l1.addASubscription(davidWalliams);
        //---------------------------------------------------

        Subscription RonaldMcDonald = new Subscription("Maccys", DW);
        u3.newLibrary(l3);
        assertAll(
                //Happy path
                () -> assertTrue(u1.getSubscriptionForLib(davidWalliams)),
                //checking subscription which colleges doesnt have
                () -> assertFalse(u1.getSubscriptionForLib(RonaldMcDonald)),
                //checking for subscription with college no in agreement
                () -> assertFalse(u3.getSubscriptionForLib(davidWalliams))

        );

    }

    //Complaint 6
    //Acquisition of books or proceedings of marginal interest to the university, which
    //could be borrowed from other universities with which UWON has an agreement.
    @Test
    @DisplayName("Check if book can be borrowed using the UWON agreement ")
    public void sendingCompSciBooksFromLSADToUL() {
        //-------------HAPPY PATH-------------------
        //Create new colleges
        University UL = new University();
        University LSAD = new University();
        //create new libraries
        Library bugLibrary = new Library();
        Library l1 = new Library();
        //add libraries to colleges
        UL.newLibrary(bugLibrary);
        LSAD.newLibrary(l1);
        //Create new book
        Book Worms = new Book();
        //add book to library
        bugLibrary.addBookTOLibrary(Worms);
        //Join colleges
        LSAD.joinUni(UL);
        //----------------------------------------
        University outsiders = new University();
        Book hello = new Book();
        University insiders = new University();
        insiders.joinUni(UL);

        assertAll(
                //Happy path check
                () -> assertTrue(LSAD.getBookForLib(Worms)),
                //Check if library outside the agreement can access
                () -> assertFalse(outsiders.getBookForLib(Worms)),
                //Check if book outside all colleges can be borrowed
                () -> assertFalse(LSAD.getBookForLib(hello)),
                //add a third library to agreement to ensure it doesn't just work on two
                () -> assertTrue(insiders.getBookForLib(Worms))
                );
    }

    //Complaint 7
    //Inaccuracy of card indexes, e.g. a book is stated as being available whereas it is not
    //found at the appropriate place on the shelves.
    @Test
    @DisplayName("Problem 7 : Innacuracy of book being on specified shelf")
    public void bookOnShelFinder(){
        //-------------HAPPY PATH-------------------
        //Create a new College
        University BrenysWRLD = new University();
        //Create a new library
        Library BookRoom = new Library();
        //add the library to college
        BrenysWRLD.newLibrary(BookRoom);
        //new book array list to go on shelf
        ArrayList<Book> OverwatchArraylist = new ArrayList<>() ;
        //create a new shelf
        Shelf Overwatch = new Shelf("Overwatch", OverwatchArraylist);
        //add the shelf to library
        BookRoom.newShelf(Overwatch);
        //new book
        Book bobo = new Book("Monke","Winston");
        //add book to system with specified shelf
        BookRoom.addNewBooksToSystem(bobo,Overwatch);
        //--------------------------------------------------
        Book Hello = new Book();

        University outsiders = new University();
        Library outsidersLibrary = new Library();
        outsiders.newLibrary(outsidersLibrary);

        Book outside = new Book();
        outsidersLibrary.addBookTOLibrary(outside);

         BookRoom.addBookTOLibrary(outside);

        assertAll(
                //Happy path
                () -> assertTrue(BrenysWRLD.checkUniversityForBook(bobo)),
                //Check for random book
                () -> assertFalse(BrenysWRLD.checkUniversityForBook(Hello)),
                //check book with no shelf
                () -> assertFalse(BrenysWRLD.checkUniversityForBook(outside))
        );
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

        // Treat this as someone passing objects into our test
        Object textBook = null;
        if (input.equals("book")) {
            textBook = new Book(title, author);
        } else if (input.equals("journal")) {
            textBook = new Journal(title, author);
        }

        // The actual test
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
    public void validEmail(String e) {

        Email email = new Email(e);
        assertNotEquals(null, email.getEmail());

        assertAll(
                () -> assertAll("Main",
                        () -> assertTrue(email.startsWithLetter()),
                        () -> assertTrue(email.endsWithLetter())
                ),

                () -> {
                    assertTrue(email.containsSingleStrudel());

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


    @DisplayName("Testing multiple Strudel")
    @ParameterizedTest
    @ValueSource(strings = {"hello@milan@this.com", "thisismyemail.com"})
    public void failInStrudel(String e){
        Email email = new Email(e);
        assertFalse(email.containsSingleStrudel());
    }

    @DisplayName("Faulty Starting Names")
    @ParameterizedTest
    @ValueSource(strings = {"3ilan@this.com", "231MeotireNews@metori.com"})
    public void faultyStartingName(String e){
        Email email = new Email(e);
        assertFalse(email.startsWithLetter());
    }

    @DisplayName("Faulty Ending Names")
    @ParameterizedTest
    @ValueSource(strings = {"Milan@somewhere.23", "Milan@sorry.i3"})
    public void faultyEndingNames(String e){
        Email email = new Email(e);
        assertFalse(email.endsWithLetter());
    }

    @DisplayName("Name containing incorrect characters")
    @ParameterizedTest
    @ValueSource(strings = {"hello$@world.py", "java%iscool.com@ ", "py charm@jupiter.com", "dr.racket@ireland.com"})
    public void incorrectCharacters(String e){
        Email email = new Email(e);
        assertFalse(email.nameContainsAlphanumericCharacters());
    }

    @DisplayName("Domain containing incorrect characters")
    @ParameterizedTest
    @ValueSource(strings = {"@world.p2y", "@java%iscool.com", "@py charmjupiter.com", "@dr.racketireland com"})
    public void incorrectCharaterInDomain(String e){
        Email email = new Email(e);
        assertFalse(email.domainContainAlphabeticalCharacters());
    }

    @DisplayName("Domain not containing single dot")
    @ParameterizedTest
    @ValueSource(strings = {"@world.p2.y", "@java.%iscool.com", "@py charmjupitecom", "@.."})
    public void multipleOrNoDots(String e){
        Email email = new Email(e);
        assertFalse(email.domainContainsSingleDot());
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
        l1.populateBook(new String[] {
                "The Bible",
                "Of Mice And Men",
                "Winnie The Pooh"
        });
        l2.populateBook(new String[] {
                "The Bible",
                "Alice in Wonderland",
        });
        l3.populateBook(new String[] {
                "Computer Science Vol. 3"
        });
        uwon.newLibrary(l1);
        uwon.newLibrary(l2);
        uwon.newLibrary(l3);
        // Initializing partner library.
        University partner = new University();
        Library l4 = new Library();
        l4.populateBook(new String[] {
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

        ArrayList<String> test = new ArrayList<String>(List.of("The Bible"));
        ArrayList<String> test2 = new ArrayList<String>(List.of("Alice in Wonderland"));
        ArrayList<String> test3 = new ArrayList<String>(List.of("The Bible", "Bible Of Mice Vol. 3"));
        ArrayList<String> test4 = new ArrayList<String>(List.of("Computer Science Vol. 3", "Bible Of Mice Vol. 3"));
        assertAll( ()-> {
            // If "The Bible" is searched we can expect to find "The Bible".
            assertEquals(test, uwon.findBook("The Bible"));
            // If "Alice In" is searched we can expect to find "Alice in Wonderland".
            assertEquals(test2, uwon.findBook("Alice In"));
            // If "bibl" is searched we can expect to find "The Bible" and "Bible Of Mice Vol. 3".
            assertEquals(test3, uwon.findBook("bibl"));
            // If "3" is searched we can expect to find "Computer Science Vol. 3" and "Bible Of Mice Vol. 3".
            assertEquals(test4, uwon.findBook("3"));
            // If there are no matching results, an exception will be thrown.
            assertThrows(Exception.class, ()-> uwon.findBook("Harry Potter"));
        });
    }

    /*
    We want to be able to support user registration, so we will create a user
    class that takes in inputs of a username, a password and an email.
    For the username we will limit it to 8-20 characters.
    For the password we will require 8 character minimum and at least one
    lowercase character, one uppercase character and one digit.
    For the email we will simply ensure that there is a "@" character somewhere after
    the first character and before the last character.
     */

    @Test
    @DisplayName("Test user registration.")
    public void testUserRegistration() {
        assertAll( ()-> {
            // Username validation.
            // Contains between 8 and 20 characters, should return true.
            assertTrue(User.checkValidUsername("aB1@=/?.,!¬#"));
            // Contains less than 8 characters, should return false.
            assertFalse(User.checkValidUsername("aB1@=/?"));
            // Contains more than 20 characters, should return false.
            assertFalse(User.checkValidUsername("aB1@=/?,!¬#aB1@=/?,!¬#"));

            // Password Validation
            // Contains at least 8 characters, one lowercase, one uppercase and one digit, should return true.
            assertTrue(User.checkValidPassword("aBcD123@"));
            // Contains less than 8 characters, one lowercase, one uppercase and one digit, should return false.
            assertFalse(User.checkValidPassword("aBcD1@"));
            // Contains at least 8 characters, one lowercase, one uppercase and zero digits, should return false.
            assertFalse(User.checkValidPassword("aBcDeF@#"));
            // Contains at least 8 characters, one lowercase, zero uppercase's and one digit, should return false.
            assertFalse(User.checkValidPassword("abcd12@#"));
            // Contains at least 8 characters, zero lowercase's, one uppercase and one digit, should return false.
            assertFalse(User.checkValidPassword("ABCD12@#"));

            // Email Validation
            // Contains a '@' character between the first and last character, should return true.
            assertTrue(User.checkValidEmail("jimmyjohn@ul.ie"));
            // Contains a '@' character as the last character, should return false.
            assertFalse(User.checkValidEmail("jimmyjohnul.ie@"));
            // Contains a '@' character as the first character, should return false.
            assertFalse(User.checkValidEmail("@jimmyjohnul.ie"));
            // Contains no '@' character, should return false.
            assertFalse(User.checkValidEmail("jimmyjohnul.ie"));
        });
    }

    // ===================================== End of Aaron =========================================== //
    // ====================================== Eamonn ================================================ //
    @Test

    public void isNewEBookRequestMadeWhenNotEJournal(){
        Journal journal1 = new Journal("journal1","author1");

        int sizeBefore = Library.getRequestsForEBooks().size();
        journal1.beingBound();
        int sizeAfter = Library.getRequestsForEBooks().size();
        assertTrue(sizeBefore + 1 == sizeAfter);

        Journal journal2 = new Journal("journal2","author2");
        sizeBefore = Library.getRequestsForEBooks().size();
        journal1.beingBound();
        sizeAfter = Library.getRequestsForEBooks().size();
        assertTrue(sizeBefore + 1 == sizeAfter);
    }

    @Test
    public void isNewEBookRequestMadeWhenEJournalConstructor(){
        Journal journal1 = new Journal("journal1",true);

        int sizeBefore = Library.getRequestsForEBooks().size();
        journal1.beingBound();
        int sizeAfter = Library.getRequestsForEBooks().size();
        assertTrue(sizeBefore == sizeAfter);

    }

    @Test

    public void testForLastStudentToBorrow(){
        Book book = new Book("Book", "Author");
        Student student = new Student("Student1");
        Student student2 = new Student("Student2");

        Loan loan1 = new Loan(student, book, LocalDate.now().minusDays(1));
        Loan loan2 = new Loan(student2, book, LocalDate.now());

        assertTrue(book.lastStudentBeforeDamage() == student2);
    }
    // =============================================== End of Eamonn ===================================== //
}
