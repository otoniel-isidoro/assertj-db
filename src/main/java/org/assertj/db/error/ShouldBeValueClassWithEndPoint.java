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
package org.assertj.db.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

/**
 * Creates an error message indicating that an assertion that verifies that a value is of a class.
 * 
 * @author Régis Pouiller
 * 
 */
public class ShouldBeValueClassWithEndPoint extends BasicErrorMessageFactory {

  private static final String EXPECTED_MESSAGE =
          "%nExpecting that the value at end point:%n  <%s>%nto be of class%n  <%s>%nbut was of class%n  <%s>";
  private static final String EXPECTED_MESSAGE_JUST_WITH_EXPECTED =
          "%nExpecting that the value at end point:%n  <%s>%nto be of class%n  <%s>";

  /**
   * Creates a new <code>{@link org.assertj.db.error.ShouldBeValueClassWithEndPoint}</code>.
   *
   * @param actual The actual value in the failed assertion.
   * @param expected The expected class.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeValueClassWithEndPoint(Object actual, Class expected) {
    if (actual == null) {
      return new ShouldBeValueClassWithEndPoint(null, expected);
    }
    return new ShouldBeValueClassWithEndPoint(actual, actual.getClass(), expected);
  }

  /**
   * Constructor.
   *
   * @param actual The actual value in the failed assertion.
   * @param tested The tested class.
   * @param expected The expected class.
   */
  private ShouldBeValueClassWithEndPoint(Object actual, Class tested, Class expected) {
    super(EXPECTED_MESSAGE, actual, expected, tested);
  }

  /**
   * Constructor.
   *
   * @param actual The actual value in the failed assertion.
   * @param expected The expected class.
   */
  private ShouldBeValueClassWithEndPoint(Object actual, Class expected) {
    super(EXPECTED_MESSAGE_JUST_WITH_EXPECTED, actual, expected);
  }
}
