package com.termux.command;

import android.app.*;
import android.os.*;
import android.content.Intent;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class MainActivity extends Activity implements OnClickListener
{

	@Override
	public void onClick(View p1) {
		// TODO: Implement this method
		switch(p1.getId()){
			case R.id.mainButton1:
				command(result.getText().toString());
			case R.id.mainButton:
				//But idont know to force Close the Service or Session
				return;
		}
	}
	void pesan(String s){
		Toast.makeText(this, s, 1).show();
	}
	
	public EditText result;
	public Button Submit, stopserv;
	Intent termuxIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		result = findViewById(R.id.mainEditText1);
		Submit = findViewById(R.id.mainButton1);
		stopserv = findViewById(R.id.mainButton);
		Submit.setOnClickListener(this);
		stopserv.setOnClickListener(this);
		
    }
	public void command(String commands){
		try{
			termuxIntent = new Intent();
			termuxIntent.setClassName("com.termux", "com.termux.app.RunCommandService");
			termuxIntent.setAction("com.termux.RUN_COMMAND");
			termuxIntent.putExtra("com.termux.RUN_COMMAND_PATH", commands);
			termuxIntent.putExtra("com.termux.RUN_COMMAND_SESSION_CREATE_MODE", "no-session-with-name");
			termuxIntent.putExtra("com.termux.RUN_COMMAND_BACKGROUND", false);
			termuxIntent.putExtra("com.termux.RUN_COMMAND_SESSION_NAME", "custom-name");
			startService(termuxIntent);
		}catch (Exception e){
			result.setText(e.toString());
		}
	}
}
