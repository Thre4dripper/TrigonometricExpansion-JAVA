package sample;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

    /**================================================= Separate class for Integral Multiple =====================================================**/
public class IntegralAngle extends Controller {
    
    /**================================== METHOD FOR SINE EXPANSION FOR INTEGRAL ANGLE MULTIPLE ===================================**/
    public void SinnXExpansion(HBox hbox1,Line upon){
        //Resetting upon length for Fresh output
        upon.setEndX(0);
        //n of sin(nx)
        int n=angleMultiple.get(0),r=0;
        
        //for alternate '+' and '-'
        char sign;
    
        //n  | where 2r+1<=n
        //∑   (-1)^r nCr(n,2r+1)*cos^(n-2r-1)(θ)*sin^(2r+1)(θ)
        //r=0
        while(2*r+1<=n){
            //for positive integral Angle
            if(operatorList.get(0).equals("+")){
                // (-1)^r  alternate +ve and -ve
                if(Math.pow(-1,r)>=0)sign='+';
                else sign='-';
            }
            //for negative integral Angle
            else{
                //only sine term will impact on term's sign and product of both (-1) powers must be +ve for +ve sign
                if(Math.pow(-1,r)*Math.pow(-1,2*r+1)>=0)sign='+';
                else sign='-';
            }
            
            //positive sign will not be displayed with very first term (horizontal box is empty) but negative will be shown
            //and at last of every Label's declaration ,it is added to first horizontal box
            if(!hbox1.getChildren().isEmpty() || operatorList.get(0).equals("-")){
                
                //created dynamic Label and has set its value from sign variable
                Label signLabel=new Label();
                signLabel.setText(String.valueOf(sign));
                signLabel.setFont(Font.font(null, FontWeight.NORMAL, 15));
                hbox1.getChildren().addAll(signLabel);
            }
            
            //coefficient 1 will not be displayed
            if(nCr(n,2*r+1)!=1){
                
                //Dynamic label for Coefficient of every term
                Label nCr=new Label();
                nCr.setText(String.valueOf(nCr(n,2*r+1)));
                nCr.setFont(Font.font(null, FontWeight.NORMAL, 15));
                hbox1.getChildren().addAll(nCr);
            }
            
            //power of cosine terms should not be 0
            if(n-2*r-1!=0){
                //Dynamic label for display cos
                Label cosine=new Label();
                cosine.setText("cos");
                cosine.setFont(Font.font(null, FontWeight.NORMAL, 15));
                hbox1.getChildren().addAll(cosine);
                
                //also is power is 1 then power should not be printed
                if(n-2*r-1!=1){
                    
                    //Dynamic Label for displaying cos power
                    Label cosPower=new Label();
                    cosPower.setText(String.valueOf(n-2*r-1));
                    cosPower.setFont(Font.font(null, FontWeight.NORMAL, 10));
                    hbox1.getChildren().addAll(cosPower);
                }
                //Dynamic Label for displaying corresponding cos angle
                Label cosAngle=new Label();
                
                //only first angle is extracted because this is applicable to only one input
                cosAngle.setText("("+angleList.get(0)+")");
                cosAngle.setFont(Font.font(null, FontWeight.NORMAL, 15));
                hbox1.getChildren().addAll(cosAngle);
            }
            
            //HERE STARTS THE PRINTING OF CORRESPONDING SINE TERMS
            //Dynamic label for printing sin on the screen
            
            //sine power is always non zero so no need for condition
            Label sine=new Label();
            sine.setText("sin");
            sine.setFont(Font.font(null, FontWeight.NORMAL, 15));
            hbox1.getChildren().addAll(sine);
            
            //again power should not be one to display and from the expression sine power cannot be zero in any term
            if(2*r+1!=1){
                
                //Dynamic Label for sine power
                Label sinPower=new Label();
                sinPower.setText(String.valueOf(2*r+1));
                sinPower.setFont(Font.font(null, FontWeight.NORMAL, 10));
                hbox1.getChildren().addAll(sinPower);
            }
            
            //Dynamic label for corresponding sine angle
            Label sinAngle=new Label();
            sinAngle.setText("("+angleList.get(0)+")");
            sinAngle.setFont(Font.font(null, FontWeight.NORMAL, 15));
            hbox1.getChildren().addAll(sinAngle);
            
            //r incremented for next terms of the summation
            r++;
        }
    }
    
    /**================================= METHOD FOR COSINE EXPANSION FOR INTEGRAL ANGLE MULTIPLE =================================**/
    public void CosnXExpansion(HBox hbox2,Line upon){
        //EVERY THING IS IDENTICAL HERE LIKE SINE EXPANSION EXCEPT THE COSINE SERIES
    
        //Resetting upon length for Fresh output
        upon.setEndX(0);
        //n of sin(nx)
        int n=angleMultiple.get(0),r=0;
    
        //for alternate '+' and '-'
        char sign;
    
        //n  | where 2r<=n
        //∑   (-1)^r nCr(n,2r)*cos^(n-2r)(θ)*sin^(2r)(θ)
        //r=0
        while(2*r<=n){
            //HERE FOR COSINE EXPANSION SIGN OF EVERY TERM DOESN'T DEPEND ON SINE (SINE HAS EVEN POWER) FOR -VE ANGLES
            // (-1)^r  alternate +ve and -ve
            if(Math.pow(-1,r)>=0)sign='+';
            else sign='-';
            
            //positive sign will not be displayed with very first term (horizontal box is empty) but negative will be shown
            //and at last of every Label's declaration ,it is added to Second horizontal box
            if(!hbox2.getChildren().isEmpty() || operatorList.get(0).equals("-")){
    
                //created dynamic Label and has set its value from sign variable
                Label signLabel=new Label();
                signLabel.setText(String.valueOf(sign));
                signLabel.setFont(Font.font(null, FontWeight.NORMAL, 15));
                hbox2.getChildren().addAll(signLabel);
            }
    
            //coefficient 1 will not be displayed
            if(nCr(n,2*r)!=1){
                
                //Dynamic label for Coefficient of every term
                Label nCr=new Label();
                nCr.setText(String.valueOf(nCr(n,2*r)));
                nCr.setFont(Font.font(null, FontWeight.NORMAL, 15));
                hbox2.getChildren().addAll(nCr);
            }
    
            //power of cosine terms should not be 0
            if(n-2*r!=0){
                //Dynamic label for display cos
                Label cosine=new Label();
                cosine.setText("cos");
                cosine.setFont(Font.font(null, FontWeight.NORMAL, 15));
                hbox2.getChildren().addAll(cosine);
    
                //also is power is 1 then power should not be printed
                if(n-2*r!=1){
                    //Dynamic Label for displaying cos power
                    Label cosPower=new Label();
                    cosPower.setText(String.valueOf(n-2*r));
                    cosPower.setFont(Font.font(null, FontWeight.NORMAL, 10));
                    hbox2.getChildren().addAll(cosPower);
                }
                //Dynamic Label for displaying corresponding cos angle
                Label cosAngle=new Label();
    
                //only first angle is extracted because this is applicable to only one input
                cosAngle.setText("("+angleList.get(0)+")");
                cosAngle.setFont(Font.font(null, FontWeight.NORMAL, 15));
                hbox2.getChildren().addAll(cosAngle);
                
            }
    
            //HERE STARTS THE PRINTING OF CORRESPONDING SINE TERMS
            //Dynamic label for printing sin on the screen
            
            //Also sine power should not be 0
            if(2*r!=0){
                Label sine=new Label();
                sine.setText("sin");
                sine.setFont(Font.font(null, FontWeight.NORMAL, 15));
                hbox2.getChildren().addAll(sine);
    
                //here sine power can never be 1 so no need for condition
                
                //Dynamic Label for sine power
                Label sinPower=new Label();
                sinPower.setText(String.valueOf(2*r));
                sinPower.setFont(Font.font(null, FontWeight.NORMAL, 10));
                hbox2.getChildren().addAll(sinPower);
    
                //Dynamic label for corresponding sine angle
                Label sinAngle=new Label();
                sinAngle.setText("("+angleList.get(0)+")");
                sinAngle.setFont(Font.font(null, FontWeight.NORMAL, 15));
                hbox2.getChildren().addAll(sinAngle);
            }
    
            //r incremented for next terms of the summation
            r++;
        }
    }
    
    /**================================= METHOD FOR TANGENT EXPANSION FOR INTEGRAL ANGLE MULTIPLE ===============================**/
    public void TannXExpansion(HBox hbox1, HBox hbox2, Line upon){
        //EVERY THING IS IDENTICAL HERE LIKE SINE EXPANSION EXCEPT THE TANGENT SERIES
        
        //Resetting upon length for Fresh output
        upon.setEndX(0);
        //n of sin(nx)
        int n=angleMultiple.get(0),r=0;
    
        //for alternate '+' and '-'
        char sign;
    
        //n  | where 2r+1<=n
        //∑   (-1)^r nCr(n,2r+1)*tan^(2r+1)(θ) for upper portion of upon
        //r=0
        while(2*r+1<=n){
            //for positive integral Angle
            if(operatorList.get(0).equals("+")){
                // (-1)^r  alternate +ve and -ve
                if(Math.pow(-1,r)>=0)sign='+';
                else sign='-';
            }
            //for negative integral Angle
            else{
                //only tan term will impact on term's sign and product of both (-1) powers must be +ve for +ve sign
                if(Math.pow(-1,r)*Math.pow(-1,2*r+1)>=0)sign='+';
                else sign='-';
            }
    
            //positive sign will not be displayed with very first term (horizontal box is empty) but negative will be shown
            //and at last of every Label's declaration ,it is added to first horizontal box
            if(!hbox1.getChildren().isEmpty() || operatorList.get(0).equals("-")){
    
                //created dynamic Label and has set its value from sign variable
                Label signLabel=new Label();
                signLabel.setText(String.valueOf(sign));
                signLabel.setFont(Font.font(null, FontWeight.NORMAL, 15));
                hbox1.getChildren().addAll(signLabel);
    
                //incremented upon length acc to label
                uponLength+=10;
            
            }
    
            //coefficient 1 will not be displayed
            if(nCr(n,2*r+1)!=1){
    
                //Dynamic label for Coefficient of every term
                Label nCr=new Label();
                nCr.setText(String.valueOf(nCr(n,2*r+1)));
                nCr.setFont(Font.font(null, FontWeight.NORMAL, 15));
                hbox1.getChildren().addAll(nCr);
    
                //incremented upon length acc to label
                uponLength+=nCr.getText().length()*10;
            }
    
            //power tan is always no zero so no need for condition
            
            //Dynamic label for display tan
            Label tangent=new Label();
            tangent.setText("tan");
            tangent.setFont(Font.font(null, FontWeight.NORMAL, 15));
            hbox1.getChildren().addAll(tangent);
    
            //incremented upon length acc to label
            uponLength+=30;
    
            //also is power is 1 then power should not be printed
            if(2*r+1!=1){
    
                //Dynamic Label for displaying tan power
                Label tanPower=new Label();
                tanPower.setText(String.valueOf(2*r+1));
                tanPower.setFont(Font.font(null, FontWeight.NORMAL, 10));
                hbox1.getChildren().addAll(tanPower);
    
                //incremented upon length acc to label
                uponLength+=tanPower.getText().length()*4;
            }
            //Dynamic Label for displaying corresponding cos angle
            Label tanAngle=new Label();
    
            //only first angle is extracted because this is applicable to only one input
            tanAngle.setText("("+angleList.get(0)+")");
            tanAngle.setFont(Font.font(null, FontWeight.NORMAL, 15));
            hbox1.getChildren().addAll(tanAngle);
    
            //incremented upon length acc to label
            uponLength+=7;
    
            //r incremented for next terms of the summation
            r++;
        }
        
        //EVERY THING IS IDENTICAL HERE LIKE COSINE EXPANSION EXCEPT THE TANGENT SERIES
        
        //if angle integral is non-unity then upon comes into play so applied upon length corresponding to upper portion on upon calculated by upon length
        if(angleMultiple.get(0)!=1)
        upon.setEndX(uponLength);
        
        //resetting r for new loop for new summation series
        r=0;
    
        //n  | where 2r<=n
        //∑   (-1)^r nCr(n,2r)*tan^(2r)(θ) for lower portion of upon
        //r=0
        while(2*r<=n){
            //HERE FOR TANGENT EXPANSION SIGN OF EVERY TERM DOESN'T DEPEND ON LOWER EXPANSION (TAN HAS EVEN POWER) FOR -VE ANGLES
            // (-1)^r  alternate +ve and -ve
            if(Math.pow(-1,r)*Math.pow(-1,2*r)>=0)sign='+';
            else sign='-';
    
            //positive sign will not be displayed with very first term (horizontal box is empty) but negative will be shown
            //and at last of every Label's declaration ,it is added to Second horizontal box
            if(!hbox2.getChildren().isEmpty()){
    
                //created dynamic Label and has set its value from sign variable
                Label signLabel=new Label();
                signLabel.setText(String.valueOf(sign));
                signLabel.setFont(Font.font(null, FontWeight.NORMAL, 15));
                hbox2.getChildren().addAll(signLabel);
            }
    
            //coefficient 1 will not be displayed
            if(nCr(n,2*r)!=1){
                
                //Dynamic label for Coefficient of every term
                Label nCr=new Label();
                nCr.setText(String.valueOf(nCr(n,2*r)));
                nCr.setFont(Font.font(null, FontWeight.NORMAL, 15));
                hbox2.getChildren().addAll(nCr);
            }
    
            //Dynamic label for display tan
            Label tangent=new Label();
            //Dynamic label for display tan power
            Label tanPower=new Label();
            //Dynamic label for display corresponding tan angle
            Label tanAngle=new Label();
    
            //power of cosine terms should not be 0
            if(2*r!=0){
                tangent.setText("tan");
                tangent.setFont(Font.font(null, FontWeight.NORMAL, 15));
                hbox2.getChildren().addAll(tangent);
                
                //tan power can never be unity so no need for condition
                tanPower.setText(String.valueOf(2*r));
                tanPower.setFont(Font.font(null, FontWeight.NORMAL, 10));
                hbox2.getChildren().addAll(tanPower);
    
                //only first angle is extracted because this is applicable to only one input
                tanAngle.setText("("+angleList.get(0)+")");
                tanAngle.setFont(Font.font(null, FontWeight.NORMAL, 15));
                hbox2.getChildren().addAll(tanAngle);
                
            }
            
            //for lower portion first term is always 1 (power of first term is zero)
            else if(angleMultiple.get(0)!=1){
                tangent.setText("1");
                tangent.setFont(Font.font(null, FontWeight.NORMAL, 15));
                hbox2.getChildren().addAll(tangent);
            }
            
            //r incremented for next terms of the summation
            r++;
        }
    }
}