/*
 * Created on Oct 11, 2009
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
 * Copyright (c) 2009 the original author or authors.
 */
package org.fest.assertions;

import org.junit.Test;

import org.fest.test.CodeToTest;
import static org.fest.test.ExpectedFailure.expectAssertionError;

/**
 * Tests for <code>{@link FileAssert#isNull()}</code>.
 *
 * @author Ansgar Konermann
 */
public class FileAssert_isNull_Test implements NullableAssert_isNull_TestCase {

  @Test
  public void should_pass_if_actual_is_null() {
    new FileAssert(null).isNull();
  }

  @Test
  public void should_fail_if_actual_is_not_null() {
    expectAssertionError("<some-absolute-path> should be null").on(new CodeToTest() {
      public void run() throws Throwable {
        new FileAssert(FileStub.newFile("some-absolute-path")).isNull();
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_not_null() {
    expectAssertionError("[A Test] <some-absolute-path> should be null").on(new CodeToTest() {
      public void run() throws Throwable {
        new FileAssert(FileStub.newFile("some-absolute-path")).as("A Test").isNull();
      }
    });
  }

}
