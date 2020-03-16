package com.github.anicolasp.mapr.cli.stream

import com.github.anicolasp.mapr.cli.client.MapRCLI.Auth
import com.github.anicolasp.mapr.cli.entries.Entry
import com.github.anicolasp.mapr.cli.runnable.{RunnableCommand, RunnableQuery}

trait StreamCursorEntry {
  def list(path: String, args: (String, String)*): RunnableQuery

  def delete(path: String, args: (String, String)*): RunnableCommand
}

object StreamCursorEntry {

  def apply(host: String, auth: Option[Auth]): StreamCursorEntry = new StreamCursorE(host, auth)

  class StreamCursorE(host: String, auth: Option[Auth]) extends Entry(s"${host}/rest/stream/cursor") with StreamCursorEntry {
    override def list(path: String, args: (String, String)*): RunnableQuery =
      RunnableQuery(getUrl("list"), auth, ("path", path) :: args.toList: _*)

    override def delete(path: String, args: (String, String)*): RunnableCommand =
      RunnableCommand(getUrl("delete"), auth, ("path", path) :: args.toList: _*)
  }

}