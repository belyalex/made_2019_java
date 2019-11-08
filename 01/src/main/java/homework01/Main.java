package homework01;

import java.io.*;


public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                TradeParser tpSwitch=new TradeParser(new SwitchTradeCreator());
                FileInputStream is = new FileInputStream(args[0]);
                Trade t = tpSwitch.readTrade(is);
                System.out.println(t);

                TradeParser tpEnum=new TradeParser(new EnumTradeCreator());
                is = new FileInputStream(args[0]);
                t = tpEnum.readTrade(is);
                System.out.println(t);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
