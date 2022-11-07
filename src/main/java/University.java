import java.util.ArrayList;
public class University {
    private ArrayList<Library> libraries;
    private ArrayList<University> partners;
    
    public University(){
        libraries = new ArrayList<Library>();
        partners = new ArrayList<University>();
    }
    
    public void newLibrary(Library l){
        libraries.add(l);
        for(int i = 0; i < partners.size(); i++){
            partners.get(i).getLibraries().add(l);
        }
    }

    public ArrayList<University> getPartners() {
        return partners;
    }

    public ArrayList<Library> getLibraries() {
        return libraries;
    }
    
    public boolean getBookForLib(Library l, String book){
        boolean addIt = true;
        for(int i = 0; i < libraries.size(); i++){
            if(l.inOtherLibs(book, libraries.get(i))){
                addIt = false;
                break;
            }
        }
        if(addIt == true){
            l.getNewBook(book);
        }
        return addIt;
    }
    
    public boolean subscribe(Library l, String journal){
        boolean addIt = true;
        for(int i = 0; i < libraries.size(); i++){
            if(l.inOtherLibsJournal(journal, libraries.get(i))){
                addIt = false;
                break;
            }
        }
        if(addIt == true){
            l.subscribe(journal);
        }
        return addIt;
    }
    
    public void joinUni(University u){
        libraries.addAll(u.getLibraries());
        u.getLibraries().addAll(libraries);
        partners.add(u);
        u.getPartners().add(this);
    }
}
