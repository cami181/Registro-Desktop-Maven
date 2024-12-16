package Credenziali;

public class Credenziali {
    private String user;
    private String password;

    public Credenziali(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
