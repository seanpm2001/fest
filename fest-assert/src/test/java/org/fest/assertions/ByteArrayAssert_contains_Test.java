/*
 * Created on Feb 14, 2008
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ArrayFactory.byteArray;
import static org.fest.assertions.CommonFailures.*;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link ByteArrayAssert#contains(byte...)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ByteArrayAssert_contains_Test implements GroupAssert_contains_TestCase {

  private static byte[] array;

  @BeforeClass
  public static void setUpOnce() {
    array = byteArray(8, 6);
  }

  @Test
  public void should_pass_if_actual_contains_given_value() {
    new ByteArrayAssert(array).contains((byte)8);
  }

  @Test
  public void should_pass_if_actual_contains_given_values() {
    new ByteArrayAssert(array).contains((byte)8, (byte)6);
  }

  @Test
  public void should_fail_if_actual_is_null() {
    expectErrorIfActualArrayIsNull(new CodeToTest() {
      public void run() {
        new ByteArrayAssert(null).contains((byte)8, (byte)6);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_null() {
    expectErrorWithDescriptionIfActualArrayIsNull(new CodeToTest() {
      public void run() {
        new ByteArrayAssert(null).as("A Test")
                                 .contains((byte)8, (byte)6);
      }
    });
  }

  @Test
  public void should_throw_error_if_expected_is_null() {
    expectNullPointerException("the given array of bytes should not be null").on(new CodeToTest() {
      public void run() {
        new ByteArrayAssert(array).contains(null);
      }
    });
  }

  @Test
  public void should_throw_error_and_display_description_of_assertion_if_expected_is_null() {
    expectNullPointerException("[A Test] the given array of bytes should not be null").on(new CodeToTest() {
      public void run() {
        new ByteArrayAssert(array).as("A Test")
                                  .contains(null);
      }
    });
  }

  @Test
  public void should_fail_if_actual_does_not_contain_given_values() {
    expectAssertionError("array:<[8, 6]> does not contain element(s):<[10]>").on(new CodeToTest() {
      public void run() {
        new ByteArrayAssert(array).contains((byte)10);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_does_not_contain_given_values() {
    expectAssertionError("[A Test] array:<[8, 6]> does not contain element(s):<[10]>").on(new CodeToTest() {
      public void run() {
        new ByteArrayAssert(array).as("A Test")
                                  .contains((byte)10);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_does_not_contain_given_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ByteArrayAssert(array).overridingErrorMessage("My custom message")
                                  .contains((byte)10);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_does_not_contain_given_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ByteArrayAssert(array).as("A Test")
                                  .overridingErrorMessage("My custom message")
                                  .contains((byte)10);
      }
    });
  }
}