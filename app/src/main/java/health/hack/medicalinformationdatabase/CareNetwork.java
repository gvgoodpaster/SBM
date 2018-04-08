package health.hack.medicalinformationdatabase;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CareNetwork extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_network);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        ref.child("0").child("caretakers").addValueEventListener(new ValueEventListener() {
            @TargetApi(Build.VERSION_CODES.O)
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                Iterable<DataSnapshot> caretakers = dataSnapshot.getChildren();
                final List<Caretaker> caretakerObjects = new ArrayList<Caretaker>();
                for (DataSnapshot caretaker : caretakers) {
                    String name = (String) caretaker.child("name").getValue();
                    String role = (String) caretaker.child("role").getValue();
                    String accessLevel = (String) caretaker.child("access_level").getValue();
                    HashMap<String, String> patientsMap = (HashMap) caretaker.child("patients").getValue();
                    List<String> patients = new ArrayList<String>(patientsMap.keySet());
                    Caretaker caretakerObj = new Caretaker(name, role, patients, accessLevel);
                    caretakerObjects.add(caretakerObj);
                }
                for (Caretaker caretakerObj : caretakerObjects) {
                    Log.d("READ_SUCCESS", caretakerObj.toString());
                }
                ArrayAdapter adapter = new ArrayAdapter<Caretaker>(CareNetwork.this,
                        R.layout.activity_listview, caretakerObjects);

                ListView listView = (ListView) findViewById(R.id.mobile_list);
                listView.setAdapter(adapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });




    }
}
