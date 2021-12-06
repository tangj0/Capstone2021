package com.capstone.hexagon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserStatsActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton go_back_to_main;

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
    private ImageButton imageButton85;
    private ImageButton imageButton86;
    private ImageButton imageButton87;

    private ImageButton imageButtonHex11;
    private ImageButton imageButtonHex12;
    private ImageButton imageButtonHex13;
    private ImageButton imageButtonHex14;
    private ImageButton imageButtonHex15;
    private ImageButton imageButtonHex16;
    private ImageButton imageButtonHex17;
    private ImageButton imageButtonHex18;
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
    private ImageButton imageButtonHex38;
    private ImageButton imageButtonHex41;
    private ImageButton imageButtonHex42;
    private ImageButton imageButtonHex43;
    private ImageButton imageButtonHex44;
    private ImageButton imageButtonHex45;
    private ImageButton imageButtonHex46;
    private ImageButton imageButtonHex47;
    private ImageButton imageButtonHex48;
    private ImageButton imageButtonHex51;
    private ImageButton imageButtonHex52;
    private ImageButton imageButtonHex53;
    private ImageButton imageButtonHex54;
    private ImageButton imageButtonHex55;
    private ImageButton imageButtonHex56;
    private ImageButton imageButtonHex57;
    private ImageButton imageButtonHex58;

    private TextView user;

    private ImageButton[] squareButtons;
    private ImageButton[] hexButtons;

    private int[] timeTransparency;
    private int[] spaceTransparency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_stats);

        go_back_to_main = (ImageButton) findViewById(R.id.back_button);
        go_back_to_main.setOnClickListener(this);
        go_back_to_main.setImageResource(R.drawable.logo);

        user = (TextView) findViewById(R.id.user);

        FirebaseUser currUser = FirebaseAuth.getInstance().getCurrentUser();
        String userUID = currUser.getUid();
        String name = currUser.getDisplayName();
        user.setText("Stats of: " + name);

        imageButton11 = (ImageButton) findViewById(R.id.imageButton11);
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
        imageButton85 = (ImageButton) findViewById(R.id.imageButton85);
        imageButton86 = (ImageButton) findViewById(R.id.imageButton86);
        imageButton87 = (ImageButton) findViewById(R.id.imageButton87);

        imageButton11.setOnClickListener(this);
        imageButton12.setOnClickListener(this);
        imageButton13.setOnClickListener(this);
        imageButton14.setOnClickListener(this);
        imageButton15.setOnClickListener(this);
        imageButton16.setOnClickListener(this);
        imageButton17.setOnClickListener(this);
        imageButton21.setOnClickListener(this);
        imageButton22.setOnClickListener(this);
        imageButton23.setOnClickListener(this);
        imageButton24.setOnClickListener(this);
        imageButton25.setOnClickListener(this);
        imageButton26.setOnClickListener(this);
        imageButton27.setOnClickListener(this);
        imageButton31.setOnClickListener(this);
        imageButton32.setOnClickListener(this);
        imageButton33.setOnClickListener(this);
        imageButton34.setOnClickListener(this);
        imageButton35.setOnClickListener(this);
        imageButton36.setOnClickListener(this);
        imageButton37.setOnClickListener(this);
        imageButton41.setOnClickListener(this);
        imageButton42.setOnClickListener(this);
        imageButton43.setOnClickListener(this);
        imageButton44.setOnClickListener(this);
        imageButton45.setOnClickListener(this);
        imageButton46.setOnClickListener(this);
        imageButton47.setOnClickListener(this);
        imageButton51.setOnClickListener(this);
        imageButton52.setOnClickListener(this);
        imageButton53.setOnClickListener(this);
        imageButton54.setOnClickListener(this);
        imageButton55.setOnClickListener(this);
        imageButton56.setOnClickListener(this);
        imageButton57.setOnClickListener(this);
        imageButton61.setOnClickListener(this);
        imageButton62.setOnClickListener(this);
        imageButton63.setOnClickListener(this);
        imageButton64.setOnClickListener(this);
        imageButton65.setOnClickListener(this);
        imageButton66.setOnClickListener(this);
        imageButton67.setOnClickListener(this);
        imageButton71.setOnClickListener(this);
        imageButton72.setOnClickListener(this);
        imageButton73.setOnClickListener(this);
        imageButton74.setOnClickListener(this);
        imageButton75.setOnClickListener(this);
        imageButton76.setOnClickListener(this);
        imageButton77.setOnClickListener(this);
        imageButton81.setOnClickListener(this);
        imageButton82.setOnClickListener(this);
        imageButton83.setOnClickListener(this);
        imageButton84.setOnClickListener(this);
        imageButton85.setOnClickListener(this);
        imageButton86.setOnClickListener(this);
        imageButton87.setOnClickListener(this);

        squareButtons = new ImageButton[]{imageButton11, imageButton12, imageButton13, imageButton14, imageButton15, imageButton16, imageButton17,
                imageButton21, imageButton22, imageButton23, imageButton24, imageButton25, imageButton26, imageButton27,
                imageButton31, imageButton32, imageButton33, imageButton34, imageButton35, imageButton36, imageButton37,
                imageButton41, imageButton42, imageButton43, imageButton44, imageButton45, imageButton46, imageButton47,
                imageButton51, imageButton52, imageButton53, imageButton54, imageButton55, imageButton56, imageButton57,
                imageButton61, imageButton62, imageButton63, imageButton64, imageButton65, imageButton66, imageButton67,
                imageButton71, imageButton72, imageButton73, imageButton74, imageButton75, imageButton76, imageButton77,
                imageButton81, imageButton82, imageButton83, imageButton84, imageButton85, imageButton86, imageButton87};
        timeTransparency = new int[]{39, 60, 9, 30, 45, 58, 23, 11, 49, 75, 84, 20, 21, 56, 15, 3, 61, 93, 36, 37, 44, 12, 50, 63, 59, 19, 55, 97, 27, 46, 17, 6, 28, 38, 16, 8, 18, 40, 96, 25, 85, 80, 52, 24, 13, 86, 100, 47, 78, 33, 48, 54, 91, 73, 71, 82};

        for (int i = 0; i < squareButtons.length; i++) {
            if (timeTransparency[i] == 0) {
                squareButtons[i].setBackgroundResource(R.drawable.square0);
            }
            else if (timeTransparency[i] < 20) {
                squareButtons[i].setBackgroundResource(R.drawable.square20);
            }
            else if (timeTransparency[i] < 40) {
                squareButtons[i].setBackgroundResource(R.drawable.square40);
            }
            else if (timeTransparency[i] < 60) {
                squareButtons[i].setBackgroundResource(R.drawable.square60);
            }
            else if (timeTransparency[i] < 80) {
                squareButtons[i].setBackgroundResource(R.drawable.square80);
            }
            else if (timeTransparency[i] <= 100) {
                squareButtons[i].setBackgroundResource(R.drawable.square100);
            }
        }


        imageButtonHex11 = (ImageButton) findViewById(R.id.imageButtonHex11);
        imageButtonHex12 = (ImageButton) findViewById(R.id.imageButtonHex12);
        imageButtonHex13 = (ImageButton) findViewById(R.id.imageButtonHex13);
        imageButtonHex14 = (ImageButton) findViewById(R.id.imageButtonHex14);
        imageButtonHex15 = (ImageButton) findViewById(R.id.imageButtonHex15);
        imageButtonHex16 = (ImageButton) findViewById(R.id.imageButtonHex16);
        imageButtonHex17 = (ImageButton) findViewById(R.id.imageButtonHex17);
        imageButtonHex18 = (ImageButton) findViewById(R.id.imageButtonHex18);
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
        imageButtonHex38 = (ImageButton) findViewById(R.id.imageButtonHex38);
        imageButtonHex41 = (ImageButton) findViewById(R.id.imageButtonHex41);
        imageButtonHex42 = (ImageButton) findViewById(R.id.imageButtonHex42);
        imageButtonHex43 = (ImageButton) findViewById(R.id.imageButtonHex43);
        imageButtonHex44 = (ImageButton) findViewById(R.id.imageButtonHex44);
        imageButtonHex45 = (ImageButton) findViewById(R.id.imageButtonHex45);
        imageButtonHex46 = (ImageButton) findViewById(R.id.imageButtonHex46);
        imageButtonHex47 = (ImageButton) findViewById(R.id.imageButtonHex47);
        imageButtonHex48 = (ImageButton) findViewById(R.id.imageButtonHex48);
        imageButtonHex51 = (ImageButton) findViewById(R.id.imageButtonHex51);
        imageButtonHex52 = (ImageButton) findViewById(R.id.imageButtonHex52);
        imageButtonHex53 = (ImageButton) findViewById(R.id.imageButtonHex53);
        imageButtonHex54 = (ImageButton) findViewById(R.id.imageButtonHex54);
        imageButtonHex55 = (ImageButton) findViewById(R.id.imageButtonHex55);
        imageButtonHex56 = (ImageButton) findViewById(R.id.imageButtonHex56);
        imageButtonHex57 = (ImageButton) findViewById(R.id.imageButtonHex57);
        imageButtonHex58 = (ImageButton) findViewById(R.id.imageButtonHex58);

        imageButtonHex11.setOnClickListener(this);
        imageButtonHex12.setOnClickListener(this);
        imageButtonHex13.setOnClickListener(this);
        imageButtonHex14.setOnClickListener(this);
        imageButtonHex15.setOnClickListener(this);
        imageButtonHex16.setOnClickListener(this);
        imageButtonHex17.setOnClickListener(this);
        imageButtonHex18.setOnClickListener(this);
        imageButtonHex21.setOnClickListener(this);
        imageButtonHex22.setOnClickListener(this);
        imageButtonHex23.setOnClickListener(this);
        imageButtonHex24.setOnClickListener(this);
        imageButtonHex25.setOnClickListener(this);
        imageButtonHex26.setOnClickListener(this);
        imageButtonHex27.setOnClickListener(this);
        imageButtonHex28.setOnClickListener(this);
        imageButtonHex31.setOnClickListener(this);
        imageButtonHex32.setOnClickListener(this);
        imageButtonHex33.setOnClickListener(this);
        imageButtonHex34.setOnClickListener(this);
        imageButtonHex35.setOnClickListener(this);
        imageButtonHex36.setOnClickListener(this);
        imageButtonHex37.setOnClickListener(this);
        imageButtonHex38.setOnClickListener(this);
        imageButtonHex41.setOnClickListener(this);
        imageButtonHex42.setOnClickListener(this);
        imageButtonHex43.setOnClickListener(this);
        imageButtonHex44.setOnClickListener(this);
        imageButtonHex45.setOnClickListener(this);
        imageButtonHex46.setOnClickListener(this);
        imageButtonHex47.setOnClickListener(this);
        imageButtonHex48.setOnClickListener(this);
        imageButtonHex51.setOnClickListener(this);
        imageButtonHex52.setOnClickListener(this);
        imageButtonHex53.setOnClickListener(this);
        imageButtonHex54.setOnClickListener(this);
        imageButtonHex55.setOnClickListener(this);
        imageButtonHex56.setOnClickListener(this);
        imageButtonHex57.setOnClickListener(this);
        imageButtonHex58.setOnClickListener(this);

        hexButtons = new ImageButton[] {imageButtonHex11, imageButtonHex12, imageButtonHex13, imageButtonHex14, imageButtonHex15, imageButtonHex16, imageButtonHex17, imageButtonHex18,
                imageButtonHex21, imageButtonHex22, imageButtonHex23, imageButtonHex24, imageButtonHex25, imageButtonHex26, imageButtonHex27, imageButtonHex28,
                imageButtonHex31, imageButtonHex32, imageButtonHex33, imageButtonHex34, imageButtonHex35, imageButtonHex36, imageButtonHex37, imageButtonHex38,
                imageButtonHex41, imageButtonHex42, imageButtonHex43, imageButtonHex44, imageButtonHex45, imageButtonHex46, imageButtonHex47, imageButtonHex48,
                imageButtonHex51, imageButtonHex52, imageButtonHex53, imageButtonHex54, imageButtonHex55, imageButtonHex56, imageButtonHex57, imageButtonHex58};
        spaceTransparency = new int[]{3, 52, 12, 71, 67, 64, 6, 16, 31, 59, 97, 98, 21, 90, 28, 58, 9, 45, 26, 69, 34, 62, 91, 7, 54, 44, 93, 81, 89, 36, 46, 48, 25, 84, 75, 33, 60, 57, 4, 15, 68, 22, 94, 38, 49, 79, 42, 18};

        for (int i = 0; i < hexButtons.length; i++) {
            if (spaceTransparency[i] == 0) {
                hexButtons[i].setBackgroundResource(R.drawable.hex0);
            }
            else if (spaceTransparency[i] < 20) {
                hexButtons[i].setBackgroundResource(R.drawable.hex20);
            }
            else if (spaceTransparency[i] < 40) {
                hexButtons[i].setBackgroundResource(R.drawable.hex40);
            }
            else if (spaceTransparency[i] < 60) {
                hexButtons[i].setBackgroundResource(R.drawable.hex60);
            }
            else if (spaceTransparency[i] < 80) {
                hexButtons[i].setBackgroundResource(R.drawable.hex80);
            }
            else if (spaceTransparency[i] < 100) {
                hexButtons[i].setBackgroundResource(R.drawable.hex100);
            }
        }
    }

    @Override
    public void onClick(View v) {
        // Sign up button is clicked
        if (v.getId() == R.id.back_button) {
            goToLoginPage();
        }

        if (v.getId() == R.id.imageButton11) {
            MessageBox("Contribution of this day is: " + timeTransparency[0]);
        }
        if (v.getId() == R.id.imageButton12) {
            MessageBox("Contribution of this day is: " + timeTransparency[1]);
        }
        if (v.getId() == R.id.imageButton13) {
            MessageBox("Contribution of this day is: " + timeTransparency[2]);
        }
        if (v.getId() == R.id.imageButton14) {
            MessageBox("Contribution of this day is: " + timeTransparency[3]);
        }
        if (v.getId() == R.id.imageButton15) {
            MessageBox("Contribution of this day is: " + timeTransparency[4]);
        }
        if (v.getId() == R.id.imageButton16) {
            MessageBox("Contribution of this day is: " + timeTransparency[5]);
        }
        if (v.getId() == R.id.imageButton17) {
            MessageBox("Contribution of this day is: " + timeTransparency[6]);
        }
        if (v.getId() == R.id.imageButton21) {
            MessageBox("Contribution of this day is: " + timeTransparency[7]);
        }
        if (v.getId() == R.id.imageButton22) {
            MessageBox("Contribution of this day is: " + timeTransparency[8]);
        }
        if (v.getId() == R.id.imageButton23) {
            MessageBox("Contribution of this day is: " + timeTransparency[9]);
        }
        if (v.getId() == R.id.imageButton24) {
            MessageBox("Contribution of this day is: " + timeTransparency[10]);
        }
        if (v.getId() == R.id.imageButton25) {
            MessageBox("Contribution of this day is: " + timeTransparency[11]);
        }
        if (v.getId() == R.id.imageButton26) {
            MessageBox("Contribution of this day is: " + timeTransparency[12]);
        }
        if (v.getId() == R.id.imageButton27) {
            MessageBox("Contribution of this day is: " + timeTransparency[13]);
        }
        if (v.getId() == R.id.imageButton31) {
            MessageBox("Contribution of this day is: " + timeTransparency[14]);
        }
        if (v.getId() == R.id.imageButton32) {
            MessageBox("Contribution of this day is: " + timeTransparency[15]);
        }
        if (v.getId() == R.id.imageButton33) {
            MessageBox("Contribution of this day is: " + timeTransparency[16]);
        }
        if (v.getId() == R.id.imageButton34) {
            MessageBox("Contribution of this day is: " + timeTransparency[17]);
        }
        if (v.getId() == R.id.imageButton35) {
            MessageBox("Contribution of this day is: " + timeTransparency[18]);
        }
        if (v.getId() == R.id.imageButton36) {
            MessageBox("Contribution of this day is: " + timeTransparency[19]);
        }
        if (v.getId() == R.id.imageButton37) {
            MessageBox("Contribution of this day is: " + timeTransparency[20]);
        }
        if (v.getId() == R.id.imageButton41) {
            MessageBox("Contribution of this day is: " + timeTransparency[21]);
        }
        if (v.getId() == R.id.imageButton42) {
            MessageBox("Contribution of this day is: " + timeTransparency[22]);
        }
        if (v.getId() == R.id.imageButton43) {
            MessageBox("Contribution of this day is: " + timeTransparency[23]);
        }
        if (v.getId() == R.id.imageButton44) {
            MessageBox("Contribution of this day is: " + timeTransparency[24]);
        }
        if (v.getId() == R.id.imageButton45) {
            MessageBox("Contribution of this day is: " + timeTransparency[25]);
        }
        if (v.getId() == R.id.imageButton46) {
            MessageBox("Contribution of this day is: " + timeTransparency[26]);
        }
        if (v.getId() == R.id.imageButton47) {
            MessageBox("Contribution of this day is: " + timeTransparency[27]);
        }
        if (v.getId() == R.id.imageButton51) {
            MessageBox("Contribution of this day is: " + timeTransparency[28]);
        }
        if (v.getId() == R.id.imageButton52) {
            MessageBox("Contribution of this day is: " + timeTransparency[29]);
        }
        if (v.getId() == R.id.imageButton53) {
            MessageBox("Contribution of this day is: " + timeTransparency[30]);
        }
        if (v.getId() == R.id.imageButton54) {
            MessageBox("Contribution of this day is: " + timeTransparency[31]);
        }
        if (v.getId() == R.id.imageButton55) {
            MessageBox("Contribution of this day is: " + timeTransparency[32]);
        }
        if (v.getId() == R.id.imageButton56) {
            MessageBox("Contribution of this day is: " + timeTransparency[33]);
        }
        if (v.getId() == R.id.imageButton57) {
            MessageBox("Contribution of this day is: " + timeTransparency[34]);
        }
        if (v.getId() == R.id.imageButton61) {
            MessageBox("Contribution of this day is: " + timeTransparency[35]);
        }
        if (v.getId() == R.id.imageButton62) {
            MessageBox("Contribution of this day is: " + timeTransparency[36]);
        }
        if (v.getId() == R.id.imageButton63) {
            MessageBox("Contribution of this day is: " + timeTransparency[37]);
        }
        if (v.getId() == R.id.imageButton64) {
            MessageBox("Contribution of this day is: " + timeTransparency[38]);
        }
        if (v.getId() == R.id.imageButton65) {
            MessageBox("Contribution of this day is: " + timeTransparency[39]);
        }
        if (v.getId() == R.id.imageButton66) {
            MessageBox("Contribution of this day is: " + timeTransparency[40]);
        }
        if (v.getId() == R.id.imageButton67) {
            MessageBox("Contribution of this day is: " + timeTransparency[41]);
        }
        if (v.getId() == R.id.imageButton71) {
            MessageBox("Contribution of this day is: " + timeTransparency[42]);
        }
        if (v.getId() == R.id.imageButton72) {
            MessageBox("Contribution of this day is: " + timeTransparency[43]);
        }
        if (v.getId() == R.id.imageButton73) {
            MessageBox("Contribution of this day is: " + timeTransparency[44]);
        }
        if (v.getId() == R.id.imageButton74) {
            MessageBox("Contribution of this day is: " + timeTransparency[45]);
        }
        if (v.getId() == R.id.imageButton75) {
            MessageBox("Contribution of this day is: " + timeTransparency[46]);
        }
        if (v.getId() == R.id.imageButton76) {
            MessageBox("Contribution of this day is: " + timeTransparency[47]);
        }
        if (v.getId() == R.id.imageButton77) {
            MessageBox("Contribution of this day is: " + timeTransparency[48]);
        }
        if (v.getId() == R.id.imageButton81) {
            MessageBox("Contribution of this day is: " + timeTransparency[49]);
        }
        if (v.getId() == R.id.imageButton82) {
            MessageBox("Contribution of this day is: " + timeTransparency[50]);
        }
        if (v.getId() == R.id.imageButton83) {
            MessageBox("Contribution of this day is: " + timeTransparency[51]);
        }
        if (v.getId() == R.id.imageButton84) {
            MessageBox("Contribution of this day is: " + timeTransparency[52]);
        }
        if (v.getId() == R.id.imageButton85) {
            MessageBox("Contribution of this day is: " + timeTransparency[53]);
        }
        if (v.getId() == R.id.imageButton86) {
            MessageBox("Contribution of this day is: " + timeTransparency[54]);
        }
        if (v.getId() == R.id.imageButton87) {
            MessageBox("Contribution of this day is: " + timeTransparency[55]);
        }

        if (v.getId() == R.id.imageButtonHex11) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[0]);
        }
        if (v.getId() == R.id.imageButtonHex12) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[1]);
        }
        if (v.getId() == R.id.imageButtonHex13) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[2]);
        }
        if (v.getId() == R.id.imageButtonHex14) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[3]);
        }
        if (v.getId() == R.id.imageButtonHex15) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[4]);
        }
        if (v.getId() == R.id.imageButtonHex16) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[5]);
        }
        if (v.getId() == R.id.imageButtonHex17) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[6]);
        }
        if (v.getId() == R.id.imageButtonHex18) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[7]);
        }
        if (v.getId() == R.id.imageButtonHex21) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[8]);
        }
        if (v.getId() == R.id.imageButtonHex22) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[9]);
        }
        if (v.getId() == R.id.imageButtonHex23) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[10]);
        }
        if (v.getId() == R.id.imageButtonHex24) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[11]);
        }
        if (v.getId() == R.id.imageButtonHex25) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[12]);
        }
        if (v.getId() == R.id.imageButtonHex26) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[13]);
        }
        if (v.getId() == R.id.imageButtonHex27) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[14]);
        }
        if (v.getId() == R.id.imageButtonHex28) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[15]);
        }
        if (v.getId() == R.id.imageButtonHex31) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[16]);
        }
        if (v.getId() == R.id.imageButtonHex32) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[17]);
        }
        if (v.getId() == R.id.imageButtonHex33) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[18]);
        }
        if (v.getId() == R.id.imageButtonHex34) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[19]);
        }
        if (v.getId() == R.id.imageButtonHex35) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[20]);
        }
        if (v.getId() == R.id.imageButtonHex36) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[21]);
        }
        if (v.getId() == R.id.imageButtonHex37) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[22]);
        }
        if (v.getId() == R.id.imageButtonHex38) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[23]);
        }
        if (v.getId() == R.id.imageButtonHex41) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[24]);
        }
        if (v.getId() == R.id.imageButtonHex42) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[25]);
        }
        if (v.getId() == R.id.imageButtonHex43) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[26]);
        }
        if (v.getId() == R.id.imageButtonHex44) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[27]);
        }
        if (v.getId() == R.id.imageButtonHex45) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[28]);
        }
        if (v.getId() == R.id.imageButtonHex46) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[29]);
        }
        if (v.getId() == R.id.imageButtonHex47) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[30]);
        }
        if (v.getId() == R.id.imageButtonHex48) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[31]);
        }
        if (v.getId() == R.id.imageButtonHex51) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[32]);
        }
        if (v.getId() == R.id.imageButtonHex52) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[33]);
        }
        if (v.getId() == R.id.imageButtonHex53) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[34]);
        }
        if (v.getId() == R.id.imageButtonHex54) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[35]);
        }
        if (v.getId() == R.id.imageButtonHex55) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[36]);
        }
        if (v.getId() == R.id.imageButtonHex56) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[37]);
        }
        if (v.getId() == R.id.imageButtonHex57) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[38]);
        }
        if (v.getId() == R.id.imageButtonHex58) {
            MessageBox("Contribution of this hexagon is: " + spaceTransparency[39]);
        }
    }

    private void goToLoginPage() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void MessageBox(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
