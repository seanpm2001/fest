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

import static org.fest.assertions.CommonFailures.expectErrorIfConditionIsNull;
import static org.fest.assertions.EmptyArrays.emptyBooleanArray;
import static org.fest.assertions.NotNull.notNullBooleanArray;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link BooleanArrayAssert#doesNotSatisfy(Condition)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class BooleanArrayAssert_doesNotSatisfy_Test implements GenericAssert_doesNotSatisfy_TestCase {

  private static boolean[] array;

  @BeforeClass
  public static void setUpOnce() {
    array = emptyBooleanArray();
  }

  @Test
  public void should_pass_if_condition_is_not_satisfied() {
    new BooleanArrayAssert(null).doesNotSatisfy(notNullBooleanArray());
  }

  @Test
  public void should_throw_error_if_condition_is_null() {
    expectErrorIfConditionIsNull().on(new CodeToTest() {
      public void run() {
        new BooleanArrayAssert(array).doesNotSatisfy(null);
      }
    });
  }

  @Test
  public void should_fail_if_condition_is_satisfied() {
    expectAssertionError("actual value:<[]> should not satisfy condition:<NotNull>").on(new CodeToTest() {
      public void run() {
        new BooleanArrayAssert(array).doesNotSatisfy(notNullBooleanArray());
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_condition_is_satisfied() {
    expectAssertionError("[A Test] actual value:<[]> should not satisfy condition:<NotNull>").on(new CodeToTest() {
      public void run() {
        new BooleanArrayAssert(array).as("A Test")
                                     .doesNotSatisfy(notNullBooleanArray());
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_condition_if_condition_is_satisfied() {
    expectAssertionError("actual value:<[]> should not satisfy condition:<Not Null>").on(new CodeToTest() {
      public void run() {
        new BooleanArrayAssert(array).doesNotSatisfy(notNullBooleanArray().as("Not Null"));
      }
    });
  }

  @Test
  public void should_fail_and_display_descriptions_of_assertion_and_condition_if_condition_is_satisfied() {
    expectAssertionError("[A Test] actual value:<[]> should not satisfy condition:<Not Null>").on(new CodeToTest() {
      public void run() {
        new BooleanArrayAssert(array).as("A Test")
                                     .doesNotSatisfy(notNullBooleanArray().as("Not Null"));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_condition_is_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new BooleanArrayAssert(array).overridingErrorMessage("My custom message")
                                     .doesNotSatisfy(notNullBooleanArray());
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_condition_is_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new BooleanArrayAssert(array).as("A Test")
                                     .overridingErrorMessage("My custom message")
                                     .doesNotSatisfy(notNullBooleanArray());
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_condition_if_condition_is_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new BooleanArrayAssert(array).overridingErrorMessage("My custom message")
                                     .doesNotSatisfy(notNullBooleanArray().as("Not Null"));
      }
    });
  }
}
