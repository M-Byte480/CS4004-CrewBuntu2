public class Journal {
    private String title;
    private String author;
    private String name;
    private boolean eJournal;
    private int subscriptionMonths;

    public Journal(String name, boolean eJournal) {
        this.name = name;
        this.eJournal = eJournal;
        subscriptionMonths = 12;
    }

    public Journal(String title, String author){
        this.title = title;
        this.author = author;
    }


    public void journalDelivery() {
        subscriptionMonths--;
    }

    public void renew(int renewAmt) {
        subscriptionMonths += renewAmt;
    }

    public String getName() {
        return name;
    }

    public boolean iseJournal() {
        return eJournal;
    }

    public int getSubscriptionMonths() {
        return subscriptionMonths;
    }

    public String toString() {
        String phys = "Physical Journal";
        if (iseJournal()) {
            phys = "eJournal";
        }
        return "Title:" + getName() + ", " + phys + " with " + getSubscriptionMonths() + " subscription months left";
    }

}