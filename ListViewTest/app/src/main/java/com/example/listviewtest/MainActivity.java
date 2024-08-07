package com.example.listviewtest;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView cityListView;
    private List<String> cities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // 設置窗口邊距
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 綁定 ListView 並註冊 Listener
        cityListView = findViewById(R.id.cityListView);
        setCities();
        cityListView.setOnItemClickListener(this);
    }

    // 動態新增城市
    private void setCities() {
        cities = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.cities)));
        cities.add("Hualien");
        cities.add("Taitung");
        cities.add("Changhua");

        // new ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cities);
        cityListView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedCity = cities.get(i);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("城市")
                .setMessage("您選擇的是 : " + selectedCity)
                .setCancelable(true)
                .setPositiveButton("確定", null)
                .setNegativeButton("取消", null)
                .setNeutralButton("放棄", null)
                .show();

        // 顯示 Toast 信息（如果需要）
        // Toast.makeText(this, "您選擇的是 : " + selectedCity, Toast.LENGTH_SHORT).show();

        // 更新 TextView 信息（如果需要）
        // TextView tv = findViewById(R.id.textView);
        // tv.setText("您選擇的是 : " + selectedCity);
    }
}
