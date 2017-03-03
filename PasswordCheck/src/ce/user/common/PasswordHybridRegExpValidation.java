package ce.user.common;

public class PasswordHybridRegExpValidation {
	public static boolean passwordMatchLength(String password){
		String[] illegalPassword = {
				"name","password","user"
		};
		for(int i=0;i<illegalPassword.length;i++){
			if(illegalPassword[i].equals(password)){
				return false;
			}
		}
		if(password.length()<8){
			return false;
		}
		return true;
	}
}	
