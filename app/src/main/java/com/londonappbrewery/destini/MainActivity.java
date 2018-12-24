package com.londonappbrewery.destini;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // TODO: Steps 4 & 8 - Declare member variables here:

    TextView story;
    Button ans1,ans2;
    Decision root;
    final Decision r=root;
    RelativeLayout back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        root = new Decision(getResources().getString(R.string.T1_Story),getResources().getString(R.string.T1_Ans1),getResources().getString(R.string.T1_Ans2),"","");
        root.left.child = new Decision(getResources().getString(R.string.T3_Story),getResources().getString(R.string.T3_Ans1),getResources().getString(R.string.T3_Ans2),getResources().getString(R.string.T6_End),getResources().getString(R.string.T5_End));
        root.right.child = new Decision(getResources().getString(R.string.T2_Story),getResources().getString(R.string.T2_Ans1),getResources().getString(R.string.T2_Ans2),"",getResources().getString(R.string.T4_End));
        root.right.child.left.child = new Decision(getResources().getString(R.string.T3_Story),getResources().getString(R.string.T3_Ans1),getResources().getString(R.string.T3_Ans2),getResources().getString(R.string.T6_End),getResources().getString(R.string.T5_End));


        // TODO: Step 5 - Wire up the 3 views from the layout to the member variables:
            story = findViewById(R.id.storyTextView);
            ans1 = findViewById(R.id.buttonTop);
            ans2 = findViewById(R.id.buttonBottom);
            back = findViewById(R.id.back);

            story.setText(root.data);
            ans1.setText(root.left.data);
            ans2.setText(root.right.data);

        // TODO: Steps 6, 7, & 9 - Set a listener on the top button:

            ans1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(root.left.endData=="") {
                        root = root.left.child;
                        changeStory(root);
                    }else
                    {
                        back.setVisibility(View.INVISIBLE);
                        showResult(root.left.endData);
                    }



                }
            });



        // TODO: Steps 6, 7, & 9 - Set a listener on the bottom button:

        ans2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                if(root.right.endData=="") {
                    root = root.right.child;
                    changeStory(root);
                }else
                {
                    back.setVisibility(View.INVISIBLE);
                    showResult(root.right.endData);
                }



            }
        });


    }

    private void changeStory(Decision root)
    {
        story.setText(root.data);
        ans1.setText(root.left.data);
        ans2.setText(root.right.data);
    }

    private void showResult(String data)
    {
        back.setBackgroundResource(R.color.back);
        showPopUp();
        story.setText(data);
        ans1.setVisibility(View.GONE);
        ans2.setVisibility(View.GONE);
        ans1.setText("RESET");
        ans1.setVisibility(View.VISIBLE);
        ans1.setTextSize(getResources().getDimension(R.dimen.reset));

    }

    private void showPopUp()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("It's Over now!!!");
        alert.setMessage("Please provide some feedback so that we can improve the user experience");
        alert.setPositiveButton("Result", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
                back.setVisibility(View.VISIBLE);

            }
        });

        alert.setNegativeButton("Feedback", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel(); //provide the code here
            }
        });

        AlertDialog al = alert.create();
        al.show();

    }

}
