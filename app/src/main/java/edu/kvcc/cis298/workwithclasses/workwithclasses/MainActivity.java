package edu.kvcc.cis298.workwithclasses.workwithclasses;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView mResult;
    private EditText mInput;
    private Button mSubmit;

    private Calc myCalc;

    private double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mResult = (TextView) findViewById(R.id.result_text_view);
        mInput = (EditText) findViewById(R.id.number_input);
        mSubmit = (Button) findViewById(R.id.submit_button);

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
