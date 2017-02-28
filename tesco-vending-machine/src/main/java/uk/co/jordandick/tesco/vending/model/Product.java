package uk.co.jordandick.tesco.vending.model;

/**
 * The Enum Product.
 */
public enum Product {

                     /** The product a. */
                     PRODUCT_A("A", 0.6d),
                     /** The product b. */
                     PRODUCT_B("B", 1d),

                     /** The product c. */
                     PRODUCT_C("C", 1.7d);

    /** The name. */
    private final String name;

    /** The price. */
    private final double price;

    /**
     * Instantiates a new product.
     *
     * @param name
     *            the name
     * @param price
     *            the price
     */
    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Gets the price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

}
