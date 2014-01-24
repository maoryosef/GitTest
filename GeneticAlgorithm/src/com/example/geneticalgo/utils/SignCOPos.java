/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.geneticalgo.utils;

/**
 *
 * @author Itay
 */
public enum SignCOPos {
    Beginning,
    End;

     public static SignCOPos IntToPos(int value) {
        SignCOPos pos;

        switch (value) {
            case 0:
                pos = SignCOPos.Beginning;
                break;
            case 1:
                pos = SignCOPos.End;
                break;
            default:
                pos = SignCOPos.End;
        }

        return pos;
     }
}
