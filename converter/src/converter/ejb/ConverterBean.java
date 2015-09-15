/**
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package converter.ejb;

import javax.ejb.Stateless;
import java.math.BigDecimal;

/**
 * This is the bean class for the ConverterBean enterprise bean.
 * @author ian
 */
@Stateless
public class ConverterBean {

    private BigDecimal mUsdToJpyRate = new BigDecimal("120.04");
    private BigDecimal mJpyToEurRate = new BigDecimal("0.0074");

    public BigDecimal usdToJpy(BigDecimal usd) {
        BigDecimal jpy = usd.multiply(mUsdToJpyRate);
        return jpy.setScale(2, BigDecimal.ROUND_UP);
    }

    public BigDecimal jpyToEur(BigDecimal jpy) {
        BigDecimal eur = jpy.multiply(mJpyToEurRate);
        return eur.setScale(2, BigDecimal.ROUND_UP);
    }

}
