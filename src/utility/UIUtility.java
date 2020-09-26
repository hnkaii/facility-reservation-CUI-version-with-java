package utility;

import io.StandardInputReader;

public class UIUtility {

    private UIUtility() {
    }

    public static void showHeader(String title, String loginUserName,
            boolean showUserNameflg) {
        System.out.println();
        System.out.println("F.A.C.I.L.I.T.Y　ー　R.E.S.E.R.V.A.T.I.O.N　ー　S.Y.S.T.E.M");
        System.out.println("=== " + title + " ===");
        if (showUserNameflg) {
            System.out.println("User Name: " + loginUserName);
        }
        System.out.println();
    }

    public static void printMessage(String message) {
        System.out.println();
        System.out.println(message);
        System.out.println();
    }

    public static String getInputAsString(String promptMsg) {
        return StandardInputReader.getInputString(promptMsg);
    }

    public static int getInputAsInt(String promptMsg) {
        return StandardInputReader.getInputInt(promptMsg);
    }
}
