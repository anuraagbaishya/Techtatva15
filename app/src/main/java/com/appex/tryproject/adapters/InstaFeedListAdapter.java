package com.appex.tryproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.appex.tryproject.R;
import com.appex.tryproject.model.instagram.InstaFeed;
import com.appex.tryproject.model.instagram.InstagramDatum;
import com.appex.tryproject.activites.ImageActivity;
import com.appex.tryproject.resources.Constants;
import com.google.gson.Gson;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by MAHE on 14-Jul-15.
 */
public class InstaFeedListAdapter extends BaseAdapter {
    InstaFeed instaFeed;
    Context context;
    LayoutInflater layoutInflater = null;
    Typeface typeface;
    Typeface typeface1;

    public InstaFeedListAdapter(Context context, InstaFeed instaFeed, Typeface typeface, Typeface typeface1) {
        this.instaFeed = instaFeed;
        this.context = context;
        this.typeface=typeface;
        this.typeface1=typeface1;
    }


    @Override
    public int getCount() {
        return instaFeed.getData().size();
    }

    @Override
    public InstagramDatum getItem(int position) {
        return instaFeed.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {

        if (convertView == null) {
            if (layoutInflater == null) {
                layoutInflater = LayoutInflater.from(context);
            }
            convertView = layoutInflater.inflate(R.layout.insta_feed_layout, parent, false);
            ViewHolder viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        final ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        final InstagramDatum instagramDatum = getItem(position);
        try {
            viewHolder.instaFeedUsernameTextView.setText("@" + instagramDatum.getUser().getUsername());
            viewHolder.instaFeedUsernameTextView.setTypeface(typeface);
            Picasso.with(context).load(instagramDatum.getUser().getProfilePicture()).into(viewHolder.instaFeedUserImageView);
            viewHolder.instaFeedTitleTextView.setText(instagramDatum.getCaption().getText());
            viewHolder.instaFeedTitleTextView.setTypeface(typeface1);
            viewHolder.instaFeedLikesTextView.setText("Likes: " + instagramDatum.getLikes().getCount());
            viewHolder.instaFeedLikesTextView.setTypeface(typeface1);
            viewHolder.instaFeedCommentsTextView.setText("Comments: " + instagramDatum.getComments().getCount());
            viewHolder.instaFeedCommentsTextView.setTypeface(typeface1);
            Picasso.with(context).load(instagramDatum.getImages().getStandardResolution().getUrl()).into(viewHolder.instaFeedImageView, new Callback() {
                @Override
                public void onSuccess() {
                    viewHolder.instaFeedProgressBar.setVisibility(View.GONE);
                    viewHolder.instaFeedImageView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onError() {

                }
            });

            viewHolder.instaFeedImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, ImageActivity.class)
                            .putExtra(Constants.INSTA_DATA, new Gson().toJson(instagramDatum)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    static class ViewHolder {
        TextView instaFeedUsernameTextView, instaFeedTitleTextView, instaFeedCommentsTextView, instaFeedLikesTextView;
        ImageView instaFeedImageView, instaFeedUserImageView;
        ProgressBar instaFeedProgressBar;

        public ViewHolder(View view) {
            instaFeedUsernameTextView = (TextView) view.findViewById(R.id.insta_feed_username_text_view);
            instaFeedTitleTextView = (TextView) view.findViewById(R.id.insta_feed_title_text_view);
            instaFeedCommentsTextView = (TextView) view.findViewById(R.id.insta_feed_comments_text_view);
            instaFeedLikesTextView = (TextView) view.findViewById(R.id.insta_feed_likes_text_view);
            instaFeedImageView = (ImageView) view.findViewById(R.id.insta_feed_image_view);
            instaFeedUserImageView = (ImageView) view.findViewById(R.id.insta_feed_user_image_view);
            instaFeedProgressBar = (ProgressBar) view.findViewById(R.id.insta_feed_progress_bar);
        }
    }
}
