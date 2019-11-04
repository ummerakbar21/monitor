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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.monitor.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MonitorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener , CompoundButton.OnCheckedChangeListener {

    private static final int GALLERY_REQUEST_CODE = 1 ;
    Switch simpleSwitch;
    TextView progressTv,dateTxtView;
    private Spinner spin1;
    private Spinner spin2, distanceBlockSpinner,foundationSpinner,mastSpinner;
    private String[] stationsFrom = { "From","Baramula", "Sopore", "Hamre", "Pattan", "Mazhom", "Budgam", "Srinagar", "Pampore","Kakapora","Awantipora","Panzgam","Bijbehara","Anantnag","Dadura","Qazigund","Banihal","Arpinchala","Sumber","Dharam","Sangaldan","Udhampur"};
    private String[]  stationsTo = { "To","Baramula", "Sopore", "Hamre", "Pattan", "Mazhom", "Budgam", "Srinagar", "Pampore","Kakapora","Awantipora","Panzgam","Bijbehara","Anantnag","Dadura","Qazigund","Banihal","Arpinchala","Sumber","Dharam","Sangaldan","Udhampur"};
    private String[]  type = { "Type"};

    private TextView distanceTV, chainageView,dateView;
    private   String[] distance={"9","7","6","7","5","11","13","5","8","6","11","12","9","14","13","8","7","11","10","14","8","0"};
    private   String[] chainage={"9","7","6","7","5","7"};
    private String[] distanceInKm={"Km","1","2","3","4","5"};
    private String[] distanceInL={"L","L1","L2","L3","L4","L5"};
    private ImageView datePicker;
    private int mYear, mMonth, mDay;
    private Button chooseBtn;
    private Spinner blockLocSpinner;
    private CheckBox mastsCheckbox



,foundationCheckbox;
    private Button saveBtn;
    private Switch holdSwitch;
    private EditText reasonView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);
        simpleSwitch = findViewById(R.id.status_Switch); // initiate Switch
        progressTv = findViewById(R.id.switch_label_tv);
        datePicker = findViewById(R.id.date_picker);
        chooseBtn = findViewById(R.id.Choose_Files_btn);
        mastsCheckbox=findViewById(R.id.masts_checkbox);
        mastsCheckbox.setOnCheckedChangeListener(this);
        foundationCheckbox=findViewById(R.id.foundation_checkbox);
        foundationCheckbox.setOnCheckedChangeListener(this);

        saveBtn=findViewById(R.id.save_btn);
        saveBtn.setEnabled(false);
        saveBtn.setBackgroundColor(Color.GRAY);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MonitorActivity.this, "Data uploaded", Toast.LENGTH_SHORT).show();
            }
        });





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
        holdSwitch= findViewById(R.id.status_Switch);
        reasonView=findViewById(R.id.hold_tv);
        reasonView.setVisibility(View.GONE);
        dateTxtView=findViewById(R.id.date_tv);
        dateTxtView.setVisibility(View.GONE);

        holdSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(holdSwitch.isChecked()){
                    reasonView.setVisibility(View.VISIBLE);
                }else {
                    reasonView.setVisibility(View.GONE);
                }

            }
        });


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
                        Calendar calendar1= Calendar.getInstance();
                        calendar1.set(Calendar.YEAR,year);
                        calendar1.set(Calendar.MONTH,month);
                        calendar1.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        String date= dateFormat.format(calendar1.getTime());
                        dateTxtView.setText(date);
                        dateTxtView.setVisibility(View.VISIBLE);

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
        try {
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
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(foundationCheckbox.isChecked() && mastsCheckbox.isChecked()){
            saveBtn.setEnabled(true);
            saveBtn.setBackgroundColor(Color.RED);

        }else {
            saveBtn.setEnabled(false);
            saveBtn.setBackgroundColor(Color.GRAY);
        }

    }
}
