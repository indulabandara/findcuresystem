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
import com.android.mobile.findcure.viewmodels.DonorData;

import java.util.List;


public class SearchDonorAdapter extends RecyclerView.Adapter<SearchDonorAdapter.PostHolder> {


    private List<DonorData> postLists;
    private Context context;

    public class PostHolder extends RecyclerView.ViewHolder
    {
        TextView Name, Address, contact, posted, totaldonate;
        Button contactBtn, shareBtn;


        public PostHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.donorName);
            contact = itemView.findViewById(R.id.donorContact);
            totaldonate = itemView.findViewById(R.id.totaldonate);
            Address = itemView.findViewById(R.id.donorAddress);
            posted = itemView.findViewById(R.id.lastdonate);
            contactBtn = itemView.findViewById(R.id.contactButton);
            shareBtn = itemView.findViewById(R.id.shareButton);

        }
    }

    public SearchDonorAdapter(List<DonorData> postLists, Context context)
    {
        this.postLists = postLists;
        this.context = context;
    }

    @Override
    public PostHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View listitem = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.search_donor_item, viewGroup, false);

        return new PostHolder(listitem);
    }

    @Override
    public void onBindViewHolder(PostHolder postHolder, int i) {

        final DonorData donorData = postLists.get(i);
        postHolder.Name.setText(donorData.getName());
        postHolder.contact.setText(donorData.getContact());
        postHolder.Address.setText(donorData.getAddress());
        postHolder.totaldonate.setText(donorData.getTotalDonate()+" times");
        postHolder.posted.setText(donorData.getLastDonate());

        postHolder.shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Donor is available, Name is: " +
                        donorData.getName() + "\n"
                        + "Address: " + donorData.getAddress() + "\n"
                        + "Contact number: " + donorData.getContact() + "\n"
                        + "Total donation: " + donorData.getTotalDonate()+" times"+"\n"+"with your required blood group");
                sendIntent.setType("text/plain");
                Intent.createChooser(sendIntent, "Share via Donate app");
                context.startActivity(sendIntent);
            }
        });

        postHolder.contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = ("tel:" + donorData.getContact().trim());
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
