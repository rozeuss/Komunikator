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
public class CredentialsHandler {
    private static CredentialsHandler credentialsHandlerHolder;
    private CredentialsHandler(){}
    
    
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
    
    
    
    public static CredentialsHandler getInstance(){
        if(credentialsHandlerHolder == null)
            credentialsHandlerHolder = new CredentialsHandler();
            
        return credentialsHandlerHolder;
    }
    
}
