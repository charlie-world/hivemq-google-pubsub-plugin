package com.charlieworld.hivemq.pubsub

import com.hivemq.spi.callback.CallbackPriority
import com.hivemq.spi.callback.events.OnPublishReceivedCallback
import com.hivemq.spi.message.PUBLISH
import com.hivemq.spi.security.ClientData
import com.typesafe.config.ConfigFactory
import org.slf4j.LoggerFactory

class Callback extends OnPublishReceivedCallback with Handler {

  import scala.concurrent.ExecutionContext.Implicits.global

  private val log = LoggerFactory.getLogger(classOf[Callback])
  private val topic = ConfigFactory.load.getString("google.cloud.pubsub.topic")
  private val projectId = ConfigFactory.load.getString("google.cloud.pubsub.project.id")
  private val pubsubPublisher = new PubsubPublisher(topic, projectId)

  log.info(s"Initialized ${classOf[Callback].getCanonicalName}")

  override def onPublishReceived(publish: PUBLISH, clientData: ClientData): Unit = {
    handleOnPublishReceived(pubsubPublisher, publish)
  }

  override def priority: Int =
    CallbackPriority.HIGH
}