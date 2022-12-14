package com.yougrocery.yougrocery.models;

/**
 * {@link Product} specific assertions - Generated by CustomAssertionGenerator.
 *
 * Although this class is not final to allow Soft assertions proxy, if you wish to extend it, 
 * extend {@link AbstractProductAssert} instead.
 */
@javax.annotation.Generated(value="assertj-assertions-generator")
public class ProductAssert extends AbstractProductAssert<ProductAssert, Product> {

  /**
   * Creates a new <code>{@link ProductAssert}</code> to make assertions on actual Product.
   * @param actual the Product we want to make assertions on.
   */
  public ProductAssert(Product actual) {
    super(actual, ProductAssert.class);
  }

  /**
   * An entry point for ProductAssert to follow AssertJ standard <code>assertThat()</code> statements.<br>
   * With a static import, one can write directly: <code>assertThat(myProduct)</code> and get specific assertion with code completion.
   * @param actual the Product we want to make assertions on.
   * @return a new <code>{@link ProductAssert}</code>
   */
  @org.assertj.core.util.CheckReturnValue
  public static ProductAssert assertThat(Product actual) {
    return new ProductAssert(actual);
  }
}
