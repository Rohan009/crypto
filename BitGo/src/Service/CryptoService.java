package Service;

import model.CryptoCoins;
import model.CryptoData;

import java.util.HashMap;
import java.util.Map;

public class CryptoService {

    static Map<CryptoCoins, CryptoData> cryptoDataMap = new HashMap<>();

    public static void addCryptoData(CryptoData cryptoData, CryptoCoins cryptoCoin) {
        cryptoDataMap.put(cryptoCoin, cryptoData);
    }

    public CryptoData getCryptoData(CryptoCoins cryptoCoin) {
        return cryptoDataMap.get(cryptoCoin);
    }
}
