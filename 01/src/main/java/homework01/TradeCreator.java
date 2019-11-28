package homework01;

public interface TradeCreator {
    public Trade create(String type, Number price) throws UnknownTradeType;
}
