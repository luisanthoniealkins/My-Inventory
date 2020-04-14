package com.laacompany.uts_mcd.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laacompany.uts_mcd.ItemDetailActivity;
import com.laacompany.uts_mcd.ObjectClass.Transaction;
import com.laacompany.uts_mcd.R;

import java.util.ArrayList;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {

    private Context mContext;
    private ArrayList<Transaction> mTransactions;

    public ItemAdapter(Context context, ArrayList<Transaction> transactions){
        mContext = context;
        mTransactions = transactions;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        return new ItemHolder(layoutInflater, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        holder.bind(mTransactions.get(position),position);
    }

    @Override
    public int getItemCount() {
        return mTransactions.size();
    }

    public void setTransactions(ArrayList<Transaction> transactions){
        mTransactions = transactions;
    }


    public class ItemHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        private int position;
        private TextView mTVName,mTVDescription, mTVQuantity;

        ItemHolder(@NonNull LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item, parent, false));

            mTVName = itemView.findViewById(R.id.id_item_title);
            mTVDescription = itemView.findViewById(R.id.id_item_description);
            mTVQuantity = itemView.findViewById(R.id.id_item_quantity);

            itemView.setOnClickListener(this);
        }

        void bind(Transaction transaction, int position){
            this.position = position;

            String desc = transaction.getDescription();
            if (desc.length() > 100){
                desc = desc.substring(0,100) + "...";
            }
            String quan = "Quantity : " + transaction.getQuantity();

            mTVName.setText(transaction.getName());
            mTVDescription.setText(desc);
            mTVQuantity.setText(quan);

        }


        @Override
        public void onClick(View v) {
            Intent intent = ItemDetailActivity.newIntentEdit(mContext, position);
            mContext.startActivity(intent);
        }
    }

}
