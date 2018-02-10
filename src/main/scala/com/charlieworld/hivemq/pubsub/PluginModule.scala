package com.charlieworld.hivemq.pubsub

import com.hivemq.spi.HiveMQPluginModule
import com.hivemq.spi.plugin.meta.Information

@Information(name = "charlieworld-hivemq-pubsub-plugin", version = "0.1.0")
class PluginModule extends HiveMQPluginModule {

  override protected def entryPointClass: Class[Plugin] =
    classOf[Plugin]

  override def configurePlugin(): Unit = {}
}