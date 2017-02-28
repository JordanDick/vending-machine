package uk.co.jordandick.tesco.vending.model;

/**
 * The Enum Coin.
 */
public enum Coin {

                  /** The ten pence. */
                  TEN_PENCE(0.10d),
                  /** The twenty pence. */
                  TWENTY_PENCE(0.2d),
                  /** The fifty pence. */
                  FIFTY_PENCE(0.5d),

                  /** The pound. */
                  POUND(1d);

    /** The value. */
    private final double value;

    /**
     * Instantiates a new coin.
     *
     * @param value
     *            the value
     */
    Coin(double value) {
        this.value = value;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public double getValue() {
        return value;
    }

}
