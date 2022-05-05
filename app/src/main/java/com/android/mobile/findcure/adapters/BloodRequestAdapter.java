package com.android.mobile.findcure.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.mobile.findcure.R;
import com.android.mobile.findcure.viewmodels.CustomUserData;

import java.util.List;


public class BloodRequestAdapter extends RecyclerView.Adapter<BloodRequestAdapter.PostHolder> {


    private List<CustomUserData> postLists;
    private Context context;


    public class PostHolder extends RecyclerView.ViewHolder {
        TextView Name, bloodgroup, Address, contact, posted;
        Button contactBtn, shareBtn;

        public PostHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.reqstUser);
            contact = itemView.findViewById(R.id.targetCN);
            bloodgroup = itemView.findViewById(R.id.targetBG);
            Address = itemView.findViewById(R.id.reqstLocation);
            posted = itemView.findViewById(R.id.posted);
            contactBtn = itemView.findViewById(R.id.contactButton);
            shareBtn = itemView.findViewById(R.id.shareButton);

        }
    }

    public BloodRequestAdapter(List<CustomUserData> postLists, Context context) {
        this.postLists = postLists;
        this.context = context;
    }

    @Override
    public PostHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View listitem = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.request_list_item, viewGroup, false);

        return new PostHolder(listitem);
    }

    @Override
    public void onBindViewHolder(PostHolder postHolder, int i) {

//        if(i%2==0)
//        {
//            postHolder.itemView.setBackgroundColor(Color.parseColor("#C13F31"));
//        }
//        else
//        {
//            postHolder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
//        }

        final CustomUserData customUserData = postLists.get(i);
        postHolder.Name.setText(customUserData.getName());
        postHolder.Address.setText(customUserData.getAddress() + ", " + customUserData.getDivision());
        postHolder.bloodgroup.setText(customUserData.getBloodGroup());
        postHolder.posted.setText(customUserData.getTime() + ", " + customUserData.getDate());
        postHolder.contact.setText(customUserData.getContact());

        postHolder.shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Blood is needed for: " +
                        customUserData.getName() + "\n"
                        + "Address: " + customUserData.getAddress() + ", "
                        + customUserData.getDivision() + "\n"
                        + "Contact number: " + customUserData.getContact() + "\n"
                        + "Blood group is: " + customUserData.getBloodGroup());
                sendIntent.setType("text/plain");
                Intent.createChooser(sendIntent, "Share via Donate app");
                context.startActivity(sendIntent);
            }
        });

        postHolder.contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = ("tel:" + customUserData.getContact().trim());
                Intent mIntent = new Intent(Intent.ACTION_DIAL);
                mIntent.setData(Uri.parse(number));
                context.startActivity(mIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return postLists.size();
    }
}
