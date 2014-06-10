package info.adamjsmith.databases;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;


public class DatabasesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        DBAdapter db = new DBAdapter(this);
        
        db.open();
        long id = db.insertContact("Adam Smith", "adam.smith18@ntlworld.com");
        id = db.insertContact("Mary Jackson", "Mary@jackson.com");
        db.close();
        
        
    }
}
