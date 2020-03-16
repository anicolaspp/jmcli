package com.github.anicolasp.mapr.cli.stream

import com.github.anicolasp.mapr.cli.client.MapRCLI.Auth
import com.github.anicolasp.mapr.cli.entries.Entry
import com.github.anicolasp.mapr.cli.runnable.{RunnableCommand, RunnableQuery}

trait StreamEntry {
  def assign(): StreamAssignEntry

  def create(path: String, args: (String, String)*): RunnableCommand

  def cursor(): StreamCursorEntry

  def edit(path: String, args: (String, String)*): RunnableCommand

  def delete(path: String): RunnableCommand

  def info(path: String): RunnableQuery

  def topic(): StreamTopicEntry
}

object StreamEntry {
  def apply(host: String, auth: Option[Auth]): StreamEntry = new StreamE(host, auth)

  class StreamE(host: String, auth: Option[Auth]) extends Entry(s"${host}/rest/stream") with StreamEntry {
    override def assign(): StreamAssignEntry = StreamAssignEntry(host, auth)

    override def create(path: String, args: (String, String)*): RunnableCommand =
      RunnableCommand(getUrl("create"), auth, ("path", path) :: args.toList: _*)

    override def cursor(): StreamCursorEntry = StreamCursorEntry(host, auth)

    override def edit(path: String, args: (String, String)*): RunnableCommand =
      RunnableCommand(getUrl("edit"), auth, ("path", path) :: args.toList: _*)

    override def delete(path: String): RunnableCommand =
      RunnableCommand(getUrl("delete"), auth, ("path", path))

    override def info(path: String): RunnableQuery =
      RunnableQuery(getUrl("info"), auth, ("path", path))

    override def topic(): StreamTopicEntry = StreamTopicEntry(host, auth)
  }

}