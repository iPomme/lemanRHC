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
package models

import scala.concurrent.Future
import play.api.libs.ws.{WS, Response}
import play.api.Play
import play.api.libs.json.{JsNull, JsValue, Json}

class RelavanceJSON {


  def login(user: String, password: String): Future[Response] = {

    val json: JsValue = Json.arr(Json.obj(
      "cde" -> "LOG",
      "model" -> Json.obj(
        "Type" -> 1,
        "Data" -> "localhost"
      ),
      "concept" -> Json.obj(
        "Type" -> 1,
        "Data" -> user
      ),
      "item" -> Json.obj(
        "Type" -> 1,
        "Data" -> password
      ),
      "act" -> Json.obj(
        "SessionID" -> "4321"
      )
    )
    )
    post(json)
  }

  def getModels(): Future[Response] = {
    val json: JsValue = Json.arr(Json.obj(
      "cde" -> "GET",
      "act" -> Json.obj(
        "SessionID" -> "4321",
        "OpApplyVia" -> 2),
      "ret" -> ""
    )
    )
    post(json)
  }

  def getConcepts(): Future[Response] = {
    val json: JsValue = Json.arr(Json.obj(
      "cde" -> "GET",
      "model" -> Json.obj(
        "Type" -> 18,
        "Data" -> Json.arr(
          Json.obj(
            "Key" -> "0,3,13,265",
            "Value" -> ""
          )
        )
      ),
      "act" -> Json.obj(
        "SessionID" -> "4321"
      )
    )
    )

    post(json)

  }

  def getAccountForWorkBalance(): Future[Response] = {
    // [{"cde":"GET","model":{"Type":18,"Data":[{"Key":"0,3,13,265","Value":""}]},"concept":{"Type":18,"Data":[{"Key":"2,1025,265,1","Value":""},{"Key":"2,1025,265,2","Value":""}]},"item":{ "Type":18, "Data":[ { "Key":"2,1,264,3", "Value":"" }] }, "act":{"SessionID":"1234567"}}]
    val json: JsValue = Json.arr(Json.obj(
      "cde" -> "GET",
      "model" -> Json.obj(
        "Type" -> 18,
        "Data" -> Json.arr(
          Json.obj(
            "Key" -> "0,3,13,265",
            "Value" -> ""
          )
        )
      ),
      "concept" -> Json.obj(
        "Type" -> 18,
        "Data" -> Json.arr(
          Json.obj(
            "Key" -> "2,1025,265,1", // WorkingBalance concept
            "Value" -> ""
          ),
          Json.obj(
            "Key" -> "2,1025,265,2", // Accounts concept
            "Value" -> ""
          )
        )
      ),
      "item" -> Json.obj(
        "Type" -> 18,
        "Data" -> Json.arr(
          Json.obj(
            "Key" -> "2,1,264,3", // The value 0.00
            "Value" -> ""
          )
        )
      ),
      "act" -> Json.obj(
        "SessionID" -> "1234567"
      )
    )
    )
    post(json)
  }

  def getAccountsForCustomer(): Future[Response] = {
    val json: JsValue = Json.arr(Json.obj(
      "cde" -> "GET",
      "model" -> Json.obj(
        "Type" -> 18,
        "Data" -> Json.arr(
          Json.obj(
            "Key" -> "0,3,13,265",
            "Value" -> ""
          )
        )
      ),
      "concept" -> Json.obj(
        "Type" -> 18,
        "Data" -> Json.arr(
          Json.obj(
            "Key" -> "2,1025,265,3", // Customer concept
            "Value" -> ""
          ),
          Json.obj(
            "Key" -> "2,1025,265,2", // Accounts concept
            "Value" -> ""
          )
        )
      ),
      "item" -> Json.obj(
        "Type" -> 18,
        "Data" -> Json.arr(
          Json.obj(
            "Key" -> "2,1,272,5", // Account 100391
            "Value" -> ""
          )
        )
      ),
      "act" -> Json.obj(
        "SessionID" -> "1234567"
      )
    )
    )
    post(json)
  }

  private def post(json: JsValue): Future[Response] = {
    println(s"posting ${json.toString()}")
    val url = WS.url(Play.current.configuration.getString("relavanceWS.url").get).withHeaders(("Content-Type", "multipart/form-data"))
    url post json
  }
}