package com.github.anicolasp.mapr.cli.runnable

import requests.Response

import scala.concurrent.{ExecutionContext, Future}

object Async {

  implicit class AsyncRunnableQuery(query: RunnableQuery) {
    def runAsync()(implicit ec: ExecutionContext): Future[Response] = Future(query.run())
  }

  implicit class AsyncRunnableCommand(command: RunnableCommand) {
    def runAsync()(implicit ec: ExecutionContext): Future[Response] = Future(command.run())
  }

}
