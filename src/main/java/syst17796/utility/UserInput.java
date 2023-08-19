package syst17796.utility;

import java.util.Scanner;

public class UserInput { // single class handles all user input
    private String sTemp;// has built-in data validation to prevent program crashes
    private int iTemp; // custom verification needed on a case-bycase basis
    private boolean bTemp;
    private boolean bFlag;

    Scanner input = new Scanner(System.in);

    public String aString() { // gets "a String", no validation required
        return input.nextLine();
    }

    public int anInt() { // gets "an int"
        do {
            bFlag = true;
            try { // takes input as string, attempts to parse as int
                sTemp = input.nextLine();
                iTemp = Integer.parseInt(sTemp);
            } catch (NumberFormatException e) { // catches values that aren't integers
                bFlag = false; // marks input as invalid
                System.out.println("Error: Please enter an integer.");
                System.out.print("> "); // provides descriptive error message, prompts for new input
            }
        } while (bFlag == false); // repeats until valid input given
        return iTemp; // returns valid input
    }

    public boolean aBoolean(String optionTrue, String optionFalse) { // gets "a boolean"
        // Note: option exists for programmer to input their own "true" and "false"
        // arguments
        // as "yes" and "no" can be easier for the user to understand
        // option similarly exists for yes/no questions to allow for more descriptive
        // inputs
        do {
            bFlag = true;
            sTemp = input.nextLine(); // gets user input

            // translates everything to lowercase for comparisons
            String optionTrueIgnoreCase = optionTrue.toLowerCase();
            String optionFalseIgnoreCase = optionFalse.toLowerCase();
            sTemp = sTemp.toLowerCase();

            // determines if answer is true, false, or invalid
            if (sTemp.equals(optionTrueIgnoreCase)) {
                bTemp = true;
            } else if (sTemp.equals(optionFalseIgnoreCase)) {
                bTemp = false;
            } else {
                bFlag = false; // marks input as invalid
                System.out.println(
                        "Error: Please enter either [" + optionTrue + "] or [" + optionFalse + "] (case insensitive).");
                System.out.print("> "); // reprompts user for input, gives available options, reminds user of case
                                        // insensitivity
            }
        } while (bFlag == false); // loops until valid input
        return bTemp; // returns valid input
    }

    // miscellaneous utility methods
    public void aWait() { // waits for user input, allows for temporary pausing of output
        input.nextLine();
    }

    public void aClearScreen() { // clears the console to keep output clean
        System.out.print("\033[H\033[2J");
        System.out.flush(); // prevents user from scrolling up through previously cleared output
    }
}