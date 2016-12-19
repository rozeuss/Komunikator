/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;


import database.CredentialsSender;
import security.PasswordHasher;

/**
 *
 * @author ADMIN
 */
public class LogInCredentialsHandler {
    private static LogInCredentialsHandler credentialsHandlerHolder;
    private LogInCredentialsHandler(){}
    
    
    public void HashAndSendCredentials(String username, String password) throws NullPointerException, IllegalArgumentException {
                        if ((username.length() == 0 || password.length() == 0))
				throw new IllegalArgumentException();
                        else if ((username.length() >= 50 || password.length() >= 50))
				throw new IllegalArgumentException();
			
                        String PasswordHashed = null;
			PasswordHashed = PasswordHasher.hashpw(password, PasswordHasher.gensalt());
                        CredentialsSender.getInstance().sendCredentials(username, password);
                        PasswordHashed = null;
			
    }
    
    
    
    public static LogInCredentialsHandler getInstance(){
        if(credentialsHandlerHolder == null)
            credentialsHandlerHolder = new LogInCredentialsHandler();
            
        return credentialsHandlerHolder;
    }
    
}
