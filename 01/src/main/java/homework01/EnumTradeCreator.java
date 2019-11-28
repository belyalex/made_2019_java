package homework01;

public class EnumTradeCreator implements TradeCreator {
    @Override
    public Trade create(String type, Number price) throws UnknownTradeType {
        try {
            return InstrumentType.valueOf(type).createTrade(price);
        } catch (IllegalArgumentException e) {
            throw new UnknownTradeType("Unknown trade type: " + type);
        }
    }
}
