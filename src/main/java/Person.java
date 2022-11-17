public class Person {
    String name ;
    boolean admin;


    public Person(String name , boolean perms){
        this.name = name;
        this.admin = perms;
    }

    public boolean isAdmin() {
        return admin;
    }


}
