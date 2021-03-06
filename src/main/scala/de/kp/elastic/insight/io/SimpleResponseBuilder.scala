package de.kp.elastic.insight.io
/* Copyright (c) 2014 Dr. Krusche & Partner PartG
* 
* This file is part of the Elastic-Insight project
* (https://github.com/skrusche63/elastic-insight).
* 
* Elastic-Insight is free software: you can redistribute it and/or modify it under the
* terms of the GNU General Public License as published by the Free Software
* Foundation, either version 3 of the License, or (at your option) any later
* version.
* 
* Elastic-Insight is distributed in the hope that it will be useful, but WITHOUT ANY
* WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
* A PARTICULAR PURPOSE. See the GNU General Public License for more details.
* You should have received a copy of the GNU General Public License along with
* Elastic-Insight. 
* 
* If not, see <http://www.gnu.org/licenses/>.
*/

import de.kp.spark.core.Names
import de.kp.spark.core.model._

import org.elasticsearch.common.xcontent.XContentFactory
import org.elasticsearch.common.xcontent.XContentBuilder

import de.kp.elastic.insight.model._

class SimpleResponseBuilder extends ResponseBuilder {

  def build(res:ServiceResponse,pretty:Boolean):XContentBuilder = {
      
    val builder = XContentFactory.jsonBuilder()
	if (pretty) builder.prettyPrint().lfAtEnd()

	builder
      .startObject()
        .field("service",res.service)
        .field("task",res.task)
        .field("status",res.status)
        .field("uid",res.data(Names.REQ_UID))
    
    res.data.get(Names.REQ_MESSAGE) match {
	  
	  case None => {/* do nothing */}
	  case Some(message) => builder.field("message",message)
	}
    
	builder.endObject()
    builder

  }
  
}