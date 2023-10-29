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

import androidx.annotation.NonNull;

import com.example.attendancesystem.db.DBAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewFacultyActivity extends Activity {

	ArrayList<FacultyBean> facultyBeanList;
	private ListView listView ;
	private ArrayAdapter<String> listAdapter;

	DBAdapter dbAdapter = new DBAdapter(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.__listview_main);

//
		FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference facultyRef = database.getReference("faculty");
//

		listView=(ListView)findViewById(R.id.listview);
		final ArrayList<String> facultyList = new ArrayList<String>();

//		facultyBeanList=dbAdapter.getAllFaculty();
//
//		for(FacultyBean facultyBean : facultyBeanList)
//		{
//			String users = " FirstName: " + facultyBean.getFaculty_firstname()+"\nLastname:"+facultyBean.getFaculty_lastname();
//
//			facultyList.add(users);
//			Log.d("users: ", users);
//
//		}
		facultyRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				ArrayList<String> facultyList = new ArrayList<String>();
				for (DataSnapshot facultySnapshot : dataSnapshot.getChildren()) {
					FacultyBean facultyBean = facultySnapshot.getValue(FacultyBean.class);
					String users = " FirstName: " + facultyBean.getFaculty_firstname() + "\nLastname:" + facultyBean.getFaculty_lastname();
					facultyList.add(users);
					Log.d("users: ", users);
				}
				listAdapter = new ArrayAdapter<String>(ViewFacultyActivity.this, R.layout.view_faculty_list, R.id.labelF, facultyList);
				listView.setAdapter(listAdapter);
			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {
				Log.e("ViewFacultyActivity", "Failed to retrieve faculty list", databaseError.toException());
				Toast.makeText(getApplicationContext(), "Failed to retrieve faculty list", Toast.LENGTH_LONG).show();
			}
		});



//		listAdapter = new ArrayAdapter<String>(this, R.layout.view_faculty_list, R.id.labelF, facultyList);
//		listView.setAdapter( listAdapter );

		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int position, long arg3) {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewFacultyActivity.this);
				alertDialogBuilder.setTitle(getTitle() + "decision");
				alertDialogBuilder.setMessage("Are you sure?");
				alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						if (!facultyList.isEmpty() && position < facultyList.size()) {
							String key = facultyBeanList.get(position).getKey();
							facultyRef.child(key).removeValue();
							facultyList.remove(position);
							listAdapter.notifyDataSetChanged();
						}
					}

				});

				alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// cancel the alert box and put a Toast to the user
						dialog.cancel();
						Toast.makeText(getApplicationContext(), "You choose cancel", Toast.LENGTH_LONG).show();
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
