package syst17796.utility;

import java.util.Scanner;

public class UserInput {
    private String sTemp;
    private int iTemp;
    private boolean bTemp;
    private boolean bFlag;

    Scanner input = new Scanner(System.in);

    public String aString() {
        return input.nextLine();
    }

    public int anInt() {
        do {
            bFlag = true;
            try {
                sTemp = input.nextLine();
                iTemp = Integer.parseInt(sTemp);
            } catch (NumberFormatException e) {
                bFlag = false;
                System.out.println("Error: Please enter an integer.");
                System.out.print("> ");
            }
        } while (bFlag == false);
        return iTemp;
    }

    public boolean aBoolean(String optionTrue, String optionFalse) {
        do {
            bFlag = true;
            sTemp = input.nextLine();

            String optionTrueIgnoreCase = optionTrue.toLowerCase();
            String optionFalseIgnoreCase = optionFalse.toLowerCase();
            sTemp = sTemp.toLowerCase();

            if (sTemp.equals(optionTrueIgnoreCase)) {
                bTemp = true;
            } else if (sTemp.equals(optionFalseIgnoreCase)) {
                bTemp = false;
            } else {
                bFlag = false;
                System.out.println(
                        "Error: Please enter either [" + optionTrue + "] or [" + optionFalse + "] (case insensitive).");
                System.out.print("> ");
            }
        } while (bFlag == false);
        return bTemp;
    }

    public void aWait() {
        input.nextLine();
    }

    public void aClearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}