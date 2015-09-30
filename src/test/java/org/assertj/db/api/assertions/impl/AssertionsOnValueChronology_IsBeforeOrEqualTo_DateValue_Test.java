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
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.Table;
import org.junit.Test;

import java.sql.Date;
import java.sql.Timestamp;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertionsOnValueChronology} class :
 * {@link AssertionsOnValueChronology#isBeforeOrEqualTo(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, org.assertj.db.type.Value, org.assertj.db.type.DateValue)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnValueChronology_IsBeforeOrEqualTo_DateValue_Test extends AbstractTest {

  /**
   * This method tests the {@code isBeforeOrEqualTo} assertion method.
   */
  @Test
  public void test_is_before_or_equal_to() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssert2 = AssertionsOnValueChronology.isBeforeOrEqualTo(tableAssert, info,
                                                                             getValue(null, Timestamp.valueOf("2007-12-23 00:00:00")),
                                                                             DateValue.of(2007, 12, 23));
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueChronology.isBeforeOrEqualTo(tableAssert, info,
                                                                 getValue(null, Timestamp.valueOf("2007-12-23 09:01:05")),
                                                                 DateValue.of(2007, 12, 24));
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueChronology.isBeforeOrEqualTo(tableAssert, info,
                                                                 getValue(null, Date.valueOf("2007-12-23")),
                                                                 DateValue.of(2007, 12, 23));
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueChronology.isBeforeOrEqualTo(tableAssert, info,
                                                                 getValue(null, Date.valueOf("2007-12-23")),
                                                                 DateValue.of(2007, 12, 24));
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the value is after.
   */
  @Test
  public void should_fail_because_value_is_after() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnValueChronology.isBeforeOrEqualTo(tableAssert, info,
                                                    getValue(null, Timestamp.valueOf("2007-12-23 09:01:05")),
                                                    DateValue.of(2007, 12, 23));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting:%n"
                                                      + "  <2007-12-23T09:01:05.000000000>%n"
                                                      + "to be before or equal to %n"
                                                      + "  <2007-12-23T00:00:00.000000000>"));
    }
    try {
      AssertionsOnValueChronology.isBeforeOrEqualTo(tableAssert, info,
                                                    getValue(null, Date.valueOf("2007-12-24")),
                                                    DateValue.of(2007, 12, 23));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting:%n"
                                                      + "  <2007-12-24>%n"
                                                      + "to be before or equal to %n"
                                                      + "  <2007-12-23>"));
    }
  }

  /**
   * This method should fail because the value is not compatible.
   */
  @Test
  public void should_fail_because_value_is_not_compatible() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnValueChronology.isBeforeOrEqualTo(tableAssert, info,
                                                    getValue(null, "test"),
                                                    DateValue.of(2007, 12, 23));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting:%n"
                                                      + "  <\"test\">%n"
                                                      + "to be of type%n"
                                                      + "  <[DATE, DATE_TIME]>%n"
                                                      + "but was of type%n"
                                                      + "  <TEXT>"));
    }
  }
}
