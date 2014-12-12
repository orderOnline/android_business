package com.invsol.getfoody.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.invsol.getfoody.R;
import com.invsol.getfoody.dialogs.ExpectedDeliveryTimeDialog.ExpectedDeliveryDialogListener;

public class DeclineOrderDialog extends DialogFragment{
	
	/* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface DeclineOrderDialogListener {
        public void onDODialogPositiveClick(DialogFragment dialog);
    }
    
    // Use this instance of the interface to deliver action events
    DeclineOrderDialogListener mListener;
    
    // Override the Fragment.onAttach() method to instantiate the AddCategoryDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the AddCategoryDialogListener so we can send events to the host
            mListener = (DeclineOrderDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement DeclineOrderDialogListener");
        }
    }
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Get the layout inflater
	    LayoutInflater inflater = getActivity().getLayoutInflater();

	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
	    builder.setView(inflater.inflate(R.layout.layout_declinereason, null))
	    // Add action buttons
	           .setPositiveButton(getResources().getString(R.string.text_order_done), new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	            	// Send the positive button event back to the host activity
                       mListener.onDODialogPositiveClick(DeclineOrderDialog.this);
	               }
	           })
	           .setNegativeButton(getResources().getString(R.string.text_order_cancel), new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	            	   DeclineOrderDialog.this.getDialog().cancel();
	               }
	           });   
	    Dialog dialog = builder.create();
	    /*dialog.setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {                    //
                Button positiveButton = ((AlertDialog) dialog)
                        .getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setEnabled(false);
            }
        });  */  
	    return dialog;
	}

}
