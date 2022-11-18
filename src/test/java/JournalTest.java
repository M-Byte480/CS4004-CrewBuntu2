import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JournalTest {

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



}