package com.example.maya14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MainActivity extends AppCompatActivity {

    String previousText,
    line,
    newText;

    EditText inputText;

    TextView textView;

    FileOutputStream fos;

    OutputStreamWriter osw;

    BufferedWriter bw;

    FileInputStream fis;

    Intent si;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputText = findViewById(R.id.inputText);
        textView = findViewById(R.id.textView);
        read();
    }

    /**
     * the function creates an option menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    /**
     * the function activates the menu
     */
    public boolean onOptionsItemSelected(MenuItem item)
    {
        String st = item.getTitle().toString();
        if(st.equals("Credits"))
        {
            si = new Intent(this,Credits.class);
            startActivity(si);
        }
        return true;
    }

    /**
     * the function reads the text saved in the internal file
     */
    public void read() {
        try {
            fis= openFileInput("test.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        }
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        StringBuffer sb = new StringBuffer();
        try {
            line = br.readLine();
            while (line != null) {
                sb.append(line+'\n');
                line = br.readLine();
            }
            previousText=sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        textView.setText(previousText);
    }

    /**
     * the function saves the input text in the internal file
     *
     * @param view the view
     */
    public void saveText(View view) {
        try {
            fos = openFileOutput("test.txt",MODE_PRIVATE);
            osw = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osw);
            newText= String.valueOf(inputText.getText());
            bw.write(previousText + newText);
            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * the function saves the input text in the internal file and exits the program
     *
     * @param view the view
     */
    public void saveAndExit(View view) {
        saveText(view);
        finish();
    }

    /**
     * the function will reset the history of the internal file
     * (clearing all the text from the file)
     *
     * @param view the view
     */
    public void resetHistory(View view){
        try {
            fos = openFileOutput("test.txt",MODE_PRIVATE);
            osw = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osw);
            previousText="";
            newText="";
            textView.setText("");
            bw.write("");
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}