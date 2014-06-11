package info.adamjsmith.databases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
        
        /*db.open();
        long id = db.insertContact("Adam Smith", "adam.smith18@ntlworld.com");
        id = db.insertContact("Mary Jackson", "Mary@jackson.com");
        db.close();
        
        db.open();
        Cursor c = db.getAllContacts();
        if(c.moveToFirst()) {
        	do {
        		DisplayContact(c);
        	} while (c.moveToNext());
        }
        db.close();
    }
        
    db.open();
    Cursor c = db.getContact(2);
    if (c.moveToFirst()) 
    	DisplayContact(c);
    else
    	Toast.makeText(this, "No Contact Found", Toast.LENGTH_LONG).show();
    db.close();
        
    db.open();
    if (db.updateContact(1, "Adam Smith", "new.email@example.com"))
    	Toast.makeText(this, "Update successful.", Toast.LENGTH_LONG).show();
    else
    	Toast.makeText(this,  "Update failed.", Toast.LENGTH_LONG).show();
    db.close();*/
    
        try {
        	String destPath = "/data/data/" + getPackageName() + "/databases";
        	File f = new File(destPath);
        	if (!f.exists()) {
        		f.mkdirs();
        		f.createNewFile();
        		
        		CopyDB(getBaseContext().getAssets().open("mydb"), new FileOutputStream(destPath + "MyDB"));
        	}
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }
        
        db.open();
        Cursor c = db.getAllContacts();
        if(c.moveToFirst()) {
        	do {
        		DisplayContact(c);
        	} while (c.moveToNext());
        }
        db.close();
    }
    
    public void DisplayContact(Cursor c) {
    	Toast.makeText(this, "id: " + c.getString(0) + "\n" + 
    					"name: " + c.getString(1) + "\n" +
    					"Email: " + c.getString(2), Toast.LENGTH_LONG).show();
    } 
    
    public void CopyDB(InputStream inputStream, OutputStream outputStream) throws IOException {
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = inputStream.read(buffer)) > 0) {
    		outputStream.write(buffer, 0, length);
    	}
    	inputStream.close();
    	outputStream.close();
    }
}
