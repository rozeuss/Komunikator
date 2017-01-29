package tasks;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import handlers.LogInCredentialsHandler;
import java.util.logging.Level;
import javafx.concurrent.Task;


public class LogInCredentialsHandlerTrigger extends Task{
    
    private String username;
    private String password;
    public LogInCredentialsHandlerTrigger(){}

    @Override
    protected Object call() throws IllegalArgumentException, NullPointerException {
        try {
            LogInCredentialsHandler.getInstance().HashAndSendCredentials(username, password);
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
