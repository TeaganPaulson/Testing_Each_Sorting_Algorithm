
import java.util.Scanner;
/**
 * 
 * @author Teagan Paulson
 * @version 2023420
 * This class allows for multiple line table headers and ease of access to nicely 
 * arranged Ascii table values
 */
public class TableClassResizableHeaders {
    private final ArrayList<String> tableHeader;
    private final ArrayList<ArrayList<String>> table;
    private final ArrayList<ArrayList<ArrayList<String>>> headers;
    
    private int leftSpacing=2;
    private int rightSpacing=2;
    private int numberOfHeaders=0;
    private int alignment = 0;
    
    private boolean moneySigns=false;
    private boolean commas=false;
    private boolean side=false;
    
    
    private char customSpace =' ';
    private char customWall ='|';
    private char customCorner = '+';
    private char customDash = '-';
    
    
    /**
     * Default Constructor
     */
    public TableClassResizableHeaders(){
        table = new ArrayList<>();
        tableHeader = new ArrayList<>();
        headers = new ArrayList<>();
    }
    /**
     * 
     * @param column the column being search for longest index
     * @return returns modified differience so that the string grows with the table header
     */
    private int longestStringModified(int column){
        int sizeAdd=0;
        int hold=longestStringInColumn(column);
        if(this.tableHeader.size()!=0){
            if(isTableHeaderBigger()){
                sizeAdd=differenceBetweenTopandBottom()/table.size();
            if(differenceBetweenTopandBottom()%table.size()-column>0)
                sizeAdd++;
                }
        }
        return hold+sizeAdd;
    }
    /**
     * 
     * @param column column being searched
     * @return returns the number of leftSpace the longest string takes up
     */
    private int longestStringInColumn(int column){
        int end=table.get(column).size();
        int temp =0;
        if(table.get(column)==null)
            return 0;
        for(int i=0;i<end;i++){
            if(table.get(column).get(i)!=null)
                if(temp<this.turnStringIntoLongest(table.get(column).get(i)).length())
                    temp = turnStringIntoLongest(table.get(column).get(i)).length();
            }
        int max = longestStringInColumnHeader(column);
        if(max>temp)
            return max;
        return temp;
    }
    private int longestStringInColumnHeader(int column){
        int currentMax=0;
        for(int i=0;i<headers.size();i++)
            if(column<headers.get(i).size())
                try{
                for(int j = 0;j<headers.get(i).get(column).size();j++)
                    {
                        String temp = this.turnStringIntoLongest(headers.get(i).get(column).get(j));
                        if(currentMax<temp.length())
                            currentMax=temp.length();
                    }
                }catch(Exception exc){}
        return currentMax;
    }
    
    /**
     * 
     * @param column column being added to
     * @param str string being added
     */
    public void add(int column, String str){
        int rowBeingAddedTo=column;
        if(side)
            rowBeingAddedTo++;
        table.get(rowBeingAddedTo).add(str);
    }
    /**
     * 
     * @param columnIndex index for column
     * @param indexRow index for row
     * @param replace string replacing existing information
     * @return replaced element
     * @throws IndexOutOfBoundsException if out of bounds on table
     */
    public String set(int columnIndex,int indexRow,String replace)throws IndexOutOfBoundsException{
        int indexOfRow=indexRow+numberOfHeaders;
        int indexOfColumn=columnIndex;
        if(side)
            indexOfColumn++;
        String temp = table.get(indexOfColumn).set(indexOfRow, replace);
        return temp;
    }
    /**
     * 
     * @param indexOfColumn index for column length being searched
     * @return returns int of size doesn't include header for size
     */
    public int sizeOfColumn(int indexOfColumn){
        if(side)
            indexOfColumn++;
        
        return this.table.get(indexOfColumn).size();
    }
    /**
     * 
     * @return index of Column that was added
     */
    public int addColumn(){
        ArrayList<String> temp = new ArrayList<>();
        for(int i=0;i<headers.size();i++){
            headers.get(i).add(null);
        }
        table.add(temp);
        return table.size()-1;
    }
    /**
     * 
     * @param columnIndex columnIndex searched
     * @param rowIndex rowIndex being searched
     * @return String element at columnIndex and rowIndex index
     */
    public String get(int rowIndex,int columnIndex){
        int rowAdd=0;
        if(side)rowAdd++;
        try{
            return table.get(columnIndex+rowAdd).get(rowIndex);
        }catch(IndexOutOfBoundsException ioobe){return null;}
        
    }
    /**
     * 
     * @param columnIndex columnIndex searched
     * @param rowIndex rowIndex being searched
     * @return String element at columnIndex and rowIndex index
     */
    private String unrestrictedGet(int columnIndex, int rowIndex){
        try{
            return table.get(columnIndex).get(rowIndex);
        }catch(IndexOutOfBoundsException ioobe){return null;}
        
    }
    /**
     * 
     * @return number of elements in each row
     */
    private int maxSizeOfAllRows(){
        int max=0;
        int start=0;
        if(side)
           start=1; 
        for(int i=start;i<table.size();i++){
            if(max<table.get(i).size())
                max=table.get(i).size();
        }
        return max;
    }
    /**
     * 
     * @param position a number between 0-2 0 being left align, 1 being centered, 2 being right centered
     * sets alignment of the table
     */
    public void setAlignment(int position){
        switch(position){
            case 0:
            case 1:
            case 2:
                alignment = position;
                break;
            default:
                throw new IllegalArgumentException("Not within range of 0-2 for alignment");
        }
    }
    /**
     * 
     * @param size max size of the row what decides how many leftSpace there will be
     * @return 
     */
    private String alignLeft(int size,int align){
        String temp="";
        if(align==0)
            for(int z = 0; z<size;z++)
                    temp+=this.customSpace;
        if(align==1)
            for(int z = 0; z<size/2+size%2;z++)
                    temp+=this.customSpace;
        return temp;
    }
    /**
     * 
     * @param size max size of the row what decides how many leftSpace there will be
     * @return 
     */
    private String alignRight(int size,int align){
        String temp="";
        if(align==2)
            for(int z = 0; z<size;z++)
                    temp+=this.customSpace;
        if(align==1)
            for(int z = 0; z<size/2;z++)
                    temp+=this.customSpace;
        return temp;
    }
    /**
     * 
     * @param column converts all columns into 
     * @return ascii row that is the correct spacing
     */
    private String asciiRow(int column){
        String leftSpace="";
        String rightSpace="";
        for(int i=0;i<leftSpacing;i++)
            leftSpace+=customSpace;
        for(int i=0;i<rightSpacing;i++)
            rightSpace+=customSpace;
        String row =""+customWall;
        for(int i=0;i<table.size();i++)
        {
            int align=alignment;
            if(side&&i==0)
                align=2;
            int size = longestStringModified(i);
            String hold = turnStringIntoLongest(unrestrictedGet(i,column));
            
            if(hold==null){
                row+=leftSpace;
                for(int z=0;z<size;z++)
                    row+=customSpace;
                row +=rightSpace+this.customWall; 
            }else{
                String innerSpaceRight=this.alignRight(size-hold.length(),align);
                String innerSpaceLeft=this.alignLeft(size-hold.length(),align);
                row += leftSpace+innerSpaceLeft+hold+innerSpaceRight+rightSpace+customWall;
            }
        }
        return row;
    }
    
    
    /**
     * 
     * @return a ascii row repressented by ascii values
     * calculates the proper size of the columns in a signle row
     */
    private String row(){
        String left="",right="";
        String temp ="" + this.customCorner;    
        for(int i=0;i<table.size();i++)
        {
            int size = longestStringModified(i);
            String hold = "";
            if(i==0)
                hold+=left;
            for(int z=0;z<size+leftSpacing+rightSpacing;z++){
                hold+=this.customDash;
            }
            if(table.size()-1==i)
                hold+=right;
            temp+=hold+this.customCorner;
        }
        return temp;
    }
    /**
     * 
     * @return String for the table head that resizes as columns are added
     */
    public String tableHeadRow(){
        
        int maxSizeOfBottom= leftSpacing*table.size()+rightSpacing*table.size()+longestStringsAddedTogether()+table.size()-1;
        int maxSizeOfTop= leftSpacing+rightSpacing+this.longestStringInTableHeader();
        String temp ="" + this.customCorner;
        if(maxSizeOfBottom>=maxSizeOfTop){
            String hold = "";
            for(int z=0;z<maxSizeOfBottom;z++){
                hold+=this.customDash;
            }
            temp+=hold+this.customCorner;
        }else{
            String hold = "";
            for(int z=0;z<maxSizeOfTop;z++){
                hold+=this.customDash;
            }
            temp+=hold+this.customCorner;
        }
        
        return temp;
    }
    /**
     * 
     * @return returns if table has header true if does
     */
    public boolean hasHeaders(){return headers.size()!=0;}
    /**
     * 
     * @return number of headers the table has
     */
    public int numberOfHeaders(){return headers.size();}
    /**
     * Adds headers
     */
    public void addHeaders(){
        headers.add(new ArrayList<ArrayList<String>>());
        if(table.size()==0)
            throw new IllegalStateException("Table Has no Columns to add headers to.");
        for(int i=0;i<table.size();i++)
            headers.get(0).add(new ArrayList<String>());
        
    }
    /**
     * removes headers on each row
     * @param indexOfHeader Index of header row that will be removed
     */
    public void removeHeaders(int indexOfHeader){
        if(hasHeaders()){
            if(headers.size()<indexOfHeader)
                throw new IndexOutOfBoundsException("Index Out of bounds");
            for(int i=0;i<table.size();i++)
                headers.remove(indexOfHeader);
        }else{
            throw new IllegalStateException("No headers to remove");
        }
        
    }
    /**
     * @param headerIndex index of the header you would like to set
     * @param column index of the column you would like to set the header to
     * @param head string that will be the header
     * @throws IndexOutOfBoundsException if out of bounds of the table
     * sets the String of the header at index and column given besides side which is another method
     */
    public void setHeaders(int headerIndex,int column,String head)throws IndexOutOfBoundsException{
        ArrayList<String> temp = turnStringIntoList(head);
        if(!hasHeaders())
            addHeaders();
        if(headerIndex>headers.size()-1)
            throw new IndexOutOfBoundsException("Index Out of bounds");
        headers.get(headerIndex).set(column, temp);
    }
    
    /**
     * 
     * @param str String being turned into an array list
     * @return array list where \n is the delimeter
     */
    private ArrayList<String> turnStringIntoList(String str){
        ArrayList<String> temp = new ArrayList<>();
        if(str.contains("\n"))
        {
            Scanner scan = new Scanner(str);
            scan.useDelimiter("\n");
            while(scan.hasNext())
                temp.add(scan.next());
        }
        else
            temp.add(str);
        return temp;
    }
    
    /**
     * Removes sides from the table
     */
    public void removeSides(){
        if(side){
            table.remove(0);
        }
        side=false;
    }
    /**
     * Adds sides into the table
     */
    public void addSides(){
        if(!side){
            ArrayList<String> temp = new ArrayList<>();
            table.add(0,temp);
            
            for(int i=0;i<maxSizeOfAllRows();i++)
                table.get(0).add(null);
        }
        side=true;
    }
    /**
     * 
     * @return true if has sides otherwise false
     */
    public boolean hasSides(){return side;}
    
    
    /**
     * 
     * @param row index of the row that will have a side header added
     * @param sideBar String that will be set for that row
     * @throws IndexOutOfBoundsException if index is negative
     * This method adds sets side headers from row 0-maxSize you may sets side headers before table has expanded to them but they will not decide the size of the Table
     */
    public void setSides(int row,String sideBar)throws IndexOutOfBoundsException{
        if(!side)
            addSides();
        if(row<0)throw new IndexOutOfBoundsException("Index-Out of bounds");
        //if(row>sizeTable)throw new IndexOutOfBoundsException("Index-Out of bounds");
        int size = table.get(0).size();
        for(int i=size;i<row+1;i++)
            table.get(0).add(null);
        if(hasHeaders()){
            table.get(0).add(null);
            table.get(0).set(row+1, sideBar);
        }
        else
            table.get(0).set(row, sideBar);
    }
    
    public void removeColumn(int column){
        if(side)
            table.remove(column+1);
        else
            table.remove(column);
    }
    /**
     * 
     * @param tableHead Adds table head
     */
    public void addTableHeadder(String tableHead){
        this.tableHeader.add(tableHead);
    }
    /**
     * 
     * @param index index that will have it's value replaced
     * @param head string that will replace head
     * @return returns replaced element at index
     * @throws IndexOutOfBoundsException 
     */
    public String setTableHeadder(int index, String head)throws IndexOutOfBoundsException{
        return tableHeader.set(index, head);
    }
    /**
     * 
     * @param index index that will be removed from
     * @return item removed from table head
     * @throws IndexOutOfBoundsException if not within bounds of header
     */
    public String removeTableHeadder(int index)throws IndexOutOfBoundsException{
        return this.tableHeader.remove(index);
    }
    /**
     * 
     * @return size of the table header
     */
    public int tableHeaderSize(){
        return this.tableHeader.size();
    }
    /**
     * 
     * @return returns a string representation of an ascii table
     */
    public String asciiTable(){
        String row = "";
        row+=row();
        String temp=row;
        int maxColumn = maxSizeOfAllRows();
        for(int i=0;i<maxColumn;i++)
            temp+="\n"+(asciiRow(i))+"\n"+row;
        String head="";
        for(int i=0;i<tableHeader.size();i++)
            head += tableHeadRow()+"\n"+printTableHeader(i)+"\n";
        String header="";
        for(int i=0; i<headers.size();i++)
            header+=asciiRowHeader(i);
        if(!headers.isEmpty())
            header=row()+"\n"+header;
        return head+header+temp;
    }
    /**
     * 
     * @param in string being parsed into number
     * @return returns true if string it is a double but not a int
     */
    private boolean isDouble(String in){
        try{
            Double.parseDouble(in);
            if(!isLong(in))
                return true;
        }catch( NumberFormatException | NullPointerException nfe){return false;}
        return false;
    }
    /**
     * 
     * @param in string being parsed into int
     * @return if string is parsable integer returns true
     */
    private boolean isLong(String in){
        try{
            Long.parseLong(in);
            return true;
        }catch( NumberFormatException | NullPointerException nfe){return false;}
    }
    /**
     * 
     * @return size of longest string in table header
     */
    private int longestStringInTableHeader(){
        int size = 0;
        for(int i=0;i<tableHeader.size();i++)
        {
            if(tableHeader.get(i)!=null)
                if(size<tableHeader.get(i).length())
                    size=turnStringIntoLongest(tableHeader.get(i)).length();
        }
        return size;
    }
    
    
    /**
     * 
     * @return size of all strings added together
     */
    private int longestStringsAddedTogether(){
        int length = 0;
        for(int i=0;i<table.size();i++)
        {
            length += this.longestStringInColumn(i);
        }
        return length;
    }
    /**
     * 
     * @return if table header is bigger true otherwise false
     */
    private boolean isTableHeaderBigger(){
        int maxSizeOfBottom= leftSpacing*table.size()+rightSpacing*table.size()+longestStringsAddedTogether()+table.size()-1;
        int maxSizeOfTop= leftSpacing+rightSpacing+this.longestStringInTableHeader();
        return maxSizeOfTop>maxSizeOfBottom;
    }
    /**
     * 
     * @return calculates the difference between top and bottom strings
     */
    public int differenceBetweenTopandBottom(){
        int maxSizeOfBottom= leftSpacing*table.size()+rightSpacing*table.size()+longestStringsAddedTogether()+table.size()-1;
        int maxSizeOfTop= leftSpacing+rightSpacing+this.longestStringInTableHeader();
        int difference=maxSizeOfTop-maxSizeOfBottom;
        if(difference<0)
            difference*=(-1);
        return difference;
    }
    /**
     * 
     * @param index index of the header
     * @return prints all the conents of the header at cell index with proper formating
     */
    private String printTableHeader(int index){
            String holdLeft ="";
            String holdRight ="";
            holdLeft+=this.customWall;
            int size=0;
            
            if(tableHeader.size()>1)
                size = longestStringInTableHeader()-tableHeader.get(index).length();
                
            for(int i =0; i<leftSpacing+size/2+size%2;i++)
                holdLeft+=this.customSpace;
            for(int i =0; i<rightSpacing+size/2;i++)
                holdRight+=this.customSpace;
            //if odd add left otherwise add space right
            if(!isTableHeaderBigger()){
                int calculatedDifference = differenceBetweenTopandBottom();
                for(int i =0; i< calculatedDifference;i++){
                    if(i%2==0)
                        holdLeft+=this.customSpace;
                    else
                        holdRight+=this.customSpace;
                }
            }
        return holdLeft+turnStringIntoLongest(tableHeader.get(index))+holdRight+customWall;
    }
    
    private String turnStringIntoLongest(String number){
        String hold = number;
        if(this.isLong(hold)){
            if(commas)hold=IntToTrippleComma(hold);
            if(moneySigns)hold="$"+hold;
        }
        else if(this.isDouble(hold)){
            if(commas)hold=DoubleToTrippleComma(hold);
            if(moneySigns)hold="$"+hold;
        }
        return hold;
    }
    
    private String DoubleToTrippleComma(String doubleString){
        Scanner scan = new Scanner(doubleString);
        scan.useDelimiter("\\.");
        String left;
        if(scan.hasNext())
            left = scan.next();
        else return doubleString;
        String right;
        if(scan.hasNext())
            right = scan.nextLine();
        else return doubleString;
        return IntToTrippleComma(left)+right;
    }
    
    /**
     * 
     * @param intString String that will have number added to it 
     */
    private String IntToTrippleComma(String intString){
        
        ArrayList<String> temp = new ArrayList<>();
        for(int i=intString.length()-1;i>=0;i--){
            temp.add(""+intString.charAt(i));
        }
        int addedSize=0;
        for(int i =0;i<temp.size();i++){
            if(isDivisibleByThree(i-addedSize)){
                temp.add(i, ",");
                i+=3;
                addedSize++;
            }
        }
        
        String conc="";
        int size = temp.size();
        for(int i=size-1;i>=0;i--){
            conc +=temp.get(i);
        }
        return conc;
    }
    /**
     * 
     * @param number int being check if divisible by zero it will have a remainder of zero if wholey divisible
     * @return return true if dividable without remainder otherwise false
     */
    public static boolean isDivisibleByThree(int number){
        return number/3>0&&number%3==0;
    }
    /**
     * 
     * @param setting enables or disable money symbol
     */
    public void setMoneySymbol(boolean setting){
        moneySigns=setting;
    }
    /**
     * 
     * @param setting enables or disables commas every three leftSpace for numbers
     */
    public void setCommas(boolean setting){
        commas=setting;
    }
    /**
     * 
     * @param array two Dimensional array of object being added with to String
     * turns two Dimensional array into table object
     */
    public void addColumn(Object[]array){
        int start=0;
         if(side)start++;
         ArrayList<String>temp=new ArrayList<>();
        for (Object array1 : array)
            temp.add(array1.toString());
        for(int i=0;i<headers.size();i++)
            headers.get(i).add(null);
        table.add(temp);
    }
    /**
     * 
     * @param array two Dimensional array of object being added with to String
     * turns two Dimensional array into table object
     * this will keep columns and rows the same
     */
    public void addTwoDimensionalArrayPreserved(Object[][]array){
        int maxSize=0;
        for (Object[] array1 : array)
            if (maxSize < array1.length)
                maxSize = array1.length;
            
        Object[][] reversed = new Object[maxSize][array.length];
        for(int i=0;i<array.length;i++){
            for(int z=0;z<array[i].length;z++)
                reversed[z][i]=array[i][z];
        }
        
        for (Object[] reversed1 : reversed) 
            if (reversed1 != null)addColumn(reversed1);
        
    }
    /**
     * 
     * @param array two Dimensional array of object being added with to String
     * turns two Dimensional array into table object
     * this will reverse columns and rows the same
     */
    public void addTwoDimensionalArrayReversed(Object[][]array){
        for (Object[] array1 : array) {
            if (array1 != null)addColumn(array1);
        }
    }
    /**
     * 
     * @param rightPadding right padding of the table
     * @throws IllegalArgumentException if right padding is negative
     * sets the padding of the table on the right side
     */
    public void setPaddingRight(int rightPadding)throws IllegalArgumentException{
        if(rightPadding<0)
            throw new IllegalArgumentException("Can't be have negative space.");
        rightSpacing=rightPadding;
    }
    /**
     * 
     * @param leftPadding left padding of the table
     * @throws IllegalArgumentException if left padding is negative
     * sets the padding of the table on the left side
     */
    public void setPaddingLeft(int leftPadding)throws IllegalArgumentException{
    if(leftPadding<0)
        throw new IllegalArgumentException("Can't be have negative space.");
    leftSpacing=leftPadding;
    }
    /**
     * 
     * @param spacing padding of the table
     * @throws IllegalArgumentException throws this if spacing is negative
     * modifies spacing of the table
     */
    public void setSpacing(int spacing)throws IllegalArgumentException{
        if(spacing<0)
            throw new IllegalArgumentException("Can't be have negative space.");
        leftSpacing = spacing;
        rightSpacing = spacing;
    }
    /**
     * 
     * @param space char value that will be the space for the ascii table
     * sets the ascii tables space
     */
    public void setSpaceChar(char space){
        this.customSpace=space;
    }
    /**
     * 
     * @param wall char value that will be the wall for the ascii table
     * sets the ascii tables wall
     */
    public void setWallChar(char wall){
        this.customWall=wall;
    }
    
    /**
     * 
     * @param corner char value that will be the corner for the ascii table
     * sets the ascii tables corner
     */
    public void setCornerChar(char corner){
        this.customCorner=corner;
    }
    /**
     * 
     * @param dash char value that will be the dashes for the ascii table
     * sets the ascii tables dashes
     */
    public void setDashChar(char dash){
        this.customDash=dash;
    }
    /**
     * 
     * @return reversed array of the Table where columns are rows and rows are columns
     */
    public String[][] toArrayReversed(){
       
        
         int start=0;
         if(side)start++;
         String[][] temp = new String[table.size()-start][];
        for(int i=start;i<table.size();i++){
            temp[i-start]=new String[table.get(i).size()-numberOfHeaders];
        }
        for(int i=start;i<table.size();i++)
            for(int z=numberOfHeaders;z<table.get(i).size();z++)
                temp[i-start][z-numberOfHeaders]=table.get(i).get(z);
        return temp;
    }
    /**
     * 
     * @return Creates an array using the table this will not print the headers
     */
    public String[][] toArray(){
        int maxSize=0;
        for(int i=0;i<table.size();i++){
            int tempSize=table.get(i).size();
            if(tempSize>maxSize)maxSize=tempSize;
        }
        
        int start=0;
         if(side)start++;
        
        String[][] temp = new String[maxSize-numberOfHeaders][table.size()-start];
        
         
         
        for(int i=start;i<table.size();i++)
            for(int z=numberOfHeaders;z<table.get(i).size();z++)
                temp[z-numberOfHeaders][i-start]=table.get(i).get(z);
        return temp;
    }
    
    /**
     * 
     * @param column converts all columns into 
     * @return ascii row that is the correct spacing
     */
    public String asciiRowHeader(int rowIndex){
        String leftSpace="";
        String rightSpace="";
        int total = leftSpacing+rightSpacing;
        for(int i=0;i<(total)/2;i++)
            leftSpace+=customSpace;
        for(int i=0;i<total/2+total%2;i++)
            rightSpace+=customSpace;
        String row="";
        for(int q=0;q<maxNumberOfLinesInHeader(rowIndex);q++)
        {
            row +=customWall;
            for(int i=0;i<headers.get(rowIndex).size();i++)
            {
            
                int size = longestStringModified(i);
                String hold=null;
                try{hold = turnStringIntoLongest(headers.get(rowIndex).get(i).get(q));}catch(Exception e){}
                if(hold==null){
                    row+=leftSpace;
                    for(int z=0;z<size;z++)
                        row+=customSpace;
                    row +=rightSpace+this.customWall; 
                }else{
                    String innerSpaceRight=this.alignRight(size-hold.length(),1);
                    String innerSpaceLeft=this.alignLeft(size-hold.length(),1);
                    row += leftSpace+innerSpaceLeft+hold+innerSpaceRight+rightSpace+customWall; 
                }
            } 
            row+="\n";
        }
        return row;
    }
    
    private int maxNumberOfLinesInHeader(int row){
        int max=0;
        for(int i=0;i<headers.get(row).size();i++)
            try{
                for(int j=0;j<headers.get(row).get(i).size();j++)
                    if(headers.get(row).get(i).size()>max)
                        max=headers.get(row).get(i).size();
            }catch(Exception ex){}
        return max;
    }
}
