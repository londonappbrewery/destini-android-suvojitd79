package com.londonappbrewery.destini;

final class myButton{

    String data;
    String endData; //last question
    Decision child;

    myButton(String data,String  endData)
    {
        this.data = data;
        this.endData = endData;
        this.child = null;
    }

}

public class Decision{

        String data;
        myButton left,right;
        Decision(String data,String left,String right,String leftData,String rightData)
        {
                this.data = data;
                this.left = new myButton(left,leftData);
                this.right = new myButton(right,rightData);
        }

}
