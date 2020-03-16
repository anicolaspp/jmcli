package com.github.anicolasp.mapr.cli.table

import com.github.anicolasp.mapr.cli.client.MapRCLI.Auth
import com.github.anicolasp.mapr.cli.entries.Entry
import com.github.anicolasp.mapr.cli.runnable.RunnableQuery

trait TableCFEntry {
  def list(path: String, cfname: Option[String] = None): RunnableQuery
}

object TableCFEntry {
  def apply(host: String, auth: Option[Auth]): TableCFEntry = new TableCFE(host, auth)


  class TableCFE(host: String, auth: Option[Auth]) extends Entry(s"${host}/rest/table/cf") with TableCFEntry {
    override def list(path: String, cfname: Option[String] = None): RunnableQuery = {
      val args = ("path", path) :: cfname.map(name => List(("cfname", name))).getOrElse(Nil)

      RunnableQuery(getUrl("list"), auth, args: _*)
    }
  }

}