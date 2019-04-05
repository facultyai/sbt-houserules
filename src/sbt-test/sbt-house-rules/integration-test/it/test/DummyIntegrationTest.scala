package test

import org.scalatest.{FlatSpec, Matchers}

class DummyIntegrationTest extends FlatSpec with Matchers {

  "integration test" should "work" in {
    println("dummy integration test succeeded")
  }
}