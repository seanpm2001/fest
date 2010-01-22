/*
 * Created on Jan 21, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.javafx.maven;

import static org.apache.tools.ant.Project.*;
import static org.fest.util.Strings.isEmpty;

import org.apache.maven.plugin.logging.Log;
import org.apache.tools.ant.*;

/**
 * Understands a <code>{@link BuildListener}</code> that writes build events to a Maven log.
 *
 * @author Alex Ruiz
 */
class LoggingBuildListener implements BuildListener {

  private final Log log;

  LoggingBuildListener(Log log) {
    this.log = log;
  }

  public void buildStarted(BuildEvent event) {
    log(event);
  }

  public void buildFinished(BuildEvent event) {
    log(event);
  }

  public void targetStarted(BuildEvent event) {
    log(event);
  }

  public void targetFinished(BuildEvent event) {
    log(event);
  }

  public void taskStarted(BuildEvent event) {
    log(event);
  }

  public void taskFinished(BuildEvent event) {
    log(event);
  }

  private void log(BuildEvent event) {
    log.info(event.getMessage());
  }

  public void messageLogged(BuildEvent event) {
    log(formattedMessageFrom(event), event.getPriority());
  }

  private static String formattedMessageFrom(BuildEvent event) {
    String message = event.getMessage();
    return isEmpty(message) ? "" : message.trim();
  }

  private void log(String message, int priority) {
    switch(priority) {
      case MSG_ERR:
        log.error(message);
        break;

      case MSG_WARN:
        log.warn(message);
        break;

      case MSG_INFO:
        log.info(message);
        break;

      case MSG_DEBUG:
        log.debug(message);
    };
  }
}
