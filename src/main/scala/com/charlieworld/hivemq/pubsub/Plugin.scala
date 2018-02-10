package com.charlieworld.hivemq.pubsub

import javax.annotation.PostConstruct

import com.google.inject.Inject
import com.hivemq.spi.PluginEntryPoint

class Plugin @Inject()(callback: Callback) extends PluginEntryPoint {

  @PostConstruct
  def postConstruct(): Unit = {
    getCallbackRegistry.addCallback(callback)
  }
}
