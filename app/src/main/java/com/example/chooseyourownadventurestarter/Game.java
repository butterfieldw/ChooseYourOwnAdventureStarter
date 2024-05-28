package com.example.chooseyourownadventurestarter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Game extends AppCompatActivity {

    private TextView storyTextView;
    private Button northBtn, southBtn, eastBtn, westBtn;
    private World world;
    private Place playerLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        String[] row1 = getResources().getStringArray(R.array.row1);
        String[] row2 = getResources().getStringArray(R.array.row2);
        String[] row3 = getResources().getStringArray(R.array.row3);
        String[][] descriptions = { row1, row2, row3 };

        world = new World(descriptions);
        world.setPlayerPosition(1, 1);
        playerLocation = world.getPlayerLocation();

        storyTextView = findViewById(R.id.storyTextView);
        storyTextView.setText(playerLocation.getDescription());

        northBtn = findViewById(R.id.northBtn);
        northBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(playerLocation.getRow() - 1, playerLocation.getCol());
            }
        });
        southBtn = findViewById(R.id.southBtn);
        southBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(playerLocation.getRow() + 1, playerLocation.getCol());
            }
        });
        eastBtn = findViewById(R.id.eastBtn);
        eastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(playerLocation.getRow(), playerLocation.getCol() + 1);
            }
        });
        westBtn = findViewById(R.id.westBtn);
        westBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(playerLocation.getRow(), playerLocation.getCol() - 1);
            }
        });
    }

    public void move(int row, int col) {
        boolean invalidRow = row < 0 || row >= world.getMap().length;
        boolean invalidCol = col < 0 || col >= world.getMap()[0].length;
        if (invalidRow || invalidCol) {
            String feedback = "Cannot move in that direction.\nYour location is: " + playerLocation;
            storyTextView.setText(feedback);
        } else {
            world.setPlayerPosition(row, col);
            playerLocation = world.getPlayerLocation();
            storyTextView.setText(playerLocation.toString());
        }
    }
}