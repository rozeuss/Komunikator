/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
public class FileReader {
    
    private File file;
    private String filePath;
    FileInputStream fileInputStream;
    BufferedReader bufferedReader;
    private int lineCounter;

    public boolean setFile(String filepath, int lines) throws FileNotFoundException{
    	boolean isFileExist = false;
        lineCounter = 0;
        if(new File(filepath).exists()) {
        	isFileExist = true;
            filePath = filepath;
            fileInputStream = new FileInputStream(filePath);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        }

        return isFileExist;
    }
    
    public void setLineCounter(int lineCounter) {
        this.lineCounter = lineCounter;
    }
    
 
    public String readLine() {
        String line = null;
        
        
        
		try {
			line = bufferedReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return line;
        
    }
    
    
}
