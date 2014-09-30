package com.offonb.view;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetFeed;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import com.offonb.R;
import com.offonb.constants.Constants;

public class SignupActivity extends ActionBarActivity {

	// private Account account;
	private String USERNAME = "rachnakhokhar@gmail.com";
	private String PASSWORD = "rach18febprash";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);

		// Action on click of AddBrands Button
		TextView textview_addbrands = (TextView) findViewById(R.id.link_add_brands);
		textview_addbrands.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent screenChangeIntent = null;
				screenChangeIntent = new Intent(SignupActivity.this,
						AddBrandsActivity.class);
				SignupActivity.this.startActivity(screenChangeIntent);
			}
		});

		// Action on click of AddBrands Button
		TextView textview_addcategories = (TextView) findViewById(R.id.link_add_categories);
		textview_addcategories.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent screenChangeIntent = null;
				screenChangeIntent = new Intent(SignupActivity.this,
						AddCategoriesActivity.class);
				SignupActivity.this.startActivity(screenChangeIntent);
			}
		});

		new SpreadsheetTask().execute();
	}

	class SpreadsheetTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			SpreadsheetService spreadsheet_service = new SpreadsheetService("OffOnB");
			spreadsheet_service.setProtocolVersion(SpreadsheetService.Versions.V3);
			try {
				spreadsheet_service.setUserCredentials(USERNAME, PASSWORD);
				URL metafeedUrl = new URL(
						"https://spreadsheets.google.com/feeds/spreadsheets/private/full");
				SpreadsheetFeed feed = spreadsheet_service.getFeed(metafeedUrl,
						SpreadsheetFeed.class);
				SpreadsheetEntry offonbSpreadsheet = null;
				List<SpreadsheetEntry> spreadsheets = feed.getEntries();
				for (SpreadsheetEntry spreadsheet : spreadsheets) {
					System.out.println(spreadsheet.getTitle().getPlainText());
					if (spreadsheet
							.getTitle()
							.getPlainText()
							.equals(Constants.SPREADSHEET_REGISTER_BUSINESSUSER)) {
						offonbSpreadsheet = spreadsheet;
						break;
					}
				}

				// Get the first worksheet of the first spreadsheet.
			    // TODO: Choose a worksheet more intelligently based on your
			    // app's needs.
			    WorksheetFeed worksheetFeed = spreadsheet_service.getFeed(
			    		offonbSpreadsheet.getWorksheetFeedUrl(), WorksheetFeed.class);
			    List<WorksheetEntry> worksheets = worksheetFeed.getEntries();
			    WorksheetEntry worksheet = worksheets.get(0);

			    // Fetch the list feed of the worksheet.
			    URL listFeedUrl = worksheet.getListFeedUrl();
			    ListFeed listFeed = spreadsheet_service.getFeed(listFeedUrl, ListFeed.class);

			    // Create a local representation of the new row.
			    ListEntry row = new ListEntry();
			    row.getCustomElements().setValueLocal(Constants.SPREADSHEET_REGISTER_STORE_NAME, "Pantaloons");
			    row.getCustomElements().setValueLocal(Constants.SPREADSHEET_REGISTER_STORE_ADDRESS, "60/1, Temple Road, Civil Lines, Opp. Science College Ground");
			    row.getCustomElements().setValueLocal(Constants.SPREADSHEET_REGISTER_STORE_COUNTRY, "India");
			    row.getCustomElements().setValueLocal(Constants.SPREADSHEET_REGISTER_STORE_STATE, "Maharashtra");
			    row.getCustomElements().setValueLocal(Constants.SPREADSHEET_REGISTER_STORE_CITY, "Nagpur");
			    row.getCustomElements().setValueLocal(Constants.SPREADSHEET_REGISTER_BUSINESSUSER_NAME, "Rachna");
			    row.getCustomElements().setValueLocal(Constants.SPREADSHEET_REGISTER_BUSINESSUSER_EMAIL, "rachnakhokhar@pantaloons.com");
			    row.getCustomElements().setValueLocal(Constants.SPREADSHEET_REGISTER_BUSINESSUSER_PHONENUMBER, "9876543210");

			    // Send the new row to the API for insertion.
			    row = spreadsheet_service.insert(listFeedUrl, row);

			} catch (AuthenticationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

	}
}
