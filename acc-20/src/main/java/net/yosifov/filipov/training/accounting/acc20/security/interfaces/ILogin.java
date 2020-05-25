package net.yosifov.filipov.training.accounting.acc20.security.interfaces;

/// tova mojebi she se naloji da go preprabotim za
public interface ILogin {
    String Login(String token);
    String Login(String UserName, String Password);
    void Logout(String token);
}
