package com.xykj.myapplication;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.security.MessageDigest;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String str = getSHA1(this);
        Log.d("at22", str);

    }
    public static String getSHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName()
                    , PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] signatures = md.digest(cert);
            StringBuffer sha1 = new StringBuffer();
            int i = 0;
            for (byte key : signatures) {
                String appendString = Integer.toHexString(0xFF & key).toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    sha1.append("0");
                sha1.append(appendString);
                if (signatures.length - 1 == i)
                    break;
                sha1.append(":");
                i++;
            }
            return sha1.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
