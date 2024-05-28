package com.example.chooseyourownadventurestarter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Game extends AppCompatActivity {

    private ImageView storyImageView;
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

        storyImageView = findViewById(R.id.storyImageView);
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
            updateImage(row, col);
        }
    }

    public void updateImage(int row, int col) {
        if (row == 0 && col == 0) {
            storyImageView.setImageResource(R.drawable.r0c0);
        } else if (row == 0 && col == 1) {
            storyImageView.setImageResource(R.drawable.r0c1);
        } else if (row == 0 && col == 2) {
            storyImageView.setImageResource(R.drawable.r0c2);
        } else if (row == 1 && col == 0) {
            storyImageView.setImageResource(R.drawable.r1c0);
        } else if (row == 1 && col == 1) {
            storyImageView.setImageResource(R.drawable.r1c1);
        } else if (row == 1 && col == 2) {
            storyImageView.setImageResource(R.drawable.r1c2);
        } else if (row == 2 && col == 0) {
            storyImageView.setImageResource(R.drawable.r2c0);
        } else if (row == 2 && col == 1) {
            storyImageView.setImageResource(R.drawable.r2c1);
        } else if (row == 2 && col == 2) {
            storyImageView.setImageResource(R.drawable.r2c2);
        } else {
            storyImageView.setImageResource(R.drawable.grid);
        }
    }
}