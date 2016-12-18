/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import security.Password;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javafx.concurrent.Task;

/**
 *
 * @author Maciej
 */
public class CredentialsSender {
    
    private CredentialsSender(){}
    private static CredentialsSender credentialsSenderHolder;
    public void sendCredentials(String username, String hashedPassword) {
         
        
        Task<String> sendCredentials = new Task<String>() {
            @Override
            protected String call() throws Exception {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        
        
        
        
    }
    public static CredentialsSender getInstance(){
        if(credentialsSenderHolder == null)
            credentialsSenderHolder = new CredentialsSender();
            
        return credentialsSenderHolder;
    }
}
