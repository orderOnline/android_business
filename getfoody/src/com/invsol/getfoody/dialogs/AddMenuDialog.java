package com.invsol.getfoody.dialogs;

import com.invsol.getfoody.R;
import com.invsol.getfoody.dialogs.AddCategoryDialog.AddCategoryDialogListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

public class AddMenuDialog extends DialogFragment{
	
	/* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface AddMenuDialogListener {
        public void onMenuDialogPositiveClick(DialogFragment dialog);
        public void onMenuDialogNegativeClick(DialogFragment dialog);
    }
    
    // Use this instance of the interface to deliver action events
    AddMenuDialogListener mListener;
    
    // Override the Fragment.onAttach() method to instantiate the AddMenuDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the AddMenuDialogListener so we can send events to the host
            mListener = (AddMenuDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement AddMenuDialogListener");
        }
    }
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Get the layout inflater
	    LayoutInflater inflater = getActivity().getLayoutInflater();

	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
	    builder.setView(inflater.inflate(R.layout.layout_add_menuitem, null))
	    // Add action buttons
	           .setPositiveButton(getResources().getString(R.string.text_addmenuitems_add), new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	            	// Send the positive button event back to the host activity
                       mListener.onMenuDialogPositiveClick(AddMenuDialog.this);
	               }
	           })
	           .setNegativeButton(getResources().getString(R.string.text_addmenuitems_cancel), new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	            	   AddMenuDialog.this.getDialog().cancel();
	               }
	           });      
	    return builder.create();
	}


}
