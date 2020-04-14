package com.laacompany.uts_mcd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.laacompany.uts_mcd.Adapter.ItemAdapter;
import com.laacompany.uts_mcd.Handler.Handle;
import com.laacompany.uts_mcd.ObjectClass.Transaction;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    ItemAdapter adapter;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.id_new_item){
            startActivity(ItemDetailActivity.newIntentAdd(this));
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.id_main_activity_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Handle.init(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateAdapter();
    }

    private void updateAdapter(){

        ArrayList<Transaction> transactions = Handle.sTransactions;

        if (adapter == null){
            adapter = new ItemAdapter(this, transactions);
            mRecyclerView.setAdapter(adapter);
        } else {
            adapter.setTransactions(transactions);
            adapter.notifyDataSetChanged();
        }

    }
}
