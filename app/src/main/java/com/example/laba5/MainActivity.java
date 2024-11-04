package com.example.laba5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

public class MainActivity extends AppCompatActivity {


    private EditText FNameEnter;
    private EditText SNameEnter;
    private TextView Result;
    private Button SendButton;

    private ActivityResultLauncher<Intent> Launcher; // Лаунчер для активити с возвратом

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    FNameEnter=findViewById(R.id.editTextText3);
    SNameEnter=findViewById(R.id.editTextText4);
    Result=findViewById(R.id.textView2);
    SendButton=findViewById(R.id.button3);

    Launcher = registerForActivityResult
            (
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    String message = result.getData().getStringExtra("message");
                    Result.setText(message);
                }
            }
            );
    }

    public void onClick(View view){
        String FName = FNameEnter.getText().toString();
        String SName = SNameEnter.getText().toString();
        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        intent.putExtra("FName",FName);
        intent.putExtra("SName",SName);

        Launcher.launch(intent);
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Получаем Intent, который запустил MainActivity
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("message")) {

            // Извлекаем переданный текст
            String message = intent.getStringExtra("message");

            // Обновляем текст
            Result.setText(message);

            // Удаляем "message" из Intent, чтобы не переустанавливать текст каждый раз
            intent.removeExtra("message");
        }
    }




}