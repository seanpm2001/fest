/*
 * Created on Mar 29, 2009
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
 * Copyright @2009 the original author or authors.
 */
package org.fest.assertions;

import static java.util.Collections.emptyList;
import static org.fest.assertions.CommonFailures.expectErrorIfActualListIsNull;
import static org.fest.assertions.CommonFailures.expectErrorWithDescriptionIfActualListIsNull;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.Test;

/**
 * Tests for <code>{@link ListAssert#isNotNull()}</code>.
 *
 * @author Alex Ruiz
 */
public class ListAssert_isNotNull_Test implements GenericAssert_isNotNull_TestCase {

  @Test
  public void should_pass_if_actual_is_not_null() {
    new ListAssert(emptyList()).isNotNull();
  }

  @Test
  public void should_fail_if_actual_is_null() {
    expectErrorIfActualListIsNull(new CodeToTest() {
      public void run() {
        new ListAssert(null).isNotNull();
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_null() {
    expectErrorWithDescriptionIfActualListIsNull(new CodeToTest() {
      public void run() {
        new ListAssert(null).as("A Test")
                            .isNotNull();
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_null() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ListAssert(null).overridingErrorMessage("My custom message")
                            .isNotNull();
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_null() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ListAssert(null).as("A Test")
                            .overridingErrorMessage("My custom message")
                            .isNotNull();
      }
    });
  }
}