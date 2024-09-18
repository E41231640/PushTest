package com.dini.theestudentt;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText fullname, edtusername, edtemail, edtpassword, edtnim, edtalamat, edtnotelp, edit_TextDate;
    Button btnregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        fullname = findViewById(R.id.edt_fullname);
        edtusername = findViewById(R.id.edt_username);
        edtemail = findViewById(R.id.edt_email);
        edtpassword = findViewById(R.id.edt_password);
        edtnim = findViewById(R.id.edt_nim);
        edtalamat = findViewById(R.id.edt_alamat);
        edtnotelp = findViewById(R.id.edt_telp);
        btnregister = findViewById(R.id.btn_register);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namalengkap = fullname.getText().toString();
                String hasilnim = edtnim.getText().toString();
                String Email = edtemail.getText().toString();
                String hp = edtnotelp.getText().toString();
                String password = edtpassword.getText().toString();
                String username = edtusername.getText().toString();
                String alamatmu = edtalamat.getText().toString();
                String bdate = edit_TextDate.getText().toString();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Informasi Mahasiswa");
                builder.setMessage("Nama Lengkap: " + namalengkap + "\nNIM: " + hasilnim +
                        "\nEmail: " + Email + "\nNO. Handphone: " + hp +
                        "\nPassword: " + password + "\nUsername: " + username +
                        "\nAlamat: " + alamatmu + "\nTanggal Lahir: " + bdate);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }});






        edit_TextDate = findViewById(R.id.edt_bdate);

        edit_TextDate.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1;
                        edit_TextDate.setText(selectedDate);
                    }, year, month, day);
            datePickerDialog.show();
        });


        Spinner spinner = findViewById(R.id.gender);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, "Jenis Kelamin: " + item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Laki-Laki");
        arrayList.add("Wanita");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.gender), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}