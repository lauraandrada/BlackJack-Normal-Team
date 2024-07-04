public class Card {
    private int value;
    private String type;
    
    // private int[] cards = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
    // private String[] type = {"trefla", "romb", "inima", ""};

    public Card(int value, String type){
        this.value = value;
        this.type = type;
    }

    public String toString(){
        return value + " " + type ;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}


