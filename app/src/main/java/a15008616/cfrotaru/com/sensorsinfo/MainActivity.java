package a15008616.cfrotaru.com.sensorsinfo;

import android.content.Context;
import android.hardware.Sensor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;



public class MainActivity extends AppCompatActivity {


    TextView nameTxtView ,vendorTxtView, versionTxtView, typeTxtView, maxRangeTxtView, resolutionTxtView, powerTxtView, minDelayTxtView;
    Button nextButton, previousButton;
    int sensorsNumber;
    int currentPosition = 0;
    int nextPosition = 1;
    int previousPosition;
    String[][] sensorsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameTxtView         = (TextView) findViewById(R.id.nameTxtView);
        vendorTxtView       = (TextView) findViewById(R.id.vendorTxtView);
        versionTxtView      = (TextView) findViewById(R.id.versionTxtView);
        typeTxtView         = (TextView) findViewById(R.id.typeTxtView);
        maxRangeTxtView     = (TextView) findViewById(R.id.maxRangeTxtView);
        resolutionTxtView   = (TextView) findViewById(R.id.resolutionTxtView);
        powerTxtView        = (TextView) findViewById(R.id.powerTxtView);
        minDelayTxtView     = (TextView) findViewById(R.id.minDelayTxtView);
        nextButton          = (Button)   findViewById(R.id.nextButton);
        previousButton      = (Button)   findViewById(R.id.previousButton);




        PermissionsRequest pr = new PermissionsRequest(this);
        pr.externalRWPermission();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);



        SensorInfoRetrieve sir = new SensorInfoRetrieve(this);
        SensorInfo si = new SensorInfo();
        sensorsArray = si.sensors(sir.getSystemSensorInfo());
        sensorsNumber = sensorsArray.length;


        //Default Sensor displayed on the app is opened
        updateInfo(currentPosition, nextPosition, sensorsNumber - 1);

        //Hides buttons when there is no use for them
        if(sensorsNumber < 3) previousButton.setVisibility(View.GONE);
        if(sensorsNumber < 2) nextButton.setVisibility(View.GONE);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nextPosition + 1 > sensorsNumber - 1) {
                    nextPosition = 0;
                    previousPosition = sensorsNumber - 2;
                    currentPosition = sensorsNumber - 1;
                }else
                    if (currentPosition == 0){
                    previousPosition = 0;
                    nextPosition = 2;
                    currentPosition = 1;
                }else
                    if (currentPosition + 1 > sensorsNumber - 1 )
                    {
                        currentPosition = 0;
                        nextPosition = 1;
                        previousPosition = sensorsNumber -1;

                    }else
                    {
                        previousPosition++;
                        nextPosition++;
                        currentPosition++;
                    }
                updateInfo(currentPosition,nextPosition,previousPosition);

            }

        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (previousPosition - 1 < 0) {
                    nextPosition--;
                    previousPosition = sensorsNumber - 1;
                    currentPosition--;
                }else
                if (currentPosition == 0){
                    previousPosition = sensorsNumber -3;
                    nextPosition = 0;
                    currentPosition = sensorsNumber - 2;
                }
                if (nextPosition - 1 < 0){
                    currentPosition = sensorsNumber - 2;
                    nextPosition = sensorsNumber -1;
                    previousPosition--;
                }
                else
                {
                    previousPosition--;
                    nextPosition--;
                    currentPosition--;

                }
                updateInfo(currentPosition,nextPosition,previousPosition);
            }

        });





        saveFile(this);

    }

    //Method to write sensors information on internal storage
    //If the sensors.txt file is written on internal storage,
    //it will be read and written to external storage as well
    //(Used to test if the file on internal storage is written as expected
    public void saveFile(Context context) {
        SensorInfoWriteFile siwf = new SensorInfoWriteFile(context);
        SensorInfoRetrieve sir = new SensorInfoRetrieve(context);
        SensorInfo si = new SensorInfo();

        List<Sensor> sensorsList = sir.getSystemSensorInfo();

        //checks if there is a sensors file on internal storage
        //if not, it creates one
        if (!siwf.internalFileExists())
            siwf.writeFile(si.sensors(sensorsList));
        //checks if there is a sensors file on internal storage, if it is
        //and on external storage is missing, it copies the data from internal
        // to external storage in downloads folder
        if (siwf.internalFileExists() && !siwf.externalFileExists())
            siwf.writeFileExternal(sir.getFileSensorInfo());


    }

    public void updateInfo(int currentPosition, int nextPosition, int previousPosition){
        nameTxtView.setText("Name: " + sensorsArray[currentPosition][0] + currentPosition);
        vendorTxtView.setText("Vendor: " + sensorsArray[currentPosition][1] + sensorsNumber);
        versionTxtView.setText("Version: " + sensorsArray[currentPosition][2]);
        typeTxtView.setText("Type: " + sensorsArray[currentPosition][3]);
        maxRangeTxtView.setText("Max Range: " + sensorsArray[currentPosition][4]);
        resolutionTxtView.setText("Resolution: " + sensorsArray[currentPosition][5]);
        powerTxtView.setText("Power: " + sensorsArray[currentPosition][6]+"mAh");
        minDelayTxtView.setText("Min Delay: " + sensorsArray[currentPosition][7]);
        nextButton.setText(sensorsArray[nextPosition][0] + nextPosition);
        previousButton.setText(sensorsArray[previousPosition][0] + previousPosition);
    }

}
