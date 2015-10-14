package edu.kvcc.cis298.workwithclasses.workwithclasses;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    private TextView mMessage;
    private boolean mBeenThereDoneThat;

    public static Intent newIntent(Context packageContext, String theMessage){
        Intent i = new Intent(packageContext, SubActivity.class);
        i.putExtra("the_sub_key", theMessage);
        return i;
    }

    public static boolean userWentToSubActivity( Intent result){
        //returning result you are getting from the intent; declared in sub activity as been_there
        return result.getBooleanExtra("been_there", false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        mMessage = (TextView) findViewById(R.id.message);

        String theMessageString = getIntent().getStringExtra("the_sub_key");
        mMessage.setText(theMessageString);

        mBeenThereDoneThat = true;

        Intent data = new Intent();
        data.putExtra("been_there", mBeenThereDoneThat);
        //set result will pass the result back to the previous activity
        //result ok will get sent to main activity
        //since main actiity will not know been_there, we write another static method so it knows
        //the key
        setResult(RESULT_OK, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub, menu);
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
