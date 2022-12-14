import java.util.regex.Pattern;

public class User {

    private String username;
    private String password;
    private String email;
    public static final Pattern passwordValidator = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(.+){8,}$");

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }


    public static boolean checkValidUsername(String username) { return (username.trim().length() >= 8 && username.trim().length() <= 20); }

    public static boolean checkValidPassword(String password) { return passwordValidator.matcher(password).matches(); }

    public static boolean checkValidEmail(String email) {
        int atSignLocation = (email.trim().indexOf('@'));
        return (atSignLocation > 0 && atSignLocation < (email.trim().length() - 1));
    }
}
