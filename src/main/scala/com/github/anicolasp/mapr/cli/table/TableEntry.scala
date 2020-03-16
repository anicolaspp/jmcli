package com.github.anicolasp.mapr.cli.table

import com.github.anicolasp.mapr.cli.client.MapRCLI.Auth
import com.github.anicolasp.mapr.cli.entries.Entry
import com.github.anicolasp.mapr.cli.runnable.{RunnableCommand, RunnableQuery}

trait TableEntry {
  def create(path: String, args: (String, String)*): RunnableCommand

  def cf(): TableCFEntry

  def delete(path: String): RunnableCommand

  def edit(path: String, args: (String, String)*)

  def info(path: String): RunnableQuery

  def region(): TableRegionEntry
}


object TableEntry {
  def apply(host: String, auth: Option[Auth]): TableEntry = new TableE(host, auth)

  class TableE(host: String, auth: Option[Auth]) extends Entry(s"${host}/rest/table") with TableEntry {
    override def create(path: String, args: (String, String)*): RunnableCommand = {
      val toArgs = if (!args.toMap.contains("tabletype")) {
        ("tabletype", "json") :: args.toList
      } else {
        args.toList
      }

      RunnableCommand(getUrl("create"), auth, ("path", path) :: toArgs: _*)
    }

    override def delete(path: String): RunnableCommand =
      RunnableCommand(getUrl("delete"), auth, ("path", path))

    override def info(path: String): RunnableQuery =
      RunnableQuery(getUrl("info"), auth, ("path", path))

    override def region(): TableRegionEntry = TableRegionEntry(host, auth)

    override def edit(path: String, args: (String, String)*): Unit =
      RunnableCommand("edit", auth, ("path", path) :: args.toList: _*)

    override def cf(): TableCFEntry = TableCFEntry(host, auth)
  }

}