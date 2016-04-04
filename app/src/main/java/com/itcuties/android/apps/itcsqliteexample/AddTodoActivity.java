package com.itcuties.android.apps.itcsqliteexample;

import com.itcuties.android.apps.itcsqliteexample.sql.TodoDAO;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTodoActivity extends Activity implements OnClickListener {

	// GUI components
	private EditText todoText;		// Text field
	private Button addNewButton;	// Add new button
	private Button backButton;		// Back button
	private EditText priority;  	// Text field
	
	// DAO
	private TodoDAO dao;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_todo);
	
		// Create DAO object
		dao = new TodoDAO(this);
		
		todoText 		= (EditText)findViewById(R.id.newTodoText);
		addNewButton 	= (Button)findViewById(R.id.addNewTodoButton);
		backButton		= (Button)findViewById(R.id.menuGoBackButton);
		priority 		= (EditText)findViewById(R.id.newPriortity);

		addNewButton.setOnClickListener(this);
		backButton.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// If add button was clicked
		if (addNewButton.isPressed()) {
			// Get entered text
			String todoTextValue = todoText.getText().toString();
			todoText.setText("");
			Integer todoPriorityValue = Integer.parseInt(priority.getText().toString());
			priority.setText("");


			
			// Add text to the database
			dao.createTodo(todoTextValue, todoPriorityValue);
			
			// Display success information
			Toast.makeText(getApplicationContext(), "New TODO added!", Toast.LENGTH_LONG).show();
			
		} else if (backButton.isPressed()) {
			// When back button is pressed
			// Create an intent
			Intent intent = new Intent(this, MainActivity.class);
			// Start activity
			startActivity(intent);
			// Finish this activity
			this.finish();
			
			// Close the database
			dao.close();
		}
		
	}
	
}
