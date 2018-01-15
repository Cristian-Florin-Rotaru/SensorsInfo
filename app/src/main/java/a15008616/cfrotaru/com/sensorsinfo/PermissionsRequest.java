package a15008616.cfrotaru.com.sensorsinfo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;



public class PermissionsRequest {
    Context context;

    public PermissionsRequest(Context context) {
        this.context = context;
    }
    //Method to ask for external read/write permission
    public void externalRWPermission() {

        // Permission for writing on external storage (not needed yet)
        int permissionCheck = ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            // ask permissions here using below code
            int REQUEST_CODE = 1;
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE);
        }
    }
}
