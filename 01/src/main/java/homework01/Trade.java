package homework01;

public abstract class Trade {
    private Number price;

    public Trade(Number price) {
        this.price = price;
    }

    public Number getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Trade {" + this.getClass().toString() + ", price: " + price.toString() + "}";
    }
}
