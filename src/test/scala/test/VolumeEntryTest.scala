package com.github.anicolasp.mapr.cli
package test


import com.github.anicolasp.mapr.cli.client.MapRCLI
import org.scalatest.FlatSpec
import org.scalatest.matchers.should.Matchers


class VolumeEntryTest extends FlatSpec with Matchers {

  "volume list" should "return all volumes" in {

    val client = MapRCLI
      .withHost("https://172.20.60.107:8443")
      .withAuth("msapp", "msrules!")


    val volumeListCommand = client.volume().list()

    val response = volumeListCommand.run()

    response.statusCode should be(200)

    ujson.read(response.text())("data").arr.size should not be 0
  }

  it should "create volume with name" in {
    val client = MapRCLI
      .withHost("https://172.20.60.107:8443")
      .withAuth("msapp", "msrules!")

    val volumeCreate = client.volume().create("npereztest")

    val response = volumeCreate.run()

    response.statusCode should be (200)
  }

  it should "create and mount the volume" in {
    val client = MapRCLI
      .withHost("https://172.20.60.107:8443")
      .withAuth("msapp", "msrules!")

    val volumeCreate = client
      .volume()
      .create("npereztest", ("path", "/user/npereztest"))

    val response = volumeCreate.run()

    response.statusCode should be (200)
  }
}
