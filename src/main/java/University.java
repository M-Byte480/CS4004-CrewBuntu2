import java.util.ArrayList;
public class University {
    private ArrayList<Library> libraries;
    private ArrayList<University> partners;
    
    public University(){
        libraries = new ArrayList<Library>();
        partners = new ArrayList<University>();
    }

    //adding a new library to partners
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


    //go through every library
    //if library contains book don't add it
    //if it doesn't add it to specified library
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

    //go through every library
    //if library contains subscription don't add it
    //if it doesn't add it to specified library
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

    //adding all university libraries
    //add all librarys to university
    //add university to partners
    //add this university to partners
    public void joinUni(University u){
        libraries.addAll(u.getLibraries());
        u.getLibraries().addAll(libraries);
        partners.add(u);
        u.getPartners().add(this);
    }
}
