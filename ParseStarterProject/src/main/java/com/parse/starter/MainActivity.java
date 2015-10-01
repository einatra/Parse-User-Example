/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseUser;


public class MainActivity extends AppCompatActivity {

  EditText userName, pass;
  Button btnIn;
  TextView signUp;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

  initViews();

  signUp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      Intent intent = new Intent(MainActivity.this, SignUp.class);
      startActivity(intent);
    }
  });

    btnIn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            logIn();
        }
    });

    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }
    public void logIn() {
        ParseUser.logInInBackground(userName.getText().toString(), pass.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, com.parse.ParseException e) {
                if (user != null) {
                    Toast.makeText(MainActivity.this, "You are looged in!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Welcome.class);
                    startActivity(intent);

                } else {
                    // Signup failed. Look at the ParseException to see what happened.
                }
            }
        });
    }


  private void initViews(){
    userName  = (EditText) findViewById(R.id.mainUserNEdit);
    pass = (EditText) findViewById(R.id.mainPassEdit);
    btnIn = (Button) findViewById(R.id.mainInBtn);
    signUp = (TextView) findViewById(R.id.mainSignText);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
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


}

