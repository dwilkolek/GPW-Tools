package eu.wilkolek.gpw;

import org.w3c.dom.Node;

/**
 *
 * @author Slishu
 */
public class GPWRecord {

    private String valor;
    private String shortcut;
    private String currency;
    private String lastTransaction;
    private Double rate;
    private String TKO;
    private Double setdownOpening;
    private Double setdownMin;
    private Double setdownMax;
    private Double setdownClose;
    private Double change;
    private Integer buyCount;
    private Integer buyVolume;
    private String buyLimit;
    private String sellLimit;
    private Integer sellVolume;
    private Integer sellCount;
    private Integer volumeLastTransaction;
    private Integer transactionCount;
    private Integer c_vol;
    private Double c_val;

    public GPWRecord() {
    }

    private String getString(Node node) {
        String value = null;
        try {
            value = node.getChildNodes().item(0).getTextContent();
        } catch (Exception e) {
            value = node.getTextContent();
        }
        return value;
    }

    private Double getDouble(Node node) {
        String str = this.getString(node);
        if (str != null && !"---".equals(str) && !str.isEmpty()) {
            return new Double(str.replace(",", ".").replace(" ", ""));
        }
        return null;
    }

    private Integer getInteger(Node node) {
        String str = this.getString(node);
        if (str != null && !"---".equals(str) && !str.isEmpty()) {
            return new Integer(str);
        }
        return null;
    }

    public GPWRecord(Node item) {

        /*0: null */
        /*1: null*/
        /*2: 06MAGNA */
        this.valor = this.getString(item.getChildNodes().item(2).getChildNodes().item(0));
        /*3: 06N*/
        this.shortcut = this.getString(item.getChildNodes().item(3).getChildNodes().item(0));
        /*4: PLN*/
        this.currency = this.getString(item.getChildNodes().item(4).getChildNodes().item(0));
        /*5: 16:48:22*/
        this.lastTransaction = this.getString(item.getChildNodes().item(5).getChildNodes().item(0));
        /*6: 1,93*/
        this.rate = this.getDouble(item.getChildNodes().item(6).getChildNodes().item(0));
        /*7: ---*/
        this.TKO = this.getString(item.getChildNodes().item(7).getChildNodes().item(0));
        /*8: 1,92*/
        this.setdownOpening = this.getDouble(item.getChildNodes().item(8).getChildNodes().item(0));
        /*9: 1,84*/
        this.setdownMin = this.getDouble(item.getChildNodes().item(9).getChildNodes().item(0));
        /*10: 1,92*/
        this.setdownMax = this.getDouble(item.getChildNodes().item(10).getChildNodes().item(0));
        /*11: 1,92*/
        this.setdownClose = this.getDouble(item.getChildNodes().item(11).getChildNodes().item(0));
        /*12: -0,52*/
        this.change = this.getDouble(item.getChildNodes().item(12).getChildNodes().item(0));
        /*13: 1*/
        this.buyCount = this.getInteger(item.getChildNodes().item(13).getChildNodes().item(0));
        /*14: 698*/
        this.buyVolume = this.getInteger(item.getChildNodes().item(14).getChildNodes().item(0));
        /*15: 1,85*/
        this.buyLimit = this.getString(item.getChildNodes().item(15).getChildNodes().item(0));
        /*16: 1,92*/
        this.sellLimit = this.getString(item.getChildNodes().item(16).getChildNodes().item(0));
        /*17: 4980*/
        this.sellVolume = this.getInteger(item.getChildNodes().item(17).getChildNodes().item(0));
        /*18: 1*/
        this.sellCount = this.getInteger(item.getChildNodes().item(18).getChildNodes().item(0));
        /*19: 3*/
        this.volumeLastTransaction = this.getInteger(item.getChildNodes().item(19).getChildNodes().item(0));
        /*20: 7*/
        this.transactionCount = this.getInteger(item.getChildNodes().item(20).getChildNodes().item(0));
        /*21: 638*/
        this.c_vol = this.getInteger(item.getChildNodes().item(21).getChildNodes().item(0));
        /*22: 1,22*/
        this.c_val = this.getDouble(item.getChildNodes().item(22).getChildNodes().item(0));

    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLastTransaction() {
        return lastTransaction;
    }

    public void setLastTransaction(String lastTransaction) {
        this.lastTransaction = lastTransaction;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getTKO() {
        return TKO;
    }

    public void setTKO(String TKO) {
        this.TKO = TKO;
    }

    public Double getSetdownOpening() {
        return setdownOpening;
    }

    public void setSetdownOpening(Double setdownOpening) {
        this.setdownOpening = setdownOpening;
    }

    public Double getSetdownMin() {
        return setdownMin;
    }

    public void setSetdownMin(Double setdownMin) {
        this.setdownMin = setdownMin;
    }

    public Double getSetdownMax() {
        return setdownMax;
    }

    public void setSetdownMax(Double setdownMax) {
        this.setdownMax = setdownMax;
    }

    public Double getSetdownClose() {
        return setdownClose;
    }

    public void setSetdownClose(Double setdownClose) {
        this.setdownClose = setdownClose;
    }

    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public Integer getBuyVolume() {
        return buyVolume;
    }

    public void setBuyVolume(Integer buyVolume) {
        this.buyVolume = buyVolume;
    }

    public String getBuyLimit() {
        return buyLimit;
    }

    public void setBuyLimit(String buyLimit) {
        this.buyLimit = buyLimit;
    }

    public String getSellLimit() {
        return sellLimit;
    }

    public void setSellLimit(String sellLimit) {
        this.sellLimit = sellLimit;
    }

    public Integer getSellVolume() {
        return sellVolume;
    }

    public void setSellVolume(Integer sellVolume) {
        this.sellVolume = sellVolume;
    }

    public Integer getSellCount() {
        return sellCount;
    }

    public void setSellCount(Integer sellCount) {
        this.sellCount = sellCount;
    }

    public Integer getVolumeLastTransaction() {
        return volumeLastTransaction;
    }

    public void setVolumeLastTransaction(Integer volumeLastTransaction) {
        this.volumeLastTransaction = volumeLastTransaction;
    }

    public Integer getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(Integer transactionCount) {
        this.transactionCount = transactionCount;
    }

    public Integer getC_vol() {
        return c_vol;
    }

    public void setC_vol(Integer c_vol) {
        this.c_vol = c_vol;
    }

    public Double getC_val() {
        return c_val;
    }

    public void setC_val(Double c_val) {
        this.c_val = c_val;
    }
    
    
    
    @Override
    public String toString() {
        return this.valor + " [" + this.shortcut + "]";
    }
    
}
