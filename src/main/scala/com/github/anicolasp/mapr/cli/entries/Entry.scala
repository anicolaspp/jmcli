package com.github.anicolasp.mapr.cli.entries

abstract class Entry(base: String) {
  protected def getUrl(end: String) = s"${base}/${end}"
}
