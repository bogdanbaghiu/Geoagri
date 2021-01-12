package com.bogdanbaghiu.parenthesescheck;

import java.util.Scanner;

public class ParenthesesCheck {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        scannerProcessing();
    }

    /**
     * Serves to ask the user for the string to process with parentheses
     */
    private static void scannerProcessing() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter data: ");
        String input = sc.nextLine();
        long parOpen = input.chars().filter(ch -> ch == '(').count();
        long parClose = input.chars().filter(ch -> ch == ')').count();
        if (parOpen == parClose) {
            if (parOpen == 0) {
                System.out.println("OK String");
            } else {
                if (checkParentheses((int) parOpen, input)) {
                    System.out.println("This string is not ok");
                } else {
                    System.out.println("OK String");
                }
            }
        }
    }

    /**
     * Check parentheses
     */
    private static boolean checkParentheses(int parOpen, String input) {
        String[] stringsOpen, stringsClose;
        String finalOpen;
        String firstClose;
        for (int i = 0; i < parOpen; i++) {
            stringsOpen = input.split("\\(");
            if (parOpen - i < stringsOpen.length) {
                finalOpen = stringsOpen[parOpen - i];
                if (finalOpen.contains(")")) {
                    stringsClose = finalOpen.split("\\)");
                    firstClose = stringsClose[0];
                    if (firstClose != null) {
                        input = input.replace("(" + stringsClose[0] + ")", "");
                    } else {
                        return true;
                    }
                } else {
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    }

}
