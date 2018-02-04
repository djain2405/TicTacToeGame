package com.my.divya.tictactoeapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean playerX;
    private boolean playerO;
    private boolean winnerX;
    private boolean winnerO;
    private int [][]board;
    private int count;
    private TextView turn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        playerX = true;
        playerO = false;
        board = new int[3][3];
        turn = (TextView)findViewById(R.id.turnText);
        count  = 0;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void move(View v)
    {
        int x = -1;
        int y = -1;
        int id = v.getId();
        Button temp = (Button) findViewById(id);
        //System.out.println("Divya id is " + id);
        switch(id)
        {
            case R.id.id1:
                x = 0;
                y = 0;
                break;
            case R.id.id2:
                x = 0;
                y = 1;
                break;
            case R.id.id3:
               x = 0;
                y = 2;
                break;
            case R.id.id4:

                x = 1;
                y = 0;
                break;
            case R.id.id5:
                //System.out.println("Divya id is " + id);
                x = 1;
                y = 1;
                break;
            case R.id.id6:

                x = 1;
                y = 2;
                break;
            case R.id.id7:

                x = 2;
                y = 0;
                break;
            case R.id.id8:

                x = 2;
                y = 1;
                break;
            case R.id.id9:

                x = 2;
                y = 2;
                break;
        }


        if(board[x][y] != 0)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error");
            builder.setMessage("This Cell is not empty");
            builder.setCancelable(true);
            builder.create().show();
            return;
        }
        if(playerX)
        {
            count++;
            temp.setText("X");
            turn.setText("Player O Turn");
            board[x][y] = 1;
            playerX = false;
            playerO = true;
        }
        else
        {
            count++;
            temp.setText("O");
            turn.setText("Player X Turn");
            board[x][y] = -1;
            playerO = false;
            playerX = true;
        }

        checkWinner(count);
    }

    void checkWinner(int count)
    {

        //Check for horizontal rows
        for(int i = 0; i < 3; i++)
        {
            int sum = board[i][0] + board[i][1] + board[i][2];
            if(sum == 3) {
                winnerX = true;
                break;
            }
            else if(sum == -3)
            {
                winnerO = true;
                break;
            }
        }
        //Check for vertical rows
        if(winnerX == false && winnerO == false) {
            for (int i = 0; i < 3; i++) {
                int sum = board[0][i] + board[1][i] + board[2][i];
                if (sum == 3) {
                    winnerX = true;
                    break;
                } else if (sum == -3) {
                    winnerO = true;
                    break;
                }
            }
        }

        if(winnerX == false && winnerO == false) {
            //Check for Diagonal left to right rows
            int sum = board[0][0] + board[1][1] + board[2][2];
            if (sum == 3) {
                winnerX = true;

            } else if (sum == -3) {
                winnerO = true;

            }
        }
        if(winnerX == false && winnerO == false) {
            //Check for Diagonal right to left rows
            int sum = board[2][0] + board[1][1] + board[0][2];
            if (sum == 3) {
                winnerX = true;

            } else if (sum == -3) {
                winnerO = true;

            }
        }

        if(winnerX) {

            turn.setText("Game Over!");

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Win");
            builder.setMessage("Player X Won this game!");
            builder.setCancelable(false);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            builder.create().show();
        }

        if(winnerO) {
            turn.setText("Game Over!");

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Win");
            builder.setMessage("Player O Won this game!");
            builder.setCancelable(false);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            builder.create().show();
        }

        if(count == 9 && winnerX == false && winnerO == false)
        {
            turn.setText("Game Over!");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Draw");
            builder.setMessage("This game is a Draw!");
            builder.setCancelable(false);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            builder.create().show();
        }

        }


    }

