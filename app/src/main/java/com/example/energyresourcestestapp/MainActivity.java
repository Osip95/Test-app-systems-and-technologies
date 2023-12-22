package com.example.energyresourcestestapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.energyresourcestestapp.connectionscreen.ConnectionFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new ConnectionFragment()).commit();
    }
}