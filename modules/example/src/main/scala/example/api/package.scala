/*
 * Copyright 2022 LambdaWorks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package example

import zio.Chunk
import zio.json._

package object api {

  final case class ErrorResponseData(body: Chunk[String])

  object ErrorResponseData {
    implicit val encoder: JsonEncoder[ErrorResponseData] = DeriveJsonEncoder.gen[ErrorResponseData]
  }

  final case class ErrorResponse(errors: ErrorResponseData)

  object ErrorResponse {
    implicit val encoder: JsonEncoder[ErrorResponse] = DeriveJsonEncoder.gen[ErrorResponse]

    def fromReasons(reasons: String*): ErrorResponse =
      new ErrorResponse(ErrorResponseData(Chunk.fromIterable(reasons)))
  }

}
