package com.invsol.getfoody.fragmentadapters;

import com.invsol.getfoody.R;
import com.invsol.getfoody.dataobjects.ReviewsItem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ReviewsFragmentAdapter  extends ArrayAdapter<ReviewsItem> {

	Context context;
	int layoutResourceId;
	ReviewsItem[] reviewsItem = null;
	ViewHolder holder = null;

	public ReviewsFragmentAdapter(Context context, int layoutResourceId,
			ReviewsItem[] objects) {
		super(context, layoutResourceId, objects);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.reviewsItem = objects;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		//ViewHolder holder = null;

		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater layout_inflator = ((Activity) context)
					.getLayoutInflater();
			convertView = layout_inflator.inflate(R.layout.item_review, null);
			holder.dataCell_reviewtext = (TextView) convertView.findViewById(R.id.textview_review);
			holder.dataCell_reviewuser = (TextView) convertView
					.findViewById(R.id.textview_postedby);
			holder.dataCell_reviewposted = (TextView) convertView.findViewById(R.id.textview_postedtimeago);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
			
		}

		ReviewsItem item = reviewsItem[position];
		holder.dataCell_reviewuser.setText(item.getReview_postedby_user());
		holder.dataCell_reviewposted.setText(item.getReview_posted_timeago());
		holder.dataCell_reviewtext.setText(item.getReview_text());
			

		return convertView;
	}

	/**
	 * A class defining the view holder
	 */
	static class ViewHolder {
		private TextView dataCell_reviewtext;
		private TextView dataCell_reviewuser;
		private TextView dataCell_reviewposted;
	}

}