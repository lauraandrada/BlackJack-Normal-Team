package cards;

public class Card {
    private int value;
    private String type;
    


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

    public String getType() {
        return type;
    }
}


