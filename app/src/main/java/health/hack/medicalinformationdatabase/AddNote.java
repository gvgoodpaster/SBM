package health.hack.medicalinformationdatabase;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

public class AddNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onClick(View view)
    {
        Intent old_intent = getIntent();
        String doctor = old_intent.getStringExtra("car");
        Intent new_intent = getIntent();
        String user = new_intent.getStringExtra("key");
        EditText editText = (EditText) findViewById(R.id.editText);
        String note = editText.getText().toString();
        AddNote yes = new AddNote();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        yes.writeNewNote(note, user, doctor, ref);
        Intent intent = new Intent(this, HealthFeed.class);
        intent.putExtra("key",user);
        intent.putExtra("car", doctor);
        startActivity(intent);

    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void writeNewNote(String text, String patient, String caretaker, DatabaseReference ref) {

        String newID = Integer.toString(ThreadLocalRandom.current().nextInt(0, 100000));
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time = new SimpleDateFormat( "HH:mm");
        Note newNote = new Note(date.format(cal.getTime()).toString(), time.format(cal.getTime()).toString(), text, patient, caretaker);
        //ref.child("0").child("notes").setValue(newID);
        ref.child("0").child("notes").child(newID).setValue(newNote);

    }
}