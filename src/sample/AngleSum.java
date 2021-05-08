package sample;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.ArrayList;

/**================================================= Separate class for ANGLE SUM =====================================================**/
public class AngleSum extends Controller {
    
    //2d dynamic Arraylist for storing tan  [[tan][tan]...+[tan][tan]...+[...]]
    ArrayList<ArrayList<Double>> tanList = new ArrayList<>();
    //dynamic list for storing cos terms
    ArrayList<Double> cosList=new ArrayList<>();
    //dynamic list for storing signs for every term
    ArrayList<Integer> signList =new ArrayList<>();
    //terms iterator
    int itr=0;
    //for switching odd to even alternative cyclic summations (later explained)
    boolean tanSwitch=true;
    
    /**=========================== METHOD FOR CREATING AND INITIALISING 2D DYNAMIC TAN LIST ==================================**/
    public void setTanList() {
        //calling other initialising functions for other dynamic lists
        setCosList();
        setSignList();
        
        /*LOGIC BEHIND 2D dynamic tan list
        *   since acc to summation series, no. of cos = no. of angles
        *   and no. of tan terms = 2^n | n = no. of angles
        *
        *  sin(a+b+c)=cos(a).cos(b).cos(c).[tan(a)+tan(b)+tan(c)-tan(a).tan(b).tan(c)]
        *   whenever a cos collides with its corresponding angle's tan, a sin will form
        *
        *   sin(a+b+c)=cos(a).cos(b).cos(c).[tan(a).1.1+1.tan(b).1+1.1.tan(c)-tan(a).tan(b).tan(c)]
        *   so we have equated the no. of tans in each term equal to no. of cosines by making dynamic list of dynamic lists of tans
        *   cause no. of cosines and tans vary with no. of angles
        *   so no. of elements in cosine list = no.of elements in each tans term to simplify the multiplication process of cosines with tans
        * */
        
        //no. of tan terms will be eq to 2^(no of angles)
        for (int i = 0; i <=  Math.pow(2, angleList.size() - 1)-1; i++) {
            //added a new list in parent list
            tanList.add(new ArrayList<>());
            
            //no. of tans in each term = no. of angles or no. of cosines
            for (int j = 0; j < angleList.size(); j++) {
                tanList.get(i).add( 1.0);
            }
        }
    }
    
    /**=========================== METHOD FOR CREATING AND INITIALISING DYNAMIC COSINE LIST ==================================**/
    public void setCosList() {
        //no. of cosines = no of angles acc to summation series
        for(int i=0;i<angleList.size();i++)
           cosList.add(2.0);
    }
    
    /**=========================== METHOD FOR CREATING AND INITIALISING DYNAMIC COSINE LIST ==================================**/
    public void setSignList() {
        //since every term has its individual sign so no. of signs = no. of terms = 2^(no. of angles)
        for(int i=0;i<=Math.pow(2, angleList.size() - 1)-1;i++)
            signList.add(-1);
    }
    
    /**====================================== METHOD FOR SINE EXPANSION FOR MULTIPLE ANGLES  ====================================**/
    public void sinSum(ComboBox trig_combo, HBox hbox1,Line upon) {
        /* summation series
        * sin(θ₁+θ₂+θ₃+....+) = cos(θ₁).cos(θ₂).cos(θ₃)...[S₁-S₃+S₅-S₇+....]
        *
        * where Sn = ∑ tan(θ₁).tan(θ₂).tan(θ₃)...
        *                 cyclic
        * */
        
        //iterator for every term
        itr=0;
        
        //resetting upon length
        upon.setEndX(0);
        //created and initialised tan list
        setTanList();
     
        //creation of tan terms from cyclic summation
        for(int i=0;i<angleList.size();i++)
            //only those tan terms change which to be converted into sine , 1.0 -> 0.5
        cyclicSum(i,trig_combo);
    
        //checking for negative angle inputs , in that case sine will cause a sign change
        //loop for iterating over every term of tan's parent list
        for (int i = 0; i < Math.pow(2, angleList.size() - 1); i++)
            //loop for iterating over every tan in each term (2d list)
            for (int j = 0; j < angleList.size(); j++)
                //when corresponding  angle is negative and corresponding tan term is 0.5 (sine) sign is altered
                if(operatorList.get(j).equals("-") && tanList.get(i).get(j)==0.5)
                    signList.set(i, signList.get(i)*-1);
                
        //checking and printing final result after multiplying cos with tan (2.0x.05)
        for(int i=0;i<Math.pow(2,angleList.size()-1);i++){
            
            //as usual '+' should not be printed before first term
            if(i!=0 || operatorList.get(0).equals("-")){
                
                //Dynamic Label for storing each term's sign
                Label signLabel=new Label();
                if(signList.get(i)>0)
                signLabel.setText("+");
                else signLabel.setText("-");
                hbox1.getChildren().addAll(signLabel);
            }
            
            //multiplying every cos from cosine list with every tan in inner tan list to produce combination of cosine and sine in each term
            for(int j=0;j<angleList.size();j++){
                //when result is 1 , it is sine at that place of the term
                    if(cosList.get(j)*tanList.get(i).get(j)==1) {
                        Label sinLabel=new Label();
                        
                        //also when angle multiple is unity it should not be printed with corresponding angle
                        if(angleMultiple.get(j)==1) sinLabel.setText("sin(" +angleList.get(j)+")");
                        else sinLabel.setText("sin(" +angleMultiple.get(j)+angleList.get(j)+")");
                        
                        sinLabel.setFont(Font.font(null, FontWeight.NORMAL, 15));
                        hbox1.getChildren().addAll(sinLabel);
                    }
                    
                    //when result is 2 , it is cosine at that place of the term
                    else {
                        Label cosLabel=new Label();
                        
                        //also when angle multiple is unity it should not be printed with corresponding angle
                        if(angleMultiple.get(j)==1) cosLabel.setText("cos(" +angleList.get(j)+")");
                        else cosLabel.setText("cos(" +angleMultiple.get(j)+angleList.get(j)+")");
                        
                        cosLabel.setFont(Font.font(null, FontWeight.NORMAL, 15));
                        hbox1.getChildren().addAll(cosLabel);
                    }
            }
        }
        //clearing every dynamic list after their use
        tanList.clear();
        cosList.clear();
        signList.clear();
    }
    
    /**=================================== METHOD FOR COSINE EXPANSION FOR MULTIPLE ANGLES  ====================================**/
    public void cosSum(ComboBox trig_combo, HBox hbox2,Line upon) {
        //HERE EVERYTHING IS SAME EXCEPT THE COSINE SERIES
        
        /* summation series
         * cos(θ₁+θ₂+θ₃+....+) = cos(θ₁).cos(θ₂).cos(θ₃)...[1-S₂+S₅-S₄+....]
         * Since , S₀ will always be 1
         * where Sn = ∑ tan(θ₁).tan(θ₂).tan(θ₃)...
         *                 cyclic
         * */
        
        //iterator for every term
        itr=0;
    
        //resetting upon length
        upon.setEndX(0);
        //created and initialised tan list
        setTanList();
    
            //creation of tan terms from cyclic summation
            for(int i=0;i<angleList.size();i++)
                //only those tan terms change which to be converted into sine , 1.0 -> 0.5
                cyclicSum(i,trig_combo);
    
        //checking for negative angle inputs , in that case sine will cause a sign change
        //loop for iterating over every term of tan's parent list
        for (int i = 0; i < Math.pow(2, angleList.size() - 1); i++)
            //loop for iterating over every tan in each term (2d list)
            for (int j = 0; j < angleList.size(); j++)
                //when corresponding  angle is negative and corresponding tan term is 0.5 (sine) sign is altered
                if(operatorList.get(j).equals("-") && tanList.get(i).get(j)==0.5)
                    signList.set(i, signList.get(i)*-1);
    
        //checking and printing final result after multiplying cos with tan (2.0x.05)
        for(int i=0;i<Math.pow(2,angleList.size()-1);i++){
    
            //as usual '+' should not be printed before first term
            if(i!=0 || operatorList.get(0).equals("-")){
                Label signLabel=new Label();
                if(signList.get(i)==1)
                    signLabel.setText("+");
                else signLabel.setText("-");
                hbox2.getChildren().addAll(signLabel);
            }
    
            //multiplying every cos from cosine list with every tan in inner tan list to produce combination of cosine and sine in each term
            for(int j=0;j<angleList.size();j++){
                //when result is 1 , it is sine at that place of the term
                if(cosList.get(j)*tanList.get(i).get(j)==1) {
                    Label sinLabel=new Label();
    
                    //also when angle multiple is unity it should not be printed with corresponding angle
                    if(angleMultiple.get(j)==1) sinLabel.setText("sin(" +angleList.get(j)+")");
                    else sinLabel.setText("sin(" +angleMultiple.get(j)+angleList.get(j)+")");
                    
                    sinLabel.setFont(Font.font(null, FontWeight.NORMAL, 15));
                    hbox2.getChildren().addAll(sinLabel);
                }

                //when result is 2 , it is cosine at that place of the term
                else {
                    Label cosLabel=new Label();
    
                    //also when angle multiple is unity it should not be printed with corresponding angle
                    if(angleMultiple.get(j)==1) cosLabel.setText("cos(" +angleList.get(j)+")");
                    else cosLabel.setText("cos(" +angleMultiple.get(j)+angleList.get(j)+")");
                    
                    cosLabel.setFont(Font.font(null, FontWeight.NORMAL, 15));
                    hbox2.getChildren().addAll(cosLabel);
                }
            }
        }
        //clearing every dynamic list after their use
        tanList.clear();
        cosList.clear();
        signList.clear();
    }
    
    /**=================================== METHOD FOR TANGENT EXPANSION FOR MULTIPLE ANGLES  ====================================**/
    public void tanSum(ComboBox trig_combo, HBox hbox1, HBox hbox2, Line upon) {
        //HERE EVERYTHING IS SAME EXCEPT THE TANGENT SERIES
    
        /* summation series
         * tan(θ₁+θ₂+θ₃+....+) = [S₁-S₃+S₅-S₇+....]/ [1-S₂+S₅-S₄+....]
         *
         * Since , S₀ will always be 1
         *
         * where Sn = ∑ tan(θ₁).tan(θ₂).tan(θ₃)...
         *                 cyclic
         * */
    
        //iterator for every term
        itr=0;
        
        //for switching odd cyclic summation to even cyclic summation
        tanSwitch=true;
    
        //resetting upon length
        uponLength=0;
        //created and initialised tan list
        setTanList();
    
        //creation of tan terms from cyclic summation (odd ones)
        for(int i=0;i<angleList.size();i++)
            //only those tan terms change which to be converted into sine , 1.0 -> 0.5
            cyclicSum(i,trig_combo);
    
        //checking for negative angle inputs , in that case sine will cause a sign change
        //loop for iterating over every term of tan's parent list
        for (int i = 0; i < Math.pow(2, angleList.size() - 1); i++)
            //loop for iterating over every tan in each term (2d list)
            for (int j = 0; j < angleList.size(); j++)
                //when corresponding  angle is negative and corresponding tan term is 0.5 (sine) sign is altered
                if(operatorList.get(j).equals("-") && tanList.get(i).get(j)==0.5)
                    signList.set(i, signList.get(i)*-1);
                
                
        //checking and printing final result after multiplying cos with tan (2.0x.05) in Hbox1
        for(int i=0;i<Math.pow(2,angleList.size()-1);i++){
    
            //as usual '+' should not be printed before first term
            if(i!=0 || operatorList.get(0).equals("-")){
    
                //Dynamic Label for storing each term's sign
                Label signLabel=new Label();
                if(signList.get(i)==1)
                    signLabel.setText("+");
                else signLabel.setText("-");
                hbox1.getChildren().addAll(signLabel);
                
                //incrementing upon length corresponding to above Label
                uponLength+=signLabel.getText().length()*8;
            }
    
            //checking every tan from inner tan list ,if its default value is changed then it is printed
            for(int j=0;j<angleList.size();j++){
                //when result is 1 , it is ignored in case of tan expansion cause only tan will be printed
                    if(tanList.get(i).get(j)==0.5) {
                    Label tanLabel=new Label();
    
                    //also when angle multiple is unity it should not be printed with corresponding angle
                    if(angleMultiple.get(j)==1) tanLabel.setText("tan(" +angleList.get(j)+")");
                    else tanLabel.setText("tan(" +angleMultiple.get(j)+angleList.get(j)+")");
                    
                    tanLabel.setFont(Font.font(null, FontWeight.NORMAL, 15));
                    hbox1.getChildren().addAll(tanLabel);
    
                    //incrementing upon length corresponding to above Label
                    uponLength+=tanLabel.getText().length()*6.4;
                }
            }
        }
        
        //NOW EVERYTHING HAVE BEEN RESET FOR GENERATING EVEN CYCLIC SUMMATION SERIES BELOW THE UPON
        //resetting iterator for new series
        itr=0;
        
        //changing cyclic summation sequence
        tanSwitch=false;
        
        //clearing all the lists for new series
        tanList.clear();
        signList.clear();
        cosList.clear();
        
        //again created and initialised tan list
        setTanList();
    
        //creation of tan terms from cyclic summation but this time even ones
        for(int i=0;i<angleList.size();i++)
            //only those tan terms change which to be converted into sine , 1.0 -> 0.5
            cyclicSum(i,trig_combo);
    
        //checking for negative angle inputs , in that case sine will cause a sign change
        //loop for iterating over every term of tan's parent list
        for (int i = 0; i < Math.pow(2, angleList.size() - 1); i++)
            //loop for iterating over every tan in each term (2d list)
            for (int j = 0; j < angleList.size(); j++)
                //when corresponding  angle is negative and corresponding tan term is 0.5 (sine) sign is altered
                if(operatorList.get(j).equals("-") && tanList.get(i).get(j)==0.5)
                    signList.set(i, signList.get(i)*-1);
                
                
        //checking and printing final result after multiplying cos with tan (2.0x.05) in Hbox2
        for(int i=0;i<Math.pow(2,angleList.size()-1);i++){
    
            //as usual '+' should not be printed before first term and negative will not come in every case
            if(i!=0){
    
                //Dynamic Label for storing each term's sign
                Label signLabel=new Label();
                if(signList.get(i)==1)
                    signLabel.setText("+");
                else signLabel.setText("-");
                hbox2.getChildren().addAll(signLabel);
            }
    
            //checking every tan from inner tan list ,if its default value is changed then it is printed
            for(int j=0;j<angleList.size();j++){
                //when result is 1 , it is ignored in case of tan expansion cause only tan will be printed
                if(tanList.get(i).get(j)==0.5) {
                    Label tanLabel=new Label();
    
                    //also when angle multiple is unity it should not be printed with corresponding angle
                    if(angleMultiple.get(j)==1) tanLabel.setText("tan(" +angleList.get(j)+")");
                    else tanLabel.setText("tan(" +angleMultiple.get(j)+angleList.get(j)+")");
                    
                    tanLabel.setFont(Font.font(null, FontWeight.NORMAL, 15));
                    hbox2.getChildren().addAll(tanLabel);
                
                }
                
                /*In every tan expansion 1 is always present at first below the upon
                * since from cyclic summation of S₀ , nothing will be changed from first inner tan list
                *
                * so for 1 , one inner tan list should not contain a single 0.5
                * This process is only applicable to first iteration of the loop only
                * and 1 should not be printed  for single angle
                * */
                else if (j==0 && angleList.size()!=1){
                    Label tanLabel=new Label("1");
                    tanLabel.setFont(Font.font(null, FontWeight.NORMAL, 15));
                    hbox2.getChildren().addAll(tanLabel);
                }
                //and nothing should be printed when input is a single angle
                else if(angleList.size()==1)uponLength=0;
            }
        }
        
        //upon length corresponding to Hbox1 elements
        upon.setEndX(uponLength);
    
        //clearing every dynamic list after their use
        tanList.clear();
        cosList.clear();
        uponLength=0;
    }
    
    /**==================================== METHOD FOR GENERATING CYCLIC SUMMATION TAN SERIES ==================================**/
    public void cyclicSum(int iterator,ComboBox trig_combo){
        
        //total number of elements to choose from
        int[] arr = {0,1, 2, 3, 4,5};
        //r elements at a time
        int r=0;
        
        //for sin series only odd cyclic summation is req
        if(trig_combo.getValue().equals("SIN"))
            r = 2*iterator+1;
        //for cosine series only even cyclic summation is req
        else if(trig_combo.getValue().equals("COS"))
            r=2*iterator;
        ////for tan series both cyclic summation is req
        else if(trig_combo.getValue().equals("TAN")){
            //odd ones above the upon
            if(tanSwitch) r=2*iterator+1;
            //even ones below the upon
            else r=2*iterator;
        }
        
        //total number of angles to choose from  based on user input
        int n = angleList.size();
        //and here the magic starts
        printCombination(arr, n, r,iterator);
    
    }
    
    /**===============BELOW WRITTEN CODE IS COPIED FROM INTERNET EVEN I CANT UNDERSTAND, BUT IT WORKS==================**/
    
    /**=====================================Code for generating Combination without repetition==============================================**/
    /* arr[]  ---> Input Array
    data[] ---> Temporary array to store current combination
    start & end ---> Staring and Ending indexes in arr[]
    index  ---> Current index in data[]
    r ---> Size of a combination to be printed */
    public void combinationUtil(int[] arr, int[] data, int start,
                                int end, int index, int r,int iterator)
    {
        // Current combination is ready to be printed, print it
        if (index == r)
        {
           
            //for alternate '+' or '-' for  every term based on which terms are generated by which cyclic summation
            //here cyclic summation differ by iterator and no. of terms by itr
            if(Math.pow(-1,iterator)>= 0) signList.set(itr, signList.get(itr)*-1);
            
            for (int j=0; j<r; j++){
                //changing the default values in tan inner lists to be converted into sines
                tanList.get(itr).set(data[j],0.5);
                //System.out.print(data[j]+" ");
            }
            itr++;
            //System.out.println();
            return;
        }
        
        // replace index with all possible elements. The condition
        // "end-i+1 >= r-index" makes sure that including one element
        // at index will make a combination with remaining elements
        // at remaining positions
        for (int i=start; i<=end && end-i+1 >= r-index; i++)
        {
            data[index] = arr[i];
            combinationUtil(arr, data, i+1, end, index+1, r,iterator);
        }
    }
    
    // The main function that prints all combinations of size r
    // in arr[] of size n. This function mainly uses combinationUtil()
    public void printCombination(int[] arr, int n, int r, int iterator)
    {
        // A temporary array to store all combination one by one
        int[] data=new int[r];
        
        // Print all combination using temporary array 'data[]'
        combinationUtil(arr, data, 0, n-1, 0, r,iterator);
    }
}
