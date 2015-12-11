package nyc.c4q.android.ui;

public class RealAuthenticationManager implements AuthenticationManager {
  public boolean validateLogin(String email, String password) {
    // TODO - implement authentication logic
    // valid credentials are email: "c4q", password: "c4q"
      if (email.equals("c4q") && password.equals("c4q"))
          return true;
      else
          return false;
  }
}
