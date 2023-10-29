package com.example.attendancesystem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ViewStudentByBranchYear extends Activity {

	ArrayList<StudentBean> studentBeanList;
	private ListView listView;
	private ArrayAdapter<String> listAdapter;
	String branch;
	String year;

	private FirebaseDatabase mDatabase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.__listview_main);

		mDatabase = FirebaseDatabase.getInstance();

		listView = (ListView) findViewById(R.id.listview);
		final ArrayList<String> studentList = new ArrayList<String>();

		branch = getIntent().getExtras().getString("branch");
		year = getIntent().getExtras().getString("year");

		mDatabase.getReference("student").orderByChild("student_department_year").equalTo(branch+year)
				.addChildEventListener(new ChildEventListener() {
					@Override
					public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
						StudentBean student = dataSnapshot.getValue(StudentBean.class);
						String users = " FirstName: " + student.getStudent_firstname()+"\nLastname:"+student.getStudent_lastname();
						studentList.add(users);
						listAdapter.notifyDataSetChanged();
						Log.d("users: ", users);
					}

					@Override
					public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
					}

					@Override
					public void onChildRemoved(DataSnapshot dataSnapshot) {
					}

					@Override
					public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
					}

					@Override
					public void onCancelled(DatabaseError databaseError) {
						Log.w("ViewStudentByBranchYear", "onCancelled", databaseError.toException());
						Toast.makeText(getApplicationContext(), "Failed to load student data.", Toast.LENGTH_LONG).show();
					}
				});

		listAdapter = new ArrayAdapter<String>(this, R.layout.view_student_list, R.id.label, studentList);
		listView.setAdapter(listAdapter);

		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
										   final int position, long arg3) {

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewStudentByBranchYear.this);

				alertDialogBuilder.setTitle(getTitle()+"decision");
				alertDialogBuilder.setMessage("Are you sure?");

				alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {

						studentList.remove(position);
						listAdapter.notifyDataSetChanged();
						listAdapter.notifyDataSetInvalidated();

						// delete student from database
						mDatabase.getReference("student").child(studentBeanList.get(position).getStudent_username()).removeValue();
					}

				});

				alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// cancel the alert box and put a Toast to the user
						dialog.cancel();
						Toast.makeText(getApplicationContext(), "You choose cancel",
								Toast.LENGTH_LONG).show();
					}
				});

				AlertDialog alertDialog = alertDialogBuilder.create();
				// show alert
				alertDialog.show();

				return false;
			}
		});
	}
}
