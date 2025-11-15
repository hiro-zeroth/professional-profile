package taskawaii.control;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import taskawaii.components.HomePage;
import taskawaii.components.StartPage;

/**
 *This is the main class of the project.
 * @author Raphael
 */
public class TASKawaii {
    
    /**
     * This is the constructor for the TASKawaii class.
     */
    public TASKawaii(){};
    
    /**
     *This is where the user profile will be stored.
     */
    private static User user;

    /**
     *This is where the HomePage will be stored. It is declared static to allow
     * other classes easy access.
     */
    private static HomePage homePage;

    /**
     *This is the main function of the class. It will first try to read for an
     * existing userdata.ser file to get its contents. If it fails it will prompt
     * the StartPage. The program will repetitively test every second if the user
     * has submitted a name for the user in the StartPage to proceed to the HomePage.
     * If there is an already existing userdata.ser file, it will not open the StartPage
     * and proceed directly to the HomePage.
     * @param args - String input from the command prompt
     */
    public static void main(String[] args) {
        try {
            try (FileInputStream fileIn = new FileInputStream("userdata.ser");
                    ObjectInputStream in = new ObjectInputStream(fileIn)) {
                user = (User) in.readObject();
            }
            userDone = true;
        } catch (IOException i) {
            user = new User();
            StartPage startPage = new StartPage(user);
        } catch (ClassNotFoundException c) {
            System.out.println(c.getMessage());
            return;
        }
        while(true){
            if(userDone){
                homePage = new HomePage(user);
                homePage.setVisible(true);
                homePage.run();
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(TASKawaii.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *This is the getter for the HomePage of the program.
     * @return It will return the HomePage of the program.
     */
    public static HomePage getHomePage(){
        return homePage;
    }
    
    /**
     *This is a boolean variable that signifies if user setup is done.
     */
    public static boolean userDone = false;
}