package transferData;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import transferDataContainers.*;
import controllers.*;

public class Receiver {
	
	public Object read(ObjectInputStream in) 
										throws ClassNotFoundException, IOException, EOFException {

		return in.readObject();
	}
}

