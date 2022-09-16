package org.cache2k.jcache.provider;

/*-
 * #%L
 * cache2k JCache provider
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

import org.cache2k.Cache2kBuilder;
import org.cache2k.jcache.ExtendedMutableConfiguration;
import org.cache2k.jcache.JCacheConfig;
import org.junit.jupiter.api.Test;

import javax.cache.Cache;
import javax.cache.CacheManager;

import static javax.cache.Caching.getCachingProvider;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Jens Wilke
 */
public class CacheConfigTest {

  final String CACHE_NAME = getClass().getSimpleName();
  Cache<Object, Object> cache;

  @Test
  public void test() {
    CacheManager mgr = getCachingProvider().getCacheManager();
    cache = mgr.createCache(CACHE_NAME, ExtendedMutableConfiguration.of(
      Cache2kBuilder.forUnknownTypes()
        .with(JCacheConfig.class, b -> b
          .supportOnlineListenerAttachment(true)
          .enableStatistics(true)
          .enableManagement(true)
          .enableReadThrough(true)
        )
        .loader(k -> k)));
    Object v = cache.get(1);
    assertEquals(1, v);
    cache.close();
  }

}
