package com.github.anicolasp.mapr.cli
package test


import org.scalatest.FlatSpec
import org.scalatest.matchers.should.Matchers


class VolumeTest extends FlatSpec with Matchers{

  "volume list" should "return all volumes" in {

    val client = MapRCLI
      .withHost("https://172.20.60.107:8443")
      .withAuth("msapp", "msrules!")


    val volumeListCommand = client.volume().list()

    println(volumeListCommand.run())

  }
}
