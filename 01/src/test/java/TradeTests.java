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


    @Test
    public void TestBadStrings() {
        for (String s : sBadStrings) {
            TestBadString(s);
        }
    }

    private void TestBadString(String s) {
        TestBadString(s, 1);
        TestBadString(s, 2);
    }

    private void TestBadString(String s, int method) {
        Trade t = Main.readTrade(convertStringToInputStream(s), method);
        assertNull(t);
    }

    @Test
    public void TestBond() {
        TestBond(1);
        TestBond(2);
    }

    private void TestBond(int method) {
        Trade t = Main.readTrade(convertStringToInputStream(sBond), method);
        assertTrue(t instanceof Bond);
        assertEquals(10.5, t.getPrice());
    }

    @Test
    public void TestCommoditySpot() {
        TestCommoditySpot(1);
        TestCommoditySpot(2);
    }

    private void TestCommoditySpot(int method) {
        Trade t = Main.readTrade(convertStringToInputStream(sCommoditySpot), method);
        assertTrue(t instanceof CommoditySpot);
        assertEquals(0.65, t.getPrice());
    }

    private InputStream convertStringToInputStream(String s) {
        return new ByteArrayInputStream(s.getBytes());
    }

    @Test
    public void TestIrSwap() {
        TestIrSwap(1);
        TestIrSwap(2);
    }

    private void TestIrSwap(int method) {
        Trade t = Main.readTrade(convertStringToInputStream(sIrSwap), method);
        assertTrue(t instanceof IrSwap);
        assertEquals(100.0, t.getPrice());
    }

    @Test
    public void TestFxSpot() {
        TestFxSpot(1);
        TestFxSpot(2);
    }

    private void TestFxSpot(int method) {
        Trade t = Main.readTrade(convertStringToInputStream(sFxSpot), method);
        assertTrue(t instanceof FxSpot);
        assertEquals(20.0, t.getPrice());
    }
}
