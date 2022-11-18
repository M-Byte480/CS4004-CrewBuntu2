import java.util.regex.Pattern;

public class Email {
    private String email;
    private String name = null;
    private String domain = null;

    public Email(String email) {
        this.email = email.toLowerCase();
        if (emailParts().length == 2) {
            name = emailParts()[0];
            domain = emailParts()[1];
        }
    }

    public String getEmail() {
        return email;
    }

    public String[] emailParts() {
        String[] emailParts = email.split("@");
        return emailParts;
    }

    public boolean containsSingleStrudel() {
        return Pattern.matches("^[^@]*@[^@]*$", email);
    }


    public boolean startsWithLetter() {
        return Pattern.matches("^[a-z].*$", email);
    }

    public boolean endsWithLetter() {
        return Pattern.matches("^.*[a-z]$", email);
    }

    public boolean nameContainsAlphanumericCharacters(){
    return Pattern.matches("^[a-z0-9]*$",name);
}

    public boolean domainContainAlphabeticalCharacters(){
        return Pattern.matches("^[a-z.]*$", domain);
    }

    public boolean domainContainsSingleDot(){
       return Pattern.matches("^[^\\.]*\\.[^\\.]*$", domain);
    }
}
