package a15008616.cfrotaru.com.sensorsinfo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        // Permission for writing on external storage (not needed yet)
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            // ask permissions here using below code
            int REQUEST_CODE = 1;
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE);
        }


        SensorManager oSM = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorsList = oSM.getSensorList(Sensor.TYPE_ALL);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testFile(sensorsList, this);

    }

    //Method to write a text file in the downloads folder on external storage containing sensors information
    public void testFile(List<Sensor> sensorsList, Context context) {
        SensorInfoWriteFile siwf = new SensorInfoWriteFile(context);
        SensorInfoRetrieve sir = new SensorInfoRetrieve(context);

        SensorInfo si = new SensorInfo();
        if (!siwf.internalFileExists())
            siwf.writeFile(si.sensors(sensorsList));

        if (!siwf.externalFileExists())
            siwf.writeFileExternal(si.sensors(sensorsList));

        if (siwf.externalFileExists())
            siwf.writeFileExternal(sir.getSensorInfo());


    }
    //test textView
    public void txtViewChange(String str){
        TextView txtPath = (TextView) this.findViewById(R.id.textView);
        txtPath.setText(str);
    }

}
