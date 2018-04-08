package health.hack.medicalinformationdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void LoggedIn(View view)
    {
        Intent intent = new Intent(this, MainMenu.class);
        EditText editText2 = (EditText) findViewById(R.id.editText3);
        String user = editText2.getText().toString();
        intent.putExtra("car",user);
        startActivity(intent);

    }

}
