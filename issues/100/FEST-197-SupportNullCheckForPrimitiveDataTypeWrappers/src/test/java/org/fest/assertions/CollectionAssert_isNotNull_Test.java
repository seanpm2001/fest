/*
 * Created on Jan 10, 2007
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.CommonFailures.expectErrorIfCollectionIsNull;
import static org.fest.assertions.CommonFailures.expectErrorWithDescriptionIfCollectionIsNull;

import java.util.ArrayList;

import org.fest.test.CodeToTest;
import org.junit.Test;

/**
 * Tests for <code>{@link CollectionAssert#isNotNull()}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class CollectionAssert_isNotNull_Test implements NullableAssert_isNotNull_TestCase {

  @Test
  public void should_pass_if_actual_is_not_null() {
    new CollectionAssert(new ArrayList<String>()).isNotNull();
  }

  @Test
  public void should_fail_if_actual_is_null() {
    expectErrorIfCollectionIsNull(new CodeToTest() {
      public void run() {
        new CollectionAssert(null).isNotNull();
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_null() {
    expectErrorWithDescriptionIfCollectionIsNull(new CodeToTest() {
      public void run() {
        new CollectionAssert(null).as("A Test").isNotNull();
      }
    });
  }

  @Test
  public void should_have_leaf_assertion_class_as_return_type() {
    CollectionAssert initialInstance = new CollectionAssert(new ArrayList<Integer>());
    @SuppressWarnings("unused")
    CollectionAssert returnValue = initialInstance.isNotNull();
  }
}
