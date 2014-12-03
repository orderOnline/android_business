package com.invsol.getfoody.view;

import com.invsol.getfoody.R;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.TextView;

public class LegalActivity extends ActionBarActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_legal);
		
		final CheckBox chkbox1 = (CheckBox)findViewById(R.id.checkBox1);
		final CheckBox chkbox2 = (CheckBox)findViewById(R.id.checkBox2);
		final CheckBox chkbox3 = (CheckBox)findViewById(R.id.checkBox3);
		final CheckBox chkbox4 = (CheckBox)findViewById(R.id.checkBox4);
		
		TextView btn_next = (TextView)findViewById(R.id.btn_next);
		btn_next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				if(chkbox1.isChecked() &&
					chkbox2.isChecked() &&
					chkbox3.isChecked() &&
					chkbox4.isChecked()){
					Intent screenChangeIntent = null;
					screenChangeIntent = new Intent(LegalActivity.this,
							MenuActivity.class);
					LegalActivity.this.startActivity(screenChangeIntent);
				}else{
					AlertDialog.Builder builder = new AlertDialog.Builder(
							LegalActivity.this);
					builder.setTitle(getResources().getString(R.string.info));
					builder.setMessage(getResources().getString(R.string.text_legal_required));
					builder.setPositiveButton(getResources().getString(R.string.OK),
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog, int which) {
									dialog.cancel();
								}
							});
					AlertDialog alertDialog = builder.create();
					alertDialog.show();
				}
			}
		});
	}

}
