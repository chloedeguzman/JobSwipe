package com.example.crem.jobswipe;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Logo extends Activity {

    EditText nameTxt, emailTxt, descripTxt, tagTxt, passTxt;
    ImageView NoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        nameTxt = (EditText) findViewById(R.id.txtName);
        emailTxt = (EditText) findViewById(R.id.txtEmail);
        descripTxt = (EditText) findViewById(R.id.txtDescrip);
        tagTxt = (EditText) findViewById(R.id.txtTag);
        passTxt = (EditText) findViewById(R.id.txtPass);

        NoImage = (ImageView) findViewById(R.id.NoImage);
        final Button addBtn = (Button) findViewById(R.id.btnAdd);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getApplicationContext(), "Your logo has been added!", Toast.LENGTH_SHORT).show();
            }
        });

        NoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Company Logo"), 1);
            }

        });
    }

    public void onActivityResult(int reqCode, int resCode, Intent data) {
        if(resCode == RESULT_OK) {
            if(reqCode == 1)
                NoImage.setImageURI(data.getData());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startCheckingStuff(View view) {
        Intent intent = new Intent(this,CheckingActivity.class);
        startActivity(intent);
    }
}