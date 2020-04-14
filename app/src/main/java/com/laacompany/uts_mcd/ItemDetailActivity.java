package com.laacompany.uts_mcd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.laacompany.uts_mcd.Handler.Handle;
import com.laacompany.uts_mcd.ObjectClass.Transaction;

import java.util.Objects;

public class ItemDetailActivity extends AppCompatActivity {
    private static final String EXTRA_MODE = "mode";
    private static final String EXTRA_ID = "index";
    private EditText mETName,mETDescription, mETQuantity;
    private String mode;
    private int index;

    public static Intent newIntentAdd(Context packageContext){
        Intent intent = new Intent(packageContext, ItemDetailActivity.class);
        intent.putExtra(EXTRA_MODE,"add");
        return intent;
    }

    public static Intent newIntentEdit(Context packageContext, int index){
        Intent intent = new Intent(packageContext, ItemDetailActivity.class);
        intent.putExtra(EXTRA_MODE,"edit");
        intent.putExtra(EXTRA_ID,index);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        mode = getIntent().getStringExtra(EXTRA_MODE);



        mETName = findViewById(R.id.id_edt_name);
        mETDescription = findViewById(R.id.id_edt_description);
        mETQuantity = findViewById(R.id.id_edt_quantity);

        if (mode != null && mode.equals("edit")){
            index = getIntent().getIntExtra(EXTRA_ID,0);

            mETName.setText(Handle.sTransactions.get(index).getName());
            mETDescription.setText(Handle.sTransactions.get(index).getDescription());
            mETQuantity.setText(String.valueOf(Handle.sTransactions.get(index).getQuantity()));
        }

    }


    public void Submit(View view) {

        String name = mETName.getText().toString();
        String desc = mETDescription.getText().toString();
        String quanS = mETQuantity.getText().toString();
        boolean error = false;
        if (name.isEmpty()){
            mETName.setError("Name should not be empty");
            error = true;
        }

        if (desc.isEmpty()){
            mETDescription.setError("Description should not be empty");
            error = true;
        }

        if (quanS.isEmpty()){
            mETQuantity.setError("Quantity should not be empty");
            error = true;
        }

        if (error) return;

        if (quanS.length() > 8){
            Toast.makeText(this, "Are you sure your item quantity is " + quanS + "?", Toast.LENGTH_SHORT).show();
            return;
        }

        int quan = Integer.parseInt(quanS);

        if (quan < 0){
            Toast.makeText(this, "No minus quantity", Toast.LENGTH_SHORT).show();
            return;
        }



        if (mode.equals("add")){
            Transaction transaction = new Transaction(name,desc,quan);
            Handle.sTransactions.add(transaction);
            Handle.insert(transaction);
        } else {
            Handle.sTransactions.get(index).setName(name);
            Handle.sTransactions.get(index).setDescription(desc);
            Handle.sTransactions.get(index).setQuantity(quan);
            Handle.update(Handle.sTransactions.get(index));
        }
        finish();
    }
}
