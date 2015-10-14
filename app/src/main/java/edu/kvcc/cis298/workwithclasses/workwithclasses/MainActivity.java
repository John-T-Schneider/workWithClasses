package edu.kvcc.cis298.workwithclasses.workwithclasses;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private TextView mResult;
    private EditText mInput;
    private Button mSubmit;
    private Button mActivity;

    private Calc myCalc;

    private double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mResult = (TextView) findViewById(R.id.result_text_view);
        mInput = (EditText) findViewById(R.id.number_input);
        mSubmit = (Button) findViewById(R.id.submit_button);
        mActivity = (Button) findViewById(R.id.new_activity_button);

        myCalc = new Calc();

        if (savedInstanceState != null) {

            result = savedInstanceState.getDouble("theKey", 0);
            mResult.setText(Double.toString(result));

            double[] otherStuff = savedInstanceState.getDoubleArray("anotherKey");

            //log cat filter shit
            Log.i(Double.toString(otherStuff[0]), Double.toString(otherStuff[0]));
            //mInput.setText("21");

        }

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double input = Double.parseDouble(mInput.getText().toString());
                result = myCalc.multiplyByFour(input);
                mResult.setText(Double.toString(result));
            }
        });

        mActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent = pass in who you are (main activity) and we would like to start the
                //sub activity. so on click it starts sub activity.
                Intent i = SubActivity.newIntent(MainActivity.this, "This is my message");
                startActivityForResult(i, 1234);
                //1234 just needs to be a unique number so we can check it,
                // it identifies this result
            }

        });


    }

    //SAVES VALUE FOR SWITCHING ORIENTATION
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putDouble("theKey",result);
        double[] myArray = new double[3];
        myArray[0]= 2.0;
        myArray[1]= 3.0;
        myArray[2]= 4.0;
        outState.putDoubleArray("anotherKey", myArray);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK){
            //Do work because its not successful
            //Use the return statement to get out of override method
            return;
        }
        if (requestCode == 1234) {
            if (data != null){
                //Do Work, not sure what yet.
                boolean beenThere = SubActivity.userWentToSubActivity(data);
                if (beenThere){
                    Toast.makeText(MainActivity.this,"User went there", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else if (requestCode == 5678){

        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
