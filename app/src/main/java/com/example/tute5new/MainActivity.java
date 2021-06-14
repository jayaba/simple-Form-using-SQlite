package com.example.tute5new;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText editText1,editText2,editText3,editText4,editText5;
Button btn1,btn2,btn3,btn4;
    DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1=findViewById(R.id.editTextTextPersonName);
        editText2=findViewById(R.id.editTextTextPersonName2);
        editText3=findViewById(R.id.editTextTextPersonName3);
        editText4=findViewById(R.id.editTextTextPersonName4);
        editText5=findViewById(R.id.editTextTextPersonName5);
        btn1=findViewById(R.id.button);
        btn2=findViewById(R.id.button2);
        btn3=findViewById(R.id.button3);
        btn4=findViewById(R.id.button4);
        dbHelper=new DbHelper(this);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int emId= Integer.parseInt(editText1.getText().toString());
                String nameE=editText2.getText().toString();
                String addressE=editText3.getText().toString();
                int ageE= Integer.parseInt(editText4.getText().toString());
                String positionE=editText5.getText().toString();

                Boolean checkingInsertData=dbHelper.insertData(emId,nameE,addressE,ageE,positionE);
                if(checkingInsertData==true){
                    Toast.makeText(MainActivity.this,"Data Saved successfully",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Data not Saved",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int emId= Integer.parseInt(editText1.getText().toString());
                String nameE=editText2.getText().toString();
                String addressE=editText3.getText().toString();
                int ageE= Integer.parseInt(editText4.getText().toString());
                String positionE=editText5.getText().toString();

                Boolean checkingUpdateData=dbHelper.updateData(emId,nameE,addressE,ageE,positionE);
                if(checkingUpdateData==true){
                    Toast.makeText(MainActivity.this,"Data Updated successfully",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_SHORT).show();
                }
            }

        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=dbHelper.getData();
if(res.getCount()==0){
    Toast.makeText(MainActivity.this,"No data entered before",Toast.LENGTH_SHORT).show();
 return;
}
StringBuffer buffer=new StringBuffer();
while (res.moveToNext()){
    buffer.append("Emplore id "+res.getString(0)+"\n");
    buffer.append("Emplore name "+res.getString(1)+"\n");
    buffer.append("Emplore address "+res.getString(2)+"\n");
    buffer.append("Emplore age "+res.getString(3)+"\n");
    buffer.append("Emplore position "+res.getString(4)+"\n");


}
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
builder.setCancelable(true);
builder.setTitle("Previous Data");
builder.setMessage(buffer.toString());
builder.show();
            }
        });
btn4.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String nameE=editText2.getText().toString();
        Boolean checkDeleteData=dbHelper.DeleteData(nameE);

        if(checkDeleteData==true){
            Toast.makeText(MainActivity.this,"Data Deleted successfully",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this,"Data not Deleted",Toast.LENGTH_SHORT).show();
        }
    }
});

    }
}