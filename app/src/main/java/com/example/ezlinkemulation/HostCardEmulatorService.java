package com.example.ezlinkemulation;

import android.content.Intent;
import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.widget.EditText;

public class MyHostApduService extends HostApduService {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    public byte[] processCommandApdu(byte[] apdu, Bundle extras) {
        Intent intent = new Intent(this, MyHostApduService.class);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    @Override
    public void onDeactivated(int reason) {

    }
}
