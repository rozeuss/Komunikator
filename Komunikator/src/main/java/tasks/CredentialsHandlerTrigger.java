/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasks;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import handlers.CredentialsHandler;
import java.util.logging.Level;
import javafx.concurrent.Task;
import security.PasswordHasher;

/**
 *
 * @author ADMIN
 */
public class CredentialsHandlerTrigger extends Task{
    private String username;
    private String password;
    public CredentialsHandlerTrigger(){}

    @Override
    protected Object call() throws IllegalArgumentException, NullPointerException {
        try {
            CredentialsHandler.getInstance().HashAndSendCredentials(username, password);
        } catch (NullPointerException  e) {
            LOGGER.log(Level.WARNING, null, e);
        }
        
        return null;
    }
    
    public void SetUsername(String username){
        this.username = username;
    }
    public void SetPassword(String password){
        this.password = password;
    }
    
}
