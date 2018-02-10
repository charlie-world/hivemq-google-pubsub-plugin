package com.charlieworld.hivemq.pubsub

import com.google.protobuf.ByteString
import com.google.pubsub.v1.PubsubMessage
import com.hivemq.spi.message.PUBLISH
import org.slf4j.LoggerFactory

/**
  * Writer Charlie Lee 
  * Created at 2018. 2. 10.
  */
trait Handler extends FutureConversion {

  import scala.concurrent.ExecutionContext.Implicits.global

  private val log = LoggerFactory.getLogger(classOf[Callback])

  def handleOnPublishReceived(pubsubPublisher: PubsubPublisher, publish: PUBLISH): Unit = {
    val message = PubsubMessage.newBuilder().setData(ByteString.copyFrom(publish.getPayload)).build()
    pubsubPublisher.publish(message).failed.foreach {
      case ex: Exception =>
        log.error(
          s"""
             |An error occurred while exporting an MQTT PUBLISH to Pub/Sub.
             |-----
             |payload: ${message.getData.toStringUtf8}
           """.stripMargin,
          new Exception(ex)
        )
    }
  }
}
