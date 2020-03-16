package com.github.anicolasp.mapr.cli.stream

import com.github.anicolasp.mapr.cli.client.MapRCLI.Auth
import com.github.anicolasp.mapr.cli.entries.Entry
import com.github.anicolasp.mapr.cli.runnable.RunnableQuery

trait StreamAssignEntry {
  def list(path: String, args: (String, String)*): RunnableQuery
}

object StreamAssignEntry {
  def apply(host: String, auth: Option[Auth]): StreamAssignEntry = new StreamAssignE(host, auth)

  class StreamAssignE(host: String, auth: Option[Auth]) extends Entry(s"${host}/rest/stream/assign") with StreamAssignEntry {
    override def list(path: String, args: (String, String)*): RunnableQuery =
      RunnableQuery(getUrl("list"), auth, ("path", path) :: args.toList: _*)
  }

}