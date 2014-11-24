/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2014 the original author or authors.
 */
package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on the methods which verifies if a value is equal to a string.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsEqualTo_String_Test extends AbstractTest {

  /**
   * This method tests that the value is equal to a string.
   */
  @Test
  public void test_if_value_is_equal_to_string() {
    Table table = new Table(source, "test");
    assertThat(table)
        .column("var12")
            .value().isEqualTo("text").returnToColumn()
            .value().isEqualTo("another text").returnToColumn()
        .returnToTable()
        .column("var1")
            .value().isEqualTo("1").returnToColumn()
            .value().isEqualTo("10").returnToColumn()
        .returnToTable()
        .column("var3")
            .value().isEqualTo("2").returnToColumn()
            .value().isEqualTo("20").returnToColumn()
        .returnToTable()
        .column()
            .value().isEqualTo("3").returnToColumn()
            .value().isEqualTo("30").returnToColumn()
        .returnToTable()
        .column()
            .value().isEqualTo("4").returnToColumn()
            .value().isEqualTo("40").returnToColumn()
        .returnToTable()
        .column()
            .value().isEqualTo("5.6").returnToColumn()
            .value().isEqualTo("50.6").returnToColumn()
        .returnToTable()
        .column("var7")
            .value().isEqualTo("7.8").returnToColumn()
            .value().isEqualTo("70.8").returnToColumn()
        .returnToTable()
        .column("var13")
            .value().isEqualTo("5").returnToColumn()
            .value().isEqualTo("50").returnToColumn()
        .returnToTable()
        .column("var14")
            .value().isEqualTo("7").returnToColumn()
            .value().isEqualTo("70").returnToColumn()
        .returnToTable()
        .column("var8")
            .value().isEqualTo("09:46:30").returnToColumn()
            .value().isEqualTo("12:29:49").returnToColumn()
            .value().isEqualTo("12:29:49").returnToColumn()
        .returnToTable()
        .column("var9")
            .value().isEqualTo("2014-05-24").returnToColumn()
            .value().isEqualTo("2014-05-30").returnToColumn()
            .value().isEqualTo("2014-05-30").returnToColumn()
        .returnToTable()
        .column("var9")
            .value().isEqualTo("2014-05-24T00:00").returnToColumn()
            .value().isEqualTo("2014-05-30T00:00").returnToColumn()
            .value().isEqualTo("2014-05-30T00:00").returnToColumn()
            .returnToTable()
        .column("var9")
            .value().isEqualTo("2014-05-24T00:00:00").returnToColumn()
            .value().isEqualTo("2014-05-30T00:00:00").returnToColumn()
            .value().isEqualTo("2014-05-30T00:00:00").returnToColumn()
        .returnToTable()
        .column("var9")
            .value().isEqualTo("2014-05-24T00:00:00.000000000").returnToColumn()
            .value().isEqualTo("2014-05-30T00:00:00.000000000").returnToColumn()
            .value().isEqualTo("2014-05-30T00:00:00.000000000").returnToColumn()
        .returnToTable()
        .column("var10")
            .value().isEqualTo("2014-05-24T09:46:30.000000000").returnToColumn()
            .value().isEqualTo("2014-05-30T12:29:49.000000000").returnToColumn()
            .value().isEqualTo("2014-05-30T00:00:00.000000000").returnToColumn()
        .returnToTable()
        .column("var10")
            .value().isEqualTo("2014-05-24T09:46:30.000000000").returnToColumn()
            .value().isEqualTo("2014-05-30T12:29:49.000000000").returnToColumn()
            .value().isEqualTo("2014-05-30T00:00:00").returnToColumn()
        .returnToTable()
        .column("var10")
            .value().isEqualTo("2014-05-24T09:46:30").returnToColumn()
            .value().isEqualTo("2014-05-30T12:29:49").returnToColumn()
            .value().isEqualTo("2014-05-30").returnToColumn()
        .returnToTable()
        .column("var10")
            .value().isEqualTo("2014-05-24T09:46:30").returnToColumn()
            .value().isEqualTo("2014-05-30T12:29:49").returnToColumn()
            .value().isEqualTo("2014-05-30T00:00").returnToColumn();
  }

  /**
   * This method should fail because the value is not equal to the string.
   */
  @Test
  public void should_fail_because_value_is_not_equal_to_string() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var12")
          .value().isEqualTo("Text");
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Column at index 11 of test table] \n" +
          "Expecting:\n" +
          "  <\"text\">\n" +
          "to be equal to: \n" +
          "  <\"Text\">");
    }
  }

  /**
   * This method should fail because the value is not equal to the number.
   */
  @Test
  public void should_fail_because_value_is_not_equal_to_number() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var1")
          .value().isEqualTo("2");
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Column at index 0 of test table] \n" +
          "Expecting:\n" +
          "  <\"1\">\n" +
          "to be equal to: \n" +
          "  <\"2\">");
    }
  }

  /**
   * This method should fail because the value is not equal to the time.
   */
  @Test
  public void should_fail_because_value_is_not_equal_to_time() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var8")
          .value().isEqualTo("09:46:31");
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Column at index 7 of test table] \n" +
          "Expecting:\n" +
          "  <\"09:46:30.000000000\">\n" +
          "to be equal to: \n" +
          "  <\"09:46:31\">");
    }
  }

  /**
   * This method should throw a {@code AssertJDBException}, because the time is not parsable.
   * 
   * <pre>
   * Expected <-9:46:31> is not correct to compare to <09:46:30>
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_because_the_time_is_not_parsable() {
    Table table = new Table(source, "test");
    assertThat(table).column("var8")
        .value().isEqualTo("-9:46:31");
  }

  /**
   * This method should fail because the value is not equal to the date.
   */
  @Test
  public void should_fail_because_value_is_not_equal_to_date() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var9")
          .value().isEqualTo("2014-05-25");
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Column at index 8 of test table] \n" +
          "Expecting:\n" +
          "  <\"2014-05-24\">\n" +
          "to be equal to: \n" +
          "  <\"2014-05-25\">");
    }
  }

  /**
   * This method should throw a {@code AssertJDBException}, because the date is not parsable.
   * 
   * <pre>
   * Expected <2-14-05-25> is not correct to compare to <2014-05-25>
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_because_the_date_is_not_parsable() {
    Table table = new Table(source, "test");
    assertThat(table).column("var9")
    .value().isEqualTo("2-14-05-25");
  }

  /**
   * This method should fail because the value is not equal to the date/time.
   */
  @Test
  public void should_fail_because_value_is_not_equal_to_datetime() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var10")
          .value().isEqualTo("2014-05-24T09:46:31");
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Column at index 9 of test table] \n" +
          "Expecting:\n" +
          "  <\"2014-05-24T09:46:30.000000000\">\n" +
          "to be equal to: \n" +
          "  <\"2014-05-24T09:46:31\">");
    }
  }

  /**
   * This method should throw a {@code AssertJDBException}, because the date/time is not parsable.
   * 
   * <pre>
   * Expected <2014-05-A4T09:46:31.000000000> is not correct to compare to <2014-05-24T09:46:31.000000000>
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_because_the_datetime_is_not_parsable() {
    Table table = new Table(source, "test");
    assertThat(table).column("var10")
    .value().isEqualTo("2014-05-A4T09:46:31.000000000");
  }

  /**
   * This method should fail because it is not possible to compare.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_it_is_not_possible_to_compare() {
    Table table = new Table(source, "test");
    assertThat(table).column("var1")
        .value().isEqualTo("***");
  }

  /**
   * This method should fail because the value is not a text.
   */
  @Test
  public void should_fail_because_value_is_not_a_text() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var2")
          .value().as("var2").isEqualTo("Text");
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var2] \n" +
          "Expecting:\n" +
          "  <true>\n" +
          "to be of type\n" +
          "  <[TEXT, NUMBER, DATE, TIME, DATE_TIME]>\n" +
          "but was of type\n" +
          "  <BOOLEAN>");
    }
  }

}
