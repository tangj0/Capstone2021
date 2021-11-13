package com.capstone.hexagon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

public class UserStatsActivity extends AppCompatActivity implements View.OnClickListener{

    private Button go_back_to_main;

    private ImageButton imageButton11;
    private ImageButton imageButton12;
    private ImageButton imageButton13;
    private ImageButton imageButton14;
    private ImageButton imageButton15;
    private ImageButton imageButton16;
    private ImageButton imageButton17;
    private ImageButton imageButton21;
    private ImageButton imageButton22;
    private ImageButton imageButton23;
    private ImageButton imageButton24;
    private ImageButton imageButton25;
    private ImageButton imageButton26;
    private ImageButton imageButton27;
    private ImageButton imageButton31;
    private ImageButton imageButton32;
    private ImageButton imageButton33;
    private ImageButton imageButton34;
    private ImageButton imageButton35;
    private ImageButton imageButton36;
    private ImageButton imageButton37;
    private ImageButton imageButton41;
    private ImageButton imageButton42;
    private ImageButton imageButton43;
    private ImageButton imageButton44;
    private ImageButton imageButton45;
    private ImageButton imageButton46;
    private ImageButton imageButton47;
    private ImageButton imageButton51;
    private ImageButton imageButton52;
    private ImageButton imageButton53;
    private ImageButton imageButton54;
    private ImageButton imageButton55;
    private ImageButton imageButton56;
    private ImageButton imageButton57;
    private ImageButton imageButton61;
    private ImageButton imageButton62;
    private ImageButton imageButton63;
    private ImageButton imageButton64;
    private ImageButton imageButton65;
    private ImageButton imageButton66;
    private ImageButton imageButton67;
    private ImageButton imageButton71;
    private ImageButton imageButton72;
    private ImageButton imageButton73;
    private ImageButton imageButton74;
    private ImageButton imageButton75;
    private ImageButton imageButton76;
    private ImageButton imageButton77;
    private ImageButton imageButton81;
    private ImageButton imageButton82;
    private ImageButton imageButton83;
    private ImageButton imageButton84;

    private ImageButton imageButtonHex11;
    private ImageButton imageButtonHex12;
    private ImageButton imageButtonHex13;
    private ImageButton imageButtonHex14;
    private ImageButton imageButtonHex15;
    private ImageButton imageButtonHex16;
    private ImageButton imageButtonHex21;
    private ImageButton imageButtonHex22;
    private ImageButton imageButtonHex23;
    private ImageButton imageButtonHex24;
    private ImageButton imageButtonHex25;
    private ImageButton imageButtonHex26;
    private ImageButton imageButtonHex27;
    private ImageButton imageButtonHex28;
    private ImageButton imageButtonHex31;
    private ImageButton imageButtonHex32;
    private ImageButton imageButtonHex33;
    private ImageButton imageButtonHex34;
    private ImageButton imageButtonHex35;
    private ImageButton imageButtonHex36;
    private ImageButton imageButtonHex37;
    private ImageButton imageButtonHex41;
    private ImageButton imageButtonHex42;
    private ImageButton imageButtonHex43;
    private ImageButton imageButtonHex51;
    private ImageButton imageButtonHex52;
    private ImageButton imageButtonHex53;
    private ImageButton imageButtonHex54;
    private ImageButton imageButtonHex55;
    private ImageButton imageButtonHex56;
    private ImageButton imageButtonHex57;
    private ImageButton imageButtonHex61;
    private ImageButton imageButtonHex62;
    private ImageButton imageButtonHex63;
    private ImageButton imageButtonHex64;

    private TextView user;
    private TextView time;
    private TextView space;

    private TextView mon;
    private TextView tue;
    private TextView wed;
    private TextView thu;
    private TextView fri;
    private TextView sat;
    private TextView sun;

    private ImageButton[] squareButtons;
    private ImageButton[] hexButtons;

    private int[] timeTransparency;
    private int[] spaceTransparency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_stat);

        go_back_to_main = (Button) findViewById(R.id.back_button);
        go_back_to_main.setOnClickListener(this);

        user = (TextView) findViewById(R.id.user);
        time = (TextView) findViewById(R.id.time);
        space = (TextView) findViewById(R.id.space);

        mon = (TextView) findViewById(R.id.mon);
        tue = (TextView) findViewById(R.id.tue);
        wed = (TextView) findViewById(R.id.wed);
        thu = (TextView) findViewById(R.id.thu);
        fri = (TextView) findViewById(R.id.fri);
        sat = (TextView) findViewById(R.id.sat);
        sun = (TextView) findViewById(R.id.sun);

        String userName = "John";
        user.setText("Stat of User: " + userName);
        time.setText("Time");
        space.setText("Space");

        mon.setText("MON");
        tue.setText("TUE");
        wed.setText("WED");
        thu.setText("THU");
        fri.setText("FRI");
        sat.setText("SAT");
        sun.setText("SUN");

        imageButton11 = (ImageButton) findViewById(R.id.imageButton11);
        imageButton11.setOnClickListener(this);
        imageButton12 = (ImageButton) findViewById(R.id.imageButton12);
        imageButton13 = (ImageButton) findViewById(R.id.imageButton13);
        imageButton14 = (ImageButton) findViewById(R.id.imageButton14);
        imageButton15 = (ImageButton) findViewById(R.id.imageButton15);
        imageButton16 = (ImageButton) findViewById(R.id.imageButton16);
        imageButton17 = (ImageButton) findViewById(R.id.imageButton17);
        imageButton21 = (ImageButton) findViewById(R.id.imageButton21);
        imageButton22 = (ImageButton) findViewById(R.id.imageButton22);
        imageButton23 = (ImageButton) findViewById(R.id.imageButton23);
        imageButton24 = (ImageButton) findViewById(R.id.imageButton24);
        imageButton25 = (ImageButton) findViewById(R.id.imageButton25);
        imageButton26 = (ImageButton) findViewById(R.id.imageButton26);
        imageButton27 = (ImageButton) findViewById(R.id.imageButton27);
        imageButton31 = (ImageButton) findViewById(R.id.imageButton31);
        imageButton32 = (ImageButton) findViewById(R.id.imageButton32);
        imageButton33 = (ImageButton) findViewById(R.id.imageButton33);
        imageButton34 = (ImageButton) findViewById(R.id.imageButton34);
        imageButton35 = (ImageButton) findViewById(R.id.imageButton35);
        imageButton36 = (ImageButton) findViewById(R.id.imageButton36);
        imageButton37 = (ImageButton) findViewById(R.id.imageButton37);
        imageButton41 = (ImageButton) findViewById(R.id.imageButton41);
        imageButton42 = (ImageButton) findViewById(R.id.imageButton42);
        imageButton43 = (ImageButton) findViewById(R.id.imageButton43);
        imageButton44 = (ImageButton) findViewById(R.id.imageButton44);
        imageButton45 = (ImageButton) findViewById(R.id.imageButton45);
        imageButton46 = (ImageButton) findViewById(R.id.imageButton46);
        imageButton47 = (ImageButton) findViewById(R.id.imageButton47);
        imageButton51 = (ImageButton) findViewById(R.id.imageButton51);
        imageButton52 = (ImageButton) findViewById(R.id.imageButton52);
        imageButton53 = (ImageButton) findViewById(R.id.imageButton53);
        imageButton54 = (ImageButton) findViewById(R.id.imageButton54);
        imageButton55 = (ImageButton) findViewById(R.id.imageButton55);
        imageButton56 = (ImageButton) findViewById(R.id.imageButton56);
        imageButton57 = (ImageButton) findViewById(R.id.imageButton57);
        imageButton61 = (ImageButton) findViewById(R.id.imageButton61);
        imageButton62 = (ImageButton) findViewById(R.id.imageButton62);
        imageButton63 = (ImageButton) findViewById(R.id.imageButton63);
        imageButton64 = (ImageButton) findViewById(R.id.imageButton64);
        imageButton65 = (ImageButton) findViewById(R.id.imageButton65);
        imageButton66 = (ImageButton) findViewById(R.id.imageButton66);
        imageButton67 = (ImageButton) findViewById(R.id.imageButton67);
        imageButton71 = (ImageButton) findViewById(R.id.imageButton71);
        imageButton72 = (ImageButton) findViewById(R.id.imageButton72);
        imageButton73 = (ImageButton) findViewById(R.id.imageButton73);
        imageButton74 = (ImageButton) findViewById(R.id.imageButton74);
        imageButton75 = (ImageButton) findViewById(R.id.imageButton75);
        imageButton76 = (ImageButton) findViewById(R.id.imageButton76);
        imageButton77 = (ImageButton) findViewById(R.id.imageButton77);
        imageButton81 = (ImageButton) findViewById(R.id.imageButton81);
        imageButton82 = (ImageButton) findViewById(R.id.imageButton82);
        imageButton83 = (ImageButton) findViewById(R.id.imageButton83);
        imageButton84 = (ImageButton) findViewById(R.id.imageButton84);

        squareButtons = new ImageButton[]{imageButton11, imageButton12, imageButton13, imageButton14, imageButton15, imageButton16, imageButton17, imageButton21, imageButton22, imageButton23, imageButton24, imageButton25, imageButton26, imageButton27, imageButton31, imageButton32, imageButton33, imageButton34, imageButton35, imageButton36, imageButton37, imageButton41, imageButton42, imageButton43, imageButton44, imageButton45, imageButton46, imageButton47, imageButton51, imageButton52, imageButton53, imageButton54, imageButton55, imageButton56, imageButton57, imageButton61, imageButton62, imageButton63, imageButton64, imageButton65, imageButton66, imageButton67, imageButton71, imageButton72, imageButton73, imageButton74, imageButton75, imageButton76, imageButton77, imageButton81, imageButton82, imageButton83, imageButton84};
        timeTransparency = new int[]{3, 36, 219, 135, 123, 118, 248, 252, 99, 202, 175, 21, 102, 125, 172, 17, 129, 195, 221, 248, 101, 191, 155, 136, 238, 37, 213, 242, 118, 154, 54, 92, 225, 159, 92, 89, 161, 247, 127, 82, 190, 39, 199, 64, 219, 70, 173, 30, 39, 246, 253, 234, 200};

//        for (int i = 0; i < squareButtons.length; i++) {
//            squareButtons[i].getBackground().setAlpha(timeTransparency[i]);
//        }
        imageButton11.getBackground().setAlpha(50);
        imageButton12.getBackground().setAlpha(190);
        imageButton13.getBackground().setAlpha(100);

        imageButtonHex11 = (ImageButton) findViewById(R.id.imageButtonHex11);
        imageButtonHex11.setOnClickListener(this);
        imageButtonHex12 = (ImageButton) findViewById(R.id.imageButtonHex12);
        imageButtonHex13 = (ImageButton) findViewById(R.id.imageButtonHex13);
        imageButtonHex14 = (ImageButton) findViewById(R.id.imageButtonHex14);
        imageButtonHex15 = (ImageButton) findViewById(R.id.imageButtonHex15);
        imageButtonHex16 = (ImageButton) findViewById(R.id.imageButtonHex16);
        imageButtonHex21 = (ImageButton) findViewById(R.id.imageButtonHex21);
        imageButtonHex22 = (ImageButton) findViewById(R.id.imageButtonHex22);
        imageButtonHex23 = (ImageButton) findViewById(R.id.imageButtonHex23);
        imageButtonHex24 = (ImageButton) findViewById(R.id.imageButtonHex24);
        imageButtonHex25 = (ImageButton) findViewById(R.id.imageButtonHex25);
        imageButtonHex26 = (ImageButton) findViewById(R.id.imageButtonHex26);
        imageButtonHex27 = (ImageButton) findViewById(R.id.imageButtonHex27);
        imageButtonHex28 = (ImageButton) findViewById(R.id.imageButtonHex28);
        imageButtonHex31 = (ImageButton) findViewById(R.id.imageButtonHex31);
        imageButtonHex32 = (ImageButton) findViewById(R.id.imageButtonHex32);
        imageButtonHex33 = (ImageButton) findViewById(R.id.imageButtonHex33);
        imageButtonHex34 = (ImageButton) findViewById(R.id.imageButtonHex34);
        imageButtonHex35 = (ImageButton) findViewById(R.id.imageButtonHex35);
        imageButtonHex36 = (ImageButton) findViewById(R.id.imageButtonHex36);
        imageButtonHex37 = (ImageButton) findViewById(R.id.imageButtonHex37);
        imageButtonHex41 = (ImageButton) findViewById(R.id.imageButtonHex41);
        imageButtonHex42 = (ImageButton) findViewById(R.id.imageButtonHex42);
        imageButtonHex43 = (ImageButton) findViewById(R.id.imageButtonHex43);
        imageButtonHex51 = (ImageButton) findViewById(R.id.imageButtonHex51);
        imageButtonHex52 = (ImageButton) findViewById(R.id.imageButtonHex52);
        imageButtonHex53 = (ImageButton) findViewById(R.id.imageButtonHex53);
        imageButtonHex54 = (ImageButton) findViewById(R.id.imageButtonHex54);
        imageButtonHex55 = (ImageButton) findViewById(R.id.imageButtonHex55);
        imageButtonHex56 = (ImageButton) findViewById(R.id.imageButtonHex56);
        imageButtonHex57 = (ImageButton) findViewById(R.id.imageButtonHex57);
        imageButtonHex61 = (ImageButton) findViewById(R.id.imageButtonHex61);
        imageButtonHex62 = (ImageButton) findViewById(R.id.imageButtonHex62);
        imageButtonHex63 = (ImageButton) findViewById(R.id.imageButtonHex63);
        imageButtonHex64 = (ImageButton) findViewById(R.id.imageButtonHex64);

        hexButtons = new ImageButton[] {imageButtonHex11, imageButtonHex12, imageButtonHex13, imageButtonHex14, imageButtonHex15, imageButtonHex16, imageButtonHex21, imageButtonHex22, imageButtonHex23, imageButtonHex24, imageButtonHex25, imageButtonHex26, imageButtonHex27, imageButtonHex28, imageButtonHex31, imageButtonHex32, imageButtonHex33, imageButtonHex34, imageButtonHex35, imageButtonHex36, imageButtonHex37, imageButtonHex41, imageButtonHex42, imageButtonHex43, imageButtonHex51, imageButtonHex52, imageButtonHex53, imageButtonHex54, imageButtonHex55, imageButtonHex56, imageButtonHex57, imageButtonHex61, imageButtonHex62, imageButtonHex63, imageButtonHex64};
        spaceTransparency = new int[]{190, 22, 232, 151, 99, 190, 53, 243, 170, 36, 193, 167, 226, 105, 217, 255, 226, 177, 242, 242, 136, 182, 97, 23, 91, 120, 70, 69, 169, 129, 220, 23, 252, 40, 133};

        for (int i = 0; i < hexButtons.length; i++) {
            hexButtons[i].getBackground().setAlpha(spaceTransparency[i]);
        }
    }

    @Override
    public void onClick(View v) {
        // Sign up button is clicked
        if (v.getId() == R.id.back_button) {
            goToLoginPage();
        }

        if(v.getId() == R.id.imageButton11) {
            MessageBox("Contribution this day is: " + timeTransparency[1]);
        }

        if(v.getId() == R.id.imageButtonHex11) {
            MessageBox("Contribution this hexagon is: " + spaceTransparency[1]);
        }

//        else if (v.getId() == R.id.user_stat_button) {
//            goToUserStatPage();
//        }
    }

    private void goToLoginPage() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void MessageBox(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
