package webapp.models;

public class LoginService
{
	public boolean isUserValid(String username, String password) {
		if ((username.equals("Gosho") && password.equals("root")) || (username.equals("Katq") && password.equals("root"))) {
			return true;
		}
		
		return false;
	}
}
