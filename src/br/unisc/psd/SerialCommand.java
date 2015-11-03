/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.psd;

/**
 *
 * @author m72589
 */
class SerialCommand {

    private static boolean lerNovo = true;

    public static String setCommand(String value) {
        
        if (!value.contains("A") && !value.contains("B") && !value.contains("C") && !value.contains("F")) {
            lerNovo = true;
        }
        if (true) {
            if (value.contains("A")) {
                lerNovo = false;
                return "0x0A";
            } else if (value.contains("B")) {
                lerNovo = false;
                return "0x0B";
            } else if (value.contains("C")) {
                lerNovo = false;
                return "0x0C";
            } else if (value.contains("F")) {
                lerNovo = false;
                return "0x0F";
            }
        }
        return null;
    }
}