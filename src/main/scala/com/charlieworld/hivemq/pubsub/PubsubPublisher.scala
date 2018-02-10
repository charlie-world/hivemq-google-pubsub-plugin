package com.charlieworld.hivemq.pubsub

import com.google.cloud.pubsub.v1.Publisher
import com.google.pubsub.v1.{PubsubMessage, TopicName}
import org.slf4j.LoggerFactory

import scala.concurrent.{ExecutionContext, Future}

/**
  * Writer Charlie Lee 
  * Created at 2018. 2. 10.
  */
class PubsubPublisher(topic: String, projectId: String)(implicit ec: ExecutionContext) extends FutureConversion {

  private val topicName = TopicName.of(projectId, topic)
  private val publisher = Publisher.newBuilder(topicName).build()

  private val log = LoggerFactory.getLogger(classOf[PubsubPublisher])

  def publish(message: PubsubMessage): Future[String] = {
    publisher.publish(message).toScala
  }
}
