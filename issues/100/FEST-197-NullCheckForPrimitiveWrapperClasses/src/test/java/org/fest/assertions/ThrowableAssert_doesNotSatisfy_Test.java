/*
 * Created on Dec 23, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.CommonFailures.*;
import static org.fest.assertions.NotNull.notNullThrowable;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.Test;

/**
 * Tests for <code>{@link ThrowableAssert#doesNotSatisfy(Condition)}</code>.
 *
 * @author David DIDIER
 * @author Alex Ruiz
 */
public class ThrowableAssert_doesNotSatisfy_Test implements GenericAssert_doesNotSatisfy_TestCase {

  @Test
  public void should_pass_if_condition_is_not_satisfied() {
    new ThrowableAssert(null).doesNotSatisfy(notNullThrowable());
  }

  @Test
  public void should_throw_error_if_condition_is_null() {
    expectErrorIfConditionIsNull().on(new CodeToTest() {
      public void run() {
        new ThrowableAssert(new Exception()).doesNotSatisfy(null);
      }
    });
  }

  @Test
  public void should_fail_if_condition_is_satisfied() {
    expectAssertionError("actual value:<java.lang.Exception> should not satisfy condition:<NotNull>").on(new CodeToTest() {
      public void run() {
        new ThrowableAssert(new Exception()).doesNotSatisfy(notNullThrowable());
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_condition_is_satisfied() {
    expectAssertionError("[A Test] actual value:<java.lang.Exception> should not satisfy condition:<NotNull>").on(new CodeToTest() {
      public void run() {
        new ThrowableAssert(new Exception()).as("A Test")
                                            .doesNotSatisfy(notNullThrowable());
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_condition_if_condition_is_satisfied() {
    String message = "actual value:<java.lang.Exception> should not satisfy condition:<Not Null>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new ThrowableAssert(new Exception()).doesNotSatisfy(notNullThrowable().as("Not Null"));
      }
    });
  }

  @Test
  public void should_fail_and_display_descriptions_of_assertion_and_condition_if_condition_is_satisfied() {
    String message = "[A Test] actual value:<java.lang.Exception> should not satisfy condition:<Not Null>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new ThrowableAssert(new Exception()).as("A Test")
                                            .doesNotSatisfy(notNullThrowable().as("Not Null"));
      }
    });
  }
}
