import homework01.*;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class TradeTests {
    private String sBond =
            "Trade: {\n" +
                    "   type: BOND,\n" +
                    "   price: 10.5\n" +
                    "}";

    private String sCommoditySpot =
            "Trade: {\n" +
                    "   type: COMMODITY_SPOT,\n" +
                    "   price: 0.65\n" +
                    "}";

    private String sIrSwap =
            "Trade: {\n" +
                    "   type: IR_SWAP,\n" +
                    "   price: 100\n" +
                    "}";

    private String sFxSpot =
            "Trade: {\n" +
                    "   type: FX_SPOT,\n" +
                    "   price: 20\n" +
                    "}";

    private String[] sBadStrings = {
            "Trade: {\n",
            "       ",
            "",
            "sajdkasjdsakj",
            "\n",
            "Trade: {\n" +
                    "   type: IR_SWAP,\n" +
                    "   price: 100\n",
            "Trade: {\n" +
                    "   type: COMMODITY_SPOT\n" +
                    "}"
    };

    private TradeParser tpSwitch=new TradeParser(new SwitchTradeCreator());
    private TradeParser tpEnum=new TradeParser(new EnumTradeCreator());


    @Test
    public void TestBadStrings() {
        for (String s : sBadStrings) {
            TestBadString(s);
        }
    }

    private void TestBadString(String s) {
        TestBadString(s, tpSwitch);
        TestBadString(s, tpEnum);
    }

    private void TestBadString(String s, TradeParser tp) {
        Trade t = tp.readTrade(convertStringToInputStream(s));
        assertNull(t);
    }

    @Test
    public void TestBond() {
        TestBond(tpSwitch);
        TestBond(tpEnum);
    }

    private void TestBond(TradeParser tp) {
        Trade t = tp.readTrade(convertStringToInputStream(sBond));
        assertTrue(t instanceof Bond);
        assertEquals(10.5, t.getPrice());
        assertEquals("Trade {class homework01.Bond, price: 10.5}", t.toString());
    }

    @Test
    public void TestCommoditySpot() {
        TestCommoditySpot(tpSwitch);
        TestCommoditySpot(tpEnum);
    }

    private void TestCommoditySpot(TradeParser tp) {
        Trade t = tp.readTrade(convertStringToInputStream(sCommoditySpot));
        assertTrue(t instanceof CommoditySpot);
        assertEquals(0.65, t.getPrice());
        assertEquals("Trade {class homework01.CommoditySpot, price: 0.65}", t.toString());
    }

    private InputStream convertStringToInputStream(String s) {
        return new ByteArrayInputStream(s.getBytes());
    }

    @Test
    public void TestIrSwap() {
        TestIrSwap(tpSwitch);
        TestIrSwap(tpEnum);
    }

    private void TestIrSwap(TradeParser tp) {
        Trade t = tp.readTrade(convertStringToInputStream(sIrSwap));
        assertTrue(t instanceof IrSwap);
        assertEquals(100.0, t.getPrice());
        assertEquals("Trade {class homework01.IrSwap, price: 100.0}", t.toString());
    }

    @Test
    public void TestFxSpot() {
        TestFxSpot(tpSwitch);
        TestFxSpot(tpEnum);
    }

    private void TestFxSpot(TradeParser tp) {
        Trade t = tp.readTrade(convertStringToInputStream(sFxSpot));
        assertTrue(t instanceof FxSpot);
        assertEquals(20.0, t.getPrice());
        assertEquals("Trade {class homework01.FxSpot, price: 20.0}", t.toString());
    }
}
