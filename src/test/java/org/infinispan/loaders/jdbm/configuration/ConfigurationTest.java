package org.infinispan.loaders.jdbm.configuration;

import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.loaders.jdbm.JdbmCacheStoreConfig;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = "unit", testName = "loaders.jdbm.configuration.ConfigurationTest")
public class ConfigurationTest {

   public void testBdbjeCacheStoreConfigurationAdaptor() {
      ConfigurationBuilder b = new ConfigurationBuilder();
      b.loaders().addStore(JdbmCacheStoreConfigurationBuilder.class).location("/tmp/jdbm").expiryQueueSize(100).fetchPersistentState(true).async().enable();
      Configuration configuration = b.build();
      JdbmCacheStoreConfiguration store = (JdbmCacheStoreConfiguration) configuration.loaders().cacheLoaders().get(0);
      Assert.assertEquals("/tmp/jdbm", store.location());
      Assert.assertEquals(100, store.expiryQueueSize());
      Assert.assertTrue(store.fetchPersistentState());
      Assert.assertTrue(store.async().enabled());

      b = new ConfigurationBuilder();
      b.loaders().addStore(JdbmCacheStoreConfigurationBuilder.class).read(store);
      Configuration configuration2 = b.build();
      JdbmCacheStoreConfiguration store2 = (JdbmCacheStoreConfiguration) configuration2.loaders().cacheLoaders().get(0);
      Assert.assertEquals("/tmp/jdbm", store2.location());
      Assert.assertEquals(100, store2.expiryQueueSize());
      Assert.assertTrue(store2.fetchPersistentState());
      Assert.assertTrue(store2.async().enabled());
   }
}