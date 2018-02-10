package co.bitfinder.awair.hivemq.pubsub

import com.charlieworld.hivemq.pubsub.{FutureConversion, Handler, PubsubPublisher}
import com.google.protobuf.ByteString
import com.google.pubsub.v1.PubsubMessage
import com.hivemq.spi.message.{PUBLISH, QoS}
import org.joda.time.DateTimeUtils
import org.mockito.Mockito.{timeout, verify, when}
import org.scalatest.mockito.MockitoSugar
import org.scalatest.prop.PropertyChecks
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.Future

class HandlerSpec extends FlatSpec with PropertyChecks with Matchers with MockitoSugar with FutureConversion with Handler {

  "Handler" should "handle PUBLISHes to export to Pub/Sub" in {
    DateTimeUtils.setCurrentMillisFixed(0)
    val mqttPayload = "{}"
    val mqttTopic = "device/awair-glow/event/score"
    val publish = new PUBLISH(mqttPayload.getBytes, mqttTopic, QoS.AT_MOST_ONCE)
    val publishMessage = PubsubMessage.newBuilder().setData(ByteString.copyFrom(publish.getPayload)).build()
    val pubsubPublisher = mock[PubsubPublisher]

    when(pubsubPublisher.publish(publishMessage))
      .thenReturn(Future.successful(""))

    handleOnPublishReceived(pubsubPublisher, publish)

    verify(pubsubPublisher, timeout(3000)).publish(publishMessage)
  }
}
