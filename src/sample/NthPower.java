package sample;


import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

    /**================================================= Separate class for Nth Power =====================================================**/
public class NthPower extends Controller{
    
    /**========================================= METHOD FOR SINE EXPANSION FOR Nth POWER ========================================**/
    public void sinPower(ComboBox power_combo, HBox hbox1, HBox hbox2, Line upon){
        //nth Power
        int n= Integer.parseInt(power_combo.getValue().toString());
        int r=0;
        
        // first one is for Alternate '+' or '-' every term and second one is for whole expansion in [brackets]
        char sign='+',sign2;
        
        //for not printing '+' sign before the first term
        counter=0;
        
        //resetting upon length
        upon.setEndX(0);
        
        //preLabel for sign for whole Expression
        Label preLabel=new Label();
        
        //for Even power
        if(n%2==0){
            if(Math.pow(-1,n/2)>=0)sign2='+';
            else sign2='-';
        }
        //for odd power
        else {
            if(Math.pow(-1,(n-1)/2)>=0)sign2='+';
            else sign2='-';
        }
        //for unity power , nothing should be printed before [Brackets]
        if(n==1) preLabel.setText("[");
        else preLabel.setText(String.valueOf(sign2)+1+".[");
        
        //and lastly added it to Hbox 1
        preLabel.setFont(Font.font(null, FontPosture.REGULAR,15));
        hbox1.getChildren().addAll(preLabel);
    
        //n  | where 2r<n
        //∑   (-1)^(n/2) [(-1)^r*nCr(n,2r)*cos((n-2r)θ)] + nCr(n,n/2)/2        for n is even
        //r=0
    
        //n  | where 2r<n
        //∑   (-1)^(n/2) [(-1)^r*nCr(n,2r)*sin((n-2r)θ)]                                for n is odd
        //r=0
       while(2*r<n){
           
           //when power is even cos can handle -ve sign for -ve angle only (-1) power determine term's sign
           if(n%2==0){
               if(Math.pow(-1,r)>=0)sign='+';
               else sign='-';
           }
           //when power is even we have to check all possible scenarios for -ve sign
           else {
                    if(Math.pow(-1,r)>=0 && operatorList.get(0).equals("+"))sign='+';
               else if(Math.pow(-1,r)>=0 && operatorList.get(0).equals("-"))sign='-';
               else if(Math.pow(-1,r)<=0 && operatorList.get(0).equals("+"))sign='-';
               else if(Math.pow(-1,r)<=0 && operatorList.get(0).equals("-"))sign='+';
           }
           
           //for not printing '+' sign before first term
           if(counter!=0 || operatorList.get(0).equals("-")){
               
               //created dynamic Label and has set its value from sign variable
               Label signLabel=new Label();
               signLabel.setText(String.valueOf(sign));
               signLabel.setFont(Font.font(null, FontWeight.NORMAL, 15));
               hbox1.getChildren().addAll(signLabel);
           }
           //after first iteration counter is free for above condition
           counter=1;
    
           //coefficient 1 will not be displayed
           if(nCr(n,r)!=1){
    
               //Dynamic label for Coefficient of every term
               Label nCr=new Label();
               nCr.setText(String.valueOf(nCr(n,r)));
               nCr.setFont(Font.font(null, FontWeight.NORMAL, 15));
               hbox1.getChildren().addAll(nCr);
           }
    
           //Dynamic label for display sin or cos depending upon power is even or odd
           Label sine=new Label();
           if(n%2==0) sine.setText("cos");
           else sine.setText("sin");
           sine.setFont(Font.font(null, FontWeight.NORMAL, 15));
           hbox1.getChildren().addAll(sine);
    
           //Dynamic Label for displaying corresponding sine angle
           Label sinAngle=new Label();
           
           //also coefficient of Angle should not be printed if it is 1
           if((n-2*r)*angleMultiple.get(0)!=1)
           sinAngle.setText("("+(n-2*r)*angleMultiple.get(0)+angleList.get(0)+")");
           else
               sinAngle.setText("("+angleList.get(0)+")");
           
           sinAngle.setFont(Font.font(null, FontWeight.NORMAL, 15));
           hbox1.getChildren().addAll(sinAngle);
    
           //r incremented for next terms of the summation
           r++;
       }
       
       //Dynamic label for post ']' depending upon power is even or odd
        Label postLabel=new Label();
        postLabel.setFont(Font.font(null, FontWeight.NORMAL, 15));
        
        //extra term will be added if power is even
        if(n%2==0) postLabel.setText(String.valueOf(sign2)+nCr(n,n/2)/2+"]");
        else postLabel.setText("]");
        
        hbox1.getChildren().addAll(postLabel);
        
        //Finally printing 2^(n-1) below upon ,cause in sine nth power expansion whole expression is divided by 2^(n-1)
        //and it should not be printed if power is 1
        if(n!=1){
            //Dynamic label for 2^(n-1) in Hbox 2
            Label uponLabel=new Label();
            uponLabel.setText(" "+(int)Math.pow(2,n-1));
            uponLabel.setFont(Font.font(null, FontWeight.NORMAL, 15));
            hbox2.getChildren().addAll(uponLabel);
            
            //length of upon depends only on no of digits in 2^(n-1)
            upon.setEndX(String.valueOf((int)Math.pow(2,n-1)).length()*8+7);
        }
    }
    
    /**======================================== METHOD FOR COSINE EXPANSION FOR Nth POWER ======================================**/
    public void cosPower(ComboBox power_combo, HBox hbox1, HBox hbox2, Line upon){
        //EVERY THING IS IDENTICAL HERE LIKE SINE EXPANSION EXCEPT THE COSINE SERIES
    
        //nth Power
        int n= Integer.parseInt(power_combo.getValue().toString());
        int r=0;
        
        //cos nth power Expansion doesn't have any -ve sign whether angle is +ve or -ve
        //char sign='+',sign2='+';
    
        //for not printing '+' sign before the first term
        counter=0;
    
        //resetting upon length
        upon.setEndX(0);
    
        //preLabel for sign for whole label
        Label preLabel=new Label();
    
        //for unity power , nothing should be printed before [Brackets]
        if(n!=1)
        preLabel.setText(1+".[");
        else preLabel.setText("[");
    
        //and lastly added it to Hbox 1
        preLabel.setFont(Font.font(null, FontPosture.REGULAR,15));
        hbox1.getChildren().addAll(preLabel);
    
        //n  | where 2r<n
        //∑   [nCr(n,2r)*cos((n-2r)θ)] + nCr(n,n/2)/2        for n is even
        //r=0
    
        //n  | where 2r<n
        //∑    [nCr(n,2r)*cos((n-2r)θ)]                                for n is odd
        //r=0
        while(2*r<n){
            //NO CONDITIONS FOR SIGN HANDLING BEFORE EVERY TERM
    
            //for not printing '+' sign before first term
            if(counter!=0 || operatorList.get(0).equals("-")){
    
                //created dynamic Label and has set its value from sign variable
                Label signLabel=new Label();
                signLabel.setText("+");
                signLabel.setFont(Font.font(null, FontWeight.NORMAL, 15));
                hbox1.getChildren().addAll(signLabel);
               
            }
            //after first iteration counter is free for above condition
            counter=1;
    
            //coefficient 1 will not be displayed
            if(nCr(n,r)!=1){
    
                //Dynamic label for Coefficient of every term
                Label nCr=new Label();
                nCr.setText(String.valueOf(angleMultiple.get(0)*nCr(n,r)));
                nCr.setFont(Font.font(null, FontWeight.NORMAL, 15));
                hbox1.getChildren().addAll(nCr);
                
            }
    
            //Dynamic label for display cos (only cos terms are there in whole Expansion)
            Label cos=new Label();
            cos.setText("cos");
            cos.setFont(Font.font(null, FontWeight.NORMAL, 15));
            hbox1.getChildren().addAll(cos);
    
            //Dynamic Label for displaying corresponding cosine angle
            Label cosAngle=new Label();
    
            //also coefficient of Angle should not be printed if it is 1
            if((n-2*r)*angleMultiple.get(0)!=1)
                cosAngle.setText("("+(n-2*r)*angleMultiple.get(0)+angleList.get(0)+")");
            else
                cosAngle.setText("("+angleList.get(0)+")");
            
            cosAngle.setFont(Font.font(null, FontWeight.NORMAL, 15));
            hbox1.getChildren().addAll(cosAngle);
    
            //r incremented for next terms of the summation
            r++;
        }
    
        //Dynamic label for post ']' depending upon power is even or odd
        Label postLabel=new Label();
        postLabel.setFont(Font.font(null, FontWeight.NORMAL, 15));
    
        //extra term will be added if power is even
        if(n%2==0) postLabel.setText("+"+nCr(n,n/2)/2+"]");
        else postLabel.setText("]");
        
        hbox1.getChildren().addAll(postLabel);
    
        //Finally printing 2^(n-1) below upon ,cause in sine nth power expansion whole expression is divided by 2^(n-1)
        //and it should not be printed if power is 1
        if(n!=1){
            //Dynamic label for 2^(n-1) in Hbox 2
            Label uponLabel=new Label();
            uponLabel.setText(" "+(int)Math.pow(2,n-1));
            uponLabel.setFont(Font.font(null, FontWeight.NORMAL, 15));
            hbox2.getChildren().addAll(uponLabel);
    
            //length of upon depends only on no of digits in 2^(n-1)
            upon.setEndX(String.valueOf((int)Math.pow(2,n-1)).length()*8+7);
        }
    }
    
    /**======================================== METHOD FOR TANGENT EXPANSION FOR Nth POWER ======================================**/
    public void tanPower(ComboBox power_combo, HBox hbox1, HBox hbox2, Line upon){
        //EVERY THING IS IDENTICAL HERE LIKE SINE EXPANSION EXCEPT THE TANGENT SERIES
    
        //nth Power
        int n= Integer.parseInt(power_combo.getValue().toString());
        int r=0;
        
        // first one is for Alternate '+' or '-' every term and second one is for whole expansion in [brackets]
        char sign='+',sign2;
    
        //for not printing '+' sign before the first term
        counter=0;
    
        //resetting upon length
        upon.setEndX(0);
    
        //preLabels for sign for whole Expression above and below the upon in tan expansion
        Label preLabel1=new Label();
        Label preLabel2=new Label();
    
        //for Even power
        if(n%2==0){
            if(Math.pow(-1,n/2)>=0)sign2='+';
            else sign2='-';
        }
        //for odd power
        else {
            if(Math.pow(-1,(n-1)/2)>=0)sign2='+';
            else sign2='-';
        }
        //for unity power , nothing should be printed before [Brackets]
        if(n!=1) preLabel1.setText(sign2+"[");
        else preLabel1.setText(" [");
        
        //not number is req in tan expansion because they cancel out each other
        preLabel2.setText(" [");
    
        //and lastly added them to Hbox1 and Hbox2
        preLabel1.setFont(Font.font(null, FontPosture.REGULAR,15));
        preLabel2.setFont(Font.font(null, FontPosture.REGULAR,15));
        hbox1.getChildren().add(preLabel1);
        hbox2.getChildren().add(preLabel2);
    
        //incremented upon length acc to label
        uponLength+=preLabel1.getText().length()*10;
        
        //n  | where 2r<n
        //∑   of both sine and cosine series acc to their powers
        //r=0
        while(2*r<n){
            //when power is even cos can handle -ve sign for -ve angle only (-1) power determine term's sign
            if(n%2==0){
                if(Math.pow(-1,r)>=0)sign='+';
                else sign='-';
            }
            //when power is even we have to check all possible scenarios for -ve sign only for sine series
            else {
                if(Math.pow(-1,r)>=0 && operatorList.get(0).equals("+"))sign='+';
                else if(Math.pow(-1,r)>=0 && operatorList.get(0).equals("-"))sign='-';
                else if(Math.pow(-1,r)<=0 && operatorList.get(0).equals("+"))sign='-';
                else if(Math.pow(-1,r)<=0 && operatorList.get(0).equals("-"))sign='+';
            }
    
            //for not printing '+' sign before first term
            if(counter!=0 || operatorList.get(0).equals("-")){
    
                //created dynamic Label and has set its value from sign variable
                Label signLabel1=new Label();
                signLabel1.setText(String.valueOf(sign));
                signLabel1.setFont(Font.font(null, FontWeight.NORMAL, 15));
                hbox1.getChildren().addAll(signLabel1);
                
                //incremented upon length acc to label
                uponLength+=signLabel1.getText().length()*10;
    
                //Dynamic label for cos expansion below upon (always +ve acc to series)
                Label signLabel2=new Label();
                signLabel2.setText("+");
                signLabel2.setFont(Font.font(null, FontWeight.NORMAL, 15));
                hbox2.getChildren().addAll(signLabel2);
            }
            //after first iteration counter is free for above condition
            counter=1;
    
            //coefficient 1 will not be displayed
            if(nCr(n,r)!=1){
                
                //Dynamic label for Coefficient of every term above upon
                Label nCr1=new Label();
                nCr1.setText(String.valueOf(angleMultiple.get(0)*nCr(n,r)));
                nCr1.setFont(Font.font(null, FontWeight.NORMAL, 15));
                hbox1.getChildren().addAll(nCr1);
                
                //incremented upon length acc to label
                uponLength+=nCr1.getText().length()*10;
    
                //Dynamic label for Coefficient of every term below upon
                Label nCr2=new Label();
                nCr2.setText(String.valueOf(angleMultiple.get(0)*nCr(n,r)));
                nCr2.setFont(Font.font(null, FontWeight.NORMAL, 15));
                hbox2.getChildren().addAll(nCr2);
            }
    
            //Dynamic label for display sin or cos depending upon power is even or odd above upon
            Label sine=new Label();
            if(n%2==0) sine.setText("cos");
            else sine.setText("sin");
            sine.setFont(Font.font(null, FontWeight.NORMAL, 15));
            hbox1.getChildren().addAll(sine);
            
            //incremented upon length acc to label
            uponLength+=sine.getText().length()*10;
    
            //Dynamic label for display cos below upon
            Label cos=new Label();
            cos.setText("cos");
            cos.setFont(Font.font(null, FontWeight.NORMAL, 15));
            hbox2.getChildren().addAll(cos);
    
            //Dynamic Label for displaying corresponding sine angle above upon
            Label sinAngle=new Label();
            //also coefficient of Angle should not be printed if it is 1
            if((n-2*r)*angleMultiple.get(0)!=1)
                sinAngle.setText("("+(n-2*r)*angleMultiple.get(0)+angleList.get(0)+")");
            else
                sinAngle.setText("("+angleList.get(0)+")");
            
            sinAngle.setFont(Font.font(null, FontWeight.NORMAL, 15));
            hbox1.getChildren().addAll(sinAngle);
    
            //incremented upon length acc to label
            uponLength+=sinAngle.getText().length()*10;
    
            //Dynamic Label for displaying corresponding cosine angle below upon
            Label cosAngle=new Label();
            
            //also coefficient of Angle should not be printed if it is 1
            if((n-2*r)*angleMultiple.get(0)!=1)
                cosAngle.setText("("+(n-2*r)*angleMultiple.get(0)+angleList.get(0)+")");
            else
                cosAngle.setText("("+angleList.get(0)+")");
            cosAngle.setFont(Font.font(null, FontWeight.NORMAL, 15));
            hbox2.getChildren().addAll(cosAngle);
    
            //r incremented for next terms of the summation
            r++;
        }
        
        //Dynamic label for post ']' depending upon power is even or odd above upon
        Label postLabel1=new Label();
        postLabel1.setFont(Font.font(null, FontWeight.NORMAL, 15));
    
        //extra term will be added if power is even
        if(n%2==0) postLabel1.setText(String.valueOf(sign2)+nCr(n,n/2)/2+"]");
        else postLabel1.setText("]");
        
        hbox1.getChildren().addAll(postLabel1);
    
        //incremented upon length acc to label
        uponLength+=postLabel1.getText().length()*10;
    
        //Dynamic label for post ']' depending upon power is even or odd below upon
        Label postLabel2=new Label();
        postLabel2.setFont(Font.font(null, FontWeight.NORMAL, 15));
        if(n%2==0) postLabel2.setText("+"+nCr(n,n/2)/2+"]");
        else postLabel2.setText("]");
    
        hbox2.getChildren().addAll(postLabel2);
        
        //HERE 2^(n-1) is not req because both series's coefficient cancel each other
        
        //setting upon length corresponding to sine series above the upon with some trial and error expression
        upon.setEndX(uponLength-15*n);
    }
}
