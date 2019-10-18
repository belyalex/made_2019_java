package homework01;

public enum InstrumentType {
    BOND {
        @Override
        Trade createTrade(Number price) {
            return new Bond(price);
        }
    },
    COMMODITY_SPOT {
        @Override
        Trade createTrade(Number price) {
            return new CommoditySpot(price);
        }
    },
    FX_SPOT {
        @Override
        Trade createTrade(Number price) {
            return new FxSpot(price);
        }
    },
    IR_SWAP {
        @Override
        Trade createTrade(Number price) {
            return new IrSwap(price);
        }
    };

    abstract Trade createTrade(Number price);
    /*
        ZERO, DISCOUNT, MM, MM_FUTURE,
        FRA, IMM_FRA, IR_SWAP, BASIS_SWAP,
        BMA_SWAP, CC_BASIS_SWAP, CC_FIX_FLOAT_SWAP, CDS,
        CDS_INDEX, FX_SPOT, FX_FWD, HAZARD_RATE,
        RECOVERY_RATE, SWAPTION, CAPFLOOR, FX_OPTION,
        ZC_INFLATIONSWAP, ZC_INFLATIONCAPFLOOR, YY_INFLATIONSWAP, YY_INFLATIONCAPFLOOR,
        SEASONALITY, EQUITY_SPOT, EQUITY_FWD, EQUITY_DIVIDEND,
        EQUITY_OPTION, BOND, BOND_OPTION, INDEX_CDS_OPTION,
        COMMODITY_SPOT, COMMODITY_FWD, CORRELATION, COMMODITY_OPTION,
        CPR
     */
}
