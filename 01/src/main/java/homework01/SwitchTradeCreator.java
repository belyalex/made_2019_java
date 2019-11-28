package homework01;

public class SwitchTradeCreator implements TradeCreator {

    @Override
    public Trade create(String type, Number price) throws UnknownTradeType {
        switch (type) {
            case "FX_SPOT":
                return new FxSpot(price);
            case "BOND":
                return new Bond(price);
            case "COMMODITY_SPOT":
                return new CommoditySpot(price);
            case "IR_SWAP":
                return new IrSwap(price);
            default:
                throw new UnknownTradeType("Unknown trade type: " + type);
        }
    }
}
