package org.cache2k.pinpoint.stress.pairwise;

/*-
 * #%L
 * cache2k pinpoint
 * %%
 * Copyright (C) 2000 - 2022 headissue GmbH, Munich
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.cache2k.pinpoint.NeverExecutedError;

/**
 * @author Jens Wilke
 */
public interface ActorPair<R1, R2> {

  /**
   * Setup or reset action executed before the actors.
   */
  void setup();

  /**
   * First actor executed concurrently with actor2.
   * Any exceptions are propagated.
   *
   * @return outcome of the actor
   */
  R1 actor1();

  /**
   * Second actor executed concurrently with actor1.
   * Any exceptions are propagated.
   *
   * @return outcome of the actor
   */
  R2 actor2();

  /**
   * The observer can check integrity or invariants of a data structure. It
   * is executed concurrently to the actors. Any exceptions are propagated.
   */
  default void observe() { throw new NeverExecutedError(); }

  /**
   * Checks the outcome after both actors have finished. The method is expected
   * to throw an exception or assertion error in case the result is unexpected.
   *
   * @param r1 outcome of {@link #actor1()}
   * @param r2 outcome of {@link #actor2()}
   */
  void check(R1 r1, R2 r2);

}
