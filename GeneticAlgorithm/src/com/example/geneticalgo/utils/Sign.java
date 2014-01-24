/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.geneticalgo.utils;

/**
 *
 * @author Itay
 */
public enum Sign {

    Plus,
    Minus,
    Multiply,
    Devide;

    public static Sign IntToSign(int value) {
        Sign sign;

        switch (value) {
            case 0:
                sign = Sign.Plus;
                break;
            case 1:
                sign = Sign.Minus;
                break;
            case 2:
                sign = Sign.Multiply;
                break;
            case 3:
                sign = Sign.Devide;
                break;
            default:
                sign = Sign.Devide;
        }

        return sign;
    }

    public static double Calculate(double n1, double n2, Sign sign) {
        double result;

        switch (sign) {
            case Plus:
                result = n1 + n2;
                break;
            case Minus:
                result = n1 - n2;
                break;
            case Multiply:
                result = n1 * n2;
                break;
            case Devide:
                result = n1 / n2;
                break;
            default:
                result = 0;
        }

        return result;
    }

    public static String toString(Sign value) {
        String res;

        switch (value) {
            case Plus:
                res = new String("+");
                break;
            case Minus:
                res = new String("-");
                break;
            case Multiply:
                res = new String("*");
                break;
            case Devide:
                res = new String("/");
                break;
            default:
                res = new String("No such sign");
        }

        return res;
    }
}
