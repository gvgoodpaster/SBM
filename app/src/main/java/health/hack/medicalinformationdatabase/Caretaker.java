package health.hack.medicalinformationdatabase;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.List;

public class Caretaker {
    String name;
    String role;
    List<String> patients;
    String access_level;
    public Caretaker(String name, String role, List<String> patients, String access_level) {
        this.name = name;
        this.role = role;
        this.patients = patients;
        this.access_level = access_level;
    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)


    private String join(List<String> strings) {
        String result = "";
        result += strings.get(0);
        for (int i = 1; i < strings.size(); i++) {
            result += ", " + strings.get(i);
        }
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String toString() {
        return name + " is a " + role + " who takes care of " + join(patients);
    }
}
