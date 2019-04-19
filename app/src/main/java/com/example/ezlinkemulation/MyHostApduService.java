package com.example.ezlinkemulation;

import android.content.Intent;
import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class MyHostApduService extends HostApduService {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    public byte[] processCommandApdu(byte[] apdu, Bundle extras) {
        //Intent intent = new Intent(this, ToCall.class);
        String message = ByteArrayToHexString(apdu);
        Log.d("ezLinkTest", message, null);

        //intent.putExtra(EXTRA_MESSAGE, message);
        //startActivity(intent);

        String info;
        if(message.equals("00A4000C02E103")) {
            info = "6a86";
        }
        else if(message.equals("00B000000F")) {
            info = "6986";
        }
        else //903203000000
        {
           info = "020300037C0013881009722012046318519405A078BA010928041D53002841FF750003E82B45B96A1E2003D001017600005E2DA2F68C42555320203537000200010008000002020000000000000000000000000000000000000000000000019000";
        }

        return HexStringToByteArray(info);
    }
    @Override
    public void onDeactivated(int reason) {

    }

    public static byte[] HexStringToByteArray(String s) throws IllegalArgumentException {
        int len = s.length();
        if (len % 2 == 1) {
            throw new IllegalArgumentException("Hex string must have even number of characters");
        }
        byte[] data = new byte[len / 2]; // Allocate 1 byte per 2 hex characters
        for (int i = 0; i < len; i += 2) {
            // Convert each character into a integer (base-16), then bit-shift into place
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static String ByteArrayToHexString(byte[] bytes) {
        final char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        char[] hexChars = new char[bytes.length * 2]; // Each byte has two hex characters (nibbles)
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF; // Cast bytes[j] to int, treating as unsigned value
            hexChars[j * 2] = hexArray[v >>> 4]; // Select hex character from upper nibble
            hexChars[j * 2 + 1] = hexArray[v & 0x0F]; // Select hex character from lower nibble
        }
        return new String(hexChars);
    }
}


