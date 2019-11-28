package homework01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TradeParser {
    private final TradeCreator tradeCreator;

    public TradeParser(TradeCreator tradeCreator) {
        this.tradeCreator = tradeCreator;
    }

    public Trade readTrade(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        Trade t = null;
        try {
            String s = br.readLine().replaceAll(" ", "");

            if (s.equals("Trade:{")) {
                String type = null;
                Number price = null;

                while (!s.equals("}")) {
                    s = br.readLine().replaceAll("[ ,]", "");
                    String[] ss = s.split(":", 2);
                    switch (ss[0]) {
                        case "type":
                            type = ss[1];
                            break;
                        case "price":
                            price = Double.parseDouble(ss[1]);
                            break;
                        case "}":
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + ss[0]);
                    }
                }

                if ((price == null) || (type == null)) {
                    throw new NullPointerException();
                }

                try {
                    t = tradeCreator.create(type, price);
                } catch (UnknownTradeType e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (IOException | NullPointerException | IllegalStateException e) {
            System.err.println(e.getMessage());
        }

        return t;
    }
}
