package taskawaii.control;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *This is the class that will hold the user profile/data.
 * @author Raphael
 */
public class User implements Serializable{

    /**
     *This will hold the user's name.
     */
    private String name;

    /**
     *This is the TaskList for the user.
     */
    private TaskList taskList;

    /**
     *This is the constructor for the User class. It will create a TaskList
     * instance for the taskList variable.
     */
    User(){
        taskList = new TaskList();
    }

    /**
     *This is the setter for the user name.
     * @param name this is the user name
     */
    public void setName(String name){
        this.name = name;
        saveUser();
    }

    /**
     *This is the getter for the user name.
     * @return It returns the name of the user.
     */
    public String getName(){
        return name;
    }

    /**
     *This is the getter of the user TaskList.
     * @return It returns the user TaskList.
     */
    public TaskList getTaskList(){
        saveUser();
        return taskList;
    }

    /**
     *This saves the user object by using the ObjectOutputStream. The file is saved
     * to userdata.ser. This is where serialization happens.
     */
    public void saveUser(){
        try {
            FileOutputStream fileOut = new FileOutputStream("userdata.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            System.out.println(i.getMessage());
        }
    }
}
