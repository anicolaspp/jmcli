package com.github.anicolasp.mapr.cli.table

import com.github.anicolasp.mapr.cli.client.MapRCLI.Auth
import com.github.anicolasp.mapr.cli.entries.Entry
import com.github.anicolasp.mapr.cli.runnable.{RunnableCommand, RunnableQuery}

trait TableEntry {
  def create(path: String, args: Iterable[(String, String)]): RunnableCommand

  def delete(path: String): RunnableCommand

  def info(path: String): RunnableQuery

  def cf(): TableCFEntry
}


object TableEntry {
  def apply(host: String, auth: Option[Auth]): TableEntry = new TableE(host, auth)

  class TableE(host: String, auth: Option[Auth]) extends Entry(s"${host}/rest/table") with TableEntry {
    override def create(path: String, args: Iterable[(String, String)]): RunnableCommand =
      RunnableCommand(getUrl("create"), auth, ("path", path) :: args.toList)

    override def delete(path: String): RunnableCommand =
      RunnableCommand(getUrl("delete"), auth, List(("path", path)))

    override def info(path: String): RunnableQuery =
      RunnableQuery(getUrl("info"), auth, List(("path", path)))

    override def cf(): TableCFEntry = TableCFEntry(host, auth)
  }

}