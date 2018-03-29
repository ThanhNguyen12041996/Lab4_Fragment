package com.example.minhthanh.listview_lab3_androidth;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcel;
import org.parceler.Parcels;

import java.util.List;


public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder>  {

    // Store a member variable for the contacts
    public List<Profile> mContacts;
    // Store the context for easy access
    private Context mContext;
    private String path = "https://image.tmdb.org/t/p/w500/";

    // Pass in the contact array into the constructor
    public ContactsAdapter(Context context, List<Profile> contacts) {
        mContacts = contacts;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_contact, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView,mContext,mContacts);
        return viewHolder;
    }

    // Involves populating data into the Profile through holder
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Profile contact = mContacts.get(position);

        TextView textView = viewHolder.nameTextView;
        TextView textViewTitle = viewHolder.titleTextView;
        ImageView PosterFilm = viewHolder.Poster;

        textViewTitle.setText(contact.getTitle());
        textView.setText(contact.getOverview());
        Picasso.with(getContext()).load(path+contact.getPoster_path()).into(PosterFilm);

        if(textView.length() >= 150) {
            textView.setText(textView.getText().toString().substring(0,150));
            textView.append("[...]");
        }
        else {
            textView.setText(textView.getText().toString());
        }
    }

    @Parcel
    public static class User {
        // fields must be package private
        String Background,Original_Title,Date,VideoDiscription,VoteAverage,Video;


        public User() {
        }

        public User(String Background, String Original_Title, String Date, String VideoDiscription, String VoteAverage,String Video) {
            this.Background = Background;
            this.Original_Title = Original_Title;
            this.Date = Date;
            this.VideoDiscription = VideoDiscription;
            this.VoteAverage = VoteAverage;
            this.Video = Video;
        }
    }
    // Returns the total count of Profiles in the list
    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView titleTextView;
        public ImageView Poster;
        Context ctx;
        List<Profile> ProfileList ;

        // We also create a constructor that accepts the entire Profile row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView, Context ctx, List<Profile> ProfileList) {
            // Stores the ProfileView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
           itemView.setOnClickListener(this);
            this.ctx = ctx;
            this.ProfileList = ProfileList;

            nameTextView = (TextView) itemView.findViewById(R.id.contact_name);
           titleTextView = (TextView) itemView.findViewById(R.id.textView);
            Poster = (ImageView) itemView.findViewById(R.id.imageView);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            Profile Profile = this.ProfileList.get(position);
            User user = new User(path+Profile.getBackdrop_path(),Profile.getOriginal_title(),Profile.getRelease_date(),Profile.getOverview(),Profile.getVote_average(),Profile.getvideo());
            Intent i = new Intent(ctx,FilmDetail.class);
            i.putExtra("Infor", Parcels.wrap(user));
            this.ctx.startActivity(i);

        }
    }
}
