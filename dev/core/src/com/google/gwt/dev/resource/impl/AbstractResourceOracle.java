/*
 * Copyright 2014 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.dev.resource.impl;

import com.google.gwt.dev.resource.Resource;
import com.google.gwt.dev.resource.ResourceOracle;
import com.google.gwt.thirdparty.guava.common.io.Files;

import java.io.IOException;
import java.io.InputStream;

/**
 * A helper base class for {@code ResourceOracle} implementations.
 */
public abstract class AbstractResourceOracle implements ResourceOracle {
  @Override
  public Resource getResource(String pathName) {
    pathName = Files.simplifyPath(pathName);
    return getResourceMap().get(pathName);
  }

  @Override
  public InputStream getResourceAsStream(String pathName) {
    Resource resource = getResource(pathName);
    if (resource == null) {
      return null;
    }
    try {
      return resource.openContents();
    } catch (IOException e) {
      return null;
    }
  }
}
