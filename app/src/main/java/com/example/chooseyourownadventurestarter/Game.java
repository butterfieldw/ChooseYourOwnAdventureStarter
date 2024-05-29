package com.example.chooseyourownadventurestarter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

        initializeTheWorld();
        restrictPlayerMovement();

        storyImageView = findViewById(R.id.storyImageView);
        storyTextView = findViewById(R.id.storyTextView);
        storyTextView.setText(playerLocation.toString());

        northBtn = findViewById(R.id.northBtn);
        northBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move("north");
            }
        });
        southBtn = findViewById(R.id.southBtn);
        southBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move("south");
            }
        });
        eastBtn = findViewById(R.id.eastBtn);
        eastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move("east");
            }
        });
        westBtn = findViewById(R.id.westBtn);
        westBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move("west");
            }
        });
    }

    private void initializeTheWorld() {
        String[] row1 = getResources().getStringArray(R.array.row1);
        String[] row2 = getResources().getStringArray(R.array.row2);
        String[] row3 = getResources().getStringArray(R.array.row3);
        String[][] descriptions = { row1, row2, row3 };

        world = new World(descriptions);
        world.setPlayerPosition(1, 1);
        playerLocation = world.getPlayerLocation();
    }

    private void restrictPlayerMovement() {
        // The center position only allows north and south exits.
        world.getMap()[0][0].setExits(new String[] { "east" });
        world.getMap()[0][1].setExits(new String[] { "east", "south", "west" });
        world.getMap()[0][2].setExits(new String[] { "west" });
        world.getMap()[1][1].setExits(new String[] { "north", "south" });
        world.getMap()[2][0].setExits(new String[] { "east" });
        world.getMap()[2][1].setExits(new String[] { "north", "east", "west" });
        world.getMap()[2][2].setExits(new String[] { "west" });
    }

    public void move(String direction) {
        int row = playerLocation.getRow();
        int col = playerLocation.getCol();
        if (direction.equalsIgnoreCase("north")) {
            row--;
        } else if (direction.equalsIgnoreCase("east")) {
            col++;
        }  else if (direction.equalsIgnoreCase("south")) {
            row++;
        } else if (direction.equalsIgnoreCase("west")) {
            col--;
        }
        boolean invalidRow = row < 0 || row >= world.getMap().length;
        boolean invalidCol = col < 0 || col >= world.getMap()[0].length;

        if (playerLocation.canExit(direction) && !invalidRow && !invalidCol) {
            world.setPlayerPosition(row, col);
            playerLocation = world.getPlayerLocation();
            storyTextView.setText(playerLocation.toString());
            updateImage(row, col);
        } else {
            storyTextView.setText(playerLocation.toString());
            Toast.makeText(getApplicationContext(),
                      "Cannot move in that direction",
                           Toast.LENGTH_SHORT).show();
        }
    }

    public void updateImage(int row, int col) {
        int[][] images = {
                            { R.drawable.r0c0, R.drawable.r0c1, R.drawable.r0c2 },
                            { R.drawable.r1c0, R.drawable.r1c1, R.drawable.r1c2 },
                            { R.drawable.r2c0, R.drawable.r2c1, R.drawable.r2c2 },
        };

        if (row >= 0 && row < images.length && col >= 0 && col < images[0].length) {
            storyImageView.setImageResource(images[row][col]);
        } else {
            storyImageView.setImageResource(R.drawable.grid);
        }
    }
}