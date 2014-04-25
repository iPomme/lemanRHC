/*

    ___                      _   _     _ _          ___        _
   / __|___ _ _  _ _  ___ __| |_(_)_ _(_) |_ _  _  | __|_ _ __| |_ ___ _ _ _  _  TM
  | (__/ _ \ ' \| ' \/ -_) _|  _| \ V / |  _| || | | _/ _` / _|  _/ _ \ '_| || |
   \___\___/_||_|_||_\___\__|\__|_|\_/|_|\__|\_, | |_|\__,_\__|\__\___/_|  \_, |
                                             |__/                          |__/
  Copyright 2010-2014 Crossing-Tech SA, EPFL QI-J, CH-1015 Lausanne, Switzerland.
  All rights reserved.

  Unauthorized copying of this file, via any medium, is strictly prohibited.
  Proprietary and confidential.

 ==================================================================================
 */

import java.util.concurrent.TimeUnit
import models.RelavanceJSON
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.libs.json.{Json, JsValue}
import play.api.libs.ws.Response
import play.api.Logger
import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}
import scala.concurrent._
import scala.concurrent.duration._
import ExecutionContext.Implicits.global

/**
 * Created by nicolasjorand on 23/01/14.
 */
@RunWith(classOf[JUnitRunner])
class RelavanceJSONSpec extends Specification {
  private val log = Logger(this.getClass)

  "RelavanceJSON" should {

    "login" in new WithApplication {
      val totest = new RelavanceJSON()
      val fut = totest.login("System Administrator", "xingtech")
      val result = Await.result(fut, Duration(3, TimeUnit.SECONDS))
      println(result.json.toString)
      val key = result.json \\ "Key"
      val value = result.json \\ "Value"
      key.isEmpty === false
      value.isEmpty === true
      result.status === 200
    }

    "answers ERR_LOGIN_FAILED with bad login" in new WithApplication {
      val totest = new RelavanceJSON()
      val fut = totest.login("System Administrator", "xi")
      val result = Await.result(fut, Duration(3, TimeUnit.SECONDS))
      result.status == 200
      val error: JsValue = result.json \\ "Value" head
      val strError = error.as[String]
      strError === "ERR_LOGIN_FAILED"
    }

    "get the model list" in new WithApplication {
      val totest = new RelavanceJSON()

      val logF = totest.login("System Administrator", "xingtech")
      val modelF = totest.getModels()

      val result: Response = Await.result(logF.flatMap(r => modelF), Duration(3, TimeUnit.SECONDS))
      println(s"Models: \n ${Json.stringify(result.json)}")

      val value: JsValue = result.json \\ "Value" head
      val strModel = value.as[String]

      strModel === "MB"
      result.status === 200
    }

    "get the concept list" in new WithApplication {
      val totest = new RelavanceJSON()

      val logF = totest.login("System Administrator", "xingtech")
      val modelF = totest.getConcepts()

      val result: Response = Await.result(logF.flatMap(r => modelF), Duration(3, TimeUnit.SECONDS))
      println(s"Concepts: \n ${Json.stringify(result.json)}")
      val value: Seq[JsValue] = result.json \\ "Value"
      val listOfValue = value.toList

      listOfValue.toString() === "List(\"WorkBalance\", \"Accounts\", \"Customer\", \"Name\", \"Currency\", \"Mnemonic\", \"MB_nexus_0\", \"NexusColumn_For_Table_TABLE_ACCOUNT-LIST-WIN\", \"CCY\", \"NexusColumn_For_Table_TABLE_CURRENCY-LIST-WIN\", \"NexusColumn_For_Table_TABLE_CUSTOMER-LIST-WIN\")"
      result.status === 200
    }

    "get all the account for a particular workBalance" in new WithApplication {
      val totest = new RelavanceJSON()

      val logF = totest.login("System Administrator", "xingtech")
      val modelF = totest.getAccountForWorkBalance()

      val result: Response = Await.result(logF.flatMap(r => modelF), Duration(3, TimeUnit.SECONDS))
      println(s"accounts: \n ${Json.stringify(result.json)}")

      val value: Seq[JsValue] = result.json \\ "Value"
      val listOfValue = value.toList

      listOfValue.toString() === "List(\"               0.00\", \"    EUR142200010\", \" EURGBP190220001\", \"    GBP128000005\", \" GBPEUR190220001\", \"    USD128000001\", \" USDEUR190220001\", \" EURUSD190220001\")"

      result.status == 200
    }

    "get all the account for a particular customer" in new WithApplication {
      val totest = new RelavanceJSON()

      val logF = totest.login("System Administrator", "xingtech")
      val modelF = totest.getAccountsForCustomer()

      val result: Response = Await.result(logF.flatMap(r => modelF), Duration(3, TimeUnit.SECONDS))
      println(s"accounts: \n ${Json.stringify(result.json)}")

      val value: Seq[JsValue] = result.json \\ "Value"
      val listOfValue = value.toList

      listOfValue.toString() === "List(\"100391\", \"      2000000089\", \"      2000000054\")"

      result.status == 200
    }

  }
}
