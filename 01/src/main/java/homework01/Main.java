package homework01;

import java.io.*;


public class Main {
    public static Trade readTrade(InputStream is, int method) {
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
                    }
                }

                if ((price == null) || (type == null)) {
                    throw new NullPointerException();
                }

                try {
                    switch (method) {
                        case 1:
                            t = createTrade1(type, price);
                            break;
                        case 2:
                            t = createTrade2(type, price);
                            break;
                    }
                } catch (UnknownTradeType e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (IOException | NullPointerException e) {
            System.err.println(e.getMessage());
        }

        return t;
    }

    private static Trade createTrade1(String type, Number price) throws UnknownTradeType {
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

    private static Trade createTrade2(String type, Number price) throws UnknownTradeType {
        try {
            return InstrumentType.valueOf(type).createTrade(price);
        } catch (IllegalArgumentException e) {
            throw new UnknownTradeType("Unknown trade type: " + type);
        }
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                FileInputStream is = new FileInputStream(args[0]);
                Trade t = readTrade(is, 1);
                System.out.println(t);

                is = new FileInputStream(args[0]);
                t = readTrade(is, 2);
                System.out.println(t);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
