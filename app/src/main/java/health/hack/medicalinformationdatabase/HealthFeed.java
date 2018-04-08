package health.hack.medicalinformationdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HealthFeed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(HealthFeed.this, AddNote.class);
                Intent old_intent = getIntent();
                String user = old_intent.getStringExtra("car");
                intent.putExtra("car", user);
                Intent new_intent = getIntent();
                String name = new_intent.getStringExtra("key");
                intent.putExtra("key", name);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("key");

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        ref.child("0").child("notes").addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> notes = dataSnapshot.getChildren();
                final List<Note> noteObjects = new ArrayList<Note>();
                for (DataSnapshot note : notes) {
                    String date = (String) note.child("date").getValue();
                    String time = (String) note.child("time").getValue();
                    String text = (String) note.child("text").getValue();
                    String patient = (String) note.child("patient").getValue();
                    String caretaker = (String) note.child("caretaker").getValue();
                    Log.d("PATIENT", patient);
                    if (patient.equalsIgnoreCase(name))
                    {
                        Note noteObj = new Note(date, time, text, patient, caretaker);
                        noteObjects.add(noteObj);
                    }
                }
                Collections.sort(noteObjects);

                ArrayAdapter adapter = new ArrayAdapter<Note>(HealthFeed.this,
                        R.layout.activity_listview, noteObjects);

                ListView listView = (ListView) findViewById(R.id.feedList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });


    }



}
