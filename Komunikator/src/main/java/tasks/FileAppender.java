/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasks;

import controllers.ChattingController;
import java.io.File;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class FileAppender {
    
    
    private File file;
    private String filePath;
    private FileChannel fileChannel;
    private FileLock lock;
    BufferedWriter bufferedWriter;
    FileWriter fileWriter;
    String messageContent;

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
    
    Logger LOGGER;
    public void setFile(String filepath) throws IOException
    {
        filePath = filepath;
        file = new File(filePath);
        if(!file.exists())
            file.createNewFile();
        
        fileChannel = new RandomAccessFile(file,"rw").getChannel();    
    }
    
    public void writeToFile(String content)
    {
        try {
            lock = fileChannel.lock();
        } catch (IOException ex) {
            Logger.getLogger(FileAppender.class.getName()).log(Level.SEVERE, "CANNOT LOCK FILE");
        }
        try {
            fileWriter     = new FileWriter(file.getAbsoluteFile(),true);
        } catch (IOException ex) {
            Logger.getLogger(FileAppender.class.getName()).log(Level.SEVERE, "FILE WRITER CREATION FAILED");
        }
            bufferedWriter = new BufferedWriter(fileWriter);   
        try {
            bufferedWriter.write(content);
        } catch (IOException ex) {
            Logger.getLogger(FileAppender.class.getName()).log(Level.SEVERE, "WRITING TO FILE FAILED");
        }
        try {
            bufferedWriter.newLine();
        } catch (IOException ex) {
            Logger.getLogger(FileAppender.class.getName()).log(Level.SEVERE, "bufferedWriter NEW LINE FAILED");
        }
        try {
            lock.release();
        } catch (IOException ex) {
            Logger.getLogger(FileAppender.class.getName()).log(Level.SEVERE, "LOCK RELEASE FAILED");
        }
        try {
            bufferedWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(FileAppender.class.getName()).log(Level.SEVERE, "BW CLOSE FAILED");
        }
           
        setMessageContent(null);

    }

    /*@Override
    public void run() {
        writeToFile(messageContent);
    }   */
}
