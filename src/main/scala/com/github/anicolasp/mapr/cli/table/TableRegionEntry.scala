package com.github.anicolasp.mapr.cli.table

import com.github.anicolasp.mapr.cli.client.MapRCLI.Auth
import com.github.anicolasp.mapr.cli.entries.Entry
import com.github.anicolasp.mapr.cli.runnable.{RunnableCommand, RunnableQuery}

trait TableRegionEntry {
  def list(path: String): RunnableQuery

  def split(path: String, fid: String): RunnableCommand
}

object TableRegionEntry {

  def apply(host: String, auth: Option[Auth]): TableRegionEntry = new TableRegionE(host, auth)

  class TableRegionE(host: String, auth: Option[Auth]) extends Entry(s"${host}/rest/table/region") with TableRegionEntry {
    override def list(path: String): RunnableQuery =
      RunnableQuery(getUrl("list"), auth, ("path", path))

    override def split(path: String, fid: String): RunnableCommand =
      RunnableCommand(getUrl("split"), auth, ("path", path), ("fid", fid))
  }

}
