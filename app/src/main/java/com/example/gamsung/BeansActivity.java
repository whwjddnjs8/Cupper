package com.example.gamsung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
// 원두 모양 버튼을 누르면 이 페이지 보임 6개의 메뉴가 있고 사용자가 원하는 원두를 고를 수 있음
// 누르면 BeansAdapter이용하여 리스트화 되고 BeansDetail로 감.
public class BeansActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    public static List<AllBeans> allBeansList = new ArrayList<>();
    private List<Beans> beansList = new ArrayList<>();
    private RecyclerView recyclerView;
    private BeansAdapter beansAdapter;
    public String group;
    private String beans1,beans2,beans3,beans4,beans5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_beans);
        TextView bean1 = (TextView) findViewById(R.id.bean1);
        TextView bean2 = (TextView) findViewById(R.id.bean2);
        TextView bean3 = (TextView) findViewById(R.id.bean3);
        TextView bean4 = (TextView) findViewById(R.id.bean4);
        TextView bean5 = (TextView) findViewById(R.id.bean5);
        TextView beanbut = (TextView) findViewById(R.id.beanbut);
        TextView cupper = (TextView)findViewById(R.id.cupper);
        cupper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

        bean1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BeansActivity.this,BeansDetail.class);
                intent.putExtra("group","G블렌딩");
                startActivity(intent);
            }
        });
        bean2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BeansActivity.this,BeansDetail.class);
                intent.putExtra("group","싱글오리진");
                startActivity(intent);
            }
        });
        bean3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BeansActivity.this,BeansDetail.class);
                intent.putExtra("group","신맛높은원두");
                startActivity(intent);
            }
        });
        bean4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BeansActivity.this,BeansDetail.class);
                intent.putExtra("group","신맛중간원두");
                startActivity(intent);
            }
        });
        bean5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BeansActivity.this,BeansDetail.class);
                intent.putExtra("group","신맛적은원두");
                startActivity(intent);
            }
        });
        beanbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),BannerCoffee.class);
                startActivity(intent);
            }
        });




    }

}