package health.hack.medicalinformationdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }


    public void MyHealthNotes(View view)
    {
        Intent intent = new Intent(this, PatientSelect.class);
        Intent old_intent = getIntent();
        String user = old_intent.getStringExtra("car");
        intent.putExtra("car", user);
        startActivity(intent);


    }

    public void CareNetwork(View view)
    {
        Intent intent = new Intent(this, CareNetwork.class);
        startActivity(intent);

    }
}
