package health.hack.medicalinformationdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class PatientSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_select);
    }



    public void LoggedIn(View view)
    {
        Intent intent = new Intent(this, HealthFeed.class);
        EditText editText3 = (EditText) findViewById(R.id.editText3);
        String name = editText3.getText().toString();
        intent.putExtra("key", name);
        Intent old_intent = getIntent();
        String user = old_intent.getStringExtra("car");
        intent.putExtra("car", user);
        startActivity(intent);
    }

}
