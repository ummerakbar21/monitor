package com.example.monitor.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.monitor.R;

public class MonitorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int GALLERY_REQUEST_CODE = 1 ;
    Switch simpleSwitch;
    TextView progressTv;
    private Spinner spin1;
    private Spinner spin2, distanceBlockSpinner,foundationSpinner,mastSpinner;
    private String[] stationsFrom = { "From","Baramula", "USA", "China", "Japan", "Other"};
    private String[]  stationsTo = { "To","Baramula", "USA", "China", "Japan", "Other"};
    private String[]  type = { "Type"};

    private TextView distanceTV, chainageView;
    private   String[] distance={"9","7","6","7","5","0"};
    private   String[] chainage={"9","7","6","7","5","7"};
    private String[] distanceInKm={"Km","7","6","7","12","0"};
    private String[] distanceInL={"L","L1","L2","L3","L4","L5"};
    private ImageView datePicker;
    private int mYear, mMonth, mDay;
    private Button chooseBtn;
    private Spinner blockLocSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);
        simpleSwitch = findViewById(R.id.status_Switch); // initiate Switch
        progressTv = findViewById(R.id.switch_label_tv);
        datePicker = findViewById(R.id.date_picker);
        chooseBtn = findViewById(R.id.Choose_Files_btn);





        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        spin1 = findViewById(R.id.block_name_from_spinner);
        spin1.setOnItemSelectedListener(this);
        spin2 = findViewById(R.id.block_name_to_spinner);
        distanceTV=findViewById(R.id.distance_tv);
        distanceBlockSpinner=findViewById(R.id.block_distance_spinner);
        distanceBlockSpinner.setOnItemSelectedListener(this);
        blockLocSpinner = findViewById(R.id.block_location_spinner);
        blockLocSpinner.setOnItemSelectedListener(this);
        chainageView= findViewById(R.id.chain_age_tv);

        foundationSpinner=findViewById(R.id.foundation_spinner);
        mastSpinner=findViewById(R.id.mast_spinner);



        //Creating the ArrayAdapter instance having the country list
          //Setting the ArrayAdapter data on the Spinner
        ArrayAdapter spin1adapter=getAdapter();
        spin1adapter.addAll(stationsFrom);
        spin1.setAdapter(spin1adapter);



        ArrayAdapter spin1adapter2=getAdapter();
        spin1adapter2.addAll(stationsTo);
        spin2.setClickable(false);
        spin2.setEnabled(false);
        spin2.setAdapter(spin1adapter2);


        ArrayAdapter foundationAdapter=getAdapter();
        foundationAdapter.addAll(type);
        foundationSpinner.setClickable(false);
        foundationSpinner.setEnabled(false);
        foundationSpinner.setAdapter(foundationAdapter);

        mastSpinner.setClickable(false);
        mastSpinner.setEnabled(false);
        mastSpinner.setAdapter(foundationAdapter);


        ArrayAdapter spinAdapterDistance=getAdapter();
        spinAdapterDistance.addAll(distanceInKm);
        distanceBlockSpinner.setAdapter(spinAdapterDistance);

        ArrayAdapter spinAdapterLoc=getAdapter();
        spinAdapterLoc.addAll(distanceInL);
        blockLocSpinner.setAdapter(spinAdapterLoc);





        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar  = Calendar.getInstance();

                mYear = calendar.get(Calendar.YEAR);
                mMonth = calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MonitorActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });

        chooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseImage();
            }
        });
    }

    private  ArrayAdapter getAdapter() {
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return aa;
    }

    private void ChooseImage() {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();


           // imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.block_name_from_spinner:
                if(position > 0){
                    if(position< stationsFrom.length-1){
                        spin2.setSelection(position+1);
                    }else {
                        spin2.setSelection(position);
                    }
                    distanceTV.setText(distance[position]);

                }else {
                    TextView tv = (TextView) view;
                    spin2.setSelection(position);
                    tv.setTextColor(Color.GRAY);

                }
                break;
            case R.id.block_location_spinner:
                if(position > 0){
                    chainageView.setText(String.valueOf( Integer.parseInt(chainage[position])+Integer.parseInt(distanceInKm[position])));


                }
                break;
            case R.id.block_distance_spinner:
                if(position > 0 && blockLocSpinner.getSelectedItemPosition()>0 )
                { chainageView.setText(String.valueOf( Integer.parseInt(chainage[position])+Integer.parseInt(distanceInKm[position])));
                }
                break;
            default:
                /*if(position > 0){
                    if(position<stationsFrom.length-1){
                        spin2.setSelection(position+1);
                    }else {
                        spin2.setSelection(position);
                    }
                    distanceTV.setText(distance[position]);

                }else {
                    TextView tv = (TextView) view;
                    tv.setTextColor(Color.GRAY);

                }*/
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
