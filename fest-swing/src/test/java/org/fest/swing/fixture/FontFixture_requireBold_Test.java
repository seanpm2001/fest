/*
 * Created on Apr 16, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.Test;

/**
 * Tests for <code>{@link FontFixture#requireBold()}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class FontFixture_requireBold_Test extends FontFixture_TestCase {

  @Test
  public void should_pass_if_font_is_bold() {
    FontFixture fixture = new FontFixture(boldFont());
    fixture.requireBold();
  }

  @Test
  public void should_fail_if_font_is_not_bold() {
    expectAssertionError("[bold] expected:<true> but was:<false>").on(new CodeToTest() {
      public void run() {
        fixture().requireBold();
      }
    });
  }

  @Test
  public void should_fail_showing_description_if_font_is_not_bold() {
    expectAssertionError("[test - bold] expected:<true> but was:<false>").on(new CodeToTest() {
      public void run() {
        FontFixture fixture = new FontFixture(font(), "test");
        fixture.requireBold();
      }
    });
  }
}
