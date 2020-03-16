package com.github.anicolasp.mapr.cli.stream

import com.github.anicolasp.mapr.cli.runnable.{RunnableCommand, RunnableQuery}

trait StreamTopicEntry {
  def create(path: String, topic: String, args: (String, String)*): RunnableCommand

  def delete(path: String, topic: String): RunnableCommand

  def info(path: String, topic: String): RunnableQuery

  def list(path: String): RunnableQuery
}

object StreamTopicEntry {
  def apply(host: String, auth: Option[Auth]): StreamTopicEntry = new StreamTopicE(host, auth)

  class StreamTopicE(host: String, auth: Option[Auth]) extends Entry(s"${host}/rest/stream/topic") with StreamTopicEntry {
    override def create(path: String, topic: String, args: (String, String)*): RunnableCommand =
      RunnableCommand(getUrl("create"), auth, ("path", path) :: ("topic", topic) :: args.toList: _*)

    override def delete(path: String, topic: String): RunnableCommand =
      RunnableCommand(getUrl("delete"), auth, ("path", path), ("topic", topic))

    override def info(path: String, topic: String): RunnableQuery =
      RunnableQuery(getUrl("info"), auth, ("path", path), ("topic", topic))

    override def list(path: String): RunnableQuery =
      RunnableQuery(getUrl("list"), auth, ("path", path))
  }

}