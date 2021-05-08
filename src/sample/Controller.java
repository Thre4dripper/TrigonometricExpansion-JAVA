package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    
    @FXML
    ComboBox trig_combo,power_combo;//Combo boxes for switching Trigonometric angles and set power
   @FXML
    TextField anglefield;//Main input field
   @FXML
    Button alpha_btn,beta_btn,gamma_btn,delta_btn,theta_btn,phi_btn;//Greek symbols buttons
   @FXML
    RadioButton angle_radio,power_radio, sum_radio;//radio buttons for switching modes
   @FXML
    Label type;//Instruction and error Message Label
   @FXML
   HBox hbox1,hbox2;//Horizontal boxes for output Upper and Lower the UPON
   @FXML
   Line upon;//UPON LINE
    
    // Storage for Main Expression and Greek Symbols
    public String expression=null,greekSymbols;
    //counter for error custom error codes and variable for UPON length
    public static int counter=0,uponLength=0;
    
    /**--------------------------------------------DYNAMIC  LISTS FOR STORING DIFF PARTS OF EXPRESSION-----------------------------------------------**/
    public static ArrayList<String> angleList =new ArrayList<>();
    public static ArrayList<Integer> operatorIndex =new ArrayList<>();
    public static ArrayList<Integer> angleMultiple=new ArrayList<>();
    public static ArrayList<String> operatorList=new ArrayList<>();
    
    /**================================================== METHOD FOR SWITCHING MODES =================================================**/
    public int switch_modes() {
        //when angle modes is selected power field is disabled
        if(angle_radio.isArmed()){
            power_combo.setDisable(true);
            power_combo.setOpacity(.5);
        }
        //Power Field is necessary for Powers mode
        else if(power_radio.isArmed()){
           power_combo.setDisable(false);
            power_combo.setOpacity(1);
        }
        //when Angle sum mode is selected power field is disabled
        else if(sum_radio.isArmed()){
            power_combo.setDisable(true);
            power_combo.setOpacity(.5);
        }
        //Returning values to switch whole Program's mode based on Radio buttons
        if(angle_radio.isSelected()) return -1;
        else if(power_radio.isSelected()) return -2;
        else if(sum_radio.isSelected()) return -3;
        return 0;
    }
    
    /**========================================== METHOD FOR SELECTING TRIGONOMETRIC ANGLES =======================================**/
    public void switchTrigFunctions(){
        //Three classes Objects for three diff modes
        IntegralAngle IA=new IntegralAngle();
        NthPower NP=new NthPower();
        AngleSum AS=new AngleSum();
        //CALCULATIONS ARE BASED ON FACTORIAL AND DOESN'T SUPPORT MORE THAN 20!
        
        //When SIN is selected
    if(trig_combo.getValue().equals("SIN")){
        
        //Limit Exceeded
        if(angleMultiple.get(0)>21){
            //Showing error message
            type.setTextFill(Color.RED);
            type.setText("INVALID ANGLE INTEGRAL");
            type.setText(type.getText()+"\nIntegral should be less than 20 because of your PC limitations");
        }
        //Outputting result on the screen
        else {
            type.setTextFill(Color.GREEN);
            type.setText("Your Sine Expansion -->");
            
            //For Sin(nx) mode
            if(switch_modes()==-1)
            IA.SinnXExpansion(hbox1,upon);
            
            //For Sin(x) Raise to the power n
            else if(switch_modes()==-2)
            NP.sinPower(power_combo,hbox1,hbox2,upon);
            
            //For Sin(x1+x2+...)
            else if(switch_modes()==-3)
                AS.sinSum(trig_combo,hbox1,upon);
        }
       
    }
        //When Cos is Selected
        else if(trig_combo.getValue().equals("COS")){
            //Limit Exceeded
        if(angleMultiple.get(0)>21){
            //Showing error message
            type.setTextFill(Color.RED);
            type.setText("INVALID ANGLE INTEGRAL");
            type.setText(type.getText()+"\nIntegral should be less than 20 because of your PC limitations");
        }
        else {
            type.setTextFill(Color.GREEN);
            type.setText("Your Cosine Expansion -->");
    
            //For Cos(nx) mode
            if(switch_modes()==-1)
                IA.CosnXExpansion(hbox1,upon);
            
            //For Cos(x) Raise to the power n
            else if(switch_modes()==-2)
                NP.cosPower(power_combo,hbox1,hbox2,upon);
    
                //For Cos(x1+x2+...)
            else if(switch_modes()==-3)
                AS.cosSum(trig_combo,hbox2,upon);
        }
    }
    //When Tan is Selected
        else if(trig_combo.getValue().equals("TAN")){
        //Limit Exceeded
        if(angleMultiple.get(0)>21){
            //Showing error message
            type.setTextFill(Color.RED);
            type.setText("INVALID ANGLE INTEGRAL");
            type.setText(type.getText()+"\nIntegral should be less than 20 because of your PC limitations");
            //Resetting Upon Length
            upon.setEndX(0);
        }
        else {
            type.setTextFill(Color.GREEN);
            type.setText("Your Tangent Expansion -->");
            //For Tan(nx) mode
            if(switch_modes()==-1)
                IA.TannXExpansion(hbox1,hbox2,upon);
    
                //For Tan(x) Raise to the power n
            else if(switch_modes()==-2)
                NP.tanPower(power_combo,hbox1,hbox2,upon);
    
                //For Tan(x1+x2+...)
            else if(switch_modes()==-3)
                AS.tanSum(trig_combo,hbox1,hbox2,upon);
        }
    }
    }
    
    /**====================================== METHOD FOR PRINTING GREEK SYMBOLS ON SCREEN ==========================================**/
    public void specialChars(){
        
        //Each corresponding button is pressed ,its symbol is Stored in Greek Symbol variable and Button gets Disabled
        //alpha (α)
        if(alpha_btn.isArmed()){
            greekSymbols=alpha_btn.getText();
            alpha_btn.setDisable(true);
        }
        //beta (β)
        else if(beta_btn.isArmed()){
            greekSymbols=beta_btn.getText();
            beta_btn.setDisable(true);
        }
        //gamma (γ)
        else if(gamma_btn.isArmed()){
            greekSymbols=gamma_btn.getText();
            gamma_btn.setDisable(true);
        }
        //delta (δ)
        else if(delta_btn.isArmed()){
            greekSymbols=delta_btn.getText();
            delta_btn.setDisable(true);
        }
        //theta (θ)
        else if(theta_btn.isArmed()){
            greekSymbols=theta_btn.getText();
            theta_btn.setDisable(true);
        }
        //phi (ɸ)
        else if(phi_btn.isArmed()){
            greekSymbols=phi_btn.getText();
            phi_btn.setDisable(true);
        }
        //appending Greek Symbols to Text Field
        anglefield.setText(anglefield.getText()+greekSymbols);
    }
    
    /**==================================== METHOD FOR ENABLING OR DISABLING GREEK SYMBOL BUTTONS ===============================**/
    public void resetSpecialChars(){
        //When a Greek symbols is missing (Backspace) from the Text Field its corresponding Greek button gets Enabled
        if(!anglefield.getText().contains("α"))alpha_btn.setDisable(false);
        if(!anglefield.getText().contains("β"))beta_btn.setDisable(false);
        if(!anglefield.getText().contains("γ"))gamma_btn.setDisable(false);
        if(!anglefield.getText().contains("δ"))delta_btn.setDisable(false);
        if(!anglefield.getText().contains("θ"))theta_btn.setDisable(false);
        if(!anglefield.getText().contains("ɸ"))phi_btn.setDisable(false);
    }
    
    /**===============================METHOD FOR CUTTING EXPRESSION INTO DIFF PARTS ================================================**/
    public void expression_cutter(){
        //Resetting error code to zero
        counter=0;
        
        //When Text Field is not empty
        if(!anglefield.getText().isEmpty()){
            
            //Storing text in Cutting Variable
            expression=anglefield.getText();
            
            //Adding '+' at the beginning if any other Symbol is not present at beginning
            if(expression.charAt(0)!='+' && expression.charAt(0)!='-')expression="+"+expression;

            //loop for iterate and store indexes of '+' and '-' in whole Expression
            for(int i=0;i<expression.length()-1;i++)
                if(expression.charAt(i)=='+' || expression.charAt(i)=='-')
                operatorIndex.add(i);
                
            //loop for iterate and store different angles along with their operators between two adjacent '+' and '-' by using their Operators indexes
            for(int i = 0; i< operatorIndex.size()-1; i++)
                angleList.add(expression.substring(operatorIndex.get(i), operatorIndex.get(i+1)));
    
            //error code 2 if anything is wrong at end of the expression
            try{
                //Store last angle which is missed from the above loop
                angleList.add(expression.substring(operatorIndex.get(operatorIndex.size()-1)));
            }catch(Exception e){
                counter=2;
            }

            //loop for iterate and extract all the operators from their angle list
            for (int i=0;i<angleList.size();i++) {
                operatorList.add(angleList.get(i).substring(0,1));

                //if angle list is greater than 2 then a angle multiple is also present in it
                if(angleList.get(i).length()>2){
                    try{
                        //so extracting their angle multiple as well from the middle section between operator and angle (1st and last index)
                        angleMultiple.add(Integer.valueOf(angleList.get(i).substring(1, angleList.get(i).length()-1)));
                    }catch(Exception e){
                        
                        //throws an exception if angle multiple is not an integer
                        counter=2;
                    }
                }
                //otherwise all the angles have 1 as their angle multiple
                else angleMultiple.add(1);
    
                //here every time a error is occurring cause i want only angles (String) should be left behind in the angle list
                try{
                    //if it is a number then i don't want it
                    Integer.parseInt(angleList.set(i,angleList.get(i).substring(angleList.get(i).length()-1)));
                    
                    //making error code 2
                    counter=2;
                }catch(Exception e){
                    
                    //throws an error if it is not a number which is i wanted
                    angleList.set(i,angleList.get(i).substring(angleList.get(i).length()-1));
                }
                
            }
            
        }
        //error code 1 for field is empty
        else counter=1;
    
        //loop for a final checkup if everything is alright in all the lists
        for(int i=0;i<angleList.size();i++){
            
            //breaking loop if there is already a error code
            if(counter==1 || counter==2)break;
            
            //checking every angle in the angle list to be a desired value
            //either it is 'a to z' or 'A to Z' or any of the greek symbol anything other than these will cause an error code 2
            else if( ( !((int)angleList.get(i).charAt(0)>=65 &&  (int)angleList.get(i).charAt(0)<=90) &&
                          !((int)angleList.get(i).charAt(0)>=97 && (int)angleList.get(i).charAt(0)<=122) &&
                        !angleList.get(i).equals("α") && !angleList.get(i).equals("β") && !angleList.get(i).equals("γ") &&
                        !angleList.get(i).equals("δ") && !angleList.get(i).equals("θ") && !angleList.get(i).equals("ɸ") ) ||
                    
                    //also every list should be of same size and must be less than 6
                    angleList.size()!=angleMultiple.size() || angleMultiple.size()!=operatorList.size() || angleList.size()>6) {
                counter=2;break;
            }
            //error code 0 if every thing is alright
            else counter=0;
        }
       
        
        /* THE FOUR HORSEMAN OF DEBUGGING
        System.out.println(operatorList);
        System.out.println(operatorIndex);
        System.out.println(angleMultiple);
        System.out.println(angleList);*/
        
        //if Power mode is activated then it should also be bug free
        //but if there should be no error in Expression (error code 1 or 2)
        if(switch_modes()==-2  && counter!=1 && counter!=2){
            //firstly Power field should not be empty
            if(power_combo.getValue()!=null){
                try{
                    //error code 5 if Power is negative Integer
                    if(Integer.parseInt(power_combo.getValue().toString())<0)counter=5;
                    
                    //error code 6 if Power is greater than 20 (factorial limitations)
                    else if(Integer.parseInt(power_combo.getValue().toString())>20)counter=6;
                    
                    //error code 0 if everything is fine
                    else counter=0;
                }catch(Exception e){
                    //error code 4 if Power is not an Integer
                    counter=4;
                }
            }
            //Throws an error code 3 if it is empty
            else counter=3;
        }
        
        //error code 1 if field is empty
        if(counter==1){
            type.setTextFill(Color.DARKCYAN);
            type.setText("Please Enter a Expression");
        }
        //error code 2 if there is an error in the expression
        else if(counter==2){
            type.setTextFill(Color.RED);
            type.setText("INVALID EXPRESSION");
            type.setText(type.getText()+"\nExpression Should be of the form (θ₁+θ₂+θ₃+θ₄+...+) up to six terms ");
        }
        //error code 3 if Power field is empty
        else if(counter==3){
            type.setTextFill(Color.DARKCYAN);
            type.setText("Please Enter some Power");
        }
        //error code 4 if there is an error in power
        else if(counter==4){
            type.setTextFill(Color.RED);
            type.setText("Invalid Power");
        }
        //error code 5 if power is negative
        else if(counter==5){
            type.setTextFill(Color.RED);
            type.setText("Power Should be a positive integer");
        
        }
        //error code 6 if power is greater than 20
        else if(counter==6){
            type.setTextFill(Color.RED);
            type.setText("Power Should be less than 21 because of your PC limitations");
        }
        //error code 0 if every test is passed
        else if(counter==0){
            //resetting both horizontal boxes
                hbox1.getChildren().clear();
                hbox2.getChildren().clear();
                
                //resetting upon length to 0
                uponLength=0;
                
                //Calling function for further calculation and printing result after testing all the errors in the input
           switchTrigFunctions();
        }
        
        //clearing all the lists after their use
        operatorIndex.clear();
        angleList.clear();
        operatorList.clear();
        angleMultiple.clear();
    }
    
    /**================================================ METHOD FOR RETURNING FACTORIAL ================================================**/
    public  long factorial(int num){
        long f=1;
        for(int i=1;i<=num;i++)
            f=f*i;
        
        if(num>=1) return f;
        else if(num==0)return 1;
        
        return 1;
    }
    
    /**================================================ METHOD FOR RETURNING nCr ========================================================**/
    public long nCr(int n,int r){
        return factorial(n)/(factorial(r)*factorial(n-r));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //adding list in combo box
         trig_combo.getItems().addAll("SIN","COS","TAN");
         
         //Selecting SIN as Initial Value
         trig_combo.setValue("SIN");
         
         //adding number list up to 20 (20!)
         power_combo.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20);
         
         //Disabling Power field at Starting
         power_combo.setDisable(true);
         power_combo.setOpacity(.5);
    
         //Initial message as Instruction
        type.setTextFill(Color.DARKCYAN);
        type.setText("Angle Multiple and Power are only applicable to the First Angle");
    }
}
