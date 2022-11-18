import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
// Please ignore this file.
public class SubscriptionTest {
    
    public SubscriptionTest() {
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
}
