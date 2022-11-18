/*/Extra library functionality
private ArrayList<Journal> eJournals;
private ArrayList<Student> borrowers;

  public void subscribe(Journal j){
        if(j.iseJournal()){
            eJournals.add(j);
        }else{
            journals.add(j);
        }
    }
    
    public ArrayList<Journal> getJournals(){
        return journals;
    }

    public ArrayList<Journal> geteJournals() {
        return eJournals;
    }
    
    public boolean inOtherLibsJournal(Journal journal, Library l){
        if(l.getJournals().contains(journal) || l.geteJournals().contains(journal)){
            return true;
        }else{
            return false;
        }
    }
*/